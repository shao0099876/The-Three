package route.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import car.ui.Car_ContentMessagePanel;
import client.DebugInfo;
import ui.BaseUI;
import ui.Level2NaviPanel;

public class Route_Level2NaviPanel {
	public static void route(Level2NaviPanel self) {
		// TODO Auto-generated method stub
		self.removeAll();
		
		JButton[] button=new JButton[10];//���ð�ť
		
		//�鿴·��
		button[0]=new JButton("�鿴·��");
		button[0].setFont(new Font("����",Font.PLAIN,14));
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		button[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("�����������鿴·�߰�ť������");
				Thread t=new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Route_ContentMessagePanel.setRouteInfo(BaseUI.contentPanel);
					}
					
				});
				t.start();
			}});
		
		//����·��
		button[1]=new JButton("����·��");
		button[1].setFont(new Font("����",Font.PLAIN,14));
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		button[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Route_ContentMessagePanel.addRouteInfo(BaseUI.contentPanel);
			}});
		
		//�޸�·��
		button[2]=new JButton("�޸�·��");
		button[2].setFont(new Font("����",Font.PLAIN,14));
		button[2].setSize(4, 1);
		button[2].setOpaque(false);
		button[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Route_ContentMessagePanel.modifyRouteInfo(BaseUI.contentPanel);
			}});
		
		//ɾ��·��
		button[3]=new JButton("ɾ��·��");
		button[3].setFont(new Font("����",Font.PLAIN,14));
		button[3].setSize(4, 1);
		button[3].setOpaque(false);
		button[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Route_ContentMessagePanel.delRouteInfo(BaseUI.contentPanel);
			}});
		
		self.add(button[0]);
		self.add(button[1]);
		self.add(button[2]);
		self.add(button[3]);
		
		self.revalidate();
		self.repaint();
	}

}
