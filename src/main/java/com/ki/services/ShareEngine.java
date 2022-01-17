package com.ki.services;

import com.ki.models.Payment;
import com.ki.models.ShareOrder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ShareEngine {
	
    public ShareOrder[] generateShareOrders(BigDecimal sharePrice, Payment[] payments) {
        Map<Integer,BigDecimal> map = groupToMap(payments);

        List<ShareOrder> list = new ArrayList<>();
        for (Entry<Integer,BigDecimal> entry : map.entrySet()) {
        	int customerId = entry.getKey();
        	BigDecimal amount = entry.getValue();
        	int shares = amount.divide(sharePrice, 2, RoundingMode.HALF_UP).intValue();
    		ShareOrder shareOrder = new ShareOrder(customerId, shares);
    		list.add(shareOrder);
        }
        
        return list.toArray(new ShareOrder[list.size()]);
    }
    
    // return a Map<customerId,amount>
    private Map<Integer,BigDecimal> groupToMap(Payment[] payments) {
    	Map<Integer,BigDecimal> map = new LinkedHashMap<>();
    	
    	for (Payment payment : payments) {
    		int customerId = payment.getCustomerId();
    		BigDecimal amount = payment.getAmountAfterFee();
    		
    		if (!map.containsKey(customerId)) {
        		map.put(customerId, amount);
    		} else {
        		BigDecimal paymentAmount = map.get(customerId);
        		map.put(customerId, paymentAmount.add(amount));
    		}
    	}

    	return map;
    }
    
}
