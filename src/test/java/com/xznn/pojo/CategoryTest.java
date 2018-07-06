package com.xznn.pojo;

import com.xznn.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class CategoryTest {

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

    @Test
    public void testAdd() {
        Category category = new Category();
        category.setId(0);
        category.setName("newCategory");
        int addRst = session.insert("addCategory", category);
        System.out.println("addRst = " + addRst);
    }

    @Test
    public void testListCategory() {
        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

    @Test
    public void testGetCategoryById() {
        int id = 14;
        Category categoryById = session.selectOne("getCategoryById", id);
        System.out.println("categoryById  = " + categoryById);
    }

    @Test
    public void testGetCategoryByName() {
        String keyWord = "cat";
        List<Category> catCategory = session.selectList("listCategoryByName", keyWord);
        for (Category c : catCategory) {
            System.out.println("listCategoryByName = " + c);
        }
    }

    @Test
    public void testUpdateCategory() {

        Category oldCategory = session.selectOne("getCategoryById", 1);
        System.out.println("oldCategory  = " + oldCategory);

        oldCategory.setName("newCategory_" + System.currentTimeMillis());
        int updateRst = session.update("updateCategory", oldCategory);
        System.out.println("updateRst = " + updateRst);

        Category newCategory = session.selectOne("getCategoryById", 1);
        System.out.println("newCategory  = " + newCategory);

    }
}