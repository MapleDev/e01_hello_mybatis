package com.xznn.pojo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderTest {

    private SqlSession session;

    @Before
    public void init() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }

    @After
    public void after() {
        session.commit();
        session.close();
    }

    @Test
    public void testListOrder() {
        List<Order> os = session.selectList("listOrder");
        for (Order o : os) {
            System.out.println(o);
            List<OrderItem> ois = o.getOrderItems();
            for (OrderItem oi : ois) {
                System.out.println("\t 对应的 OrderItem 是 " + oi);
            }
        }
    }

    @Test
    public void testDeleteOrder() {
        int id = 2;
        int deleteOrder = session.delete("deleteOrder", id);
        System.out.println("deleteOrder = " + deleteOrder);
    }

}