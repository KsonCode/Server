package pojo.quarter;

import pojo.UserEntity;

import java.util.List;

public class WorkBean extends WorksEntity {

    private UserEntity user;
    private List<CommentsEntity> comments;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }
}
