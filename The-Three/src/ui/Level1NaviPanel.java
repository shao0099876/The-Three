package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import client.DebugInfo;

public class Level1NaviPanel extends JPanel {
	private JButton[] button=new JButton[5];//���水ť
	private NaviPanel father;
	private void setButton(){//���ð�ť
		button[0]=new JButton("���ӹ���");
		button[0].setFont(new Font("����",Font.PLAIN,14));button[0].setOpaque(false);
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		
		button[0].addActionListener(new ActionListener(){//�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DebugInfo.DebugInfo("һ�����������ӹ���ť������");
						father.changeLevel2(0);
					}
					
				});
				t.start();
			}
			
		});
		
		button[1]=new JButton("·�߹���");
		button[1].setFont(new Font("����",Font.PLAIN,14));button[1].setOpaque(false);
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		
		button[1].addActionListener(new ActionListener(){//�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DebugInfo.DebugInfo("һ��������·�߰�ť������");
						father.changeLevel2(1);
					}
					
				});
				t.start();
			}
			
		});
		
		button[2]=new JButton("����");
		button[2].setFont(new Font("����",Font.PLAIN,14));button[2].setOpaque(false);
		button[2].setSize(4, 1);
		button[2].setOpaque(false);
		
		button[2].addActionListener(new ActionListener(){//�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DebugInfo.DebugInfo("һ�����������ﰴť������");
						father.changeLevel2(2);
					}
					
				});
				t.start();
			}
			
		});
		
		
	}
	
	public Level1NaviPanel(NaviPanel f){
		super();
		this.setOpaque(false);//�����������Ϊ͸��
		father=f;
		setButton();
		
		//�������ڵ�״̬���ж�����ʾ���ǵ�½�����˳�
		
		this.add(button[0]);
		this.add(button[1]);
		this.add(button[2]);
		
	}

}
