package com.wood.app.controller;

import com.wood.app.entity.PaymentNotifyEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author wood
 * @Date 2020-12-10
 */
@RestController
public class PaymentController {

    @RequestMapping(value = "/payment/notify", method= RequestMethod.POST)
    @ResponseBody
    public String paymentNotify(PaymentNotifyEntity notifyEntity) {
        return "付款异步通知：" + notifyEntity.toString();
    }

}
