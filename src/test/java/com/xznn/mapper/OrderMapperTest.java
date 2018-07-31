package com.xznn.mapper;

import com.xznn.pojo.Order;
import com.xznn.pojo.OrderItem;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class OrderMapperTest {

    private SqlSession session;
    private ProductMapper mapper;

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
        mapper = session.getMapper(ProductMapper.class);

        BasicConfigurator.configure();
    }

    @After
    public void after() {
        session.commit();
        session.close();
    }

    @Test
    public void list() {
        List<Order> os = session.selectList("listOrder");
        for (Order o : os) {
            System.out.println(o);
            List<OrderItem> ois = o.getOrderItems();
            for (OrderItem oi : ois) {
                System.out.println("\t 对应的 OrderItem 是 " + oi);
            }
        }
    }
}