package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepo;

    @Mock
    OrderService orderService;

    private Order sampleOrder;
    private Map<String, String> voucherPaymentData;
    private Map<String, String> transferPaymentData;
    private static final String PAYMENT_ID = "pay-12345";

    @BeforeEach
    void setupTest() {
        List<Product> productList = new ArrayList<>();
        Product sampleProduct = new Product();
        sampleProduct.setProductId("prod-98765");
        sampleProduct.setProductName("Sabun Mandi Cap Gajah");
        sampleProduct.setProductQuantity(3);
        productList.add(sampleProduct);

        sampleOrder = new Order("ord-54321", productList, 1711234567L, "Budi Santoso");

        voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP2024ABCD1234");

        transferPaymentData = new HashMap<>();
        transferPaymentData.put("bankName", "Bank Mandiri");
        transferPaymentData.put("referenceCode", "TRX987654321");
    }

    @Test
    void whenAddingPaymentWithValidVoucher_thenSuccessStatusIsSet() {
         
        when(paymentRepo.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

         
        Payment result = paymentService.addPayment(sampleOrder, "VOUCHER", voucherPaymentData);

         
        assertNotNull(result);
        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepo).save(any(Payment.class));
    }

    @Test
    void whenAddingPaymentWithInvalidVoucher_thenRejectedStatusIsSet() {
         
        voucherPaymentData.put("voucherCode", "WRONG123");
        when(paymentRepo.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

         
        Payment result = paymentService.addPayment(sampleOrder, "VOUCHER", voucherPaymentData);

         
        assertEquals("REJECTED", result.getStatus());
        verify(paymentRepo).save(any(Payment.class));
    }

    @Test
    void whenAddingValidBankTransfer_thenPaymentIsSuccessful() {
         
        when(paymentRepo.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

         
        Payment result = paymentService.addPayment(sampleOrder, "BANK_TRANSFER", transferPaymentData);

         
        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepo).save(any(Payment.class));
    }

    @Test
    void whenBankTransferMissingInfo_thenPaymentIsRejected() {
         
        transferPaymentData.remove("referenceCode");
        when(paymentRepo.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

         
        Payment result = paymentService.addPayment(sampleOrder, "BANK_TRANSFER", transferPaymentData);

         
        assertEquals("REJECTED", result.getStatus());
        verify(paymentRepo).save(any(Payment.class));
    }

    @Test
    void whenSettingStatusToSuccess_thenOrderStatusIsAlsoUpdated() {
         
        Payment testPayment = new Payment(PAYMENT_ID, sampleOrder, "VOUCHER", voucherPaymentData, "WAITING");
        when(paymentRepo.findById(PAYMENT_ID)).thenReturn(Optional.of(testPayment));
        when(paymentRepo.save(any(Payment.class))).thenReturn(testPayment);
        when(orderService.updateStatus(any(), eq("SUCCESS"))).thenReturn(sampleOrder);

         
        Payment result = paymentService.setStatus(testPayment, "SUCCESS");

         
        assertEquals("SUCCESS", result.getStatus());
        verify(orderService).updateStatus(sampleOrder.getId(), "SUCCESS");
    }

    @Test
    void whenSettingStatusToRejected_thenOrderStatusIsSetToFailed() {
         
        Payment testPayment = new Payment(PAYMENT_ID, sampleOrder, "BANK_TRANSFER", transferPaymentData, "WAITING");
        when(paymentRepo.findById("invalid-id")).thenReturn(Optional.empty());
        when(paymentRepo.save(any(Payment.class))).thenReturn(testPayment);
        when(orderService.updateStatus(any(), eq("FAILED"))).thenReturn(sampleOrder);

         
        Payment result = paymentService.setStatus(testPayment, "REJECTED");

         
        assertEquals("REJECTED", result.getStatus());
        verify(orderService).updateStatus(sampleOrder.getId(), "FAILED");
    }

    @Test
    void whenSettingStatusForNonExistentPayment_thenExceptionIsThrown() {
         
        Payment nonExistentPayment = new Payment("invalid-id", sampleOrder, "VOUCHER", voucherPaymentData);
        when(paymentRepo.findById("invalid-id")).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> 
            paymentService.setStatus(nonExistentPayment, "SUCCESS")
        );
        assertEquals("Payment not found with ID: invalid-id", exception.getMessage());
        assertEquals("Payment not found with ID: invalid-id", exception.getMessage());
        
        verify(paymentRepo, never()).save(any(Payment.class));
        verify(orderService, never()).updateStatus(any(), any());
    }

    @Test
    void whenGettingExistingPayment_thenCorrectPaymentIsReturned() {
         
        Payment expectedPayment = new Payment(PAYMENT_ID, sampleOrder, "VOUCHER", voucherPaymentData);
        when(paymentRepo.findById(PAYMENT_ID)).thenReturn(Optional.of(expectedPayment));

         
        Payment result = paymentService.getPayment(PAYMENT_ID);

         
        assertNotNull(result);
        assertEquals(PAYMENT_ID, result.getId());
        verify(paymentRepo).findById(PAYMENT_ID);
    }

    @Test
    void whenGettingNonExistentPayment_thenNullIsReturned() {
         
        when(paymentRepo.findById("unknown-id")).thenReturn(Optional.empty());

         
        Payment result = paymentService.getPayment("unknown-id");

         
        assertNull(result);
        verify(paymentRepo).findById("unknown-id");
    }

    @Test
    void whenGettingAllPayments_thenAllPaymentsAreReturned() {
         
        List<Payment> expectedPayments = Arrays.asList(
            new Payment("pay-1", sampleOrder, "VOUCHER", voucherPaymentData),
            new Payment("pay-2", sampleOrder, "BANK_TRANSFER", transferPaymentData)
        );
        when(paymentRepo.findAll()).thenReturn(expectedPayments);

         
        List<Payment> result = paymentService.getAllPayments();

        assertEquals(2, result.size());
        assertEquals(expectedPayments, result);
        verify(paymentRepo).findAll();
    }
}