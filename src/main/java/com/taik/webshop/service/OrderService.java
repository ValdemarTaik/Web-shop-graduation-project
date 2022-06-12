package com.taik.webshop.service;

import com.taik.webshop.domain.Order;

public interface OrderService {
    void saveOrder(Order order);

    Order getOrder(Long id);
}
