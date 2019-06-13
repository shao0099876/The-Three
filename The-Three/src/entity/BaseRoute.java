package entity;

public class BaseRoute {
	public int routeNumber;
	private String startAddr;
	private String destAddr;
	public BaseRoute(){
		
	}
	public BaseRoute(int p1,String p2,String p3){
		routeNumber=p1;
		startAddr=p2;
		destAddr=p3;
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
