package entity;

public class Driver {
	private int peopleNumber;
	private String driverNumber;
	private String peopleName;
	private int peopleAge;
	private int driverAge;
	private String phoNumber;
	private int peopleState;
	public Driver(){
		
	}
	public Driver(int p1,String p2,String p3,int p4,int p5,String p6,int p7){
		peopleNumber=p1;
		driverNumber=p2;
		peopleName=p3;
		peopleAge=p4;
		driverAge=p5;
		phoNumber=p6;
		peopleState=p7;
	}
	public String[] toStringArray() {
		// TODO Auto-generated method stub
		String[] res=new String[7];
		res[0]=Integer.toString(peopleNumber);
		res[1]=driverNumber;
		res[2]=peopleName;
		res[3]=Integer.toString(peopleAge);
		res[4]=Integer.toString(driverAge);
		res[5]=phoNumber;
		res[6]=Integer.toString(peopleState);
		return res;
	}
	
}
