package utils;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import pojo.UserEntity;

import java.util.Date;
import java.util.List;

public class TokenUtils extends HibernateDaoSupport {
    private final int TOKEN_TIME = 60 * 1000;//1天

    public boolean isVaild(String token) {
       Session session= getSessionFactory().openSession();
        String sql = "from UserEntity where token = :token";
        Query query = session.createQuery(sql);
        query.setParameter("token", token);
        System.out.println("token====="+token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                return false;
            } else {
                return false;
            }
        }
        return false;

    }

}


