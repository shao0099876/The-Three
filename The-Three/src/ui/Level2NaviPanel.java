package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Level2NaviPanel extends JPanel {
	public Level2NaviPanel(){
		super();
		this.setOpaque(false);
	}
	public void change(int num) {
		switch(num) {
		case 0:car();break;
		case 1:break;
		case 2:break;
		}
	}
	private void car() {
		//car info
		JButton[] button=new JButton[10];
		button[0]=new JButton("车辆信息");
		button[0].setFont(new Font("宋体",Font.PLAIN,14));
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BaseUI.contentPanel.setCarInfo();
			}});
		
		this.add(button[0]);
		this.revalidate();
		this.repaint();
	}
}
