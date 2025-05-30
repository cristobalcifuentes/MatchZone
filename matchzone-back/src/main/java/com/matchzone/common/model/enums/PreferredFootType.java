package com.matchzone.common.model.enums;

/**
 * Enum que indica la pierna hábil de un jugador, con código y descripción.
 */
public enum PreferredFootType {
	RIGHT(1, "derecha"),
	LEFT(2, "izquierda"),
	BOTH(3, "ambas");

	private final int code;
	private final String description;

	PreferredFootType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}