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
		JButton[] button=new JButton[10];
		
		//��ѯ������Ϣ
		button[0]=new JButton("������Ϣ");
		button[0].setFont(new Font("����",Font.PLAIN,14));
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		System.out.println("check_before");
		button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("����������ť����");
				BaseUI.contentPanel.setCarInfo();
			}});
		
		//���ӣ��޸ģ�ɾ��������Ϣ
		button[1]=new JButton("����ά��");
		button[1].setFont(new Font("����",Font.PLAIN,14));
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		System.out.println("����ά����ť�ѱ�����");//����ʹ��
		button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("����������ť����");
				BaseUI.contentPanel.carInfo_Add_Del();
			}});
		
		this.add(button[0]);
		this.add(button[1]);
		this.revalidate();
		this.repaint();
	}
}
