package browser;

import car.db.Car_Database;

public class BrowserUI {

	public static void drawAllCars() {
		// TODO Auto-generated method stub
		BrowserDialog dialog=new BrowserDialog();
		String[] gps=Car_Database.getCarGPS();
		for(String i:gps) {
			dialog.Add_Cars_Point(i);
		}
		dialog.DrawPoints();
		dialog.setSize(2000,1000);
		dialog.setVisible(true);
	}

	public static void FocusOn(String selectedValue, BrowserDialog self) {
		// TODO Auto-generated method stub
		String gps=Car_Database.getSpecifiedCarGPS(selectedValue);
		self.focuson(gps);
		
		String[] data=Car_Database.getLatestDeviceRecord(selectedValue);
		self.carNumberText.setText(data[0]);
		
		self.driverText.setText(data[1]);
		
		self.subdriverText.setText(data[4]);
		
		self.carStatusText.setText(data[3]);
		
		self.fuelText.setText(data[2]);
		self.revalidate();
		self.repaint();
	}

}
