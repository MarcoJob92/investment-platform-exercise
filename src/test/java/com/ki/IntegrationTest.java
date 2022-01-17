package com.ki;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class IntegrationTest  {

    @Test
    public void testSimulatePlatform_CardPayment() {
        String fixturePath = Fixture.getPath("card_payments_mixed.csv");

        String expected = (
            "\"customer_id\",\"shares\"\n" +
            "\"123\",\"735\"\n" +
            "\"456\",\"3430\"\n"
        );

        String result = LocalRunner.simulatePlatform(fixturePath, "card", new BigDecimal("1.2"));

        assertEquals(expected, result);
    }
    
    @Test
    public void testSimulatePlatform_CardPayment_BiggerFile() {
    	String fixturePath = Fixture.getPath("card_payments_mixed_2.csv");
    	
        String expected = (
            "\"customer_id\",\"shares\"\n" +
            "\"123\",\"9947\"\n" +
            "\"456\",\"3430\"\n" +
            "\"1\",\"2436\"\n" +
            "\"3\",\"326\"\n"
        );

        String result = LocalRunner.simulatePlatform(fixturePath, "card", new BigDecimal("1.2"));

        assertEquals(expected, result);
    }
    

    @Test
    public void testSimulatePlatform_BankPayment() {
    	String fixturePath = Fixture.getPath("bank_payments_mixed.csv");
    	
        String expected = (
            "\"customer_id\",\"shares\"\n" +
            "\"789\",\"735\"\n" +
            "\"345\",\"735\"\n"
        );

        String result = LocalRunner.simulatePlatform(fixturePath, "bank", new BigDecimal("1.2"));

        assertEquals(expected, result);
    }
    
    @Test
    public void testSimulatePlatform_BankPayment_BiggerFile() {
    	String fixturePath = Fixture.getPath("bank_payments_mixed_2.csv");
    	
        String expected = (
            "\"customer_id\",\"shares\"\n" +
            "\"789\",\"735\"\n" +
            "\"345\",\"735\"\n" +
            "\"990\",\"2371\"\n"
        );

        String result = LocalRunner.simulatePlatform(fixturePath, "bank", new BigDecimal("1.2"));

        assertEquals(expected, result);
    }
    
    @Test
    public void testSimulatePlatform_Empty() {
    	String fixturePath = Fixture.getPath("card_payments_empty.csv");
    	
        String expected = ("\"customer_id\",\"shares\"\n");

        String result = LocalRunner.simulatePlatform(fixturePath, "card", new BigDecimal("1.2"));

        assertEquals(expected, result);
    }
    
}
