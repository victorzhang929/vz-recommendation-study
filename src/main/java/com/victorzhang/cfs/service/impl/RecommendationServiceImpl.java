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

    public static final int NEIGHBORHOOD_NUM = 4;
    public static final int RECOMMENDATION_NUM = 8;

    @Resource
    MySQLJDBCDataModel recommendDataModel;

    public List<RecommendedItem> listItemBasedRecommendationResource(long userId) throws Exception {
        ItemSimilarity similarity = new PearsonCorrelationSimilarity(recommendDataModel);
        Recommender recommender = new GenericItemBasedRecommender(recommendDataModel, similarity);
        return recommender.recommend(userId, RECOMMENDATION_NUM);
    }

    public List<RecommendedItem> listUserBasedRecommendationResource(long userId) throws Exception {
        UserSimilarity similarity = new PearsonCorrelationSimilarity(recommendDataModel);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, similarity, recommendDataModel);
        Recommender recommender = new GenericUserBasedRecommender(recommendDataModel, neighborhood, similarity);
        return recommender.recommend(userId, RECOMMENDATION_NUM);
    }
}
