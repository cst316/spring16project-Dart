package net.sf.memoranda.workinghrs;

public class WorkingHours {
	
	private int mon;
	private int tue;
	private int wed;
	private int thu;
	private int fri;
	private int sat;
	private int sun;
	
	public WorkingHours(int mon, int tue, int wed, int thu, int fri, int sat, int sun){
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
		this.sat = sat;
		this.sun = sun;
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
		if(hours >= 0  && hours <= 24)
			this.mon = hours;
		else
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
	}
	public void setTuesdayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24)
			this.tue = hours;
		else
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
	}
	public void setWednesdayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24)
			this.wed = hours;
		else
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
	}
	public void setThursdayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24)
			this.thu = hours;
		else
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
	}
	public void setFridayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24)
			this.fri = hours;
		else
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
	}
	public void setSaturdayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24)
			this.sat = hours;
		else
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
	}
	public void setSundayHours(int hours) throws IllegalArgumentException {
		if(hours >= 0  && hours <= 24)
			this.sun = hours;
		else
			throw new IllegalArgumentException("ERROR: Hours must be from 0 to 24");
	}
	
	public int getWeeklyHourTotal(){
		return mon + tue + wed + thu + fri + sat + sun;
	}

}
