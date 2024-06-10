package com.example.server.Service.OrderDetail;

import com.example.server.Model.OrderDTO;
import com.example.server.Pojo.Order;
import com.example.server.Pojo.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    public boolean saveOrderDetail(OrderDTO oderDetail, Order order);

    public List<OrderDetail> getOrderDetailByOrderID(Long id);
}
