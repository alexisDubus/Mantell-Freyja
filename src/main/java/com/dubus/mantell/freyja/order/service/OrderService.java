package com.dubus.mantell.freyja.order.service;

import com.dubus.mantell.freyja.order.dto.OrderDto;
import com.dubus.mantell.freyja.order.repository.OrderRepository;
import com.dubus.mantell.freyja.order.utils.OrderAppUtils;
import com.dubus.mantell.freyja.product.dto.ProductDto;
import com.dubus.mantell.freyja.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository repository;

    private static final double TAXES_DEFAULT = 0.20;


    public Flux<OrderDto> getOrders(){
        return repository.findAll().map(OrderAppUtils::entityToDto);
    }

    public Mono<OrderDto> getOrder(final String id){
        return repository.findById(id).map(OrderAppUtils::entityToDto);
    }

    public Flux<OrderDto> getOrderInRange(double min, double max){
        return repository.findByPriceBetween(Range.closed(min,max));
    }

    public Mono<OrderDto> save(final Mono<OrderDto> orderDtoMono) throws Exception {
      /*  OrderDto orderDto = orderDtoMono.block();
        List<String> listId = orderDto.getOrderItems();

        Flux<ProductDto> productFlux = null;
        if(!listId.isEmpty()) {
            productFlux = productService.getProducts(listId);
        } else {
            throw new Exception("List of Products is empty !");
        }
        double taxes = 0;
        var ref = new Object() {
            double price = 0;
            double taxes = 0;
        };
        if(productFlux.blockFirst() != null) {
            productFlux.map(productDto -> {
                ref.taxes += productDto.getPrice()*TAXES_DEFAULT;
                ref.price += productDto.getPrice();
                return null;
            });
            orderDto.setPrice(ref.price);
            orderDto.setTaxes(ref.taxes);
        } else {
            throw new Exception("List of Products is empty !");
        }*/
      return  /*Mono.just(orderDto)*/orderDtoMono.map(OrderAppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(OrderAppUtils::entityToDto);
    }

    public Mono<OrderDto> updateOrder(final Mono<OrderDto> productDtoMono, final String id){
       return repository.findById(id)
                .flatMap(p->productDtoMono.map(OrderAppUtils::dtoToEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(OrderAppUtils::entityToDto);

    }

    public Mono<Void> deleteOrder(final String id){
        return repository.deleteById(id);
    }
}
