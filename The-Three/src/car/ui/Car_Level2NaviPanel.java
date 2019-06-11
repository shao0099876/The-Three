package car.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import browser.BrowserDialog;
import car.db.Car_Database;
import client.DebugInfo;
import ui.BaseUI;
import ui.ContentMessagePanel;
import ui.Level2NaviPanel;

public class Car_Level2NaviPanel {
	public static void car(Level2NaviPanel self) {
		//������ģ�����
		BaseUI.contentPanel.removeAll();
		BaseUI.contentPanel.revalidate();
		BaseUI.contentPanel.repaint();
		
		DebugInfo.DebugInfo("������������������Ϊ��������Panel");
		self.removeAll();
		JButton[] button=new JButton[10];
		
		//��ѯ������Ϣ
		button[0]=new JButton("������Ϣ");
		button[0].setFont(new Font("����",Font.PLAIN,14));
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("����������������Ϣ��ť������");
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Car_ContentMessagePanel.setCarInfo(BaseUI.contentPanel);
					}
					
				});
				t.start();
			}});
		
		//���ӣ��޸ģ�ɾ��������Ϣ
		button[1]=new JButton("����ά��");
		button[1].setFont(new Font("����",Font.PLAIN,14));
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("��������������ά����ť������");
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Car_ContentMessagePanel.carInfo_Add_Del(BaseUI.contentPanel);
					}
					
				});
				t.start();
			}});
		
		button[2]=new JButton("����GPS");
		button[2].setFont(new Font("����",Font.PLAIN,14));
		button[2].setSize(4, 1);
		button[2].setOpaque(false);
		button[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String raw_string=Car_Database.getCarGPS();
				DebugInfo.DebugInfo(raw_string);
				String[] res=raw_string.split("#");
				BrowserDialog map=new BrowserDialog();
				map.clean();
				map.Add_Cars_Point(res);
				map.DrawPoints();
				map.ShowGUI(Car_Database.getAllCarNumber().split("#"));
			}});
		
		self.add(button[0]);
		self.add(button[1]);
		self.add(button[2]);
		
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("������������������Panel�ػ����");
	}
}
