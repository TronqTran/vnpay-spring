package com.vn.vnpay.controller;

import com.vn.vnpay.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    //http://localhost:8080/api/v1/payment/vn-pay?amount=25011622&bankCode=ncb
    @GetMapping("/vn-pay")
    public ResponseEntity<Map<String, String>> pay(HttpServletRequest request) {
        return ResponseEntity.ok(paymentService.createVnPayPayment(request));
    }
    @GetMapping("/vn-pay-callback")
    public ResponseEntity<Map<String, String>> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            return ResponseEntity.ok(Map.of("code","ok", "message", "success"));
        } else {
            return ResponseEntity.ok(Map.of("code","fail", "message", "fail"));
        }
    }
}
