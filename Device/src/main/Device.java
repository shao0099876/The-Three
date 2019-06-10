package main;

public class Device {
	private static int amount=1;					
	private static Thread[] t=new Thread[amount];	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<amount;i++) {
			t[i]=new Thread(new DeviceTask());
			t[i].start();
		}
	}

}
