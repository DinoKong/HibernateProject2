package com.dino.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Customer {
    private Long cust_id;
    private String cust_name;
    private String cust_source;
    private String cust_industry;
    private String cust_level;
    private String cust_phone;
    private String cust_mobile;

    //一个客户可以有多个联系人
    private Set<Linkman> linkmen = new HashSet();

    @Override
    public String toString() {
        return "Customer{" +
                "cust_id=" + cust_id +
                ", cust_name='" + cust_name + '\'' +
                ", cust_source='" + cust_source + '\'' +
                ", cust_industry='" + cust_industry + '\'' +
                ", cust_level='" + cust_level + '\'' +
                ", cust_phone='" + cust_phone + '\'' +
                ", cust_mobile='" + cust_mobile + '\'' +
               // ", linkmen=" + linkmen +
                '}';
    }
}
