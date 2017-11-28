package pojo.quarter;

import pojo.User;
import pojo.UserEntity;

public class Joke extends JokesEntity{

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
