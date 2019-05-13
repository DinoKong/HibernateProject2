package com.dino.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    //sessionFactory是线程安全的
    //session的线程不安全
    public static final SessionFactory sessionFactory;
    static {
        // 1. 加载配置文件
        Configuration configure = new Configuration().configure();
        // 2. 创建sessionFactory
        sessionFactory = configure.buildSessionFactory();
    }

    public static Session openSession(){
        return sessionFactory.openSession();
    }

    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
