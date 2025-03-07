package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String paymentId = UUID.randomUUID().toString();

        Payment payment = new Payment(paymentId, order, method, paymentData);

        if ("VOUCHER".equals(method)) {
            validateVoucherPayment(payment);
        } else if ("BANK_TRANSFER".equals(method)) {
            validateBankTransferPayment(payment);
        }

        return paymentRepository.save(payment);
    }

    private void validateVoucherPayment(Payment payment) {
        String voucherCode = payment.getPaymentData().get("voucherCode");

        if (voucherCode == null || voucherCode.length() != 16) {
            payment.setStatus("REJECTED");
            return;
        }

        if (!voucherCode.startsWith("ESHOP")) {
            payment.setStatus("REJECTED");
            return;
        }

        int digitCount = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        if (digitCount != 8) {
            payment.setStatus("REJECTED");
            return;
        }


        payment.setStatus("SUCCESS");
    }

    private void validateBankTransferPayment(Payment payment) {
        Map<String, String> paymentData = payment.getPaymentData();
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty() ||
                referenceCode == null || referenceCode.isEmpty()) {
            payment.setStatus("REJECTED");
            return;
        }

        payment.setStatus("SUCCESS");
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment existingPayment = paymentRepository.findById(payment.getId());
        if (existingPayment == null) {
            throw new NoSuchElementException("Payment not found with ID: " + payment.getId());
        }

        existingPayment.setStatus(status);

        if ("SUCCESS".equals(status)) {
            orderService.updateStatus(existingPayment.getOrder().getId(), "SUCCESS");
        } else if ("REJECTED".equals(status)) {
            orderService.updateStatus(existingPayment.getOrder().getId(), "FAILED");
        }

        return paymentRepository.save(existingPayment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}