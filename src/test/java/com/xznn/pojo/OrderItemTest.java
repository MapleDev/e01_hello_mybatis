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
import java.util.List;


public class OrderItemTest {

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
    public void testAddOrderItem() {
        OrderItem orderItem = new OrderItem();
        Order order = session.selectOne("getOrder", 1);
        orderItem.setOrder(order);
        Product product = session.selectOne("getProduct", 6);
        orderItem.setProduct(product);
        orderItem.setNumber(200);
        session.insert("addOrderItem", orderItem);
    }

    @Test
    public void testDeleteOrderItem() {
        OrderItem orderItem = new OrderItem();
        Order order = session.selectOne("getOrder", 1);
        orderItem.setOrder(order);
        Product product = session.selectOne("getProduct", 6);
        orderItem.setProduct(product);
        session.insert("deleteOrderItem", orderItem);
    }

}