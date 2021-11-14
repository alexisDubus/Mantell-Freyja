package com.dubus.mantell.freyja.order.utils;

import com.dubus.mantell.freyja.order.entity.Order;
import com.dubus.mantell.freyja.order.dto.OrderDto;
import org.springframework.beans.BeanUtils;

public class OrderAppUtils {


    public static OrderDto entityToDto(Order product) {
        OrderDto productDto = new OrderDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Order dtoToEntity(OrderDto productDto) {
        Order product = new Order();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
