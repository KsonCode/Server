package pojo.quarter;

import pojo.User;
import pojo.UserEntity;

import java.util.List;

public class WorkBean extends WorksEntity {

    private User user;
    private List<CommentsEntity> comments;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }
}
