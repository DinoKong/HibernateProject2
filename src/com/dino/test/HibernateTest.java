package com.dino.test;

import com.dino.domain.Customer;
import com.dino.domain.Linkman;
import com.dino.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateTest {
    @Test
    public void test(){
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        Customer customer1 = new Customer();
        customer1.setCust_name("customer1");

        Customer customer2 = new Customer();
        customer2.setCust_name("customer2");


        Customer customer3 = new Customer();
        customer3.setCust_name("customer3");

        Linkman linkman1 = new Linkman();
        linkman1.setLink_name("linkman1");

        Linkman linkman2 = new Linkman();
        linkman2.setLink_name("linkman2");

        Linkman linkman3 = new Linkman();
        linkman3.setLink_name("linkman3");

        /*配置关系*/
        customer1.getLinkmen().add(linkman1);
        customer1.getLinkmen().add(linkman2);
        customer2.getLinkmen().add(linkman3);


        /*linkman1.setCustomer(customer1);
        linkman2.setCustomer(customer1);
        linkman3.setCustomer(customer2);*/

        currentSession.save(customer1);
        currentSession.save(customer2);
        currentSession.save(customer3);

        /*currentSession.save(linkman1);
        currentSession.save(linkman2);
        currentSession.save(linkman3);*/

        transaction.commit();
    }


    @Test
    public void test2(){
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        Linkman linkman = currentSession.get(Linkman.class, 1L);
        System.out.println(linkman);

        transaction.commit();
    }


    @Test
    public void test3(){
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        Customer customer = currentSession.get(Customer.class, 2L);
        System.out.println(customer);
        currentSession.delete(customer);
//        System.out.println(customer);

        transaction.commit();
    }

    @Test
    public void test4(){
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        Linkman linkman1 = currentSession.get(Linkman.class, 1L);
        Customer customer2 = currentSession.get(Customer.class, 2L);

//        customer2.getLinkmen().add(linkman1);
        linkman1.setCustomer(customer2);

        transaction.commit();
    }

}
