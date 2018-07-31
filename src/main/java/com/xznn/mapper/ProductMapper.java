package com.xznn.mapper;

import com.xznn.pojo.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {

    @Select("select * from product_ where cid = #{cid}")
    List<Product> listByCategory(int cid);

    @Select("select * from product_")
    @Results({
            @Result(property = "category", column = "cid", one = @One(select = "com.xznn.mapper.CategoryMapper.get"))
    })
    List<Product> list();
}
