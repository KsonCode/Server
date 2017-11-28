package service;

import pojo.UserEntity;

public interface User {
    void add(UserEntity user);
    void register(UserEntity user);
    void login(UserEntity user);
    void update(UserEntity userEntity);
    boolean isExist(String mobile);
}
