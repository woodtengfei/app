package com.wood.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: PaymentNotifyEntity
 * @Author wood
 * @Date 2020-12-09
 */
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PaymentNotifyEntity {
    private String notifyId;
    private String payAmount;
}
