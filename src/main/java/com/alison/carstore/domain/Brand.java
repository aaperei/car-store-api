package com.alison.carstore.domain;

/**
 * {@link Brand} is the enumeration which lists all possible car brands 
 * 
 * @author Alison Augusto Miranda Pereira
 *
 */

public enum Brand {
	CHEVROLET("CHEVROLET"), 
	FIAT("FIAT"), 
	FORD("FORD"), 
	HYUNDAI("HYUNDAI"), 
	JEEP("JEEP"),
	RENAULT("RENAULT"),
	TOYOTA("TOYOTA"), 
	VOLKSWAGEN("VOLKSWAGEN"); 

	private String displayName;

	Brand(final String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return this.displayName;
	}
}
