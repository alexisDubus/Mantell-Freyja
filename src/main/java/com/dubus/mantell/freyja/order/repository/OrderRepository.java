package com.dubus.mantell.freyja.order.repository;

import com.dubus.mantell.freyja.order.dto.OrderDto;
import com.dubus.mantell.freyja.order.entity.Order;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order,String> {
    Flux<OrderDto> findByPriceBetween(Range<Double> priceRange);
}
