package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.DownloadRecord;
import com.victorzhang.cfs.domain.Resource;
import com.victorzhang.cfs.domain.Score;
import com.victorzhang.cfs.domain.ScoreRecord;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.DownloadRecordMapper;
import com.victorzhang.cfs.mapper.ResourceMapper;
import com.victorzhang.cfs.mapper.ScoreRecordMapper;
import com.victorzhang.cfs.service.*;
import com.victorzhang.cfs.util.CommonUtils;
import com.victorzhang.cfs.util.query.GenericQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.victorzhang.cfs.util.Constants.*;


@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource, String> implements ResourceService {

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String CONTENT_LENGTH = "Content-Length";

    @Autowired
    @Qualifier("browseRecordService")
    private BrowseRecordService browseRecordService;

    @Autowired
    @Qualifier("downloadRecordService")
    private DownloadRecordService downloadRecordService;

    @Autowired
    @Qualifier("commentService")
    private CommentService commentService;

    @Autowired
    @Qualifier("scoreRecordService")
    private ScoreRecordService scoreRecordService;

    @Autowired
    @Qualifier("resourceMapper")
    private ResourceMapper resourceMapper;

    @Autowired
    @Qualifier("scoreRecordMapper")
    private ScoreRecordMapper scoreRecordMapper;

    @Autowired
    @Qualifier("downloadRecordMapper")
    private DownloadRecordMapper downloadRecordMapper;

    @Override
    protected BaseMapper<Resource, String> getMapper() {
        return resourceMapper;
    }

    @Override
    public void doUploadResource(MultipartFile resourceFile, String resourceName, String resourceDescription, String resourceTag, String resourceType, HttpServletRequest request) throws Exception {
        String uploadPath = CommonUtils.getUploadPath(request);
        String fileName = resourceFile.getOriginalFilename();
        String serverFileName = fileName.substring(0, fileName.lastIndexOf(DOT_STRING)) + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf(DOT_STRING));
        String fileServerPath = uploadPath + serverFileName;
        String userId = CommonUtils.sesAttr(request, USER_ID);
        String resourceId = CommonUtils.newUuid();
        String gmtCreate = CommonUtils.getDateTime();
        Resource resource = new Resource(resourceId, resourceName, resourceDescription, resourceTag, resourceType, fileServerPath, userId, gmtCreate);
        if (!super.save(resource)) {
            throw new SQLException(INSERT_ERROR);
        }
        //save resource_score rating 5(上传成功该用户对资源的评分为5分)
        saveScoreRecord(userId, resourceId, gmtCreate);

        try {
            File file = new File(fileServerPath);
            resourceFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveScoreRecord(String userId, String resourceId, String ratingTime) throws Exception {
        ScoreRecord scoreRecord = new ScoreRecord(userId, resourceId, Score.FIVE.toString(), ratingTime, SCORE_FLAG);
        if (scoreRecordMapper.save(scoreRecord) <= 0) {
            throw new SQLException(INSERT_ERROR);
        }
    }

    @Override
    public void doDownloadResource(HttpServletResponse response, HttpServletRequest request, String id) throws Exception {
        InputStream fis = null;
        OutputStream fos = null;
        Resource resource = super.getById(id);
        try {
            File file = new File(resource.getResourceServerPath());
            String fileName = file.getName();
            String downloadFileName = fileName.substring(0, fileName.lastIndexOf(DOT_STRING)) + CommonUtils.currTimestamp() + fileName.substring(fileName.lastIndexOf(DOT_STRING));
            fis = new BufferedInputStream(new FileInputStream(resource.getResourceServerPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            response.reset();
            response.setHeader(CONTENT_DISPOSITION, "attachment;filename=" + CommonUtils.formatDownloadFileName(downloadFileName));
            response.setHeader(CONTENT_LENGTH, String.valueOf(file.length()));
            fos = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            fos.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.flush();
                fos.close();
            }
        }
        //更新resource表中resource_download_count
        updateResourceDownloadCount(resource);
        //插入或者更新resource_download表格
        saveOrUpdateResourceDownload(request, id);
    }

    @Override
    public List<Map<String, Object>> listNewestResource() throws Exception {
        return resourceMapper.listNewestResource();
    }

    @Override
    public List<Map<String, Object>> listHotResource() throws Exception {
        return resourceMapper.listHotResource();
    }

    @Override
    public void removeRecordAboutThisResource(String resourceId) throws Exception {
        downloadRecordService.remove(resourceId);
        browseRecordService.remove(resourceId);
        scoreRecordService.remove(resourceId);
        commentService.removeByResourceId(resourceId);
    }

    @Override
    public Map<String, Object> getResourceDetail(String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Resource resource = getById(id);
        map.put("resource", resource);
        map.put("comments", CommonUtils.dataNull(commentService.listByResourceId(id)));
        map.put("averageScore", getAverageScore(scoreRecordService.list(id)));
        return map;
    }

    private double getAverageScore(List<ScoreRecord> scoreRecords) {
        double sum = 0.0;
        for (ScoreRecord scoreRecord : scoreRecords) {
            sum += Double.parseDouble(scoreRecord.getRating());
        }
        return sum / scoreRecords.size();
    }

    private void updateResourceDownloadCount(Resource resource) throws Exception {
        Resource updateResourceDownloadCount = new Resource();
        int resourceDownloadCount = Integer.parseInt(resource.getResourceDownloadCount()) + 1;
        updateResourceDownloadCount.setResourceDownloadCount(String.valueOf(resourceDownloadCount));
        updateResourceDownloadCount.setId(resource.getId());
        update(updateResourceDownloadCount);
    }

    private void saveOrUpdateResourceDownload(HttpServletRequest request, String id) throws Exception {
        String resourceId = id;
        String downloadTime = CommonUtils.getDateTime();
        String userId = CommonUtils.sesAttr(request, USER_ID);
        DownloadRecord downloadRecord = new DownloadRecord(resourceId, downloadTime, userId);
        int count = downloadRecordMapper.countByResourceDownload(downloadRecord);
        if (count > 0) { //数据库中存在数据，更新操作
            downloadRecordMapper.update(downloadRecord);
        } else {//数据库中不存在该记录，插入操作
            downloadRecordMapper.save(downloadRecord);
            //评分表插入或更新操作
            saveOrUpdateScoreRecord(resourceId, userId);
        }
    }

    private void saveOrUpdateScoreRecord(String resourceId, String userId) throws Exception {
        GenericQueryParam param = new GenericQueryParam();
        param.fill("resourceId", resourceId);
        param.fill("userId", userId);
        int scoreCount = scoreRecordMapper.count(param);

        ScoreRecord scoreRecord = new ScoreRecord(userId, resourceId, CommonUtils.getDateTime());
        if (scoreCount <= 0) {//数据库resource_score表中不存在该纪录，插入
            scoreRecord.setRating(Score.TWO.toString());
            scoreRecord.setScoreFlag(DOWNLOAD_SCORE_FLAG);
            scoreRecordMapper.save(scoreRecord);
        } else {//数据库resource_score表中存在该记录，更新操作
            ScoreRecord scoreRecordByDB = scoreRecordMapper.get(scoreRecord);
            //score_flag为BROWSE_SCORE_FLAG，评分更新4，并将score_flag表示为DOWNLOAD_BORWSE_SCORE_FLAG
            if (StringUtils.equals(scoreRecordByDB.getScoreFlag(), BROWSE_SCORE_FLAG)) {
                scoreRecord.setRating(Score.FOUR.toString());
                scoreRecord.setScoreFlag(DOWNLOAD_BORWSE_SCORE_FLAG);
            }
        }
    }
}
