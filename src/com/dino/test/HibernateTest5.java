package com.dino.test;

import com.dino.domain.Role;
import com.dino.domain.User;
import com.dino.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

//QBC
public class HibernateTest5 {
    @Test
    public void test1(){
        Session session = HibernateUtil.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.addOrder(Order.desc("user_id"));
        List<User> list = criteria.list();
        System.out.println(list);

        //limit 分页
        criteria.setFirstResult(0);
        criteria.setMaxResults(2);
        List<User> list1 = criteria.list();
        System.out.println(list1);
    }

    @Test
    public void test2(){
        //QBC条件查询
        Session session = HibernateUtil.openSession();
        Criteria criteria = session.createCriteria(User.class);
//        criteria.add(Restrictions.eq("user_code", "001"));
        criteria.add(Restrictions.like("user_code", "0%"));
        List<User> list = criteria.list();
        System.out.println(list);
    }

    @Test
    public void test3(){
        //QBC查询
        Session session = HibernateUtil.openSession();
        Criteria criteria = session.createCriteria(Role.class);
        //统计查询 只有一个结果
        //setProjection  设置一些聚合函数
        criteria.setProjection(Projections.rowCount());

        //执行查询 只有一个结果
        Object o = criteria.uniqueResult();
        System.out.println(o);

    }

    @Test
    public void test4(){
        //离线查询
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        //设置条件
        detachedCriteria.add(Restrictions.eq("user_code", "001"));

        Session session = HibernateUtil.openSession();
        Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);
        List<User> list = executableCriteria.list();

        for (User user : list){
            System.out.println(user);
        }
    }
}
