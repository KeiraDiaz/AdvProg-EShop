package id.ac.ui.cs.advprog.eshop.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Payment {
    private String id;
    private Order order;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    
    private static final List<String> VALID_PAYMENT_METHODS = Arrays.asList("VOUCHER", "BANK_TRANSFER");
    private static final List<String> VALID_PAYMENT_STATUSES = Arrays.asList("WAITING", "SUCCESS", "REJECTED");

    // Constructor with default status
    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        this.status = "WAITING";
    }

    // Constructor with custom status
    public Payment(String id, Order order, String method, Map<String, String> paymentData, String status) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        this.status = status;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    public String getMethod() {
        return method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
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
    
    public void validateAndSetStatus() {
        if ("VOUCHER".equals(method)) {
            validateVoucherPayment();
        } else if ("BANK_TRANSFER".equals(method)) {
            validateBankTransferPayment();
        } else {
            throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
    }
    
    private void validateVoucherPayment() {
        String voucherCode = paymentData.get("voucherCode");
        
        // Check if voucher code has correct prefix
        if (voucherCode == null || !voucherCode.startsWith("ESHOP")) {
            setStatus("REJECTED");
            return;
        }
        
        // Check if voucher has correct length (16 characters in the tests)
        if (voucherCode.length() != 16) {
            setStatus("REJECTED");
            return;
        }
        
        // Check if voucher has enough digits (4 digits in the tests)
        long digitCount = voucherCode.chars().filter(Character::isDigit).count();
        if (digitCount < 4) {
            setStatus("REJECTED");
            return;
        }
        
        // All validations passed
        setStatus("SUCCESS");
    }
    
    private void validateBankTransferPayment() {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        
        // Check if bank name exists
        if (bankName == null || bankName.isEmpty()) {
            setStatus("REJECTED");
            return;
        }
        
        // Check if reference code exists and is not empty
        if (referenceCode == null || referenceCode.isEmpty()) {
            setStatus("REJECTED");
            return;
        }
        
        // All validations passed
        setStatus("SUCCESS");
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