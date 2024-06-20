package com.example.server.Model;

import com.example.server.Pojo.Product;
import com.example.server.Pojo.WarrantyPolicy;
import lombok.Data;


import java.util.Date;
@Data
public class WarrantyDTO {
    private Long id;

    private boolean status ;

    private String userName;

    private String phoneNumber;

    private String address;

    private Date dateStart;

    private Date dateEnd;

    private Product product;

    private WarrantyPolicy warrantyPolicies;
}
