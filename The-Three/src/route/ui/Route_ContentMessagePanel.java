package route.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import route.db.Route_Database;
import ui.ContentMessagePanel;
import car.db.Car_Database;
import client.DebugInfo;
import entity.Car;
import entity.Route;

public class Route_ContentMessagePanel {
	private static Route[] array=null;
	private static ContentMessagePanel self;
	
	public static void setRouteInfo(ContentMessagePanel p_self) {//路线信息 不包含GPS信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制路线概要信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		String[] name= {"路线编号","起始站点","终点站","中转站点"};
		array=Route_Database.getAllRouteInfo();//查询所有路线信息
		
		if(array==null||array.length==0) {
			JLabel label=new JLabel("当前系统无可用路线信息！");
			self.add(label);
			self.revalidate();
			self.repaint();
			DebugInfo.DebugInfo("完成绘制查看路线信息Panel");
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
				DebugInfo.DebugInfo("路线信息表格项被点击！");
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int row = table.getSelectedRow();
				        int col = table.getSelectedColumn();
				        if(col==0){
				        	//待添加 绘制具体路线上面的车辆的GPS信息
				        	
				        }
					}
				});
				t.start();
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
		
		self.add(scroll);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("完成绘制查看路线信息Panel");
		return;
	}

}
