package de.creatision.weather.domainvalue;

public enum Degree {
	
	CELSIUS("\u2103"), FAHRENHEIT("\u2109");
	
    Degree(String code) {
    	this.code = code;
    }
    
    private String code;
    
    public String getCode() {
    	return code;
    }
}
