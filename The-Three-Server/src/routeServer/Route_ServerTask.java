package routeServer;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.ServerTask;

public class Route_ServerTask extends ServerTask{
	public Route_ServerTask(Socket p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public static String getRouteInfo(int routnum) {//��ȡĳ��·����Ϣ
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Route where routeNumber=?");
			pstmt.setInt(1,routnum);
			ResultSet res=pstmt.executeQuery();
			
			boolean flag=true;
			while(res.next()){
				if(!flag){
					sb.append("#");
				}
				flag=false;
				sb.append(res.getInt(1));
				sb.append("#");
				sb.append(res.getString(2));
				sb.append("#");
				sb.append(res.getString(3));
				sb.append("#");
				sb.append(res.getString(4));
			}
			
			res.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	public static String getAllRouteInfo() {//��ȡ����·����Ϣ
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Route");//��ѯ����·����Ϣ
			ResultSet res=pstmt.executeQuery();
			
			boolean flag=true;
			while(res.next()){
				if(!flag){
					sb.append("#");
				}
				flag=false;
				sb.append(res.getInt(1));
				sb.append("#");
				sb.append(res.getString(2));
				sb.append("#");
				sb.append(res.getString(3));
				sb.append("#");
				sb.append(res.getString(4));
			}
			
			res.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	public static String getMoHuRouteInfo(String delrouteinfo) {//��ȡĳ��·����Ϣ
		// TODO Auto-generated method stub
		int routnum=Integer.valueOf(delrouteinfo);
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Route where routeNumber like ?");
			pstmt.setInt(1,routnum);
			ResultSet res=pstmt.executeQuery();
			
			boolean flag=true;
			while(res.next()){
				if(!flag){
					sb.append("#");
				}
				flag=false;
				sb.append(res.getInt(1));
				sb.append("#");
				sb.append(res.getString(2));
				sb.append("#");
				sb.append(res.getString(3));
				sb.append("#");
				sb.append(res.getString(4));
			}
			
			res.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	public static String delRouteInfo(String del_routeinfo){//ɾ�������ĳ��·����Ϣ
		System.out.println("��ʼɾ��·����Ϣ");//����
		int deln=Integer.valueOf(del_routeinfo);//��·�߱��ת��ΪINT��
		
		String message="";
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement pstmt=conn.prepareStatement("select routeNumber from Route where routeNumber = ?");
			pstmt.setInt(1,deln);
			ResultSet res=pstmt.executeQuery();
			
			if(res==null){//Ϊ�գ�˵���޽����û�а취����ɾ������
				message="û�и�·����Ϣ��û�а취����ɾ��";
			}
			else{//�ǿգ�����ɾ��
				try {
					PreparedStatement pstmt0 = conn.prepareStatement("delete from Route where routeNumber=?");
					pstmt0.setInt(1,deln);
					pstmt0.executeUpdate();  
					pstmt0.close();
					message="ɾ���ɹ�";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			res.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	public static String addOneRouteInfo(String s){//����һ��·����Ϣ
		System.out.println("��ʼ����·����Ϣ");//����
		String message="";//�������
		
		String[] data=s.split("#");//��ȡ��Ϣ
		
		for(int i=0;i<data.length;i++){
			System.out.println(data[i]);
		}
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//��������ת��
		int newrouteNumber=Integer.valueOf(data[0]);
		System.out.println(newrouteNumber);
		
		String newstartAddr=data[1];
		System.out.println(newstartAddr);
		
		String newdestAddr=data[2];
		System.out.println(newdestAddr);
		
		String newmAddr=data[3];		
		System.out.println(newmAddr);
		
			
		try {
			PreparedStatement pstmt=conn.prepareStatement("select routeNumber from Route where routeNumber = ?");
			pstmt.setInt(1,newrouteNumber);
			ResultSet res=pstmt.executeQuery();
			
			if(res==null){//Ϊ�գ�˵���޽����Ϊ���
				System.out.println("��ʼ������ݿ�");
				PreparedStatement pstmt1=conn.prepareStatement("insert into Route values(?,?,?,?)");
				
				pstmt1.setInt(1,newrouteNumber);
				pstmt1.setString(2,newstartAddr);
				pstmt1.setString(3,newdestAddr);
				pstmt1.setString(4,newmAddr);
				pstmt1.executeUpdate();  
				pstmt1.close();
				message="�����Ϣ�ɹ�";
			}
			else{//�ǿգ��������
				message="����·����Ϣ���������";
			}
			res.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	public static String modOneRouteInfo(String s){//�޸ľ����·����Ϣ
		System.out.println("��ʼ�޸�·����Ϣ");//����
		String message="";//�������
		
		String[] data=s.split("#");//��ȡ��Ϣ
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//��������ת��
		int newrouteNumber=Integer.valueOf(data[0]);
		System.out.println(newrouteNumber);
		
		String newstartAddr=data[1];
		System.out.println(newstartAddr);
		
		String newdestAddr=data[2];
		System.out.println(newdestAddr);
		
		String newmAddr=data[3];		
		System.out.println(newmAddr);
		
			
		try {
			PreparedStatement pstmt=conn.prepareStatement("select routeNumber from Route where routeNumber = ?");
			pstmt.setInt(1,newrouteNumber);
			ResultSet res=pstmt.executeQuery();
			
			if(res==null){//Ϊ�գ�˵���޽�����������
				message="������·����Ϣ�������޸�";
			}
			else{//�ǿգ������޸�
				System.out.println("��ʼ�޸����ݿ�");
				PreparedStatement pstmt1=conn.prepareStatement("update Route set startAddr = ? , destAddr = ? , mAddr = ? where routeNumber = ?)");
				
				pstmt1.setString(1,newstartAddr);
				pstmt1.setString(2,newdestAddr);
				pstmt1.setString(3,newmAddr);
				pstmt1.setInt(4,newrouteNumber);
				pstmt1.executeUpdate();  
				pstmt1.close();
				message="�޸���Ϣ�ɹ�";
			}
			res.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
