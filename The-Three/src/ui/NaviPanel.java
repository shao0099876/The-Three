package ui;

import javax.swing.JPanel;

public class NaviPanel extends JPanel {
	private Level1NaviPanel level1;
	private Level2NaviPanel level2;
	public NaviPanel(int x,int y) {
		super();
		this.setLayout(null);//���ÿյĲ��ֹ�����
		this.setOpaque(false);
		level1=new Level1NaviPanel();
		level2=new Level2NaviPanel();
		
		level1.setBounds(0, 0, x, (int)(y*0.5));
		level2.setBounds(0, (int)(y*0.5), x, (int)(y*0.5));
		
		this.add(level1);
		this.add(level2);
		
	}
}