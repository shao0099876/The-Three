package route.db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import code.IO;
import entity.Route;

public class Route_Database {
	private static String addr="127.0.0.1";//"cal.srcserver.xyz";
	public static Route getRouteInfo(int n){//查询路线信息
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "3");//查询车辆信息具体信息中的路线的信息
			IO.write(output, Integer.toString(n));//写入路线的编号
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");
			Route[] res=new Route[data.length/4];
			for(int i=0;i<data.length;i+=4) {
				res[i/4]=new Route(Integer.valueOf(data[i+0]),data[i+1],data[i+2],data[i+3]);
			}
			output.close();
			input.close();
			socket.close();
			return res[0];
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
