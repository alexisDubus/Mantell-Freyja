package com.dubus.mantell.freyja.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private String photo;
    private String video;
    private String country;
    private String currency;
    //private LocalDateTime creationDate;
    private double price;
}
