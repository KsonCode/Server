package service.quarter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.User;
import pojo.UserEntity;
import pojo.quarter.*;

import java.util.ArrayList;
import java.util.List;

public class QuarterServiceImpl implements QuarterService {

    private SessionFactory sf;

    @Override
    public void publishJoke(JokesEntity jokesEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(jokesEntity);
        transaction.commit();

    }

    @Override
    public List<Joke> getJokes(int page) {

        List<Joke> Jokes = new ArrayList<>();
        Session session = sf.openSession();
        String sql = "from JokesEntity joke order by joke.jid desc ";
        Query query = session.createQuery(sql);
        query.setMaxResults(10);
        System.out.println("page:" + page);
        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult((page - 1) * 10);
        }
        List<JokesEntity> list = query.list();

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                Joke joke = new Joke();
                JokesEntity jokesEntity = list.get(i);
                joke.setContent(jokesEntity.getContent());
                joke.setUid(jokesEntity.getUid());
                joke.setCommentNum(jokesEntity.getCommentNum());
                joke.setCreateTime(jokesEntity.getCreateTime());
                joke.setJid(jokesEntity.getJid());
                joke.setPraiseNum(jokesEntity.getPraiseNum());
                joke.setShareNum(jokesEntity.getShareNum());
                joke.setImgUrls(jokesEntity.getImgUrls());


                long uid = joke.getUid();

                String sql2 = "from UserEntity WHERE uid = :uid";
                Query query2 = session.createQuery(sql2);
                query2.setParameter("uid", uid);

                List<UserEntity> userEntities = query2.list();
                User user = new User();
                user.setIcon(userEntities.get(0).getIcon());
                user.setNickname(userEntities.get(0).getNickname());
                user.setAge(userEntities.get(0).getAge());
                user.setFans(String.valueOf(userEntities.get(0).getFans()));
                user.setFollow(String.valueOf(userEntities.get(0).getFollow()));
                user.setPraiseNum(String.valueOf(userEntities.get(0).getPraiseNum()));

                joke.setUser(user);

                Jokes.add(joke);


            }
        }

        return Jokes;
    }

    @Override
    public void publishVideo(WorksEntity worksEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(worksEntity);
        transaction.commit();
    }

    @Override
    public List<WorkBean> getWorks(int page, String type) {

        String sql = "";
        List<WorkBean> workBeans = new ArrayList<>();
        Session session = sf.openSession();
        if ("1".equals(type)) {
            sql = "from WorksEntity work  order by work.wid desc";
            Query query = session.createQuery(sql);
            query.setMaxResults(10);
            System.out.println("page:" + page);
            if (page == 1) {
                query.setFirstResult(0);
            } else {
                query.setFirstResult((page - 1) * 10);
            }
            List<WorksEntity> list = query.list();



            if (list != null) {
                for (int i = 0; i < list.size(); i++) {

                    WorkBean workBean = new WorkBean();
                    WorksEntity worksEntity = list.get(i);
                    workBean.setCover(worksEntity.getCover());

                    workBean.setCreateTime(worksEntity.getCreateTime());
                    workBean.setLatitude(worksEntity.getLatitude());

                    workBean.setLongitude(worksEntity.getLongitude());
                    workBean.setVideoUrl(worksEntity.getVideoUrl());
                    workBean.setLocalUri(worksEntity.getLocalUri());
                    workBean.setPlayNum(worksEntity.getPlayNum());
                    workBean.setWorkDesc(worksEntity.getWorkDesc());
                    workBean.setWid(worksEntity.getWid());
                    workBean.setUid(worksEntity.getUid());

                    long uid = workBean.getUid();

                    String sql2 = "from UserEntity WHERE uid = :uid";
                    Query query2 = session.createQuery(sql2);
                    query2.setParameter("uid", uid);

                    List<UserEntity> userEntities = query2.list();
                    User user = new User();
                    user.setIcon(userEntities.get(0).getIcon());
                    user.setNickname(userEntities.get(0).getNickname());
                    user.setAge(userEntities.get(0).getAge());
                    user.setFans(String.valueOf(userEntities.get(0).getFans()));
                    user.setFollow(String.valueOf(userEntities.get(0).getFollow()));
                    user.setPraiseNum(String.valueOf(userEntities.get(0).getPraiseNum()));

                    workBean.setUser(user);

                    String sql3 = "from CommentsEntity WHERE wid = :wid";
                    Query query3 = session.createQuery(sql3);
                    query3.setParameter("wid", worksEntity.getWid());

                    List<CommentsEntity> commentsEntities = query3.list();

                    workBean.setCommentNum(commentsEntities.size());
                    workBean.setComments(commentsEntities);

                    String sql4 = "from WorkpraiseEntity WHERE wid = :wid";
                    Query query4 = session.createQuery(sql4);
                    query4.setParameter("wid", worksEntity.getWid());

                    List<WorkpraiseEntity> workpraiseEntities = query4.list();
                    workBean.setPraiseNum(workpraiseEntities.size());

                    String sql5 = "from WorkfavoriteEntity WHERE wid = :wid";
                    Query query5 = session.createQuery(sql5);
                    query5.setParameter("wid", worksEntity.getWid());

                    List<WorkfavoriteEntity> workfavoriteEntities = query5.list();
                    workBean.setFavoriteNum(workfavoriteEntities.size());

                    workBeans.add(workBean);


                }
            }
        } else  {
            sql = "from WorksEntity work where commentNum > 1  order by work.wid desc";
        }


        return workBeans;
    }

    @Override
    public List<WorksEntity> getUserWorks(int page, String uid) {

        String sql = "from WorksEntity where uid = :uid";
        Session session = sf.openSession();

        Query query = session.createQuery(sql);
        query.setParameter("uid", Long.parseLong(uid));
        query.setMaxResults(10);
        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult((page - 1) * 10);
        }
        List<WorksEntity> list = query.list();

        if (list != null) {
            return list;
        }

        return null;
    }

    @Override
    public void follow(FollowEntity followEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(followEntity);
        transaction.commit();
    }

    @Override
    public List<UserEntity> getFollowUsers(String uid) {
        List<UserEntity> userEntities = new ArrayList<>();
        String sql = "from FollowEntity where uid = :uid";
        Session session = sf.openSession();

        Query query = session.createQuery(sql);
        query.setParameter("uid", Long.parseLong(uid));

        List<FollowEntity> list = query.list();
        if (list != null) {
            for (FollowEntity followEntity : list) {

                String followid = String.valueOf(followEntity.getFollowId());

                String sql2 = "from UserEntity where uid = :followId";

                Query query2 = session.createQuery(sql2);
                query2.setParameter("followId", Long.parseLong(followid));

                UserEntity userEntity = (UserEntity) query2.list().get(0);

                userEntities.add(userEntity);


            }
        }
        return userEntities;
    }

    @Override
    public void addFavorite(WorkfavoriteEntity workfavoriteEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(workfavoriteEntity);
        transaction.commit();
    }

    @Override
    public void cancelFavorite(WorkfavoriteEntity workfavoriteEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(workfavoriteEntity);
        transaction.commit();
    }

    @Override
    public void praise(WorkpraiseEntity workpraiseEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(workpraiseEntity);
        transaction.commit();
    }

    @Override
    public void comment(CommentsEntity commentsEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(commentsEntity);
        transaction.commit();
    }

    /**
     * 本地登录，用户是否被注册
     * @param mobile
     * @return
     */
    public boolean isExist(String mobile) {

        Session session = sf.openSession();

        //当前对象是否有记录
        List<UserEntity> list = session.createQuery("from UserEntity where mobile = '"+mobile+"'").list();

        if (list!=null&&list.size()>0){
            System.out.println("yes");
            return true;
        }
        System.out.println("no");
        return false;
    }
    /**
     * 第三方登录，用户是否被注册
     * @param userid
     * @return
     */
    public boolean isUserExist(String userid) {

        Session session = sf.openSession();

        //当前对象是否有记录
        List<UserEntity> list = session.createQuery("from UserEntity where userId = '"+userid+"'").list();

        if (list!=null&&list.size()>0){
            System.out.println("yes");
            return true;
        }
        System.out.println("no");
        return false;
    }

    @Override
    public void addUser(UserEntity user) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    /**
     * 更新用户表
     * @param userEntity
     */
    @Override
    public void updateUser(UserEntity userEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(userEntity);
        transaction.commit();
    }

    @Override
    public WorksEntity getVideoDetail(String wid) {

        String sql = "from WorksEntity where wid = :wid";
        Session session = sf.openSession();

        Query query = session.createQuery(sql);
        query.setParameter("wid", Long.parseLong(wid));

        List<WorksEntity> list = query.list();

        if (list!=null&&list.size()>0){

            WorkBean workBean = new WorkBean();
            WorksEntity worksEntity = list.get(0);
            workBean.setCover(worksEntity.getCover());

            workBean.setCreateTime(worksEntity.getCreateTime());
            workBean.setLatitude(worksEntity.getLatitude());

            workBean.setLongitude(worksEntity.getLongitude());
            workBean.setVideoUrl(worksEntity.getVideoUrl());
            workBean.setLocalUri(worksEntity.getLocalUri());
            workBean.setPlayNum(worksEntity.getPlayNum());
            workBean.setWorkDesc(worksEntity.getWorkDesc());
            workBean.setWid(worksEntity.getWid());
            workBean.setUid(worksEntity.getUid());

            long uid = workBean.getUid();

            String sql2 = "from UserEntity WHERE uid = :uid";
            Query query2 = session.createQuery(sql2);
            query2.setParameter("uid", uid);

            List<UserEntity> userEntities = query2.list();
            User user = new User();
            user.setIcon(userEntities.get(0).getIcon());
            user.setNickname(userEntities.get(0).getNickname());
            user.setAge(userEntities.get(0).getAge());
            user.setFans(String.valueOf(userEntities.get(0).getFans()));
            user.setFollow(String.valueOf(userEntities.get(0).getFollow()));
            user.setPraiseNum(String.valueOf(userEntities.get(0).getPraiseNum()));


            workBean.setUser(user);
            String sql3 = "from CommentsEntity WHERE wid = :wid";
            Query query3 = session.createQuery(sql3);
            query3.setParameter("wid", Long.parseLong(wid));

            List<CommentsEntity> commentsEntities = query3.list();

            workBean.setCommentNum(commentsEntities.size());
            workBean.setComments(commentsEntities);

            String sql4 = "from WorkpraiseEntity WHERE wid = :wid";
            Query query4 = session.createQuery(sql4);
            query4.setParameter("wid", Long.parseLong(wid));

            List<WorkpraiseEntity> workpraiseEntities = query4.list();
            workBean.setPraiseNum(workpraiseEntities.size());

            String sql5 = "from WorkfavoriteEntity WHERE wid = :wid";
            Query query5 = session.createQuery(sql5);
            query5.setParameter("wid", Long.parseLong(wid));

            List<WorkfavoriteEntity> workfavoriteEntities = query5.list();
            workBean.setFavoriteNum(workfavoriteEntities.size());

            return workBean;
        }


        return null;
    }

    @Override
    public void update(WorksEntity worksEntity) {

    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
