package car.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
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

public class Car_ContentMessagePanel {
	private static Car[] array=null;
	private static ContentMessagePanel self;
	
	private static boolean flag;//�������ɾ��������Ϣʱ�ĳ��������������
	//false����ɾ���ĳ�������������ı���
	//true����ɾ���ĳ�����������ڵ����ģ���������
	
	private static String del_carnum;//��Ǳ�ɾ����·�߱��
	
	public static JTextField car_text1,car_text2,car_text3,car_text4;
	public static JComboBox car_bobox;
	public static JPanel car_mpanel,car_panel2;
	public static JTextField car_deltext;//���ڳ���ɾ��
	
	public static void comboboxChange(){
		car_bobox.removeItemListener(itemListener);
		String s=car_text1.getText();
		String[] temp_car=Car_Database.getCarNumber(s);//��������ģ����ѯ�õ��ĳ��ƺ���Ϣ
		DefaultComboBoxModel<String> car_model=new DefaultComboBoxModel<String>();
		for(int i=0;i<temp_car.length;i++){
			car_model.addElement(temp_car[i]);
		}
		car_bobox.setModel(car_model);
		car_bobox.addItemListener(itemListener);
	}
	public static void textChange() {
		Document document=car_text1.getDocument();
		document.removeDocumentListener(documentListener);
		String s=(String) car_bobox.getSelectedItem();
		car_text1.setText(s);
		document.addDocumentListener(documentListener);
	}

	private static DocumentListener documentListener=new DocumentListener() {

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			DebugInfo.DebugInfo("�ı����������ӣ�");
			Thread t=new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					comboboxChange();
				}
			});
			t.start();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			DebugInfo.DebugInfo("�ı������ݼ��٣�");
			Thread t=new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					comboboxChange();
				}
			});
			t.start();
		}
		
	};
	private static ItemListener itemListener=new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			DebugInfo.DebugInfo("Combobox�ѡ�У�");
			Thread t=new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					textChange();
				}
				
			});
			t.start();
		}};
		
	public static void setCarInfo(ContentMessagePanel p_self) {//���ӹ����Ҫ��Ϣ
		self=p_self;
		DebugInfo.DebugInfo("��ʼ���Ƴ��ӹ����Ҫ��ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		String[] name= {"���ƺ�","��ʻԱ1","��ʻԱ2","·��"};
		array=Car_Database.getCarInfo();
		
		if(array==null||array.length==0) {
			JOptionPane.showMessageDialog(self,"��ǰϵͳ�޿��ó�����Ϣ��","information",JOptionPane.INFORMATION_MESSAGE);
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
		DebugInfo.DebugInfo("��ʼ���Ƴ�����ɾ��Panel");
		self=p_self;
		self.removeAll();//������������������

		JPanel panel=new JPanel(new GridLayout(6,2,5,5));//��������
		panel.setOpaque(false);
		
		//��һ�� �������
		JLabel label1=new JLabel("�������");
		label1.setFont(new Font("����",Font.PLAIN,20));
		label1.setOpaque(false);
		panel.add(label1);
		
		car_text1=new JTextField(20);
		car_text1.setEditable(true);//����Ϊ�ɱ༭
		car_text1.setOpaque(false);
		panel.add(car_text1);
		
		//�ڶ��� ��ʻԱ1��
		JLabel label2=new JLabel("��ʻԱһ�ű��");
		label2.setFont(new Font("����",Font.PLAIN,20));
		label2.setOpaque(false);
		panel.add(label2);
		
		car_text2=new JTextField();
		car_text2.setEditable(true);//����Ϊ�ɱ༭
		car_text2.setOpaque(false);
		panel.add(car_text2);
		
		//������ ��ʻԱ2��
		JLabel label3=new JLabel("��ʻԱ���ű��");
		label3.setFont(new Font("����",Font.PLAIN,20));
		label3.setOpaque(false);
		panel.add(label3);
		
		car_text3=new JTextField();
		car_text3.setEditable(true);//����Ϊ�ɱ༭
		car_text3.setOpaque(false);
		panel.add(car_text3);
		
		//������ ·�߱��
		JLabel label4=new JLabel("·�߱��");
		label4.setFont(new Font("����",Font.PLAIN,20));
		label4.setOpaque(false);
		panel.add(label4);
		
		car_text4=new JTextField();
		car_text4.setEditable(true);//����Ϊ�ɱ༭
		car_text4.setOpaque(false);
		panel.add(car_text4);
		
		//������ ���ӿ���
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ ��Ӱ�ť
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
		
		//��ʾ������Ϣ
		String[] name1= {"���ƺ�","��ʻԱ1","��ʻԱ2","·��"};
		String[][] data1=new String[1][4];
		data1[0]=carDetail.toStringArray();
		JTable table1=new JTable(data1,name1);
		table1.setOpaque(false);
		JScrollPane scroll1=new JScrollPane(table1);
		scroll1.setOpaque(false);
		
		//��ʾ��ʻԱ����ϸ��Ϣ
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
		
		//��ʾ·�ߵ���ϸ��Ϣ
		String[] name3={"·�߱��","��ʼվ��","�յ�վ","��תվ��"};
		Route routeInfo=new Route();
		routeInfo=Route_Database.getRouteInfo(carDetail.routeNumber);//��ѯ·�ߵ���ϸ��Ϣ
		
		String[][] data3=new String[1][4];
		data3[0]=routeInfo.toStringArray();
		
		JTable table3=new JTable(data3,name3);
		table3.setOpaque(false);
		JScrollPane scroll3=new JScrollPane(table3);
		scroll3.setOpaque(false);
		
		JPanel panel=new JPanel(new GridLayout(1,3,0,0));//һ������ ��ʾ�б�
		panel.setOpaque(false);
		
		panel.add(scroll1);
		panel.add(scroll2);
		panel.add(scroll3);
		
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

		car_mpanel=new JPanel(new GridLayout(1,2,0,0));//һ������
		car_mpanel.setOpaque(false);
		
		//��һ��
		JPanel panel=new JPanel(new GridLayout(6,2,5,5));//��������
		panel.setOpaque(false);
		
		//��һ�� �������
		JLabel label1=new JLabel("�������");
		label1.setFont(new Font("����",Font.PLAIN,20));
		label1.setOpaque(false);
		panel.add(label1);
		
		car_text1=new JTextField(20);
		car_text1.setEditable(true);//����Ϊ�ɱ༭
		car_text1.setOpaque(false);
		panel.add(car_text1);
		
		//���ı���������Ӧ����
		Document document = car_text1.getDocument();
		document.addDocumentListener(documentListener);
		
		//�ڶ��� ��ʻԱ1��
		JLabel label2=new JLabel("��ʻԱһ�ű��");
		label2.setFont(new Font("����",Font.PLAIN,20));
		label2.setOpaque(false);
		panel.add(label2);
		
		car_text2=new JTextField();
		car_text2.setEditable(true);//����Ϊ�ɱ༭
		car_text2.setOpaque(false);
		panel.add(car_text2);
		
		//������ ��ʻԱ2��
		JLabel label3=new JLabel("��ʻԱ���ű��");
		label3.setFont(new Font("����",Font.PLAIN,20));
		label3.setOpaque(false);
		panel.add(label3);
		
		car_text3=new JTextField();
		car_text3.setEditable(true);//����Ϊ�ɱ༭
		car_text3.setOpaque(false);
		panel.add(car_text3);
		
		//������ ·�߱��
		JLabel label4=new JLabel("·�߱��");
		label4.setFont(new Font("����",Font.PLAIN,20));
		label4.setOpaque(false);
		panel.add(label4);
		
		car_text4=new JTextField();
		car_text4.setEditable(true);//����Ϊ�ɱ༭
		car_text4.setOpaque(false);
		panel.add(car_text4);
		
		//������ ���ӿ���
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ ��Ӱ�ť
		panel.add(new JLabel("    "));
		JButton button=new JButton();
		
		button=new JButton("���");
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
		
		car_mpanel.add(panel);
		
		//�ڶ���
		car_panel2=new JPanel();
		car_panel2.setOpaque(false);
		
		car_bobox=new JComboBox();
		car_bobox.setOpaque(false);
		car_bobox.setBorder(BorderFactory.createTitledBorder("���ƺ�ģ����ѯ���"));
		car_bobox.setSelectedIndex(-1);//���ò�ѡ��
		
		//��Ӽ�������
		car_bobox.addItemListener(itemListener);
		
		car_panel2.add(car_bobox);
		car_mpanel.add(car_panel2);
		
		self.add(car_mpanel);
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
		
		
		//��������panel
		JPanel spanel=new JPanel(new GridLayout(1,2,0,0));
		spanel.setOpaque(false);
		panel.add(spanel,BorderLayout.NORTH);
		
		//������ʾ���������
		JPanel cpanel=new JPanel();
		cpanel.setOpaque(false);
		panel.add(cpanel,BorderLayout.CENTER);
		
		car_deltext=new JTextField(20);
		car_deltext.setEditable(true);
		car_deltext.setOpaque(false);
		spanel.add(car_deltext);
		
		JButton b=new JButton("����");
		b.setFont(new Font("����",Font.PLAIN,14));
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
							cpanel.removeAll();
							cpanel.add(new JLabel("��ϵͳ�����޸ó�����Ϣ!"));
							cpanel.revalidate();
							cpanel.repaint();
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
							bb.setFont(new Font("����",Font.PLAIN,14));
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
												del_carnum=car_deltext.getText();//��ȡ��ǰ�ı����е�·�߱��
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
	
}
