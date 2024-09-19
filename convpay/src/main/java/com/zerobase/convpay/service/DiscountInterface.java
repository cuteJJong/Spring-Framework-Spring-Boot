package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;

//할인 금액 계산
public interface DiscountInterface {
    //할인된 금액 받기
    Integer getDiscountedAmount(PayRequest payRequest);
}
