package route.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.text.Document;

import browser.BrowserDialog;
import car.db.Car_Database;
import route.db.Route_Database;
import station.db.Station_Database;
import client.DebugInfo;
import ui.ContentMessagePanel;
import entity.Route;
import entity.Station;

public class Route_ContentMessagePanel {
	private static Route[] array=null;
	private static ContentMessagePanel self;
	private static boolean flag;//�������ɾ��·����Ϣʱ��·�߱����������
	//false����ɾ����·�߱���������ı���
	//true����ɾ����·�߱�������ڵ����ģ���������
	
	private static String del_routenum;//��Ǳ�ɾ����·�߱��
	
	public static JTextField route_deltext;//����·��ɾ��
	
	public static JTextField route_text1,route_text2,route_text3,route_text4;//��ɾ
	
	public static JComboBox route_bobox;
	
	//�������������ʾվ���ѯ���
	public static JComboBox station_bobox2,station_bobox3,station_bobox4;
	
	private static ItemListener itemListener1=new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("route1bobox�ѡ�У�");
				Thread t=new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String s=(String) route_bobox.getSelectedItem();
						route_text1.setText(s);
					}
					
				});
				t.start();
			}
			}
	};
	
	public static void setRouteInfo(ContentMessagePanel p_self) {//�鿴·����Ϣ ������GPS��Ϣ
		self=p_self;
		DebugInfo.DebugInfo("��ʼ����·�߸�Ҫ��ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		array=Route_Database.getAllRouteInfo();//��ѯ����·����Ϣ

		if(array==null||array.length==0) {
			self.removeAll();//�������������ȫ�����
			JOptionPane.showMessageDialog(self,"��ǰϵͳ�޿���·����Ϣ��","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
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
				        	String routeNumber=data[row][col];
				        	String raw_string=Route_Database.getCarGPSonRoute(routeNumber);
				        	String[] res=raw_string.split("#");
				        	BrowserDialog map=new BrowserDialog();
				        	map.clean();
				        	for(String i:res) {
				        		map.Add_Cars_Point(i);
				        	}
				        	map.DrawPoints();
				        	String start_GPS=Station_Database.getGPS(data[row][1]);
				        	String end_GPS=Station_Database.getGPS(data[row][2]);
				        	if(data[row][3].equals("null")) {
				        		map.DrawRoute(start_GPS,end_GPS);
				        	}
				        	else {
				        		map.DrawRoute(start_GPS, end_GPS,data[row][3].split("-"));
				        	}
				        	map.ShowGUI(Car_Database.getCarNumberonRoute(routeNumber).split("#"));
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
		
		flag=true;//��ʼ��Ϊɾ��·�߱���������ı���
		del_routenum=" ";//��ʼ��
		
		//����panel
		JPanel panel=new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BorderLayout());
		
		//��������panel
		JPanel spanel=new JPanel(new GridLayout(1,2,0,0));
		spanel.setOpaque(false);
		panel.add(spanel,BorderLayout.NORTH);
		
		//������ʾ���������
		JPanel cpanel=new JPanel();
		cpanel.setOpaque(false);
		panel.add(cpanel,BorderLayout.CENTER);
		
		route_deltext=new JTextField(20);
		route_deltext.setFont(new Font("����",Font.PLAIN,30));
		route_deltext.setEditable(true);
		route_deltext.setOpaque(false);
		spanel.add(route_deltext);
		
		JButton b=new JButton("����");
		b.setFont(new Font("����",Font.PLAIN,20));
		b.setSize(4, 1);
		b.setOpaque(false);
		spanel.add(b);
		
		//��ť�����Ӧ����
		b.addActionListener(new ActionListener(){//���Ӱ�ť�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						DebugInfo.DebugInfo("������ť������");
						String s=route_deltext.getText();
						Route[] array=Route_Database.getMohuRouteInfo(s);
						
						if(array==null||array.length==0){
							JOptionPane.showMessageDialog(self,"��ϵͳ�����޸�·����Ϣ!","information",JOptionPane.INFORMATION_MESSAGE);
							route_deltext.setText("");
							return;
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
									Thread t=new Thread(new Runnable() {
										@Override
										public void run() {
											// TODO Auto-generated method stub
											int row = table.getSelectedRow();
									        int col = table.getSelectedColumn();
									        if(col==0){
									        	del_routenum=table.getValueAt(row, 0).toString();//��ȡ��ǰ�����·�ߵı��
									        	flag=false;//������������ɾ����Ϣ��������ģ���������
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
							bb.setFont(new Font("����",Font.PLAIN,18));
							bb.setSize(4, 1);
							bb.setOpaque(false);
							cpanel.add(bb,BorderLayout.SOUTH);
							
							bb.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent arg0) {	
									Thread t=new Thread(new Runnable(){
										@Override
										public void run() {
											if(flag){//ɾ����Ϣ�����ı���
												del_routenum=route_deltext.getText();//��ȡ��ǰ�ı����е�·�߱��
											}
											String message=Route_Database.delRouteInfo(del_routenum);
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											route_deltext.setText("");//����ı���������
											cpanel.removeAll();
											cpanel.revalidate();
											cpanel.repaint();						
										}
									});
									t.start();
								}	
							});
							cpanel.revalidate();
							cpanel.repaint();	
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

	public static void addRouteInfo(ContentMessagePanel p_self){//����·����Ϣ
		self=p_self;
		DebugInfo.DebugInfo("��ʼ��������·����ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		if(newstationbobox()==false){//����stationbobox��ʼ������
			self.removeAll();//�������������ȫ�����
			JOptionPane.showMessageDialog(self,"ϵͳ������վ����Ϣ�����ܽ���·����ӣ�����ȥ���վ����Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}

		//��վ����Ϣ���������
		JPanel p = new JPanel(new GridLayout(9,3,5,0));
		p.setOpaque(false);
		
		//��һ�� ·�߱��
		JLabel lab1=new JLabel("·�߱��");
		lab1.setFont(new Font("����",Font.PLAIN,25));
		lab1.setOpaque(false);
		p.add(lab1);
		
		route_text1=new JTextField(20);
		route_text1.setText("");
		route_text1.setFont(new Font("����",Font.PLAIN,25));
		route_text1.setEditable(true);//����Ϊ�ɱ༭
		route_text1.setOpaque(false);
		p.add(route_text1);
		p.add(new JLabel("     "));
		
		//
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		
		//�ڶ��� ��ʼվ̨
		JLabel lab2=new JLabel("��ʼվ̨");
		lab2.setFont(new Font("����",Font.PLAIN,25));
		lab2.setOpaque(false);
		p.add(lab2);
		
		route_text2=new JTextField();
		route_text2.setText("");
		route_text2.setFont(new Font("����",Font.PLAIN,25));
		route_text2.setEditable(false);//����Ϊ�ɱ༭
		route_text2.setOpaque(false);
		p.add(route_text2);
		p.add(station_bobox2);
		
		//
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		
		//������ �յ�վ
		JLabel lab3=new JLabel("�յ�վ");
		lab3.setFont(new Font("����",Font.PLAIN,25));
		lab3.setOpaque(false);
		p.add(lab3);
		
		route_text3=new JTextField();
		route_text3.setText("");
		route_text3.setFont(new Font("����",Font.PLAIN,25));
		route_text3.setEditable(false);//����Ϊ�ɱ༭
		route_text3.setOpaque(false);
		p.add(route_text3);
		p.add(station_bobox3);
		
		//
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		
		//������ ��תվ
		JLabel lab4=new JLabel("��תվ");
		lab4.setFont(new Font("����",Font.PLAIN,25));
		lab4.setOpaque(false);
		p.add(lab4);
		
		route_text4=new JTextField();
		route_text4.setText("");
		route_text4.setFont(new Font("����",Font.PLAIN,25));
		route_text4.setEditable(false);//����Ϊ�ɱ༭
		route_text4.setOpaque(false);
		p.add(route_text4);
		p.add(station_bobox4);
		
		//
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		
		//������ ���Ӱ�ť
		JLabel l=new JLabel("վ����Ϣ���Ǵ��ұ�ѡ��");
		l.setFont(new Font("����",Font.PLAIN,25));
		p.add(l);
		
		p.add(new JLabel("     "));
		
		JButton b=new JButton("ȷ��");
		b.setFont(new Font("����",Font.PLAIN,18));
		b.setSize(4, 1);
		b.setOpaque(false);
		p.add(b);
		
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable(){
					@Override
					public void run() {
						DebugInfo.DebugInfo("���·����Ϣ�İ�ť������");
						String[] s=new String[4]; 
						s[0]=route_text1.getText();
						s[1]=route_text2.getText();
						s[2]=route_text3.getText();
						s[3]=route_text4.getText();
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3];//�������ӵ�·����Ϣ
						System.out.println("����·����Ϣ:"+S);
						
						String message=Route_Database.addRouteInfo(S);//������Ϣ����ڽ�������
						JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
					
						route_text1.setText("");
						route_text2.setText("");
						route_text3.setText("");
						route_text4.setText("");
						
					}
				});
				t.start();
			}
		});
		
		self.add(p);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("��ɻ�������·����ϢPanel");
		return;
	}

	public static void modifyRouteInfo(ContentMessagePanel p_self){//�޸�·����Ϣ
		self=p_self;
		DebugInfo.DebugInfo("��ʼ�����޸�·����ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		if(newstationbobox()==false){//����stationbobox��ʼ������
			self.removeAll();//�������������ȫ�����
			JOptionPane.showMessageDialog(self,"ϵͳ������վ����Ϣ�����ܽ���·���޸ģ�����ȥ���վ����Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		//��վ����Ϣ,�����޸�
		JPanel panel=new JPanel(new GridLayout(9,3,5,5));
		panel.setOpaque(false);
		
		//��һ�� ·�߱��
		JLabel label1=new JLabel("·�߱��");
		label1.setFont(new Font("����",Font.PLAIN,25));
		label1.setOpaque(false);
		panel.add(label1);
		
		route_text1=new JTextField(20);
		route_text1.setText("");
		route_text1.setFont(new Font("����",Font.PLAIN,25));
		route_text1.setEditable(false);//����Ϊ�ɱ༭
		route_text1.setOpaque(false);
		panel.add(route_text1);
		
		if(newroutebobox()==false){
			JOptionPane.showMessageDialog(self,"ϵͳû��·����Ϣ�����ܽ���·���޸ģ�����ȥ���·����Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		panel.add(route_bobox);
		
		//
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//�ڶ��� ��ʼվ̨
		JLabel label2=new JLabel("��ʼվ̨");
		label2.setFont(new Font("����",Font.PLAIN,25));
		label2.setOpaque(false);
		panel.add(label2);
		
		route_text2=new JTextField(20);
		route_text2.setText("");
		route_text2.setFont(new Font("����",Font.PLAIN,25));
		route_text2.setEditable(false);//����Ϊ�ɱ༭
		route_text2.setOpaque(false);
		panel.add(route_text2);
		panel.add(station_bobox2);
		
		//
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ �յ�վ
		JLabel label3=new JLabel("�յ�վ");
		label3.setFont(new Font("����",Font.PLAIN,25));
		label3.setOpaque(false);
		panel.add(label3);
		
		route_text3=new JTextField(20);
		route_text3.setText("");
		route_text3.setFont(new Font("����",Font.PLAIN,25));
		route_text3.setEditable(false);//����Ϊ�ɱ༭
		route_text3.setOpaque(false);
		panel.add(route_text3);
		panel.add(station_bobox3);
		
		//
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ ��תվ
		JLabel label4=new JLabel("��תվ");
		label4.setFont(new Font("����",Font.PLAIN,25));
		label4.setOpaque(false);
		panel.add(label4);
		
		route_text4=new JTextField(20);
		route_text4.setText("");
		route_text4.setFont(new Font("����",Font.PLAIN,25));
		route_text4.setEditable(false);//����Ϊ�ɱ༭
		route_text4.setOpaque(false);
		panel.add(route_text4);
		panel.add(station_bobox4);
		
		//
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ ���Ӱ�ť
		JLabel l=new JLabel("�ı��������������ұ�������s");
		l.setFont(new Font("����",Font.PLAIN,18));
		panel.add(l);
		
		panel.add(new JLabel("    "));
		
		JButton b=new JButton("ȷ��");
		b.setFont(new Font("����",Font.PLAIN,18));
		b.setSize(4, 1);
		b.setOpaque(false);
		panel.add(b);
		
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("�޸�·����Ϣ�İ�ť������");
				Thread t=new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String[] s=new String[4]; 
						s[0]=route_text1.getText();
						s[1]=route_text2.getText();
						s[2]=route_text3.getText();
						s[3]=route_text4.getText();
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3];
						System.out.println("�޸���ϢΪ��"+S);
						
						String message=Route_Database.modRouteInfo(S);//������Ϣ����ڽ�������
						JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
					
						route_text1.setText("");
						route_text2.setText("");
						route_text3.setText("");
						route_text4.setText("");
					}
				});
				t.start();
			}	
		});
		
		self.add(panel);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("��ɻ����޸�·����ϢPanel");
		return;
	}

	
	private static ItemListener station_itemListener2=new ItemListener() {//��stationbobox2������Ӧ����

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED){
					DebugInfo.DebugInfo("Combobox2�ѡ�У�");
					Thread t=new Thread(new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							String s=(String) station_bobox2.getSelectedItem();
							route_text2.setText("");
							route_text2.setText(s);
						}
						
					});
					t.start();
				}
				}
		};
	private static ItemListener station_itemListener3=new ItemListener() {//��stationbobox3������Ӧ����
	
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("Combobox3�ѡ�У�");
				Thread t=new Thread(new Runnable(){
		
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String s=(String) station_bobox3.getSelectedItem();
						route_text3.setText("");
						route_text3.setText(s);
					}
					
				});
				t.start();
			}
		}};
	private static ItemListener station_itemListener4=new ItemListener() {//��stationbobox4������Ӧ����
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("Combobox4�ѡ�У�");
				
				Thread t=new Thread(new Runnable(){
					@Override
					public void run() {
						String s=(String) station_bobox4.getSelectedItem();
						String ss=route_text4.getText();
						
						if(ss.length()==0||ss.equals("")){
							route_text4.setText("");
							route_text4.setText(s);
						}
						else{
							ss=ss+"-"+s;
							route_text4.setText("");
							route_text4.setText(ss);
						}
					}
					
				});
				t.start();
			}
		}};
	
	public static boolean newstationbobox(){//��station bobox��ʼ��
		//��վ����ӽ��б���
		station_bobox2=new JComboBox();
		station_bobox3=new JComboBox();
		station_bobox4=new JComboBox();
		
		station_bobox2.setFont(new Font("����",Font.PLAIN,25));station_bobox2.setOpaque(false);
		station_bobox3.setFont(new Font("����",Font.PLAIN,25));station_bobox3.setOpaque(false);
		station_bobox4.setFont(new Font("����",Font.PLAIN,25));station_bobox4.setOpaque(false);
		
		station_bobox2.setSelectedIndex(-1);//���ò�ѡ��
		station_bobox3.setSelectedIndex(-1);//���ò�ѡ��
		station_bobox4.setSelectedIndex(-1);//���ò�ѡ��
		
		//��ȡ����վ��
		Station[] sta=Station_Database.getStationList();
		if(sta==null||sta.length==0){
			return false;//��վ����Ϣ
		}
		
		String[] s=new String[sta.length];
		for(int i=0;i<sta.length;i++){
			s[i]=sta[i].stationName;//��ȡ����
		}
		
		//����Ϣ��ӵ��б���
		DefaultComboBoxModel<String> route_model2=new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> route_model3=new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> route_model4=new DefaultComboBoxModel<String>();
		for(int i=0;i<s.length;i++){
			route_model2.addElement(s[i]);
			route_model3.addElement(s[i]);
			route_model4.addElement(s[i]);
		}
		station_bobox2.setModel(route_model2);
		station_bobox3.setModel(route_model3);
		station_bobox4.setModel(route_model4);
		
		//�����Ӧ����
		station_bobox2.addItemListener(station_itemListener2);
		station_bobox3.addItemListener(station_itemListener3);
		station_bobox4.addItemListener(station_itemListener4);
		return true;
	}
	
	public static boolean newroutebobox(){
		//���·�߱��������
		route_bobox=new JComboBox();
		route_bobox.setFont(new Font("����",Font.PLAIN,25));
		route_bobox.setOpaque(false);
		route_bobox.setSelectedIndex(-1);//���ò�ѡ��
		
		//����Ϣ��ӵ��б���
		Route[] stat1=Route_Database.getAllRouteInfo();//��ѯ����·����Ϣ

		if(stat1==null||stat1.length==0) {
			return false;//�޿���·��
		}
		
		String[] rr=new String[stat1.length];
		for(int i=0;i<stat1.length;i++){
			rr[i]=String.valueOf(stat1[i].routeNumber);
		}
		
		DefaultComboBoxModel<String> route_model1=new DefaultComboBoxModel<String>();
		for(int i=0;i<rr.length;i++){
			route_model1.addElement(rr[i]);
		}
		route_bobox.setModel(route_model1);
		
		//��Ӽ�������
		route_bobox.addItemListener(itemListener1);
		return true;
	}
}
