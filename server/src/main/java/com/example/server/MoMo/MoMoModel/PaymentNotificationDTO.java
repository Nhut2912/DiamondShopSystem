package com.example.server.MoMo.MoMoModel;

import lombok.Data;

@Data
public class PaymentNotificationDTO {
    String partnerCode, orderId, requestId, amount, orderInfo, orderType, transId, resultCode, message, payType, responseTime,
            extraData, signature;

}
