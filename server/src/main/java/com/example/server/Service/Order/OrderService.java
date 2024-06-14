package com.example.server.Service.Order;

import com.example.server.Model.OrderDTO;
import com.example.server.Pojo.Account;
import com.example.server.Pojo.Order;

import com.example.server.Repository.IAccountRepository;
import com.example.server.Repository.IOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.sql.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService implements  IOrderService{

    @Autowired
    private IOrderRepository iOrderRepository;
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Order saveOrder(OrderDTO orderDTO) {
        try{
            Order order = new Order();
            order.setAddress(orderDTO.getAccountDTO().getAddress());
            order.setDate(orderDTO.getDate());
            if(orderDTO.getPaymentDTOS().getPaymentMethodDTO().getMethod().equals("BANKTRANSFER")){
                order.setOrderStatus("PENDING");
            }else order.setOrderStatus("PREPARING");
            order.setDelivery(orderDTO.isDelivery());
            order.setTotalPrice(orderDTO.getTotalPrice());
            Optional<Account> account = accountRepository.findById(orderDTO.getAccountDTO().getId());
            order.setAccount(account.get());
            return iOrderRepository.save(order);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) iOrderRepository.findAll();
    }

    @Override
    public List<Order> getOrderByStatus(String orderStatus) {

        return iOrderRepository.findAllByOrderStatus(orderStatus);
    }

    @Override
    public boolean updateOrderStatus(Long id, String status) {
        Optional<Order> optionalOrder = iOrderRepository.findById(id);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setOrderStatus(status);
            iOrderRepository.save(order);
            return true;
        }else {
            return false;
        }
    }


    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = iOrderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public List<Order> getOrdersByAccountID(Long id) {
        return iOrderRepository.getOrdersByAccount_Id(id);
    }

    public List<Order> getOrdersByDay(int id){
        return iOrderRepository.getOrderByDay(id);
    }
    public Map<LocalDate, Integer> getOrdersByMonthAndYear(int month, int year){
        List<Order> orders = iOrderRepository.getOrderByMonthAndYear(month, year);
        return null;
    }
}
