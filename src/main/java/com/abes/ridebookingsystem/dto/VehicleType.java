package com.abes.ridebookingsystem.dto;

public enum VehicleType {
	BIKE(WheelerType.TWO_WHEELER, 20, 5), SCOOTY(WheelerType.TWO_WHEELER, 18, 4),
	AUTO(WheelerType.THREE_WHEELER, 30, 8), SEDAN(WheelerType.FOUR_WHEELER, 50, 10),
	PREMIUM(WheelerType.FOUR_WHEELER, 70, 15), CABXL(WheelerType.FOUR_WHEELER, 90, 18);

	private final WheelerType wheelerType;
	private final int baseFare;
	private final int perKmRate;

	VehicleType(WheelerType wheelerType, int baseFare, int perKmRate) {
		this.wheelerType = wheelerType;
		this.baseFare = baseFare;
		this.perKmRate = perKmRate;
	}

	public WheelerType getWheelerType() {
		return wheelerType;
	}

	public int getBaseFare() {
		return baseFare;
	}

	public int getPerKmRate() {
		return perKmRate;
	}

	@Override
	public String toString() {
		return name() + " (" + wheelerType + ") - Base: ₹" + baseFare + ", ₹" + perKmRate + "/km";
	}
}
