package com.zerobase.convpay.config;

import com.zerobase.convpay.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;


// Spring 설정 클래스, Bean 정의와 의존성 주입을 위한 설정 정보를 제공
@Configuration
public class AppliactionConfig {

    //@Bean은 Spring container에 의해서 관리되는 
    //ConveniencePayService 객체를 담아주는
    @Bean
    public ConveniencePayService conveniencePayService(){
        return new ConveniencePayService(
                new HashSet<>(
                        Arrays.asList(moneyAdapter(), cardAdapter())
                ),
                discountByConvenience()
        );
    }

    @Bean
    //ctrl + alt + m
    public CardAdapter cardAdapter() {
        return new CardAdapter();
    }

    @Bean
    public MoneyAdapter moneyAdapter() {
        return new MoneyAdapter();
    }

    @Bean   // public으로 가능
    public DiscountByConvenience discountByConvenience() {
        return new DiscountByConvenience();
    }

}
