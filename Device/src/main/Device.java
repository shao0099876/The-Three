package main;

public class Device {
	private static int amount=1;					//机器数量
	private static Thread[] t=new Thread[amount];	//机器线程组
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<amount;i++) {
			t[i]=new Thread(new DeviceTask());
			t[i].start();
		}
	}

}
