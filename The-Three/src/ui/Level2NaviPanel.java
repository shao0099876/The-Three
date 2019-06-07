package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import route.ui.Route_Level2NaviPanel;
import car.ui.Car_Level2NaviPanel;

public class Level2NaviPanel extends JPanel {
	public Level2NaviPanel(){
		super();
		this.setOpaque(false);
	}
	public void change(int num) {
		switch(num) {
		case 0:Car_Level2NaviPanel.car(this);break;//车队管理界面显示详情
		case 1:Route_Level2NaviPanel.route(this);break;//路线管理显示详情
		case 2:break;
		}
	}
}
