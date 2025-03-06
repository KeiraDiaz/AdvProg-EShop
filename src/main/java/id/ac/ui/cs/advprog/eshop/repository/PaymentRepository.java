package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentRepository {
    private Map<String, Payment> payments;
    
    public PaymentRepository() {
        this.payments = new HashMap<>();
    }
    
    public Payment save(Payment payment) {
        return null;
    }
    
    public Payment findById(String id) {
        return null;
    }

    public List<Payment> findAll() {
        return null;
    }
    
    public List<Payment> findByOrderId(String orderId) {
        return null;
    }
}