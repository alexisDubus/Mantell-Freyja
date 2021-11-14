package com.dubus.mantell.freyja.product.service;

import com.dubus.mantell.freyja.product.utils.ProductAppUtils;
import com.dubus.mantell.freyja.product.dto.ProductDto;
import com.dubus.mantell.freyja.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Flux<ProductDto> getProducts(){
        return repository.findAll().map(ProductAppUtils::entityToDto);
    }

    public Flux<ProductDto> getProducts(List<String> ids){
        return repository.findByIdIsIn(ids).map(ProductAppUtils::entityToDto);
    }

    public Mono<ProductDto> getProduct(String id){
        return repository.findById(id).map(ProductAppUtils::entityToDto);
    }

    public Flux<ProductDto> getProductInRange(double min,double max){
        return repository.findByPriceBetween(Range.closed(min,max));
    }

    public Mono<ProductDto> save(Mono<ProductDto> productDtoMono){
      return  productDtoMono.map(ProductAppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(ProductAppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,String id){
       return repository.findById(id)
                .flatMap(p->productDtoMono.map(ProductAppUtils::dtoToEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(ProductAppUtils::entityToDto);

    }

    public Mono<Void> deleteProduct(String id){
        return repository.deleteById(id);
    }
}
