package com.xznn.pojo;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductTest {

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

        BasicConfigurator.configure();
    }

    @After
    public void after() {
        session.commit();
        session.close();
    }

    @Test
    public void testListProduct() {
        List<Product> ps = session.selectList("listProduct");
        for (Product p : ps) {
            System.out.println(p);
            Category category = p.getCategory();
            System.out.println("\t 对应的分类是 " + category);
        }
    }

    @Test
    public void testUpdateProduct() {
        // 通过Mybatis，修改 product(id=5) 对应 category(id=2) 为 product(id=5) 对应 category(id=1)
        Map<String, Object> params = new HashMap<>();
        params.put("cid", 1);
        params.put("id", 5);
        int updateProduct = session.update("updateProduct", params);
        System.out.println("updateProduct = " + updateProduct);
    }


    @Test
    public void testIf() {
        System.out.println("查询所有的");
//        List<Product> ps = session.selectList("listProduct2");
        listAll();

        System.out.println("模糊查询");
        Map<String, Object> params = new HashMap<>();
        params.put("name", "a");
        params.put("price", 10);
        List<Product> ps2 = session.selectList("listProductByName", params);
        for (Product p : ps2) {
            System.out.println(p);
        }
    }

    private void listAll() {
        List<Product> ps = session.selectList("listProductByName");
        for (Product p : ps) {
            System.out.println(p);
        }
    }

    @Test
    public void testSet() {
        Product product = new Product();
        product.setId(6);
        product.setName("TV2");
        product.setPrice(55.57f);
        session.update("updateProduct2", product);

        listAll();
    }

    @Test
    public void testWhenOtherwise() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "a");
        params.put("price", "10");
        List<Product> ps = session.selectList("testWhenOtherwise", params);
        for (Product p : ps) {
            System.out.println(p);
        }
    }

    @Test
    public void testForeach() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(3);
        ids.add(5);

        List<Product> ps = session.selectList("testForeach", ids);
        for (Product p : ps) {
            System.out.println(p);
        }
    }

    @Test
    public void testBind() {
        Map<String, String> params = new HashMap<>();
        params.put("name", "product");

        List<Product> ps = session.selectList("testBind", params);
        for (Product p : ps) {
            System.out.println(p);
        }
    }


}