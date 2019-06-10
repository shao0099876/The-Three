package ui;

import javax.swing.JPanel;

import route.ui.Route_Level2NaviPanel;
import car.ui.Car_Level2NaviPanel;
import driver.ui.Driver_Level2NaviPanel;

public class Level2NaviPanel extends JPanel {
	public Level2NaviPanel(){
		super();
		this.setOpaque(false);
	}
	public void change(int num) {
		switch(num) {
		case 0:Car_Level2NaviPanel.car(this);break;//车队管理界面显示详情
		case 1:Route_Level2NaviPanel.route(this);break;//路线管理显示详情
		case 2:break;//货物管理
		case 3:Driver_Level2NaviPanel.driver(this);break;//驾驶员管理
		}
	}
}
