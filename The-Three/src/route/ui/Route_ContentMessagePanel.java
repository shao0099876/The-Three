package route.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
	private static boolean flag;//�������ɾ��·����Ϣʱ��·�߱����������
	//false����ɾ����·�߱���������ı���
	//true����ɾ����·�߱�������ڵ����ģ���������
	private static String del_routenum;//��Ǳ�ɾ����·�߱��
	
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

	public static void delRouteInfo(ContentMessagePanel p_self) {//ɾ��·����Ϣ
		// TODO Auto-generated method stub
		self=p_self;
		DebugInfo.DebugInfo("��ʼ����ɾ��·����ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		flag=false;//��ʼ��Ϊɾ��·�߱���������ı���
		del_routenum="";//��ʼ��
		
		//����panel
		JPanel panel=new JPanel();
		panel.setOpaque(false);
		
		//��������panel
		JPanel spanel=new JPanel(new GridLayout(1,2,0,0));
		spanel.setOpaque(false);
		panel.add(spanel,BorderLayout.NORTH);
		
		//������ʾ���������
		JPanel cpanel=new JPanel();
		cpanel.setOpaque(false);
		panel.add(cpanel,BorderLayout.CENTER);
		
		self.route_deltext=new JTextField(20);
		self.route_deltext.setEditable(true);
		self.route_deltext.setOpaque(false);
		spanel.add(self.route_deltext);
		
		JButton b=new JButton("����");
		b.setFont(new Font("����",Font.PLAIN,14));
		b.setSize(4, 1);
		b.setOpaque(false);
		spanel.add(b);
		
		//��ť�����Ӧ����
		b.addActionListener(new ActionListener(){//���Ӱ�ť�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DebugInfo.DebugInfo("������ť������");
						String s=self.route_deltext.getText();
						
						Route[] array=Route_Database.getMohuRouteInfo(s);
						
						if(array==null||array.length==0){
							cpanel.removeAll();
							cpanel.add(new JLabel("��ϵͳ�����޸ó�����Ϣ!"));
						}
						else{
							cpanel.removeAll();
							
							String[] name= {"·�߱��","��ʼվ��","�յ�վ","��תվ��"};
							
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
									        	del_routenum=table.getValueAt(row, 0).toString();//��ȡ��ǰ�����·�ߵı��
									        	flag=true;//������������ɾ����Ϣ��������ģ���������
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
							
							cpanel.add(scroll,BorderLayout.CENTER);
							
							JButton bb=new JButton("ɾ��");
							bb.setFont(new Font("����",Font.PLAIN,14));
							bb.setSize(4, 1);
							bb.setOpaque(false);
							cpanel.add(bb,BorderLayout.SOUTH);
							
							bb.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent arg0) {
									// TODO Auto-generated method stub
									Thread t=new Thread(new Runnable(){

										@Override
										public void run() {
											// TODO Auto-generated method stub
											if(!flag){//ɾ����Ϣ�����ı���
												del_routenum=self.route_deltext.getText();//��ȡ��ǰ�ı����е�·�߱��
											}
											String message=Route_Database.delRouteInfo(del_routenum);
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											self.route_deltext.setText("");//����ı���������
										}
									});
								}
								
							});
						}
					}
				});
				t.start();
			}
			
		});
		
		
		self.add(panel);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("��ɻ��Ʋ鿴·����ϢPanel");
		return;
	}


}
