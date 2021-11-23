package by.itacademy.javaenterprise.seledtsova.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {

    private Long orderId;
    private Long customerId;
    private Integer quantity;
    private List<Customer> customers;

    public Order(Long orderId, Long customerId, Integer quantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.quantity = quantity;
    }

    public Order() {
    }
}
