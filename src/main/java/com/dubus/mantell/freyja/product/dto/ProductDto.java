package com.dubus.mantell.freyja.product.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private String id;
    private String name;
    private String description;
    private String photo;
    private String video;
    private String country;
    private String currency;
    //private LocalDateTime creationDate;
    private double price;

    public ProductDto(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
