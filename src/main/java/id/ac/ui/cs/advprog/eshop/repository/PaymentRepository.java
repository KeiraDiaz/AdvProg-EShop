package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentRepository {
    private Map<String, Payment> payments;
    
    public PaymentRepository() {
        this.payments = new HashMap<>();
    }
    
    public Payment save(Payment payment) {
        payments.put(payment.getId(), payment);
        return payment;
    }
    
    public Optional<Payment> findById(String id) {
        return Optional.ofNullable(payments.get(id));
    }

    public List<Payment> findAll() {
        return new ArrayList<>(payments.values());
    }
    
    public List<Payment> findByOrderId(String orderId) {
        return payments.values().stream()
            .filter(payment -> payment.getOrder() != null && 
                    orderId.equals(payment.getOrder().getId()))
            .collect(Collectors.toList());
    }
}