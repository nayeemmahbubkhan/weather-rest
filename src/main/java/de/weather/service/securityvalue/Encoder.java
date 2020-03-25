package de.weather.service.securityvalue;

public enum Encoder {

	BCRYPT(10);

	private Encoder(int strength) {
		this.strength = strength;
	}

	private int strength;

	public int getStrength() {
		return this.strength;
	}

}
