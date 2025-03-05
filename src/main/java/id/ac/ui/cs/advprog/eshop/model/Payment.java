package id.ac.ui.cs.advprog.eshop.model;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;

public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    
    private static final List<String> VALID_PAYMENT_METHODS = Arrays.asList("Credit Card", "Bank Transfer");
    private static final List<String> VALID_PAYMENT_STATUSES = Arrays.asList("SUCCESS", "REJECTED");
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getMethod() {
        return method;
    }
    
    public void setMethod(String method) {
        if (!VALID_PAYMENT_METHODS.contains(method)) {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }
        this.method = method;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        if (!VALID_PAYMENT_STATUSES.contains(status)) {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
        this.status = status;
    }
    
    public Map<String, String> getPaymentData() {
        return paymentData;
    }
    
    public void setPaymentData(Map<String, String> paymentData) {
        this.paymentData = paymentData;
    }
    
    public List<String> getValidPaymentMethods() {
        return VALID_PAYMENT_METHODS;
    }
    
    public List<String> getValidPaymentStatuses() {
        return VALID_PAYMENT_STATUSES;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}