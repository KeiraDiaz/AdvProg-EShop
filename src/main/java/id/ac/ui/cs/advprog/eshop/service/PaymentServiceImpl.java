package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private OrderService orderService;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String paymentId = order.getId();
        Payment payment = new Payment(paymentId, order, method, paymentData);
        
        validateAndSetStatus(payment);
        
        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment existingPayment = paymentRepository.findById(payment.getId())
            .orElseThrow(() -> new NoSuchElementException("Payment not found with ID: " + payment.getId()));
        

        
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
        
        existingPayment.setStatus(status);
        
        Order order = existingPayment.getOrder();
        if (order != null) {
            if ("SUCCESS".equals(status)) {
                orderService.updateStatus(order.getId(), "SUCCESS");
            } else if ("REJECTED".equals(status)) {
                orderService.updateStatus(order.getId(), "FAILED");
            }
        }
        

        return paymentRepository.save(existingPayment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    
    private void validateAndSetStatus(Payment payment) {
        String method = payment.getMethod();
        
        if ("VOUCHER".equals(method)) {
            validateVoucherPayment(payment);
        } else if ("BANK_TRANSFER".equals(method)) {
            validateBankTransferPayment(payment);
        } else {
            throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
    }
    
    private void validateVoucherPayment(Payment payment) {
        Map<String, String> paymentData = payment.getPaymentData();
        String voucherCode = paymentData.get("voucherCode");
        
        boolean isValid = voucherCode != null && 
                          voucherCode.startsWith("ESHOP") && 
                          voucherCode.length() == 16 &&
                          voucherCode.chars().filter(Character::isDigit).count() >= 4;
        
        payment.setStatus(isValid ? "SUCCESS" : "REJECTED");
    }
    
    private void validateBankTransferPayment(Payment payment) {
        Map<String, String> paymentData = payment.getPaymentData();
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        
        boolean isValid = bankName != null && !bankName.isEmpty() &&
                          referenceCode != null && !referenceCode.isEmpty();
        
        payment.setStatus(isValid ? "SUCCESS" : "REJECTED");
    }
    
    private boolean isValidStatus(String status) {
        return "WAITING".equals(status) || 
               "SUCCESS".equals(status) || 
               "REJECTED".equals(status);
    }
}