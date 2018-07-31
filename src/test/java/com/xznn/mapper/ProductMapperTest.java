package com.xznn.mapper;

import com.xznn.pojo.Category;
import com.xznn.pojo.Product;
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


public class ProductMapperTest {
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
    public void testList() {
        List<Product> ps = mapper.list();
        for (Product p : ps) {
            System.out.println(p);
            Category category = p.getCategory();
            System.out.println("\t 对应的分类是 " + category);
        }
    }

}