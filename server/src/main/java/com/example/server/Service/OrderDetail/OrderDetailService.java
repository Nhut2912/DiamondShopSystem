package com.example.server.Service.OrderDetail;

import com.example.server.Model.OrderDTO;
import com.example.server.Model.OrderDetailDTO;
import com.example.server.Model.ProductDTO;
import com.example.server.Pojo.Order;
import com.example.server.Pojo.OrderDetail;
import com.example.server.Pojo.Product;
import com.example.server.Repository.IOrderDetailRepository;
import com.example.server.Service.Order.IOrderService;
import com.example.server.Service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class OrderDetailService implements IOrderDetailService{
    @Autowired
    IOrderDetailRepository iOrderDetailRepository;
    @Autowired
    IProductService iProductService;
    @Autowired
    IOrderService iOrderService;

    @Override
    public boolean saveOrderDetail(OrderDTO orderDTO, Order order) {
        try{
            System.out.println("size: " + orderDTO.getOrderDetailDTOS().size());
            orderDTO.getOrderDetailDTOS().forEach((orderDe) -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setPriceAfterSizeAdjustment(orderDe.getPriceAfterSizeAdjustment());
                    orderDetail.setSize(orderDe.getSize());
                    Product product = iProductService.getProductById(orderDe.getProductID());
                    orderDetail.setProduct(product);
                    orderDetail.setOrder(iOrderService.getOrderById(order.getId()));
                    iOrderDetailRepository.save(orderDetail);
                        }
                    );
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
