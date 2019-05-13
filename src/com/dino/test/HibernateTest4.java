package com.dino.test;

import com.dino.domain.Role;
import com.dino.domain.User;
import com.dino.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HibernateTest4 {
    @Test
    public void test1(){
        //简单查询
        Session session = HibernateUtil.openSession();

//        Query query = session.createQuery("from User");
//        Query query = session.createQuery("select u from User u");
        Query query = session.createQuery("select u from User u order by user_id");
        List<User> list = query.list();
        System.out.println(list);
    }

    @Test
    public void test2(){
        //条件查询
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("from User where user_code = :user_code and user_name = :user_name");
        query.setParameter("user_code", "001");
        query.setParameter("user_name", "User1");
        List<User> list = query.list();
        System.out.println(list);
    }


    @Test
    public void test3(){
        Session session = HibernateUtil.openSession();
        // 查询单个属性
        Query query = session.createQuery("select c.user_name from User c");
        List<Object> list = query.list();
        System.out.println(list);

        //查询多个属性
        Query query1 = session.createQuery("select c.user_name, c.user_code from User c");
        List<Object[]> list1 = query1.list();
        for (Object[] objs : list1){
            System.out.println(Arrays.toString(objs));
        }

        System.out.println("================");

        //查询多个结果封装成对象
        Query query2 = session.createQuery("select new User(user_code, user_name) from User c");
        List<User> list2 = query2.list();
        System.out.println(list2);
    }


    @Test
    public void test4(){
        //分页查询
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("from Role");
        query.setFirstResult(0); //角标的开始位置
        query.setMaxResults(2); // 一次查多少条
        List<Role> list = query.list();
//        System.out.println(list);
        for (Role role : list){
            System.out.println(role);
        }
    }

    @Test
    public void test5(){
        //查询所有的记录数
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("select count(*) from Role ");
        Object o = query.uniqueResult();
        System.out.println(o);
    }

    @Test
    public void test6(){
        //分组查询
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("select user_code, count(*) from User group by user_code");
        List<Object[]> list = query.list();
        for (Object[] obj : list){
            System.out.println(Arrays.toString(obj));
        }
    }

    @Test
    public void test7(){
        //连接查询
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("from User u inner join u.roles");
        List<Object[]> list = query.list();
        for (Object[] obj : list){
            System.out.println(Arrays.toString(obj));
        }

    }

    @Test
    public void test8(){
        //连接查询
        Session session = HibernateUtil.openSession();
        //迫切内连接， 会自动的封装成对象
        Query query = session.createQuery("from User u inner join fetch u.roles");
        List<User> list = query.list();
        for (User user: list){
            System.out.println(user);
        }
    }
}
