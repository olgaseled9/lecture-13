package by.itacademy.javaenterprise.seledtsova.service;

import by.itacademy.javaenterprise.seledtsova.dao.CustomerDao;
import by.itacademy.javaenterprise.seledtsova.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    @Qualifier("customerDaoBean")
    private CustomerDao customerDao;

    public List<Customer> getAllCustomer() {
        return customerDao.getAll();
    }
}


