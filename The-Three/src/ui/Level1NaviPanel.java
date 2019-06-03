package ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Level1NaviPanel extends JPanel {
	public Level1NaviPanel(){
		super();
		this.setOpaque(false);
		
		JButton[] button=new JButton[4];
		
		button[0]=new JButton("车队管理");
		button[0].setFont(new Font("宋体",Font.PLAIN,14));button[0].setOpaque(false);
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		
		button[1]=new JButton("驾驶员");
		button[1].setFont(new Font("宋体",Font.PLAIN,14));button[1].setOpaque(false);
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		
		
		button[2]=new JButton("货物");
		button[2].setFont(new Font("宋体",Font.PLAIN,14));button[2].setOpaque(false);
		button[2].setSize(4, 1);
		button[2].setOpaque(false);
		
		//增加现在的状态，判断是显示的是登陆还是退出
		
		this.add(button[0]);
		this.add(button[1]);
		this.add(button[2]);
		
	}

}
