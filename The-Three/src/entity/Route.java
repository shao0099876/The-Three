package entity;

public class Route {
	public int routeNumber;
	private String startAddr;
	private String destAddr;
	private String mAddr;
	public Route(){
		
	}
	public Route(int p1,String p2,String p3,String p4){
		routeNumber=p1;
		startAddr=p2;
		destAddr=p3;
		mAddr=p4;
	}
	public String[] toStringArray() {
		// TODO Auto-generated method stub
		String[] res=new String[3];
		res[0]=Integer.toString(routeNumber);
		res[1]=startAddr;
		res[2]=destAddr;
		return res;
	}
}
