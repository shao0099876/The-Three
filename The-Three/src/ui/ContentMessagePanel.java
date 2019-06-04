package ui;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.Database;
import entity.Car;

public class ContentMessagePanel extends JPanel {
	public ContentMessagePanel() {
		super();
		this.setOpaque(false);
		
	}
	public void setCarInfo() {//���ӹ����Ҫ��Ϣ
		String[] name= {"���ƺ�","��ʻԱ1","��ʻԱ2","·��"};
		Car[] array=Database.getCarInfo();
		if(array==null||array.length==0) {
			JLabel label=new JLabel("��ǰϵͳ�޿��ó�����Ϣ��");
			this.add(label);
			this.revalidate();
			this.repaint();
			return;
		}
		String[][] data=new String[array.length][4];
		for(int i=0;i<array.length;i++) {
			data[i]=array[i].toStringArray();
		}
		JTable table=new JTable(data,name);
		table.setOpaque(false);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setOpaque(false);
		this.add(scroll);
		this.revalidate();
		this.repaint();
		return;
	}
}
