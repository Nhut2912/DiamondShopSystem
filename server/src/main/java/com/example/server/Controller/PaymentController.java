package com.example.server.Controller;




import com.example.server.MoMo.Config.Environment;
import com.example.server.MoMo.Enum.RequestType;
import com.example.server.MoMo.MoMoModel.PaymentNotificationDTO;
import com.example.server.MoMo.MoMoModel.PaymentResponse;
import com.example.server.MoMo.MoMoUtil.LogUtils;
import com.example.server.MoMo.Processor.CreateOrderMoMo;
import com.example.server.Model.OrderDTO;
import com.example.server.Model.PaymentDTO;
import com.example.server.Pojo.Payment;
import com.example.server.Service.Payment.IPaymentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mservice.shared.exception.MoMoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping(path = "api/")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
    private final Gson gson;

    @Autowired
    private IPaymentService paymentService;

    public PaymentController(Gson gson) {
        this.gson = gson;
    }

    @GetMapping("payment/{amount}")
    public PaymentResponse payment(@PathVariable Long amount) throws MoMoException {
        LogUtils.init();

        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());

        

        String orderInfo = "Pay With MoMo";
        String returnURL = "http:/localhost:3000/checkout-cart/check-payment";
        String notifyURL = "/check";
        Environment environment = Environment.selectEnv("dev");

        /*
         * create payment with capture momo wallet
         */
        PaymentResponse captureWalletMoMoResponse = CreateOrderMoMo.process(environment, orderId, requestId, Long.toString(amount), orderInfo, returnURL, notifyURL, "", RequestType.CAPTURE_WALLET, Boolean.TRUE);
        return captureWalletMoMoResponse;
    }


    @GetMapping("paymentRemainder/{amount}")
    public PaymentResponse paymentRemainder(@PathVariable Long amount) throws MoMoException {
        LogUtils.init();

        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());

        String orderInfo = "Pay Remainder";
        String returnURL = "http:/localhost:3000/purchase/remainder/check-payment";
        String notifyURL = "/check";
        Environment environment = Environment.selectEnv("dev");

        /*
         * create payment with capture momo wallet
         */
        PaymentResponse captureWalletMoMoResponse = CreateOrderMoMo.process(environment, orderId, requestId, Long.toString(amount), orderInfo, returnURL, notifyURL, "", RequestType.CAPTURE_WALLET, Boolean.TRUE);
        return captureWalletMoMoResponse;
    }





    @PostMapping("checkPayment")
    public ResponseEntity<?> paymentNotification(@RequestBody PaymentNotificationDTO paymentNotificationDTO){
        String a = new GsonBuilder()
                .disableHtmlEscaping()
                .create().toJson(paymentNotificationDTO, PaymentNotificationDTO.class);
        System.out.println("Json momo trả về sau khi hoàn thành payment:\n" + a);
        return new ResponseEntity<>(paymentNotificationDTO, HttpStatus.OK);
    }

    @GetMapping("payment")
    public ResponseEntity<?> getPaymentByOrderID(@RequestParam Long order_id){
        List<Payment> paymentList = paymentService.getPaymentByOrderId(order_id);
        return ResponseEntity.ok(Objects.requireNonNullElse(paymentList, "NOT YET"));
    }

}