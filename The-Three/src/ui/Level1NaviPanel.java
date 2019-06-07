package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Level1NaviPanel extends JPanel {
	private JButton[] button=new JButton[5];//界面按钮
	private NaviPanel father;
	private void setButton(){//设置按钮
		button[0]=new JButton("车队管理");
		button[0].setFont(new Font("宋体",Font.PLAIN,14));button[0].setOpaque(false);
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		
		button[0].addActionListener(new ActionListener(){//添加响应函数
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				father.changeLevel2(0);
			}
			
		});
		
		button[1]=new JButton("路线管理");
		button[1].setFont(new Font("宋体",Font.PLAIN,14));button[1].setOpaque(false);
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		
		button[1].addActionListener(new ActionListener(){//添加响应函数
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				father.changeLevel2(1);
			}
			
		});
		
		button[2]=new JButton("货物");
		button[2].setFont(new Font("宋体",Font.PLAIN,14));button[2].setOpaque(false);
		button[2].setSize(4, 1);
		button[2].setOpaque(false);
		
		button[2].addActionListener(new ActionListener(){//添加响应函数
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				father.changeLevel2(2);
			}
			
		});
		
		
	}
	
	public Level1NaviPanel(NaviPanel f){
		super();
		this.setOpaque(false);//将该面板设置为透明
		father=f;
		setButton();
		
		//增加现在的状态，判断是显示的是登陆还是退出
		
		this.add(button[0]);
		this.add(button[1]);
		this.add(button[2]);
		
	}

}
