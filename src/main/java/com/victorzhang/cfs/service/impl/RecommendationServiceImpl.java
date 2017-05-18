package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.service.RecommendationService;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("recommendationService")
public class RecommendationServiceImpl implements RecommendationService {

    public static final int NEIGHBORHOOD_NUM = 2;//邻居数量
    public static final int RECOMMENDATION_NUM = 2;//推荐个数

    @Resource
    MySQLJDBCDataModel recommendDataModel;

    public List<RecommendedItem> listItemBasedRecommendationResource(long userId) throws Exception {
        // 指定用户相似度计算方法，这里采用皮尔森相关度
        ItemSimilarity similarity = new PearsonCorrelationSimilarity(recommendDataModel);
        // 指定用户邻居数量，这里为2
        Recommender recommender = new GenericItemBasedRecommender(recommendDataModel, similarity);
        // 得到指定用户的推荐结果，这里是得到用户的两个推荐
        return recommender.recommend(userId, RECOMMENDATION_NUM);
    }

    public List<RecommendedItem> listUserBasedRecommendationResource(long userId) throws Exception {
        // 指定用户相似度计算方法，这里采用皮尔森相关度
        UserSimilarity similarity = new PearsonCorrelationSimilarity(recommendDataModel);
        // 指定用户邻居数量，这里为2
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, similarity, recommendDataModel);
        // 构建基于用户的推荐系统
        Recommender recommender = new GenericUserBasedRecommender(recommendDataModel, neighborhood, similarity);
        // 得到指定用户的推荐结果，这里是得到用户1的两个推荐
        return recommender.recommend(userId, RECOMMENDATION_NUM);
    }
}
