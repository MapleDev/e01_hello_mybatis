package com.xznn.mapper;

import com.xznn.pojo.Category;
import com.xznn.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class CategoryMapperTest {
    static Logger logger = Logger.getLogger(CategoryMapperTest.class);

    private SqlSession session;
    private CategoryMapper mapper;

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
        mapper = session.getMapper(CategoryMapper.class);

        BasicConfigurator.configure();
//        PropertyConfigurator.configure("C:\\Users\\bazin\\Desktop\\temp\\log4j.properties");
    }

    @After
    public void after() {
        session.commit();
        session.close();
        logger.warn("session.close();");
    }


    @Test
    public void test() {
//        add(mapper);
//        delete(mapper);
//        get(mapper);
//        update(mapper);
        listAll(mapper);
    }


    private static void update(CategoryMapper mapper) {
        Category c = mapper.get(4);
        c.setName("修改了的Category名稱");
        mapper.update(c);
        listAll(mapper);
    }

    private static void get(CategoryMapper mapper) {
        Category c = mapper.get(4);
        System.out.println(c.getName());
    }

    private static void delete(CategoryMapper mapper) {
        mapper.delete(2);
        listAll(mapper);
    }

    private static void add(CategoryMapper mapper) {
        Category c = new Category();
        c.setName("新增加的Category");
        mapper.add(c);
        listAll(mapper);
    }

    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

    @Test
    public void count() {
        int count = mapper.count();
        System.out.println("count = " + count);
    }

    @Test
    public void trans() {
        Category c1 = new Category();
        c1.setName("长度够短的名称 4");
        mapper.add(c1);

        Category c2 = new Category();
        c2.setName("超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称");
        mapper.add(c2);
        listAll(mapper);

        session.commit();
        session.close();
    }

    @Test
    public void list2() {
        List<Category> cs = mapper.list2();
        for (Category c : cs) {
            System.out.println(c.getName());
            List<Product> ps = c.getProducts();
            for (Product p : ps) {
                System.out.println("\t"+p.getName());
            }
        }
    }

    @Test
    public void list3() {
        List<Category> cs = mapper.list3();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

    @Test
    public void add100Category() {
        for (int i = 0; i < 100; i++) {
            Category c = new Category();
            c.setName("category name " + i);
            mapper.add(c);
        }
    }

}