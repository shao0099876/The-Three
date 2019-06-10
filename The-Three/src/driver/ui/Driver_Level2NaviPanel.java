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
		//������ģ�����
		BaseUI.contentPanel.removeAll();
		BaseUI.contentPanel.revalidate();
		BaseUI.contentPanel.repaint();
		
		self.removeAll();
		
		//��Ӱ�ť
		JButton[] button=new JButton[10];//���ð�ť
		
		//�鿴��ʻԱ��Ϣ
		button[0]=new JButton("�鿴��ʻԱ��Ϣ");
		button[0].setFont(new Font("����",Font.PLAIN,14));
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		button[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("�����������鿴��ʻԱ��Ϣ��ť������");
				Thread t=new Thread(new Runnable() {		
					@Override
					public void run() {
						Driver_ContentMessagePanel.setDriverInfo(BaseUI.contentPanel);
					}	
				});
				t.start();
			}});
		
		//���Ӽ�ʻԱ��Ϣ
		button[1]=new JButton("���Ӽ�ʻԱ��Ϣ");
		button[1].setFont(new Font("����",Font.PLAIN,14));
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		button[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Driver_ContentMessagePanel.addDriverInfo(BaseUI.contentPanel);
			}});
		
		//�޸ļ�ʻԱ��Ϣ
		button[2]=new JButton("�޸ļ�ʻԱ��Ϣ");
		button[2].setFont(new Font("����",Font.PLAIN,14));
		button[2].setSize(4, 1);
		button[2].setOpaque(false);
		button[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Driver_ContentMessagePanel.modifyDriverInfo(BaseUI.contentPanel);
			}});
		
		//ɾ����ʻԱ��Ϣ
		button[3]=new JButton("ɾ����ʻԱ��Ϣ");
		button[3].setFont(new Font("����",Font.PLAIN,14));
		button[3].setSize(4, 1);
		button[3].setOpaque(false);
		button[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Driver_ContentMessagePanel.delDriverInfo(BaseUI.contentPanel);
			}});
		
		self.add(button[0]);
		self.add(button[1]);
		self.add(button[2]);
		self.add(button[3]);
		
		self.revalidate();
		self.repaint();		
	}
}
