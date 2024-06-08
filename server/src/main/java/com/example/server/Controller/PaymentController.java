package com.example.server.Controller;




import com.example.server.MoMo.Config.Environment;
import com.example.server.MoMo.Enum.RequestType;
import com.example.server.MoMo.MoMoModel.PaymentNotificationDTO;
import com.example.server.MoMo.MoMoModel.PaymentResponse;
import com.example.server.MoMo.MoMoUtil.LogUtils;
import com.example.server.MoMo.Processor.CreateOrderMoMo;
import com.example.server.Model.OrderDTO;
import com.example.server.Model.PaymentDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mservice.shared.exception.MoMoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class PaymentController {
    private final Gson gson;

    public PaymentController(Gson gson) {
        this.gson = gson;
    }

    @GetMapping("payment")
    public PaymentResponse payment(OrderDTO orderDTO) throws MoMoException {
        LogUtils.init();
        String requestId = orderDTO.getId().toString();
        String orderId = orderDTO.getId().toString();
        double amount = orderDTO.getPaymentDTOS().getAmount();
        

        String orderInfo = "Pay With MoMo";
        String returnURL = "https://google.com.vn";
        String notifyURL = "/checkPayment"; //url se toi api checkPayment khi thanh toan thanh cong,
        // de gui du lieu sau khi thanh toan thanh cong ve` api cua? minh` nhu orderId, transactionId

        Environment environment = Environment.selectEnv("dev");

        /*
         * create payment with capture momo wallet
         */
        PaymentResponse captureWalletMoMoResponse = CreateOrderMoMo.process(environment, orderId, requestId, Double.toString(amount), orderInfo, returnURL, notifyURL, "", RequestType.CAPTURE_WALLET, Boolean.TRUE);
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
}