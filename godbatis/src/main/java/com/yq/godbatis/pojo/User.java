package com.yq.godbatis.pojo;

public class User {
    private String id;
    private String username;
    private String age;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public User() {
    }

    public User(String id, String username, String age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
