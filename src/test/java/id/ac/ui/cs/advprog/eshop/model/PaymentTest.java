package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentTest {

    private Map<String, String> paymentData;
    private Payment payment;
    
    @BeforeEach
    void setUp() {

        private String paymentId;
        private String paymentMethod;
        private String paymentStatus;
        paymentId = "payment-123";
        paymentMethod = "Credit Card";
        paymentStatus = "PENDING";
        
        this.paymentData = new HashMap<>();
        
        payment = new Payment();
        payment.setId(paymentId);
        payment.setMethod(paymentMethod);
        payment.setStatus(paymentStatus);
        payment.setPaymentData(paymentData);
    }

    @Test
    void testGetId() {
        assertEquals(paymentId, payment.getId());
    }

    @Test
    void testSetId() {
        String newId = "payment-456";
        payment.setId(newId);
        assertEquals(newId, payment.getId());
    }

    @Test
    void testGetMethod() {
        assertEquals(paymentMethod, payment.getMethod());
    }
    
    @Test
    void testCreatePaymentValidMethod() {
        Payment newPayment = new Payment();
        newPayment.setMethod("Bank Transfer");
        assertEquals("Bank Transfer", newPayment.getMethod());
    }
    
    @Test
    void testCreatePaymentInvalidMethod() {
        Payment newPayment = new Payment();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            newPayment.setMethod("Invalid Payment Method");
        });
        assertTrue(exception.getMessage().contains("Invalid payment method"));
    }

    @Test
    void testGetStatus() {
        assertEquals(paymentStatus, payment.getStatus());
    }

    
    @Test
    void testSetInvalidStatus() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });
        assertTrue(exception.getMessage().contains("Invalid payment status"));
    }

    @Test
    void testGetPaymentData() {
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testSetPaymentData() {
        Map<String, String> newPaymentData = new HashMap<>();
        newPaymentData.put("accountNumber", "1234567890");
        newPaymentData.put("bankName", "Sample Bank");
        
        payment.setPaymentData(newPaymentData);
        assertEquals(newPaymentData, payment.getPaymentData());
    }
    
    @Test
    void testPaymentMethodList() {
        List<String> validMethods = payment.getValidPaymentMethods();
        assertNotNull(validMethods);
        assertTrue(validMethods.contains("Credit Card"));
        assertTrue(validMethods.contains("Bank Transfer"));
    }
    
    @Test
    void testPaymentStatusList() {
        List<String> validStatuses = payment.getValidPaymentStatuses();
        assertNotNull(validStatuses);
        assertTrue(validStatuses.contains("SUCCESS"));
        assertTrue(validStatuses.contains("REJECTED"));
    }

    @Test
    void testEquals() {
        Payment samePayment = new Payment();
        samePayment.setId(paymentId);
        assertEquals(payment, samePayment);
        
        Payment differentPayment = new Payment();
        differentPayment.setId("different-id");
        assertNotEquals(payment, differentPayment);
    }

    @Test
    void testHashCode() {
        Payment samePayment = new Payment();
        samePayment.setId(paymentId);
        assertEquals(payment.hashCode(), samePayment.hashCode());
    }
}