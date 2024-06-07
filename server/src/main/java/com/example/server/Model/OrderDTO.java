package com.example.server.Model;

import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
public class OrderDTO {

    private Long Id;

    private String address;

    private String cancelReason;

    private boolean isDelivery;

    private double totalPrice;

    private String orderStatus;

    private Date date;

    private AccountDTO accountDTO;

    private Set<OrderDetailDTO> orderDetailDTOS;
}
