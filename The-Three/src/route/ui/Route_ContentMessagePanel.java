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
	
	public static void setRouteInfo(ContentMessagePanel p_self) {//·����Ϣ ������GPS��Ϣ
		self=p_self;
		DebugInfo.DebugInfo("��ʼ����·�߸�Ҫ��ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		String[] name= {"·�߱��","��ʼվ��","�յ�վ","��תվ��"};
		array=Route_Database.getAllRouteInfo();//��ѯ����·����Ϣ
		
		if(array==null||array.length==0) {
			JLabel label=new JLabel("��ǰϵͳ�޿���·����Ϣ��");
			self.add(label);
			self.revalidate();
			self.repaint();
			DebugInfo.DebugInfo("��ɻ��Ʋ鿴·����ϢPanel");
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
				DebugInfo.DebugInfo("·����Ϣ���������");
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int row = table.getSelectedRow();
				        int col = table.getSelectedColumn();
				        if(col==0){
				        	//����� ���ƾ���·������ĳ�����GPS��Ϣ
				        	
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
		DebugInfo.DebugInfo("��ɻ��Ʋ鿴·����ϢPanel");
		return;
	}

}
