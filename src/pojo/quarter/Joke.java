package pojo.quarter;

import pojo.UserEntity;

public class Joke extends JokesEntity{

    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
