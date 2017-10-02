package service;

import bean.AddrEntity;
import bean.CarEntity;
import bean.OrdersEntity;
import bean.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserImpl implements User {

    private SessionFactory sf;

    @Override
    public void add(UserEntity user) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public void register(UserEntity user) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }


    /**
     * ADD ADDR
     * @param addrEntity
     */
    public void addAddress(AddrEntity addrEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(addrEntity);
        transaction.commit();
    }

    /**
     * update ADDR
     * @param addrEntity
     */
    public void updateAddress(AddrEntity addrEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(addrEntity);
        transaction.commit();
    }

    @Override
    public void login(UserEntity user) {
        Session session = sf.openSession();


    }

    /**
     * 更新用户表
     * @param userEntity
     */
    @Override
    public void update(UserEntity userEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(userEntity);
        transaction.commit();
    }

    /**
     * 添加购物车
     * @param carEntity
     */
    public void addCar(CarEntity carEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(carEntity);
        transaction.commit();
    }

    /**
     * 更新购物车
     * @param carEntity
     */
    public void updateCar(CarEntity carEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(carEntity);
        transaction.commit();
    }

    @Override
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
     * 创建订单
     * @param orderEntity
     */
    public void createOrder(OrdersEntity orderEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(orderEntity);
        transaction.commit();
    }

    /**
     * 更新订单
     * @param orderEntity
     */
    public void updateOrder(OrdersEntity orderEntity) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(orderEntity);
        transaction.commit();
    }


    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public SessionFactory getSf() {
        return sf;
    }
}
