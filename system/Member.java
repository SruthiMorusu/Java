package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 			13.03.2014
 * 
 * @author Sruthi 
 * Since 13.03.2014
 * 
 * Modified By Sruthi*/

public class Member extends Customer {
	
	private int point;
	private String name;

	public Member(String name, String memberID, int point) {
		super(memberID);
		this.name = name;
		this.point = point;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPoint(int point){
		this.point = point;
	}
	
	public int getPoint() {
		return point;
	}

	public String toString() {
		return (getName() + "," + getId() + "," + getPoint());
	}
	
	public void updatePoint(int points) {
		if(point == -1) {
			point = 0;
		}
		this.point += points;
	}
}