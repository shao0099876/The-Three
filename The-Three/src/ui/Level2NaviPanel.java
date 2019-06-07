package ui;

import javax.swing.JPanel;

import car.ui.Car_Level2NaviPanel;

public class Level2NaviPanel extends JPanel {
	public Level2NaviPanel(){
		super();
		this.setOpaque(false);
	}
	public void change(int num) {
		switch(num) {
		case 0:Car_Level2NaviPanel.car(this);break;
		case 1:break;
		case 2:break;
		}
	}
}
