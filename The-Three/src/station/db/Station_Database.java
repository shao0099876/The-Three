package station.db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import code.IO;

public class Station_Database {
	private static String addr="cal.srcserver.xyz";

	public static String getGPS(String stationName) {
		// TODO Auto-generated method stub
		try {
			Socket socket=new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "26");
			IO.write(output, stationName);
			String raw_string=IO.read(input);
			output.close();
			input.close();
			socket.close();
			return raw_string;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
