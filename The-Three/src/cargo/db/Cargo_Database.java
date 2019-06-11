package cargo.db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import code.IO;
import entity.Car;
import entity.Cargo;

public class Cargo_Database {
	private static String addr="cal.srcserver.xyz";
	public static Cargo[] getCargoList() {
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "18");
			String raw_string=IO.read(input);
			
			String[] data=raw_string.split("#");
			Cargo[] res=new Cargo[data.length/4];
			
			for(int i=0;i<data.length;i+=4) {
				res[i/4]=new Cargo(Integer.valueOf(data[i+0]),data[i+1],data[i+2],Integer.valueOf(data[i+3]));
			}
			output.close();
			input.close();
			socket.close();
			return res;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Cargo getSpecifiedCargoList(int cargoNumber) {
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "19");
			IO.write(output, Integer.toString(cargoNumber));
			String raw_string=IO.read(input);
			String[] tmp=raw_string.split("#");
			Cargo res=new Cargo(Integer.valueOf(tmp[0]),tmp[1],tmp[2],Integer.valueOf(tmp[3]));
			output.close();
			input.close();
			socket.close();
			return res;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String addCargo(Cargo cargo) {
		
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "20");
			IO.write(output, cargo.toTransString());
			String raw_string=IO.read(input);
			output.close();
			input.close();
			socket.close();
			return raw_string;
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
