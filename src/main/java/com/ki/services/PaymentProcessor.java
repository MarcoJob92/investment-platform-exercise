package com.ki.services;

import com.ki.models.Payment;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class PaymentProcessor {

    public Payment[] getPayments(String csvPath, String source) {
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            FileReader file = new FileReader(csvPath);
            CSVReader reader = new CSVReaderBuilder(file).withSkipLines(1).build();
            
            reader.forEach(row -> {
            	Payment payment = new Payment(row, source);
                payments.add(payment);
            });
            
        } catch (IOException e) {
            e.printStackTrace();
            return new Payment[0];
        }

        return payments.toArray(new Payment[]{});
    }

    
    public Payment[] verifyPayments(Payment[] payments) {
    	return Stream.of(payments)
    				 .filter(p -> p.isSuccessful())
    				 .toArray(size -> new Payment[size]);
    }
    
}
