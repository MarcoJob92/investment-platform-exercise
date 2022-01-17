package com.ki.models;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class PaymentTest {

    @Test
    public void testInitiatingPayment() {
        int CUSTOMER_ID = 123;
        String AMOUNT = "2000";
        String CARD_STATUS = "processed";
        int CARD_ID = 45;
        String DATE = "2019-02-01";
        String PAYMENT_TYPE = "card";

        String data[] = new String[] {
                String.valueOf(CUSTOMER_ID),
                DATE,
                AMOUNT,
                String.valueOf(CARD_ID),
                CARD_STATUS,
        };
        
        Payment payment = new Payment(data, PAYMENT_TYPE);

        assertEquals(CUSTOMER_ID, payment.getCustomerId());
        assertEquals(2000, payment.getAmount());
        assertEquals(new BigDecimal(40).compareTo(payment.getFee()), 0);
        assertEquals(new BigDecimal(1960).compareTo(payment.getAmountAfterFee()), 0);
        assertEquals(LocalDate.of(2019, 2, 1), payment.getDate());
        assertEquals(PAYMENT_TYPE, payment.getPaymentType());
        assertEquals(CARD_ID, payment.getCardId());
        assertEquals(CARD_STATUS, payment.getStatus());
    }

    @Test
    public void testIsSuccessfulProcessed() {
        Payment payment = new Payment();
        payment.setStatus("processed");
        assertTrue(payment.isSuccessful());
    }

    @Test
    public void testPaymentDeclined() {
        Payment payment = new Payment();
        payment.setStatus("declined");
        assertFalse(payment.isSuccessful());
    }

    @Test
    public void testPaymentErrored() {
        Payment payment = new Payment();
        payment.setStatus("error");
        assertFalse(payment.isSuccessful());
    }
}
