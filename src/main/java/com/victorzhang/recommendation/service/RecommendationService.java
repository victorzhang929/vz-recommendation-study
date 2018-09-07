package com.victorzhang.recommendation.service;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

public interface RecommendationService {

    List<RecommendedItem> listUserBasedRecommendationResource(long userId) throws Exception;

    List<RecommendedItem> listItemBasedRecommendationResource(long userId) throws Exception;

}
