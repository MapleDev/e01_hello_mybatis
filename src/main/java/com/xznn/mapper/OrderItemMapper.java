package com.xznn.mapper;

import com.xznn.pojo.OrderItem;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderItemMapper {

    @Select(" select * from order_item_ where oid = #{oid}")
    @Results({
            @Result(property="product",column="pid",one=@One(select="com.xznn.mapper.ProductMapper.get"))
    })
    List<OrderItem> listByOrder(int oid);

}
