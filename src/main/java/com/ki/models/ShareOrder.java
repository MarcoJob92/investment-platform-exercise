package com.ki.models;

import lombok.Data;

public @Data
class ShareOrder {
	
    private int customerId;
    private int shares;
    
    public ShareOrder() {}
    
    public ShareOrder(int customerId, int shares) {
		this.customerId = customerId;
		this.shares = shares;
	}
    
}