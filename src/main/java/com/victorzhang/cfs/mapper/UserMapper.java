package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User, String> {

    User getUserByUsernameAndPassword(Map<String, Object> map);
}
