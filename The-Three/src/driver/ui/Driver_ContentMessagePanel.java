package driver.ui;

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

import client.DebugInfo;
import driver.db.Driver_Database;
import entity.Driver;
import ui.ContentMessagePanel;

public class Driver_ContentMessagePanel {
	private static ContentMessagePanel self;
	private static Driver[] array=null;
	
	private static boolean flag;//�������ɾ����ʻԱ��Ϣʱ�ļ�ʻԱ�����������
	//false����ɾ����·�߱���������ı���
	//true����ɾ����·�߱�������ڵ����ģ���������
	
	private static String del_drivernum;//��Ǳ�ɾ���ļ�ʻԱ���
	
	public static JTextField driver_deltext;//���ڼ�ʻԱɾ��
	
	public static JTextField driver_text1,driver_text2,driver_text3,driver_text4,
							driver_text5,driver_text6,driver_text7;//�����޸�
	
	public static JComboBox drivernum_bobox,driverbian_bobox;
	
	private static ItemListener driver_itemListener1=new ItemListener() {//��stationbobox2������Ӧ����

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("��ʻԱ����ѡ�У�");
				Thread t=new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String s=(String) drivernum_bobox.getSelectedItem();
						driver_text1.setText("");
						driver_text1.setText(s);
					}
					
				});
				t.start();
			}
			}
	};
	
	private static ItemListener driver_itemListener2=new ItemListener() {//��stationbobox2������Ӧ����

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("��ʻzheng����ѡ�У�");
				Thread t=new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println(1);
						String s=(String) driverbian_bobox.getSelectedItem();
						driver_text2.setText("");
						driver_text2.setText(s);
						System.out.println(2);
					}
				});
				t.start();
			}
			}
	};
	
	public static void setDriverInfo(ContentMessagePanel p_self) {//��ʻԱ��Ϣ����
		self=p_self;
		DebugInfo.DebugInfo("��ʼ���Ƴ��ӹ����Ҫ��ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		array=Driver_Database.getAllDriverInfo();//��ȡ���м�ʻԱ��������Ϣ
		if(array==null||array.length==0){
			self.removeAll();//�������������ȫ�����
			JOptionPane.showMessageDialog(self,"��ǰϵͳ�޼�ʻԱ��Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		String[] name= {"��ʻԱ���","��ʻԱ����","����״̬"};
		
		String[][] data=new String[array.length][3];//ֻ���沿����Ϣ
		
		for(int i=0;i<array.length;i++){
			data[i][0]=String.valueOf(array[i].peopleNumber);
			data[i][1]=array[i].peopleName;
			data[i][2]=String.valueOf(array[i].peopleState);
		}
		
		JTable table=new JTable(data,name);
		table.setOpaque(false);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setOpaque(false);
		
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("��ʻԱ��Ҫ��Ϣ���������");
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						int row = table.getSelectedRow();
				        int col = table.getSelectedColumn();
				        if(col==0){
				        	setDriverDetailInfo(self,array[row]);
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
		DebugInfo.DebugInfo("��ɻ��Ƽ�ʻԱ��Ҫ��ϢPanel");
		return;
	}

	public static void setDriverDetailInfo(ContentMessagePanel p_self,Driver dri){//�����ʻԱ����ϸ��Ϣ
		self=p_self;
		self.removeAll();
		
		//��ʾ�����ʻԱ����ϸ��Ϣ
		String[] name1={"��ʻԱ���","��ʻ֤���","��ʻԱ����","��ʻԱ���","��ʻԱ����","��ϵ��ʽ","����״̬"};
		String[][] data1=new String[1][7];
		data1[0]=dri.toStringArray();
		
		JTable table1=new JTable(data1,name1);
		table1.setOpaque(false);
		JScrollPane scroll1=new JScrollPane(table1);
		scroll1.setOpaque(false);
		
		self.add(scroll1);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("��ɻ��Ƽ�ʻԱ��ϸ��ϢPanel");
		return;
	}

	public static void addDriverInfo(ContentMessagePanel p_self){//���Ӽ�ʻԱ��Ϣ
		self=p_self;
		DebugInfo.DebugInfo("��ʼ�������Ӽ�ʻԱ��ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		JPanel p = new JPanel(new GridLayout(8,2,5,5));
		p.setOpaque(false);
		
		//��һ�� ��ʻԱ���
		JLabel lab1=new JLabel("��ʻԱ���");
		lab1.setFont(new Font("����",Font.PLAIN,30));
		lab1.setOpaque(false);
		p.add(lab1);
		
		driver_text1=new JTextField(20);
		driver_text1.setText("");
		driver_text1.setFont(new Font("����",Font.PLAIN,30));
		driver_text1.setEditable(true);//����Ϊ�ɱ༭
		driver_text1.setOpaque(false);
		p.add(driver_text1);
		
		//�ڶ��� ��ʻ֤���
		JLabel lab2=new JLabel("��ʻ֤���");
		lab2.setFont(new Font("����",Font.PLAIN,30));
		lab2.setOpaque(false);
		p.add(lab2);
		
		driver_text2=new JTextField();
		driver_text2.setText("");

		driver_text2.setFont(new Font("����",Font.PLAIN,30));
		driver_text2.setEditable(true);//����Ϊ�ɱ༭
		driver_text2.setOpaque(false);
		p.add(driver_text2);
		
		//������ ��ʻԱ����
		JLabel lab3=new JLabel("��ʻԱ����");
		lab3.setFont(new Font("����",Font.PLAIN,30));
		lab3.setOpaque(false);
		p.add(lab3);
		
		driver_text3=new JTextField();
		driver_text3.setText("");
		driver_text3.setFont(new Font("����",Font.PLAIN,30));
		driver_text3.setEditable(true);//����Ϊ�ɱ༭
		driver_text3.setOpaque(false);
		p.add(driver_text3);
		
		//������ ��ʻԱ���
		JLabel lab4=new JLabel("��ʻԱ���");
		lab4.setFont(new Font("����",Font.PLAIN,30));
		lab4.setOpaque(false);
		p.add(lab4);
		
		driver_text4=new JTextField();
		driver_text4.setText("");
		driver_text4.setFont(new Font("����",Font.PLAIN,30));
		driver_text4.setEditable(true);//����Ϊ�ɱ༭
		driver_text4.setOpaque(false);
		p.add(driver_text4);
		
		//������   ����
		JLabel lab5=new JLabel("����");
		lab5.setFont(new Font("����",Font.PLAIN,30));
		lab5.setOpaque(false);
		p.add(lab5);
		
		driver_text5=new JTextField();
		driver_text5.setText("");
		driver_text5.setFont(new Font("����",Font.PLAIN,30));
		driver_text5.setEditable(true);//����Ϊ�ɱ༭
		driver_text5.setOpaque(false);
		p.add(driver_text5);
		
		//������  ��ʻԱ��ϵ��ʽ
		JLabel lab6=new JLabel("��ϵ��ʽ");
		lab6.setFont(new Font("����",Font.PLAIN,30));
		lab6.setOpaque(false);
		p.add(lab6);
		
		driver_text6=new JTextField();
		driver_text6.setText("");

		driver_text6.setFont(new Font("����",Font.PLAIN,30));
		driver_text6.setEditable(true);//����Ϊ�ɱ༭
		driver_text6.setOpaque(false);
		p.add(driver_text6);
		
		//������  ��ʻԱ��ǰ״̬
		JLabel lab7=new JLabel("��ǰ����״̬");
		lab7.setFont(new Font("����",Font.PLAIN,30));
		lab7.setOpaque(false);
		p.add(lab7);
		
		driver_text7=new JTextField();
		driver_text7.setText("");
		driver_text7.setFont(new Font("����",Font.PLAIN,30));
		driver_text7.setEditable(true);//����Ϊ�ɱ༭
		driver_text7.setOpaque(false);
		p.add(driver_text7);
		
		//�ڰ��� ���Ӱ�ť
		p.add(new JLabel(" 		"));
		//��תվ��ʽ���޸�����
		
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
						DebugInfo.DebugInfo("��Ӽ�ʻԱ��Ϣ�İ�ť������");
						String[] s=new String[7]; 
						
						s[0]=driver_text1.getText();
						s[1]=driver_text2.getText();
						s[2]=driver_text3.getText();
						s[3]=driver_text4.getText();
						s[4]=driver_text5.getText();
						s[5]=driver_text6.getText();
						s[6]=driver_text7.getText();
						
						boolean flag=false;
						for(int i=0;i<7;i++){
							if(s[i].equals("")||s[i].length()==0){
								flag=true;
								break;
							}
						}
						
						if(flag){
							JOptionPane.showMessageDialog(self,"�뽫��Ϣ��д����","information",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3]+"#"+s[4]+"#"+s[5]+"#"+s[6];//�������ӵļ�ʻԱ��Ϣ
						System.out.println("���Ӽ�ʻԱ��Ϣ:"+S);
						
						String message=Driver_Database.addDriverInfo(S);//������Ϣ����ڽ�������
						JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
					
						driver_text1.setText("");
						driver_text2.setText("");
						driver_text3.setText("");
						driver_text4.setText("");
						driver_text5.setText("");
						driver_text6.setText("");
						driver_text7.setText("");
						
					}
				});
				t.start();
			}
		});
		
		self.add(p);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("��ɻ������Ӽ�ʻԱ��ϢPanel");
		return;
		
	}

	public static void modifyDriverInfo(ContentMessagePanel p_self){//�޸ļ�ʻԱ��Ϣ
		self=p_self;
		DebugInfo.DebugInfo("��ʼ�����޸ļ�ʻԱ��ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		if(newdriverbobox()==false){
			self.removeAll();//�������������ȫ�����
			JOptionPane.showMessageDialog(self,"ϵͳ�����޼�ʻԱ��Ϣ�����ܽ����޸ģ�����ȥ��Ӽ�ʻԱ��Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		JPanel p = new JPanel(new GridLayout(8,3,5,5));
		p.setOpaque(false);
		
		//��һ�� ��ʻԱ���
		JLabel lab1=new JLabel("��ʻԱ���");
		lab1.setFont(new Font("����",Font.PLAIN,25));
		lab1.setOpaque(false);
		p.add(lab1);
		
		driver_text1=new JTextField(20);
		driver_text1.setText("");

		driver_text1.setFont(new Font("����",Font.PLAIN,25));
		driver_text1.setEditable(false);//����Ϊ�ɱ༭
		driver_text1.setOpaque(false);
		p.add(driver_text1);
		p.add(drivernum_bobox);
		
		//�ڶ��� ��ʻ֤���
		JLabel lab2=new JLabel("��ʻ֤���");
		lab2.setFont(new Font("����",Font.PLAIN,25));
		lab2.setOpaque(false);
		p.add(lab2);
		
		driver_text2=new JTextField(20);
		driver_text2.setText("");

		driver_text2.setFont(new Font("����",Font.PLAIN,25));
		driver_text2.setEditable(false);//����Ϊ�ɱ༭
		driver_text2.setOpaque(false);
		p.add(driver_text2);
		p.add(driverbian_bobox);
		
		//������ ��ʻԱ����
		JLabel lab3=new JLabel("��ʻԱ����");
		lab3.setFont(new Font("����",Font.PLAIN,25));
		lab3.setOpaque(false);
		p.add(lab3);
		
		driver_text3=new JTextField(20);
		driver_text3.setText("");

		driver_text3.setFont(new Font("����",Font.PLAIN,25));
		driver_text3.setEditable(true);//����Ϊ�ɱ༭
		driver_text3.setOpaque(false);
		p.add(driver_text3);
		p.add(new JLabel("    "));
		
		//������ ��ʻԱ���
		JLabel lab4=new JLabel("��ʻԱ���");
		lab4.setFont(new Font("����",Font.PLAIN,25));
		lab4.setOpaque(false);
		p.add(lab4);
		
		driver_text4=new JTextField(20);
		driver_text4.setText("");

		driver_text4.setFont(new Font("����",Font.PLAIN,25));
		driver_text4.setEditable(true);//����Ϊ�ɱ༭
		driver_text4.setOpaque(false);
		p.add(driver_text4);
		p.add(new JLabel("    "));
		
		//������   ����
		JLabel lab5=new JLabel("����");
		lab5.setFont(new Font("����",Font.PLAIN,25));
		lab5.setOpaque(false);
		p.add(lab5);
		
		driver_text5=new JTextField(20);
		driver_text5.setText("");

		driver_text5.setFont(new Font("����",Font.PLAIN,25));
		driver_text5.setEditable(true);//����Ϊ�ɱ༭
		driver_text5.setOpaque(false);
		p.add(driver_text5);
		p.add(new JLabel("    "));
		
		//������  ��ʻԱ��ϵ��ʽ
		JLabel lab6=new JLabel("��ϵ��ʽ");
		lab6.setFont(new Font("����",Font.PLAIN,25));
		lab6.setOpaque(false);
		p.add(lab6);
		
		driver_text6=new JTextField(20);
		driver_text6.setText("");

		driver_text6.setFont(new Font("����",Font.PLAIN,25));
		driver_text6.setEditable(true);//����Ϊ�ɱ༭
		driver_text6.setOpaque(false);
		p.add(driver_text6);
		p.add(new JLabel("    "));
		
		//������  ��ʻԱ��ǰ״̬
		JLabel lab7=new JLabel("��ǰ����״̬");
		lab7.setFont(new Font("����",Font.PLAIN,25));
		lab7.setOpaque(false);
		p.add(lab7);
		
		driver_text7=new JTextField(20);
		driver_text7.setText("");

		driver_text7.setFont(new Font("����",Font.PLAIN,25));
		driver_text7.setEditable(true);//����Ϊ�ɱ༭
		driver_text7.setOpaque(false);
		p.add(driver_text7);
		p.add(new JLabel("    "));
		
		//�ڰ��� ���Ӱ�ť
		p.add(new JLabel(" 		"));
		p.add(new JLabel("    "));
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
						DebugInfo.DebugInfo("�޸ļ�ʻԱ��Ϣ�İ�ť������");
						String[] s=new String[7]; 
						
						s[0]=driver_text1.getText();
						s[1]=driver_text2.getText();
						s[2]=driver_text3.getText();
						s[3]=driver_text4.getText();
						s[4]=driver_text5.getText();
						s[5]=driver_text6.getText();
						s[6]=driver_text7.getText();
						
						boolean flag=false;
						for(int i=0;i<7;i++){
							if(s[i].equals("")||s[i].length()==0){
								flag=true;
								break;
							}
						}
						
						if(flag){
							JOptionPane.showMessageDialog(self,"�뽫��Ϣ��д����","information",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3]+"#"+s[4]+"#"+s[5]+"#"+s[6];//�������ӵļ�ʻԱ��Ϣ
						System.out.println("�޸ļ�ʻԱ��Ϣ:"+S);
						
						String message=Driver_Database.modifyDriverInfo(S);//������Ϣ����ڽ�������
						JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
					
						driver_text1.setText("");
						driver_text2.setText("");
						driver_text3.setText("");
						driver_text4.setText("");
						driver_text5.setText("");
						driver_text6.setText("");
						driver_text7.setText("");
						
					}
				});
				t.start();
			}
		});
		
		self.add(p);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("��ɻ����޸ļ�ʻԱ��ϢPanel");
		return;
	}

	public static void delDriverInfo(ContentMessagePanel p_self){//ɾ����ʻԱ��Ϣ
		self=p_self;
		DebugInfo.DebugInfo("��ʼ����ɾ��·����ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		flag=true;//��ʼ��Ϊɾ��·�߱���������ı���
		del_drivernum=" ";//��ʼ��
		
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
		
		driver_deltext=new JTextField(20);
		driver_deltext.setText("");

		driver_deltext.setFont(new Font("����",Font.PLAIN,30));
		driver_deltext.setEditable(true);
		driver_deltext.setOpaque(false);
		spanel.add(driver_deltext);
		
		JButton b=new JButton("����");
		b.setFont(new Font("����",Font.PLAIN,18));
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
						String s=driver_deltext.getText();
						Driver[] array=Driver_Database.getMohuDriverInfo(s);
						
						if(array==null||array.length==0){
							JOptionPane.showMessageDialog(self,"��ϵͳ�����޸ü�ʻԱ��Ϣ!","information",JOptionPane.INFORMATION_MESSAGE);
							driver_deltext.setText("");
							return;
						}
						else{
							cpanel.removeAll();
							
							String[] name= {"��ʻԱ���","��ʻԱ����","����״̬"};
							
							String[][] data=new String[array.length][3];
							
							for(int i=0;i<array.length;i++){
								data[i][0]=String.valueOf(array[i].peopleNumber);
								data[i][1]=array[i].peopleName;
								data[i][2]=String.valueOf(array[i].peopleState);
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
									        	del_drivernum=table.getValueAt(row, 0).toString();//��ȡ��ǰ����ļ�ʻԱ�ı��
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
												del_drivernum=driver_deltext.getText();//��ȡ��ǰ�ı����е�·�߱��
											}
											String message=Driver_Database.delDriverInfo(del_drivernum);
											
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											driver_deltext.setText("");//����ı���������
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
		DebugInfo.DebugInfo("��ɻ���ɾ����ʻԱ��ϢPanel");
		return;
		
		
		
	}
	
	public static boolean newdriverbobox(){//��driver bobox��������
		drivernum_bobox=new JComboBox();//��ʻԱ���
		driverbian_bobox=new JComboBox();//��ʻ֤���
		
		drivernum_bobox.setFont(new Font("����",Font.PLAIN,25));drivernum_bobox.setOpaque(false);
		driverbian_bobox.setFont(new Font("����",Font.PLAIN,25));driverbian_bobox.setOpaque(false);
		
		drivernum_bobox.setSelectedIndex(-1);//���ò�ѡ��
		driverbian_bobox.setSelectedIndex(-1);//���ò�ѡ��
		
		//��ȡ��Ϣ
		Driver[] dri=Driver_Database.getAllDriverInfo();
		if(dri==null||dri.length==0){
			return false;//�޼�ʻԱ��Ϣ
		}
		String[] s1=new String[dri.length];
		String[] s2=new String[dri.length];
		for(int i=0;i<dri.length;i++){
			s1[i]=String.valueOf(dri[i].peopleNumber);//��ȡ��ʻԱ���
			s2[i]=dri[i].driverNumber;//��ȡ��ʻ֤���
		}
		
		//����Ϣ��ӵ��б���
		DefaultComboBoxModel<String> drivernum_model=new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> driverbian_model=new DefaultComboBoxModel<String>();
		drivernum_model.addElement("");
		for(int i=1;i<s1.length;i++){
			drivernum_model.addElement(s1[i]);
		}
		driverbian_model.addElement("");
		for(int i=1;i<s2.length;i++){
			driverbian_model.addElement(s2[i]);
		}
		drivernum_bobox.setModel(drivernum_model);
		driverbian_bobox.setModel(driverbian_model);
		
		//�����Ӧ����
		drivernum_bobox.addItemListener(driver_itemListener1);
		driverbian_bobox.addItemListener(driver_itemListener2);
		return true;
	}
}