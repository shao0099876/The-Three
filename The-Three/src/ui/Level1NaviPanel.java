package ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Level1NaviPanel extends JPanel {
	public Level1NaviPanel(){
		super();
		this.setOpaque(false);
		
		JButton[] button=new JButton[4];
		
		button[0]=new JButton("���ӹ���");
		button[0].setFont(new Font("����",Font.PLAIN,14));button[0].setOpaque(false);
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		
		button[1]=new JButton("��ʻԱ");
		button[1].setFont(new Font("����",Font.PLAIN,14));button[1].setOpaque(false);
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		
		
		button[2]=new JButton("����");
		button[2].setFont(new Font("����",Font.PLAIN,14));button[2].setOpaque(false);
		button[2].setSize(4, 1);
		button[2].setOpaque(false);
		
		//�������ڵ�״̬���ж�����ʾ���ǵ�½�����˳�
		
		this.add(button[0]);
		this.add(button[1]);
		this.add(button[2]);
		
	}

}
