package com.xznn.mapper;

import com.xznn.dynasql.CategoryDynaSqlProvider;
import com.xznn.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryMapper {

    @Insert("insert into category_ ( name ) values (#{name}) ")
    int add(Category category);

    @Delete("delete from category_ where id= #{id} ")
    void delete(int id);

    @Select("select * from category_ where id= #{id} ")
    Category get(int id);

    @Update("update category_ set name=#{name} where id=#{id} ")
    int update(Category category);

    @Select("select * from category_ ")
    List<Category> list();

    @Select("select * from category_ ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "products", javaType = List.class, column = "id", many = @Many(select = "com.xznn.mapper.ProductMapper.listByCategory"))
    })
    List<Category> list2();

    @SelectProvider(type=CategoryDynaSqlProvider.class, method="list3")
    List<Category> list3();

    @Select("select count(1) from category_")
    int count();
}