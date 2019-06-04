package ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.Database;
import entity.Car;

public class ContentMessagePanel extends JPanel {
	private static Car[] array;
	private static ContentMessagePanel self;
	public ContentMessagePanel() {
		super();
		self=this;
		this.setOpaque(false);
		
	}
	
	public static void setCarDetailInfo(int n){//�鿴������ϸ��Ϣ
		String[] name1= {"���ƺ�","��ʻԱ1","��ʻԱ2","·��"};
		Car carDetail=new Car();
		carDetail=array[n];
		String[][] data1=new String[1][4];
		data1[0]=carDetail.toStringArray();
		JTable table1=new JTable(data1,name1);
		table1.setOpaque(false);
		JScrollPane scroll1=new JScrollPane(table1);
		scroll1.setOpaque(false);
		
		
		
		self.add(scroll1);
		self.revalidate();
		self.repaint();
		return;
	}
	
	public void setCarInfo() {//���ӹ����Ҫ��Ϣ
		String[] name= {"���ƺ�","��ʻԱ1","��ʻԱ2","·��"};
		array=Database.getCarInfo();
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
		
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
		        int col = table.getSelectedColumn();
		        String value = (String) table.getValueAt(row, col);
		        
		        if(col==0){
		        	setCarDetailInfo(row);
		        }
		        
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		this.add(scroll);
		this.revalidate();
		this.repaint();
		return;
	}
}
