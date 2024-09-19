package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;

public class DiscountByConveniecne implements DiscountInterface{

    //편의점을 통해 할인 금액 계산
    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {
        //alt + Enter 누락된 브랜치 생성
        switch (payRequest.getConvenienceType()) {
            case G25 -> {
                return payRequest.getPayAmount() * 8 / 10;
            }
            case GU -> {
                return payRequest.getPayAmount() * 9 / 10;
            }
            case SEVEN -> {
                return payRequest.getPayAmount();
            }
        }
//      payRequest.getConvenienceType()이 이 세 가지 중 하나가 아닌 다른 값이 들어올 경우,
//      컴파일러는 "모든 경우에 대해 반환값이 있어야 한다"고 요구하므로
        return payRequest.getPayAmount();
    }
}
