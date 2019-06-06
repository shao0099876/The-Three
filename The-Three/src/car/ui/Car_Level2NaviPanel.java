package car.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.BaseUI;
import ui.Level2NaviPanel;

public class Car_Level2NaviPanel {
	public static void car(Level2NaviPanel self) {
		JButton[] button=new JButton[10];
		
		//查询车辆信息
		button[0]=new JButton("车辆信息");
		button[0].setFont(new Font("宋体",Font.PLAIN,14));
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		System.out.println("check_before");
		button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("二级导航按钮监听");
				Car_ContentMessagePanel.setCarInfo(BaseUI.contentPanel);
			}});
		
		//增加，修改，删除车辆信息
		button[1]=new JButton("车辆维护");
		button[1].setFont(new Font("宋体",Font.PLAIN,14));
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		System.out.println("车辆维护按钮已被按下");//测试使用
		button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("二级导航按钮监听");
				Car_ContentMessagePanel.carInfo_Add_Del(BaseUI.contentPanel);
			}});
		
		self.add(button[0]);
		self.add(button[1]);
		self.revalidate();
		self.repaint();
	}
}
