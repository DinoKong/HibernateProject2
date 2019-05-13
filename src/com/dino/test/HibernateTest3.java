package com.dino.test;

import com.dino.domain.User;
import com.dino.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateTest3 {

    @Test
    public void test1(){
        /**
         * 当get（）方法被调用的时候就会立即触发SQL语句
         * 并且返回的对象也是实际的对象
         * 使用get（）和普通的单条查询并没有多大的区别
         * 当查询不到的时候get（）返回的是null
         */
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        User user = currentSession.get(User.class, 1L);
        System.out.println(user);

        User user10 = currentSession.get(User.class, 10L);
        System.out.println(user10);

        transaction.commit();
    }

    @Test
    public void test2(){
        /**
         * 当调用load（）方法的时候会返回一个目标对象的代理对象
         * 在这个代理对象中只储存了目标对象的ID值
         * 只有当调用ID值以外的属性的时候，会发出SQL查询语句
         * 当查询不到的时候load()会报错
         */
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User user1 = currentSession.get(User.class, 1L);

        System.out.println(user1.getUser_id());
        System.out.println(user1.getUser_name());

        System.out.println(user1);


        User user10 = currentSession.get(User.class, 10L);
        System.out.println(user10);

        transaction.commit();
    }


}
