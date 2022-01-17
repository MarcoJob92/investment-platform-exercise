package com.ki.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertyHandler {
	
	private static PropertyHandler INSTANCE;
	private Properties props = null;
	
	private PropertyHandler() {
		props = new Properties();
		try {
			props.load(new FileInputStream("app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized PropertyHandler getInstance() {
        if(INSTANCE == null)
            INSTANCE = new PropertyHandler();
        return INSTANCE;
    }
	
	public int getValue(String propKey){
		return Integer.parseInt(props.getProperty(propKey));
	}

}
