package service.quarter;

import pojo.UserEntity;
import pojo.quarter.*;

import java.util.List;

public interface QuarterService {
    void publishJoke(JokesEntity jokesEntity);

    List<Joke> getJokes(int page);

    void publishVideo(WorksEntity worksEntity);

    List<WorkBean> getWorks(int page, String type);

    List<WorksEntity> getUserWorks(int page, String uid);

    void follow(FollowEntity followEntity);

    List<UserEntity> getFollowUsers(String uid);//我的关注列表

    void addFavorite(WorkfavoriteEntity workfavoriteEntity);

    void cancelFavorite(WorkfavoriteEntity workfavoriteEntity);

    void praise(WorkpraiseEntity workpraiseEntity);//点赞

    void comment(CommentsEntity commentsEntity);//评论

    void update(WorksEntity worksEntity);//作品更新

    void addUser(UserEntity user);

    void updateUser(UserEntity userEntity);

    WorksEntity getVideoDetail(String wid);//视频详情

}
