package com.dubus.mantell.freyja.order.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private String id;
    private double price;
    private double taxes;
    private String email;
    private List<String> orderItems = new ArrayList<>();

}
