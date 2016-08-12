package com.zeusjava.cache;

import com.zeusjava.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserCache {
    private static Map<String,User> userCache;
    private static UserCache instance = null;

    private UserCache() {
        userCache = new HashMap<String,User>();
        initOneUser();
    }

    public static Map<String,User> getUserCache() {
        if(instance==null) {
            instance = new UserCache();
        }
        return userCache;
    }

    private static void initOneUser() {
        User user = new User("001","zhaohongxuan",24);
        userCache.put(user.getUserId(),user);
    }
}
