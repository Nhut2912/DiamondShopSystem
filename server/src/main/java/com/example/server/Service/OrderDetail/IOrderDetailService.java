package com.example.server.Service.OrderDetail;

import com.example.server.Model.OrderDTO;
import com.example.server.Pojo.Order;

public interface IOrderDetailService {
    public boolean saveOrderDetail(OrderDTO oderDetail, Order order);
}
