package com.zerobase.convpay.service;

import com.zerobase.convpay.type.CancelPaymentResult;
import com.zerobase.convpay.type.PayMethodType;
import com.zerobase.convpay.type.PaymentResult;

public interface PaymentInterface {
    //인터페이스에서 추상 메서드인 getPayMethodType() 구현하면(이름과 시그니처만 있기에)
    // 상속 받는 클래스에서 구현해줘야 한다.
    PayMethodType getPayMethodType();
    //결제
    PaymentResult payment(Integer payAmount);
    //결제 취소
    CancelPaymentResult cancelPayment(Integer cancelAmount);
}
