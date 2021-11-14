package com.dubus.mantell.freyja.product.utils;

import com.dubus.mantell.freyja.product.dto.ProductDto;
import com.dubus.mantell.freyja.product.entity.Product;
import org.springframework.beans.BeanUtils;

public class ProductAppUtils {


    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
