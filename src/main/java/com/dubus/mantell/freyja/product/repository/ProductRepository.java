package com.dubus.mantell.freyja.product.repository;

import com.dubus.mantell.freyja.product.dto.ProductDto;
import com.dubus.mantell.freyja.product.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
    Flux<ProductDto> findByPriceBetween(final Range<Double> priceRange);
    Flux<Product> findByIdIsIn(final List<String> ids);
}
