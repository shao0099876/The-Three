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
import entity.Driver;
import entity.Route;

public class ContentMessagePanel extends JPanel {
	private static Car[] array;
	private static ContentMessagePanel self;
	public ContentMessagePanel() {
		super();
		self=this;
		this.setOpaque(false);
		
	}
	
	public static void setCarDetailInfo(int n){//查看车辆详细信息
		
		
		//显示基本信息
		String[] name1= {"车牌号","驾驶员1","驾驶员2","路线"};
		Car carDetail=new Car();
		carDetail=array[n];
		String[][] data1=new String[1][4];
		data1[0]=carDetail.toStringArray();
		JTable table1=new JTable(data1,name1);
		table1.setOpaque(false);
		JScrollPane scroll1=new JScrollPane(table1);
		scroll1.setOpaque(false);
		
		//显示驾驶员的详细信息
		String[] name2={"驾驶员编号","驾驶证","驾驶员姓名","驾驶员年龄","驾驶年长","驾驶员联系方式","驾驶员目前状态"};
		Driver driInfo1=new Driver();
		driInfo1=Database.getDriverInfo(carDetail.people1Number);//查询驾驶员1的信息
		
		Driver driInfo2=new Driver();
		driInfo2=Database.getDriverInfo(carDetail.people2Number);//查询驾驶员2的信息
		
		String[][] data2=new String[2][7];
		data2[0]=driInfo1.toStringArray();
		data2[1]=driInfo2.toStringArray();
		
		JTable table2=new JTable(data2,name2);
		table2.setOpaque(false);
		JScrollPane scroll2=new JScrollPane(table1);
		scroll2.setOpaque(false);
		
		//显示路线的详细信息
		String[] name3={"路线编号","初始站点","终点站"};
		Route routeInfo=new Route();
		routeInfo=Database.getRouteInfo(carDetail.routeNumber);//查询路线的详细信息
		
		String[][] data3=new String[1][4];
		data3[0]=routeInfo.toStringArray();
		
		JTable table3=new JTable(data3,name3);
		table3.setOpaque(false);
		JScrollPane scroll3=new JScrollPane(table1);
		scroll3.setOpaque(false);
		
		
		self.add(scroll1);
		self.add(scroll2);
		self.add(scroll3);
		
		self.revalidate();
		self.repaint();
		return;
	}
	
	public void setCarInfo() {//车队管理概要信息
		String[] name= {"车牌号","驾驶员1","驾驶员2","路线"};
		array=Database.getCarInfo();
		if(array==null||array.length==0) {
			JLabel label=new JLabel("当前系统无可用车辆信息！");
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
