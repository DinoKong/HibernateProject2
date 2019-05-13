package com.dino.test;

import com.dino.domain.Role;
import com.dino.domain.User;
import com.dino.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateTest2 {

    @Test
    public void test1(){
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        //创建用户
        User user1 = new User();
        user1.setUser_name("User1");

        User user2 = new User();
        user2.setUser_name("User2");

        //创建角色
        Role role1 = new Role();
        role1.setRole_name("Role1");

        Role role2 = new Role();
        role2.setRole_name("Role2");

        Role role3 = new Role();
        role3.setRole_name("Role3");

        //配置关系 单向维护 双向维护
        user1.getRoles().add(role1);
        user1.getRoles().add(role2);

        user2.getRoles().add(role2);
        user2.getRoles().add(role3);

        currentSession.save(user1);
        currentSession.save(user2);

        /*currentSession.save(role1);
        currentSession.save(role2);
        currentSession.save(role3);*/

        transaction.commit();
    }

    @Test
    public void test2(){
        //关系操作   操作内部集合
        //用户1 添加一个新的角色3
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        //用户1 添加一个新的角色3
        User user = currentSession.get(User.class, 1L);
        Role role = currentSession.get(Role.class, 3L);
        user.getRoles().add(role);

        transaction.commit();
    }

    @Test
    public void test3(){
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        //把用户2的角色3  修改成角色1
        User user2 = currentSession.get(User.class, 2L);
        Role role3 = currentSession.get(Role.class, 3L);
        Role role1 = currentSession.get(Role.class, 1L);

        user2.getRoles().remove(role3);
        user2.getRoles().add(role1);

        transaction.commit();
    }
}
