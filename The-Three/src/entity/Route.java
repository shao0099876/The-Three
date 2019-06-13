package entity;

public class Route {
	public int routeNumber;
	public String startAddr;
	public String destAddr;
	public String mAddr;
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
		String[] res=new String[4];
		res[0]=Integer.toString(routeNumber);
		res[1]=startAddr;
		res[2]=destAddr;
		res[3]=mAddr;
		return res;
	}
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(routeNumber);
		sb.append(":");
		sb.append(startAddr+"-");
		sb.append(mAddr+"-");
		sb.append(destAddr);
		return sb.toString();
	}
}
