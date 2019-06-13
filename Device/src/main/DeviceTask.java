package main;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class DeviceTask implements Runnable{
	private String carNumber;
	private double x;
	private double y;
	private double fuel;
	private int driver;
	private int status;
	private int T;
	private int subdriver;
	private int test_cnt=0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		while(true) {
			StringBuilder sb=new StringBuilder();
			sb.append(carNumber);
			sb.append("#");
			sb.append(x);
			sb.append(",");
			sb.append(y);
			sb.append("#");
			sb.append(fuel);
			sb.append("#");
			sb.append(driver);
			sb.append("#");
			sb.append(status);
			sb.append("#");
			sb.append(subdriver);
			try {
				Socket socket=new Socket("cal.srcserver.xyz",8082);
				DataOutputStream output=new DataOutputStream(socket.getOutputStream());
				write(output,sb.toString());
				output.close();
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			test_cnt+=1;
			change();
			try {
				Thread.sleep(T);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void init() {
		carNumber="³E8K039";
		x=116.404;
		y=39.915;
		fuel=100;
		driver=1;
		status=1;
		subdriver=2;
		T=1000;
	}
	private void change() {
		x+=0.01;
		y+=0.01;
		fuel-=0.001;
	}
	public static void write(DataOutputStream output,String s) {
		BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(output));
		try {
			writer.write(s+"\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

}
