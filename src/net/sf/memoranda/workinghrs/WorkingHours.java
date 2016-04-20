package net.sf.memoranda.workinghrs;

import net.sf.memoranda.util.Util;

public class WorkingHours {
	
	private int mon;
	private int tue;
	private int wed;
	private int thu;
	private int fri;
	private int sat;
	private int sun;
	
	public WorkingHours(){
		mon = 0;
		tue = 0;
		wed = 0;
		thu = 0;
		fri = 0;
		sat = 0;
		sun = 0;
	}
	
	public WorkingHours(int mon, int tue, int wed, int thu, int fri, int sat, int sun){
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
		this.sat = sat;
		this.sun = sun;
	}
	
	public WorkingHours(String hrs){
		 int[] h = Util.parseWorkingHrsStamp(hrs);
		 mon = h[0];
		 tue = h[1];
		 wed = h[2];
		 thu = h[3];
		 fri = h[4];
		 sat = h[5];
		 sun = h[6];
	}
	
	public String toString(){
		StringBuffer s = new StringBuffer();
		String delimiter = " ";
		s.append(delimiter);
		s.append(Integer.toString(mon));
		s.append(delimiter);
		s.append(Integer.toString(tue));
		s.append(delimiter);
		s.append(Integer.toString(wed));
		s.append(delimiter);
		s.append(Integer.toString(thu));
		s.append(delimiter);
		s.append(Integer.toString(fri));
		s.append(delimiter);
		s.append(Integer.toString(sat));
		s.append(delimiter);
		s.append(Integer.toString(sun));
		s.append(delimiter);
		return s.toString();
	}
	
	//Getters
	public int getMondayHours(){
		return mon;
	}
	public int getTuesdayHours(){
		return tue;
	}
	public int getWednesdayHours(){
		return wed;
	}
	public int getThursdayHours(){
		return thu;
	}
	public int getFridayHours(){
		return fri;
	}
	public int getSaturdayHours(){
		return sat;
	}
	public int getSundayHours(){
		return sun;
	}
	
	//Setters
	public void setMondayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24){
			this.mon = hours;
		}
		else {
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
		}
	}
	public void setTuesdayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24){
			this.tue = hours;
		}
		else {
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
		}
	}
	public void setWednesdayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24){
			this.wed = hours;
		}
		else {
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
		}
	}
	public void setThursdayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24){
			this.thu = hours;
		}
		else {
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
		}
	}
	public void setFridayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24){
			this.fri = hours;
		}
		else {
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
		}
	}
	public void setSaturdayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24){
			this.sat = hours;
		}
		else {
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
		}
	}
	public void setSundayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24){
			this.sun = hours;
		}
		else {
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
		}
	}
	
	public int getWeeklyHourTotal(){
		return mon + tue + wed + thu + fri + sat + sun;
	}

}
