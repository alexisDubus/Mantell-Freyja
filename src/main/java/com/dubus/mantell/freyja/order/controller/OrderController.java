package com.dubus.mantell.freyja.order.controller;

import com.dubus.mantell.freyja.order.dto.OrderDto;
import com.dubus.mantell.freyja.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public Flux<OrderDto> getOrders(){
        return service.getOrders();
    }

    @GetMapping("/{id}")
    public Mono<OrderDto> getOrder(@PathVariable final String id){
        return service.getOrder(id);
    }

    @GetMapping("/product-range")
    public Flux<OrderDto> getOrderBetweenRange(@RequestParam("min") final double min, @RequestParam("max")final double max){
        return service.getOrderInRange(min,max);
    }

    @PostMapping
    public Mono<OrderDto> saveOrder(@RequestBody final Mono<OrderDto> productDtoMono) throws Exception {
        return service.save(productDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<OrderDto> updateOrder(@RequestBody final Mono<OrderDto> productDtoMono, @PathVariable final String id){
        return service.updateOrder(productDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteOrder(@PathVariable final String id){
        return service.deleteOrder(id);
    }



}
