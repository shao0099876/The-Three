package car.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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

import car.db.Car_Database;
import client.DebugInfo;
import driver.db.Driver_Database;
import entity.Car;
import entity.Driver;
import entity.Route;
import route.db.Route_Database;
import ui.ContentMessagePanel;

public class Car_ContentMessagePanel{
	private static Car[] array=null;
	private static ContentMessagePanel self;
	
	private static boolean flag;//�������ɾ��������Ϣʱ�ĳ��������������
	//false����ɾ���ĳ�������������ı���
	//true����ɾ���ĳ�����������ڵ����ģ���������
	
	private static String del_carnum;//��Ǳ�ɾ����·�߱��
	
	public static JTextField car_text1,car_text2,car_text3,car_text4;
	public static JComboBox car_bobox1,car_bobox2,car_bobox3,car_bobox4;
	
	public static JTextField car_deltext;//���ڳ���ɾ��

	private static ItemListener car_itemListener=new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("���ƺ��ѡ�У�");
				Thread t=new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String s=(String) car_bobox1.getSelectedItem();
						car_text1.setText(s);
					}
					
				});
				t.start();
			}
			}
	};

	private static ItemListener driver1_itemListener=new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("��ʻԱ1�ѡ�У�");
				Thread t=new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String s=(String) car_bobox2.getSelectedItem();
						car_text2.setText(s);
					}
					
				});
				t.start();
			}
			}
	};
	
	private static ItemListener driver2_itemListener=new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("��ʻԱ2�ѡ�У�");
				Thread t=new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String s=(String) car_bobox3.getSelectedItem();
						car_text3.setText(s);
					}
					
				});
				t.start();
			}
			}
	};

	private static ItemListener route_itemListener=new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("·�߱���ѡ�У�");
				Thread t=new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String s=(String) car_bobox4.getSelectedItem();
						car_text4.setText(s);
					}
					
				});
				t.start();
			}
			}
	};
	
	public static void setCarInfo(ContentMessagePanel p_self) {//���ӹ����Ҫ��Ϣ
		self=p_self;
		DebugInfo.DebugInfo("��ʼ���Ƴ��ӹ����Ҫ��ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		array=Car_Database.getCarInfo();
		
		if(array==null||array.length==0) {
			self.removeAll();//�������������ȫ�����
			JOptionPane.showMessageDialog(self,"��ǰϵͳ�޿��ó�����Ϣ��","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		String[] name= {"���ƺ�","��ʻԱ1","��ʻԱ2","·��"};
		
		String[][] data=new String[array.length][4];
		for(int i=0;i<array.length;i++) {
			data[i]=array[i].toStringArray();
		}
		JTable table=new JTable(data,name);
		//table.setFont(new Font("����",Font.PLAIN,18));//��������
		table.setOpaque(false);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setOpaque(false);
		
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("���Ӹ�Ҫ��Ϣ���������");
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int row = table.getSelectedRow();
				        int col = table.getSelectedColumn();
				        if(col==0){
				        	setCarDetailInfo(self,array[row]);
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
		DebugInfo.DebugInfo("��ɻ��Ƴ�����Ҫ��ϢPanel");
		return;
	}

	public static void addcarInfo(ContentMessagePanel p_self){//������Ϣ�����Ӳ���
		System.out.println("��ʼ�жϼ�ʻԱ��Ϣ");
		if(setcarbobox()==1){
			self.removeAll();//�������������ȫ�����
			JOptionPane.showMessageDialog(self,"ϵͳ�����޼�ʻԱ��Ϣ�����ܽ��г�����Ϣ��ӣ�����ȥ��Ӽ�ʻԱ��Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		System.out.println("��ʼ�ж�·����Ϣ");
		if(setcarbobox()==2){
			self.removeAll();//�������������ȫ�����
			JOptionPane.showMessageDialog(self,"ϵͳ������·����Ϣ�����ܽ��г�����Ϣ��ӣ�����ȥ���·����Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		DebugInfo.DebugInfo("��ʼ���Ƴ�����ɾ��Panel");
		self=p_self;
		self.removeAll();//������������������

		System.out.println("��ʼ��ͼ");
		
		JPanel panel=new JPanel(new GridLayout(9,3,5,5));//��������
		panel.setOpaque(false);
		
		//��һ�� �������
		JLabel label1=new JLabel("�������");
		label1.setFont(new Font("����",Font.PLAIN,25));
		label1.setOpaque(false);
		panel.add(label1);
		
		car_text1=new JTextField(30);
		car_text1.setText("");
		car_text1.setFont(new Font("����",Font.PLAIN,25));
		car_text1.setEditable(true);//����Ϊ�ɱ༭
		car_text1.setOpaque(false);
		panel.add(car_text1);
		panel.add(new JLabel("    "));
		
		//�µĿ���
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//�ڶ��� ��ʻԱ1��
		JLabel label2=new JLabel("��ʻԱһ�ű��");
		label2.setFont(new Font("����",Font.PLAIN,25));
		label2.setOpaque(false);
		panel.add(label2);
		
		car_text2=new JTextField();
		car_text2.setText("");

		car_text2.setFont(new Font("����",Font.PLAIN,25));
		car_text2.setEditable(false);//����Ϊ�ɱ༭
		car_text2.setOpaque(false);
		panel.add(car_text2);
		panel.add(car_bobox2);
		
		//�µĿ���
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ ��ʻԱ2��
		JLabel label3=new JLabel("��ʻԱ���ű��");
		label3.setFont(new Font("����",Font.PLAIN,25));
		label3.setOpaque(false);
		panel.add(label3);
		
		car_text3=new JTextField();
		car_text3.setText("");

		car_text3.setFont(new Font("����",Font.PLAIN,25));
		car_text3.setEditable(false);//����Ϊ�ɱ༭
		car_text3.setOpaque(false);
		panel.add(car_text3);
		panel.add(car_bobox3);
		
		//�µĿ���
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ ·�߱��
		JLabel label4=new JLabel("·�߱��");
		label4.setFont(new Font("����",Font.PLAIN,25));
		label4.setOpaque(false);
		panel.add(label4);
		
		car_text4=new JTextField();
		car_text4.setText("");
		car_text4.setFont(new Font("����",Font.PLAIN,25));
		car_text4.setEditable(false);//����Ϊ�ɱ༭
		car_text4.setOpaque(false);
		panel.add(car_text4);
		panel.add(car_bobox4);
		
		//������ ���ӿ���
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ ��Ӱ�ť
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		JButton button=new JButton();
		
		button=new JButton("ȷ��");
		button.setFont(new Font("����",Font.PLAIN,14));
		button.setSize(4, 1);
		button.setOpaque(false);
		panel.add(button);
		
		button.addActionListener(new ActionListener(){//���Ӱ�ť�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DebugInfo.DebugInfo("��Ӱ�ť������");
						if(car_text1.getText().length()==0||car_text1.equals("")){
							JOptionPane.showMessageDialog(self,"����д�������","information",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						StringBuilder carinfo=new StringBuilder();//�����ı����ж�Ӧ����Ϣ
						carinfo.append(car_text1.getText());
						carinfo.append("#");
						carinfo.append(car_text2.getText());
						carinfo.append("#");
						carinfo.append(car_text3.getText());
						carinfo.append("#");
						carinfo.append(car_text4.getText());
						
						String newcarinfo=carinfo.toString();
						
						String s=Car_Database.AddCarInfo(newcarinfo);
						JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
						
						car_text1.setText("");
						car_text2.setText("");
						car_text3.setText("");
						car_text4.setText("");
					}
				});
				t.start();
			}
			
		});
		
		self.add(panel);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("��ɻ��Ƴ�����ɾ��Panel");
		return;
	}
	
	public static void setCarDetailInfo(ContentMessagePanel p_self,Car carDetail){//�鿴������ϸ��Ϣ
		DebugInfo.DebugInfo("��ʼ���Ƴ�����ϸ��ϢPanel");
		self=p_self;
		self.removeAll();
		
		//��������
		CardLayout c1=new CardLayout();
		JPanel panel=new JPanel();
		panel.setLayout(c1);
		
		JPanel p1=new JPanel();//�����Ϣ
		p1.setOpaque(false);
		p1.setLayout(new BorderLayout());
		
		
		JPanel p2=new JPanel();//��ʻԱ��Ϣ
		p2.setOpaque(false);
		p2.setLayout(new BorderLayout());
		
		
		JPanel p3=new JPanel();//·����Ϣ
		p3.setOpaque(false);
		p3.setLayout(new BorderLayout());
		
		
		//��ʾ�ı�ǩ��Ϣ
		JLabel l1=new JLabel("��ʾ�����Ϣ");
		l1.setFont(new Font("����",Font.PLAIN,18));//��������
		l1.setForeground(Color.red);
		
		JLabel l2=new JLabel("��ʾ��ʻԱ��Ϣ");
		l2.setFont(new Font("����",Font.PLAIN,18));//��������
		l2.setForeground(Color.red);
		
		JLabel l3=new JLabel("��ʾ·����Ϣ");
		l3.setFont(new Font("����",Font.PLAIN,18));//��������
		l3.setForeground(Color.red);
		
		//�����Ӧ����
		MouseListener A=new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				c1.next(panel);
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
		};
		
		p1.addMouseListener(A);
		p2.addMouseListener(A);
		p3.addMouseListener(A);
		
		//��ʾ������Ϣ
		p1.add(l1,BorderLayout.NORTH);
		String[] name1= {"���ƺ�","��ʻԱ1","��ʻԱ2","·��"};
		String[][] data1=new String[1][4];
		data1[0]=carDetail.toStringArray();
		JTable table1=new JTable(data1,name1);
		//table1.setFont(new Font("����",Font.PLAIN,18));//��������
		table1.setOpaque(false);
		JScrollPane scroll1=new JScrollPane(table1);
		scroll1.setOpaque(false);
		p1.add(scroll1,BorderLayout.CENTER);
		
		//��ʾ��ʻԱ����ϸ��Ϣ	
		p2.add(l2,BorderLayout.NORTH);
		String[] name2={"��ʻԱ���","��ʻ֤","��ʻԱ����","��ʻԱ����","��ʻ�곤","��ʻԱ��ϵ��ʽ","��ʻԱĿǰ״̬"};
		Driver driInfo1=new Driver();
		driInfo1=Driver_Database.getDriverInfo(carDetail.people1Number);//��ѯ��ʻԱ1����Ϣ
		
		Driver driInfo2=new Driver();
		driInfo2=Driver_Database.getDriverInfo(carDetail.people2Number);//��ѯ��ʻԱ2����Ϣ
		
		String[][] data2=new String[2][7];
		data2[0]=driInfo1.toStringArray();
		data2[1]=driInfo2.toStringArray();
		
		JTable table2=new JTable(data2,name2);
		table2.setOpaque(false);
		JScrollPane scroll2=new JScrollPane(table2);
		scroll2.setOpaque(false);
		p2.add(scroll2,BorderLayout.CENTER);
		
		//��ʾ·�ߵ���ϸ��Ϣ	
		p3.add(l3,BorderLayout.NORTH);
		String[] name3={"·�߱��","��ʼվ��","�յ�վ","��תվ��"};
		Route routeInfo=new Route();
		routeInfo=Route_Database.getRouteInfo(carDetail.routeNumber);//��ѯ·�ߵ���ϸ��Ϣ
		
		String[][] data3=new String[1][4];
		data3[0]=routeInfo.toStringArray();
		
		JTable table3=new JTable(data3,name3);
		//table3.setFont(new Font("����",Font.PLAIN,18));//��������
		table3.setOpaque(false);
		JScrollPane scroll3=new JScrollPane(table3);
		scroll3.setOpaque(false);
		p3.add(scroll3,BorderLayout.CENTER);
		
		//��ӵ���������
		panel.add(p1,"First");
		panel.add(p2,"Second");
		panel.add(p3,"Third");
	
		// ����Ĭ����ʾ�Ŀ�Ƭ
		c1.show(panel, "First");
		
		self.add(panel);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("��ɻ��Ƴ�����ϸ��ϢPanel");
		return;
	}
	
	public static void modfiycarInfo(ContentMessagePanel p_self){//������Ϣ���޸Ĳ���
		DebugInfo.DebugInfo("��ʼ���Ƴ����޸�Panel");
		self=p_self;
		self.removeAll();//������������������

		if(setcarbobox()==0){
			self.removeAll();//�������������ȫ�����
			JOptionPane.showMessageDialog(self,"ϵͳ�����޳�����Ϣ�����ܽ��г����޸ģ�����ȥ��ӳ�����Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		if(setcarbobox()==1){
			self.removeAll();//�������������ȫ�����
			JOptionPane.showMessageDialog(self,"ϵͳ�����޼�ʻԱ��Ϣ�����ܽ��г����޸ģ�����ȥ��Ӽ�ʻԱ��Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		if(setcarbobox()==2){
			self.removeAll();//�������������ȫ�����
			JOptionPane.showMessageDialog(self,"ϵͳ������·����Ϣ�����ܽ��г����޸ģ�����ȥ���·����Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		JPanel panel=new JPanel(new GridLayout(9,3,5,5));//��������
		panel.setOpaque(false);
		
		//��һ�� �������
		JLabel label1=new JLabel("�������");
		label1.setFont(new Font("����",Font.PLAIN,25));
		label1.setOpaque(false);
		panel.add(label1);
		
		car_text1=new JTextField(20);
		car_text1.setText("");
		car_text1.setFont(new Font("����",Font.PLAIN,25));
		car_text1.setEditable(false);//����Ϊ�ɱ༭
		car_text1.setOpaque(false);
		panel.add(car_text1);
		panel.add(car_bobox1);
		
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//�ڶ��� ��ʻԱ1��
		JLabel label2=new JLabel("��ʻԱһ�ű��");
		label2.setFont(new Font("����",Font.PLAIN,25));
		label2.setOpaque(false);
		panel.add(label2);
		
		car_text2=new JTextField();
		car_text2.setText("");

		car_text2.setFont(new Font("����",Font.PLAIN,25));
		car_text2.setEditable(false);//����Ϊ�ɱ༭
		car_text2.setOpaque(false);
		panel.add(car_text2);
		panel.add(car_bobox2);
		
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ ��ʻԱ2��
		JLabel label3=new JLabel("��ʻԱ���ű��");
		label3.setFont(new Font("����",Font.PLAIN,25));
		label3.setOpaque(false);
		panel.add(label3);
		
		car_text3=new JTextField();
		car_text3.setText("");
		car_text3.setFont(new Font("����",Font.PLAIN,25));
		car_text3.setEditable(false);//����Ϊ�ɱ༭
		car_text3.setOpaque(false);
		panel.add(car_text3);
		panel.add(car_bobox3);
		
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ ·�߱��
		JLabel label4=new JLabel("·�߱��");
		label4.setFont(new Font("����",Font.PLAIN,25));
		label4.setOpaque(false);
		panel.add(label4);
		
		car_text4=new JTextField();
		car_text4.setText("");
		car_text4.setFont(new Font("����",Font.PLAIN,25));
		car_text4.setEditable(false);//����Ϊ�ɱ༭
		car_text4.setOpaque(false);
		panel.add(car_text4);
		panel.add(car_bobox4);
		
		//������ ���ӿ���
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ ��Ӱ�ť
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		JButton button=new JButton();
		
		button=new JButton("ȷ��");
		button.setFont(new Font("����",Font.PLAIN,14));
		button.setSize(4, 1);
		button.setOpaque(false);
		panel.add(button);
		
		button.addActionListener(new ActionListener(){//���Ӱ�ť�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DebugInfo.DebugInfo("��Ӱ�ť������");
						if(car_text1.getText().length()==0||car_text1.equals("")){
							JOptionPane.showMessageDialog(self,"��ѡ�������","information",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						StringBuilder carinfo=new StringBuilder();//�����ı����ж�Ӧ����Ϣ
						carinfo.append(car_text1.getText());
						carinfo.append("#");
						carinfo.append(car_text2.getText());
						carinfo.append("#");
						carinfo.append(car_text3.getText());
						carinfo.append("#");
						carinfo.append(car_text4.getText());
						
						String newcarinfo=carinfo.toString();
						
						String s=Car_Database.ModifyCarInfo(newcarinfo);//������Ϣ����ڽ�������
						JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
						
						car_text1.setText("");
						car_text2.setText("");
						car_text3.setText("");
						car_text4.setText("");
					}
				});
				t.start();
			}
			
		});
		
		self.add(panel);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("��ɻ��Ƴ�����ɾ��Panel");
		return;
		
	}

	public static void delcarInfo(ContentMessagePanel p_self){//ɾ��������Ϣ
		self=p_self;
		DebugInfo.DebugInfo("��ʼ����ɾ��������ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		
		flag=true;//��ʼ��Ϊɾ��·�߱���������ı���
		del_carnum=" ";//��ʼ��	
		
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
		
		car_deltext=new JTextField(30);
		car_deltext.setText("");
		car_deltext.setFont(new Font("����",Font.PLAIN,30));
		car_deltext.setEditable(true);
		car_deltext.setOpaque(false);
		spanel.add(car_deltext);
		
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
						String s=car_deltext.getText();
						
						
						Car[] array=Car_Database.getMohuCarInfo(s);
						
						if(array==null||array.length==0){
							JOptionPane.showMessageDialog(self,"��ϵͳ�����޸ó�����Ϣ!","information",JOptionPane.INFORMATION_MESSAGE);
							car_deltext.setText("");
							return;
						}
						else{
							cpanel.removeAll();
							
							String[] name= {"�������","��ʻԱ1","��ʻԱ2","·�߱��"};
							
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
									        	del_carnum=table.getValueAt(row, 0).toString();//��ȡ��ǰ�����·�ߵı��
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
							bb.setFont(new Font("����",Font.PLAIN,20));
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
												del_carnum=car_deltext.getText();//��ȡ��ǰ�ı����еĳ������
											}
											String message=Car_Database.DeleteCarInfo(del_carnum);
											
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											car_deltext.setText("");//����ı���������
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
		DebugInfo.DebugInfo("��ɻ���ɾ��������ϢPanel");
		return;
	}

	public static int setcarbobox(){
		System.out.println("��ʼִ��");
		car_bobox1=new JComboBox();
		car_bobox2=new JComboBox();
		car_bobox3=new JComboBox();
		car_bobox4=new JComboBox();
		
		car_bobox1.setFont(new Font("����",Font.PLAIN,25));car_bobox1.setOpaque(false);
		car_bobox2.setFont(new Font("����",Font.PLAIN,25));car_bobox2.setOpaque(false);
		car_bobox3.setFont(new Font("����",Font.PLAIN,25));car_bobox3.setOpaque(false);
		car_bobox4.setFont(new Font("����",Font.PLAIN,25));car_bobox4.setOpaque(false);
	
		car_bobox1.setSelectedIndex(-1);
		car_bobox2.setSelectedIndex(-1);
		car_bobox3.setSelectedIndex(-1);
		car_bobox4.setSelectedIndex(-1);
	
		
		System.out.println("��ȡ��ʻԱ��Ϣ");
		Driver[] dri=Driver_Database.getAllDriverInfo();
		if(dri==null||dri.length==0){
			return 1;//�޼�ʻԱ��Ϣ
		}
		String[] s2=new String[dri.length];//��ʻԱ���
		for(int i=0;i<dri.length;i++){
			System.out.println("��ʻԱ:"+s2[i]);
			s2[i]=String.valueOf(dri[i].peopleNumber);//��ȡ��ʻԱ���
		}
		DefaultComboBoxModel<String> driver_moel1=new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> driver_moel2=new DefaultComboBoxModel<String>();
		driver_moel1.addElement("");
		driver_moel2.addElement("");
		for(int i=0;i<s2.length;i++){
			driver_moel1.addElement(s2[i]);
			driver_moel2.addElement(s2[i]);
		}
		car_bobox2.setModel(driver_moel1);
		car_bobox3.setModel(driver_moel2);
		car_bobox2.addItemListener(driver1_itemListener);
		car_bobox3.addItemListener(driver2_itemListener);	
		
		System.out.println("��ȡ·����Ϣ");
		Route[] ro=Route_Database.getAllRouteInfo();
		if(ro==null||ro.length==0){
			return 2;//��·����Ϣ
		}
		String[] s3=new String[ro.length];//·�߱��
		for(int i=0;i<ro.length;i++){
			System.out.println("·��:"+s3[i]);
			s3[i]=String.valueOf(ro[i].routeNumber);//��ȡ·�߱��
		}
		DefaultComboBoxModel<String> route_model=new DefaultComboBoxModel<String>();
		route_model.addElement("");
		for(int i=0;i<s3.length;i++){
			route_model.addElement(s3[i]);
		}
		car_bobox4.setModel(route_model);
		car_bobox4.addItemListener(route_itemListener);
		
		//��ȡ������Ϣ
		System.out.println("��ȡ������Ϣ");
		Car[] ca=Car_Database.getCarInfo();
		if(ca==null||ca.length==0){
			return 0;//�޳�����Ϣ
		}
		String[] s1=new String[ca.length];//���ƺ�
		for(int i=0;i<ca.length;i++){
			s1[i]=ca[i].carNumber;//��ȡ���ƺ�
			System.out.println("���ƺ�:"+s1[i]);
		}
		//����Ϣ��ӵ��б���
		DefaultComboBoxModel<String> car_model=new DefaultComboBoxModel<String>();	
		car_model.addElement("");
		for(int i=0;i<s1.length;i++){
			car_model.addElement(s1[i]);
		}
		car_bobox1.setModel(car_model);
		car_bobox1.addItemListener(car_itemListener);
		return -1;//�ɹ�
		
	}
}
