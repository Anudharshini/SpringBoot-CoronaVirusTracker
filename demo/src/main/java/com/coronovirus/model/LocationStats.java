package com.coronovirus.model;

public class LocationStats {

	private String state;
	private String country;
	private int latestTotalReports;
	private int diffFromPreviousDay;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalReports() {
		return latestTotalReports;
	}
	public void setLatestTotalReports(int latestTotalReports) {
		this.latestTotalReports = latestTotalReports;
	}
	
	public int getDiffFromPreviousDay() {
		return diffFromPreviousDay;
	}
	public void setDiffFromPreviousDay(int diffFromPreviousDay) {
		this.diffFromPreviousDay = diffFromPreviousDay;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latestTotalReports=" + latestTotalReports
				+ ", diffFromPreviousDay=" + diffFromPreviousDay + "]";
	}
	
	
}
