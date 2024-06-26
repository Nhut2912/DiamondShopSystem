package com.example.server.Model;

import lombok.Data;

@Data
public class OrderDetailDTO {

    private Long id;

    private double priceAfterSizeAdjustment;

    private double priceBeforeSizeAdjustment;

    private int size;

    private Long productID;

}
