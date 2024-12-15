package com.shop.application.domain.order;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentType {
    String getCardType();
}
