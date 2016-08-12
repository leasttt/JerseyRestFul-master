package com.zeusjava.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    private String userId;
    private String userName;
    private int userAge;

    public User(){

    }

    public User(String userId, String userName, int userAge) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
}
