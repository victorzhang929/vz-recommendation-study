package com.victorzhang.recommendation.util.query;

import java.util.Map;

public interface QueryParam extends Map<String, Object> {
    QueryParam fill(String paramString, Object paramObject);
}
