package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.type.*;


public class ConveniencePayService {    //편결이
    private final MoneyAdapter moneyAdapter = new MoneyAdapter();
    private final CardAdapter cardAdapter = new CardAdapter();

    //PayRequest를 받아서 PayResponse를 던진다.
    public PayResponse pay(PayRequest payRequest) {
        PaymentInterface paymentInterface;

        if(payRequest.getPayMethodType() == PayMethodType.CARD) {
            paymentInterface = cardAdapter;
        }
        else {
            paymentInterface = moneyAdapter;
        }

        PaymentResult payment = paymentInterface.payment(payRequest.getPayAmount());

        if (payment == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }

        //fail fast

        //Method()

        // Exception case5
        // Exception case4
        // Exception case1
        // Exception case2
        // Exception case3
        //Success Case(Only One)

        // SUCCESS Case(Only case)
        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());
    }

    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface;

        if (payCancelRequest.getPayMethodType() == PayMethodType.CARD ) {
            paymentInterface = cardAdapter;
        }
        else {
            paymentInterface = moneyAdapter;
        }

        CancelPaymentResult cancelPaymentResult = paymentInterface.cancelPayment(
                (payCancelRequest.getPayCancelAmount()));

        if (cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL) {
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL, 0);
        }


        // SUCCESS Case(Only case)
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS,
                payCancelRequest.getPayCancelAmount());
    }
}
