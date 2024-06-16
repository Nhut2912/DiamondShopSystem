package com.example.server.Model;


import lombok.Data;

import java.sql.Date;
import java.util.List;
@Data
public class PromotionDTO {
    private Long idPromotion;
    private int promotionRate;
    private boolean active;
    private String namePromotion;
    private Date dateStart;
    private Date dateEnd;
    private List<Long> productIds;


}
