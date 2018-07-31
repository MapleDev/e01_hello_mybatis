package com.xznn.mapper;

import com.xznn.pojo.Order;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {

    @Select("select * from order_")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderItems", javaType = List.class, column = "id",
                    many = @Many(select = "com.xznn.mapper.OrderItemMapper.listByOrder"))
    })
    List<Order> list();

}
