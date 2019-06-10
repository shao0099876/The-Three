package driver.ui;

import javax.swing.JButton;

import client.DebugInfo;

import java.awt.Font;

import ui.BaseUI;
import ui.Level2NaviPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Driver_Level2NaviPanel {
	public static void driver(Level2NaviPanel self) {
		//将内容模板清空
		BaseUI.contentPanel.removeAll();
		BaseUI.contentPanel.revalidate();
		BaseUI.contentPanel.repaint();
		
		self.removeAll();
		
		//添加按钮
		JButton[] button=new JButton[10];//设置按钮
		
		//查看驾驶员信息
		button[0]=new JButton("查看驾驶员信息");
		button[0].setFont(new Font("宋体",Font.PLAIN,14));
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		button[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("二级导航：查看驾驶员信息按钮被按下");
				Thread t=new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						//Route_ContentMessagePanel.setRouteInfo(BaseUI.contentPanel);
					}
					
				});
				t.start();
			}});
		
		//增加驾驶员信息
		button[1]=new JButton("增加驾驶员信息");
		button[1].setFont(new Font("宋体",Font.PLAIN,14));
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		button[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Route_ContentMessagePanel.addRouteInfo(BaseUI.contentPanel);
			}});
		
		//修改驾驶员信息
		button[2]=new JButton("修改驾驶员信息");
		button[2].setFont(new Font("宋体",Font.PLAIN,14));
		button[2].setSize(4, 1);
		button[2].setOpaque(false);
		button[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Route_ContentMessagePanel.modifyRouteInfo(BaseUI.contentPanel);
			}});
		
		//删除驾驶员信息
		button[3]=new JButton("删除驾驶员信息");
		button[3].setFont(new Font("宋体",Font.PLAIN,14));
		button[3].setSize(4, 1);
		button[3].setOpaque(false);
		button[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Route_ContentMessagePanel.delRouteInfo(BaseUI.contentPanel);
			}});
		
		self.add(button[0]);
		self.add(button[1]);
		self.add(button[2]);
		self.add(button[3]);
		
		self.revalidate();
		self.repaint();		
	}
}
