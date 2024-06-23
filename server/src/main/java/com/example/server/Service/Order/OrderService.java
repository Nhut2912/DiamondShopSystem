package com.example.server.Service.Order;

import com.example.server.Model.OrderDTO;
import com.example.server.Model.PaymentDTO;
import com.example.server.Pojo.Account;
import com.example.server.Pojo.Order;

import com.example.server.Pojo.Payment;
import com.example.server.Repository.IAccountRepository;
import com.example.server.Repository.IOrderRepository;

import com.example.server.Service.Account.IAccountService;
import com.example.server.Service.OrderDetail.IOrderDetailService;
import com.example.server.Service.PaymenMethod.IPaymentMethodService;
import com.example.server.Service.Payment.IPaymentService;
import com.example.server.Service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository iOrderRepository;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IOrderDetailService iorderDetailService;
    @Autowired
    private IPaymentService iPaymentService;
    @Autowired
    private IPaymentMethodService iPaymentMethodService;
    @Override
    public Order saveOrder(OrderDTO orderDTO) {
        try {
            Order order = new Order();
            order.setAddress(orderDTO.getAccountDTO().getAddress());
            order.setDate(orderDTO.getDate());
            if (orderDTO.getPaymentDTOS().getPaymentMethodDTO().getMethod().equals("BANKTRANSFER")) {
                order.setOrderStatus("PENDING");
            } else order.setOrderStatus("PREPARING");
            order.setDelivery(orderDTO.isDelivery());
            order.setTotalPrice(orderDTO.getTotalPrice());
            Optional<Account> account = accountRepository.findById(orderDTO.getAccountDTO().getId());
            order.setAccount(account.get());
            return iOrderRepository.save(order);
        } catch (Exception ex) {
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
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(status);
            iOrderRepository.save(order);
            return true;
        } else {
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



    public Map<LocalDate, Integer> getOrdersByMonthAndYear(int month, int year) {
        List<Order> orders = iOrderRepository.getOrderByMonthAndYear(month, year);
        return null;
    }

    public Map<LocalDate, Long> getStatisticByWeek() throws ParseException {

        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate = currentDate.minusDays(7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());

        List<Order> orders = iOrderRepository.getOrderByStartDateAndEndDate(startDate, endDate);
        // Create a map with order counts per day
        Map<String, Long> orderCountMap = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> formatter.format(order.getDate()), // Chuyển đổi Date thành String
                        Collectors.counting()
                ));
        for (Map.Entry<String, Long> entry : orderCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Map<LocalDate, Long> resultDate = new HashMap<>();

        for (LocalDate date = beforeDate; !date.isAfter(currentDate); date = date.plusDays(1)) {
            long count = orderCountMap.getOrDefault(formatter.format(formatter.parse(date.toString())), 0L);
            resultDate.put(date, count);
        }
        System.out.println(resultDate.size());
        return resultDate;
    }

    public Map<LocalDate, Long> getStatisticByMonth() throws ParseException {

        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate.getMonth().getValue());
        LocalDate beforeDate;
        if (currentDate.getMonth().getValue() == 4 || currentDate.getMonth().getValue() == 6 ||
                currentDate.getMonth().getValue() == 9 || currentDate.getMonth().getValue() == 11) {
            beforeDate = currentDate.minusDays(30);
        } else if (Year.of(currentDate.getYear()).isLeap() && currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(29);
        } else if (currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(28);
        } else {
            beforeDate = currentDate.minusDays(31);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());

        List<Order> orders = iOrderRepository.getOrderByStartDateAndEndDate(startDate, endDate);
        // Create a map with order counts per day
        Map<String, Long> orderCountMap = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> formatter.format(order.getDate()), // Chuyển đổi Date thành String
                        Collectors.counting()
                ));
        for (Map.Entry<String, Long> entry : orderCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Map<LocalDate, Long> resultDate = new HashMap<>();

        for (LocalDate date = beforeDate; !date.isAfter(currentDate); date = date.plusDays(1)) {
            long count = orderCountMap.getOrDefault(formatter.format(formatter.parse(date.toString())), 0L);
            resultDate.put(date, count);
        }
        System.out.println(resultDate.size());
        return resultDate;
    }

    public Map<LocalDate, Double> getTotalPriceStatisticByWeek() throws ParseException {

        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate = currentDate.minusDays(7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());

        List<Order> orders = iOrderRepository.getOrderByStartDateAndEndDate(startDate, endDate);
        // Create a map with order counts per day
        Map<String, Double> orderCountMap = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> formatter.format(order.getDate()), // Chuyển đổi Date thành String
                        Collectors.summingDouble(Order::getTotalPrice)
                ));

        Map<LocalDate, Double> resultDate = new HashMap<>();

        for (LocalDate date = beforeDate; !date.isAfter(currentDate); date = date.plusDays(1)) {
            double sumPrice = orderCountMap.getOrDefault(formatter.format(formatter.parse(date.toString())), (double) 0);
            resultDate.put(date, sumPrice);
        }
        System.out.println(resultDate.size());
        return resultDate;
    }

    public Map<LocalDate, Double> getTotalPriceStatisticByMonth() throws ParseException {

        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate.getMonth().getValue());
        LocalDate beforeDate;
        if (currentDate.getMonth().getValue() == 4 || currentDate.getMonth().getValue() == 6 ||
                currentDate.getMonth().getValue() == 9 || currentDate.getMonth().getValue() == 11) {
            beforeDate = currentDate.minusDays(30);
        } else if (Year.of(currentDate.getYear()).isLeap() && currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(29);
        } else if (currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(28);
        } else {
            beforeDate = currentDate.minusDays(31);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());

        List<Order> orders = iOrderRepository.getOrderByStartDateAndEndDate(startDate, endDate);
        // Create a map with order counts per day
        Map<String, Double> orderCountMap = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> formatter.format(order.getDate()), // Chuyển đổi Date thành String
                        Collectors.summingDouble(Order::getTotalPrice)
                ));

        Map<LocalDate, Double> resultDate = new HashMap<>();

        for (LocalDate date = beforeDate; !date.isAfter(currentDate); date = date.plusDays(1)) {
            double sumPrice = orderCountMap.getOrDefault(formatter.format(formatter.parse(date.toString())), (double) 0);
            resultDate.put(date, sumPrice);
        }
        System.out.println(resultDate.size());
        return resultDate;
    }
    public double getSumTotalPriceStatisticByWeek() throws ParseException {

        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate = currentDate.minusDays(7);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());

        List<Order> orders = iOrderRepository.getOrderByStartDateAndEndDate(startDate, endDate);
        // Create a map with order counts per day
        Map<String, Double> orderCountMap = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> formatter.format(order.getDate()), // Chuyển đổi Date thành String
                        Collectors.summingDouble(Order::getTotalPrice)
                ));
        double sumToTalPrice = 0;
        for (Map.Entry<String, Double> entry : orderCountMap.entrySet()) {
            sumToTalPrice += entry.getValue();
        }

        return sumToTalPrice;
    }
    public double getSumTotalPriceStatisticByMonth() throws ParseException {

        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate;
        if (currentDate.getMonth().getValue() == 4 || currentDate.getMonth().getValue() == 6 ||
                currentDate.getMonth().getValue() == 9 || currentDate.getMonth().getValue() == 11) {
            beforeDate = currentDate.minusDays(30);
        } else if (Year.of(currentDate.getYear()).isLeap() && currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(29);
        } else if (currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(28);
        } else {
            beforeDate = currentDate.minusDays(31);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());

        List<Order> orders = iOrderRepository.getOrderByStartDateAndEndDate(startDate, endDate);
        // Create a map with order counts per day
        Map<String, Double> orderCountMap = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> formatter.format(order.getDate()), // Chuyển đổi Date thành String
                        Collectors.summingDouble(Order::getTotalPrice)
                ));
        double sumToTalPrice = 0;
        for (Map.Entry<String, Double> entry : orderCountMap.entrySet()) {
            sumToTalPrice += entry.getValue();
        }

        return sumToTalPrice;
    }

    public Long getTheSumOfOrderStatisticByWeek() throws ParseException {

        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate = currentDate.minusDays(7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());

        List<Order> orders = iOrderRepository.getOrderByStartDateAndEndDate(startDate, endDate);
        // Create a map with order counts per day
        Map<String, Long> orderCountMap = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> formatter.format(order.getDate()), // Chuyển đổi Date thành String
                        Collectors.counting()
                ));
        long sumToTalOrders = 0;
        for (Map.Entry<String, Long> entry : orderCountMap.entrySet()) {
            sumToTalOrders += entry.getValue();
        }

        return sumToTalOrders;
    }
    public Long getTheSumOfOrderStatisticByMonth() throws ParseException {

        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate;
        if (currentDate.getMonth().getValue() == 4 || currentDate.getMonth().getValue() == 6 ||
                currentDate.getMonth().getValue() == 9 || currentDate.getMonth().getValue() == 11) {
            beforeDate = currentDate.minusDays(30);
        } else if (Year.of(currentDate.getYear()).isLeap() && currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(29);
        } else if (currentDate.getMonth().getValue() == 2) {
            beforeDate = currentDate.minusDays(28);
        } else {
            beforeDate = currentDate.minusDays(31);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = formatter.parse(currentDate.toString());
        Date startDate = formatter.parse(beforeDate.toString());

        List<Order> orders = iOrderRepository.getOrderByStartDateAndEndDate(startDate, endDate);
        // Create a map with order counts per day
        Map<String, Long> orderCountMap = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> formatter.format(order.getDate()), // Chuyển đổi Date thành String
                        Collectors.counting()
                ));
        long sumToTalOrders = 0;
        for (Map.Entry<String, Long> entry : orderCountMap.entrySet()) {
            sumToTalOrders += entry.getValue();
        }

        return sumToTalOrders;
    }
    public boolean buyProduct(OrderDTO orderDto){
        if (iAccountService.isAccountExist(orderDto.getAccountDTO().getId())) {
            try{

                if(iAccountService.updateNewestInfoForAccount(orderDto.getAccountDTO())){
                    orderDto.getOrderDetailDTOS().forEach(orderDetail -> iProductService.getProductToSetStatus(orderDetail.getProductID()));
                    Order order = saveOrder(orderDto);
                    if(order != null){
                        iorderDetailService.saveOrderDetail(orderDto, order);
                        iPaymentService.createPayment(orderDto, order);
                    }
                }
                return true;
            }catch (Exception ex){
                return false;
            }
        }
        return false;
    }

    public boolean updatePayment(PaymentDTO paymentDTO, Long orderId){
        try {

            Payment newPayment = new Payment();

            Order order = getOrderById(orderId);
            newPayment.setOrder(order);
            newPayment.setAmount(paymentDTO.getAmount());
            newPayment.setPayTime(paymentDTO.getPayTime());
            if(paymentDTO.getPaymentMethodDTO().getMethod().equals("BANKTRANSFER")){
                newPayment.setImage(paymentDTO.getImage());
            }else newPayment.setTransactionCode(paymentDTO.getTransactionCode());
            newPayment.setPaymentMethod(
                    iPaymentMethodService.getPaymentMethod(paymentDTO.getPaymentMethodDTO().getMethod())

            );
            iPaymentService.updatePayment(newPayment);
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

}
