package com.example.server.Model;

import lombok.Data;

@Data
public class DiamondDTO {

    private Long id;

    private double carat;

    private String origin;

    private String color;

    private String clarity;

    private String cut;

    private String code;
}
