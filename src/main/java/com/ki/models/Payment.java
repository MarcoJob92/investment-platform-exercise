package com.ki.models;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.ki.Config;
import com.ki.services.PropertyHandler;

public @Data
class Payment {

    private int customerId;
    private LocalDate date;
    private int amount;
    private BigDecimal fee;
    private BigDecimal amountAfterFee;
    private String paymentType;
    private int cardId;
    private int bankAccountId;
    private String status;
    
    public Payment() {}

    public Payment(String[] data, String paymentType) {
    	PropertyHandler props = PropertyHandler.getInstance();
    	setCustomerId(data, props);
    	setDate(data, props);
    	setAmount(data, props);
    	setFee(data, props);
        this.setPaymentType(paymentType);
        setPaymentTypeAndAccountDetails(data, props);
    }
    
	private void setCustomerId(String[] data, PropertyHandler props) {
    	String customerId = data[props.getValue("customer_id_col")];
    	this.setCustomerId(Integer.parseInt(customerId));
	}
	
    private void setDate(String[] data, PropertyHandler props) {
    	String date = data[props.getValue("date_col")];
    	this.setDate(LocalDate.parse(date));
	}
    
    private void setAmount(String[] data, PropertyHandler props) {
    	int totalAmount = Integer.parseInt(data[props.getValue("amount_col")]);
    	this.setAmount(totalAmount);
    }
    
    // set: fee and amountAfterFee
    private void setFee(String[] data, PropertyHandler props) {
    	BigDecimal paymentFeeRate = Config.getPaymentFeeRate();
    	BigDecimal amount = new BigDecimal(this.amount);
    	this.setFee(paymentFeeRate.multiply(amount));
    	this.setAmountAfterFee(amount.subtract(this.fee));
	}
    
	private void setPaymentTypeAndAccountDetails(String[] data, PropertyHandler props) {
		if (paymentType.equals("card")) {
			String cardId = data[props.getValue("card_id_col")];
        	this.setCardId(Integer.parseInt(cardId));
        	String status = data[props.getValue("card_status_col")];
        	this.setStatus(status);
        } else {
        	String bankAccountId = data[props.getValue("bank_account_id_col")];
        	this.setBankAccountId(Integer.parseInt(bankAccountId));
        	this.setStatus("processed");
        }
	}

	public boolean isSuccessful() {
        return getStatus().equals("processed");
    }

}
