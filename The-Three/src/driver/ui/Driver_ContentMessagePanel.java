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
	
	private static DocumentListener documentListener2=new DocumentListener() {

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
					moddriver_comboboxChange();
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
					moddriver_comboboxChange();
				}
			});
			t.start();
		}
		
	};
	
	private static ItemListener itemListener2=new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			DebugInfo.DebugInfo("Combobox�ѡ�У�");
			Thread t=new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					moddriver_textChange();
				}
				
			});
			t.start();
		}};
	
	public static void setDriverInfo(ContentMessagePanel p_self) {//��ʻԱ��Ϣ����
		self=p_self;
		DebugInfo.DebugInfo("��ʼ���Ƴ��ӹ����Ҫ��ϢPanel");
		self.removeAll();//�������������ȫ�����
		
		String[] name= {"��ʻԱ���","��ʻԱ����","����״̬"};
		array=Driver_Database.getAllDriverInfo();//��ȡ���м�ʻԱ��������Ϣ
		
		if(array==null||array.length==0){
			JOptionPane.showMessageDialog(self,"��ǰϵͳ�޼�ʻԱ��Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
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
		lab1.setFont(new Font("����",Font.PLAIN,20));
		lab1.setOpaque(false);
		p.add(lab1);
		
		self.driver_addtext1=new JTextField(20);
		self.driver_addtext1.setEditable(true);//����Ϊ�ɱ༭
		self.driver_addtext1.setOpaque(false);
		p.add(self.driver_addtext1);
		
		//�ڶ��� ��ʻ֤���
		JLabel lab2=new JLabel("��ʻ֤���");
		lab2.setFont(new Font("����",Font.PLAIN,20));
		lab2.setOpaque(false);
		p.add(lab2);
		
		self.driver_addtext2=new JTextField(20);
		self.driver_addtext2.setEditable(true);//����Ϊ�ɱ༭
		self.driver_addtext2.setOpaque(false);
		p.add(self.driver_addtext2);
		
		//������ ��ʻԱ����
		JLabel lab3=new JLabel("��ʻԱ����");
		lab3.setFont(new Font("����",Font.PLAIN,20));
		lab3.setOpaque(false);
		p.add(lab3);
		
		self.driver_addtext3=new JTextField(20);
		self.driver_addtext3.setEditable(true);//����Ϊ�ɱ༭
		self.driver_addtext3.setOpaque(false);
		p.add(self.driver_addtext3);
		
		//������ ��ʻԱ���
		JLabel lab4=new JLabel("��ʻԱ���");
		lab4.setFont(new Font("����",Font.PLAIN,20));
		lab4.setOpaque(false);
		p.add(lab4);
		
		self.driver_addtext4=new JTextField(20);
		self.driver_addtext4.setEditable(true);//����Ϊ�ɱ༭
		self.driver_addtext4.setOpaque(false);
		p.add(self.driver_addtext4);
		
		//������   ����
		JLabel lab5=new JLabel("����");
		lab5.setFont(new Font("����",Font.PLAIN,20));
		lab5.setOpaque(false);
		p.add(lab5);
		
		self.driver_addtext5=new JTextField(20);
		self.driver_addtext5.setEditable(true);//����Ϊ�ɱ༭
		self.driver_addtext5.setOpaque(false);
		p.add(self.driver_addtext5);
		
		//������  ��ʻԱ��ϵ��ʽ
		JLabel lab6=new JLabel("��ϵ��ʽ");
		lab6.setFont(new Font("����",Font.PLAIN,20));
		lab6.setOpaque(false);
		p.add(lab6);
		
		self.driver_addtext6=new JTextField(20);
		self.driver_addtext6.setEditable(true);//����Ϊ�ɱ༭
		self.driver_addtext6.setOpaque(false);
		p.add(self.driver_addtext6);
		
		//������  ��ʻԱ��ǰ״̬
		JLabel lab7=new JLabel("��ǰ����״̬");
		lab7.setFont(new Font("����",Font.PLAIN,20));
		lab7.setOpaque(false);
		p.add(lab7);
		
		self.driver_addtext7=new JTextField(20);
		self.driver_addtext7.setEditable(true);//����Ϊ�ɱ༭
		self.driver_addtext7.setOpaque(false);
		p.add(self.driver_addtext7);
		
		//�ڰ��� ���Ӱ�ť
		p.add(new JLabel(" 		"));
		//��תվ��ʽ���޸�����
		
		JButton b=new JButton("ȷ��");
		b.setFont(new Font("����",Font.PLAIN,14));
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
						
						s[0]=self.driver_addtext1.getText();
						s[1]=self.driver_addtext2.getText();
						s[2]=self.driver_addtext3.getText();
						s[3]=self.driver_addtext4.getText();
						s[4]=self.driver_addtext5.getText();
						s[5]=self.driver_addtext6.getText();
						s[6]=self.driver_addtext7.getText();
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3]+"#"+s[4]+"#"+s[5]+"#"+s[6];//�������ӵļ�ʻԱ��Ϣ
						System.out.println("���Ӽ�ʻԱ��Ϣ:"+S);
						
						String message=Driver_Database.addDriverInfo(S);//������Ϣ����ڽ�������
						JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
					
						self.driver_addtext1.setText("");
						self.driver_addtext2.setText("");
						self.driver_addtext3.setText("");
						self.driver_addtext4.setText("");
						self.driver_addtext5.setText("");
						self.driver_addtext6.setText("");
						self.driver_addtext7.setText("");
						
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
		
		self.driver_mpanel=new JPanel(new GridLayout(1,2,0,0));
		self.driver_mpanel.setOpaque(false);
		
		//��һ��
		JPanel p = new JPanel(new GridLayout(8,2,5,5));
		p.setOpaque(false);
		
		//��һ�� ��ʻԱ���
		JLabel lab1=new JLabel("��ʻԱ���");
		lab1.setFont(new Font("����",Font.PLAIN,20));
		lab1.setOpaque(false);
		p.add(lab1);
		
		self.driver_modifytext1=new JTextField(20);
		self.driver_modifytext1.setEditable(true);//����Ϊ�ɱ༭
		self.driver_modifytext1.setOpaque(false);
		p.add(self.driver_modifytext1);
		
		//���ı���������Ӧ����
		Document document2 = self.driver_modifytext1.getDocument();
		document2.addDocumentListener(documentListener2);
		
		//�ڶ��� ��ʻ֤���
		JLabel lab2=new JLabel("��ʻ֤���");
		lab2.setFont(new Font("����",Font.PLAIN,20));
		lab2.setOpaque(false);
		p.add(lab2);
		
		self.driver_modifytext2=new JTextField(20);
		self.driver_modifytext2.setEditable(true);//����Ϊ�ɱ༭
		self.driver_modifytext2.setOpaque(false);
		p.add(self.driver_modifytext2);
		
		//������ ��ʻԱ����
		JLabel lab3=new JLabel("��ʻԱ����");
		lab3.setFont(new Font("����",Font.PLAIN,20));
		lab3.setOpaque(false);
		p.add(lab3);
		
		self.driver_modifytext3=new JTextField(20);
		self.driver_modifytext3.setEditable(true);//����Ϊ�ɱ༭
		self.driver_modifytext3.setOpaque(false);
		p.add(self.driver_modifytext3);
		
		//������ ��ʻԱ���
		JLabel lab4=new JLabel("��ʻԱ���");
		lab4.setFont(new Font("����",Font.PLAIN,20));
		lab4.setOpaque(false);
		p.add(lab4);
		
		self.driver_modifytext4=new JTextField(20);
		self.driver_modifytext4.setEditable(true);//����Ϊ�ɱ༭
		self.driver_modifytext4.setOpaque(false);
		p.add(self.driver_modifytext4);
		
		//������   ����
		JLabel lab5=new JLabel("����");
		lab5.setFont(new Font("����",Font.PLAIN,20));
		lab5.setOpaque(false);
		p.add(lab5);
		
		self.driver_modifytext5=new JTextField(20);
		self.driver_modifytext5.setEditable(true);//����Ϊ�ɱ༭
		self.driver_modifytext5.setOpaque(false);
		p.add(self.driver_modifytext5);
		
		//������  ��ʻԱ��ϵ��ʽ
		JLabel lab6=new JLabel("��ϵ��ʽ");
		lab6.setFont(new Font("����",Font.PLAIN,20));
		lab6.setOpaque(false);
		p.add(lab6);
		
		self.driver_modifytext6=new JTextField(20);
		self.driver_modifytext6.setEditable(true);//����Ϊ�ɱ༭
		self.driver_modifytext6.setOpaque(false);
		p.add(self.driver_modifytext6);
		
		//������  ��ʻԱ��ǰ״̬
		JLabel lab7=new JLabel("��ǰ����״̬");
		lab7.setFont(new Font("����",Font.PLAIN,20));
		lab7.setOpaque(false);
		p.add(lab7);
		
		self.driver_modifytext7=new JTextField(20);
		self.driver_modifytext7.setEditable(true);//����Ϊ�ɱ༭
		self.driver_modifytext7.setOpaque(false);
		p.add(self.driver_modifytext7);
		
		//�ڰ��� ���Ӱ�ť
		p.add(new JLabel(" 		"));
		//��תվ��ʽ���޸�����
		
		JButton b=new JButton("ȷ��");
		b.setFont(new Font("����",Font.PLAIN,14));
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
						
						s[0]=self.driver_modifytext1.getText();
						s[1]=self.driver_modifytext2.getText();
						s[2]=self.driver_modifytext3.getText();
						s[3]=self.driver_modifytext4.getText();
						s[4]=self.driver_modifytext5.getText();
						s[5]=self.driver_modifytext6.getText();
						s[6]=self.driver_modifytext7.getText();
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3]+"#"+s[4]+"#"+s[5]+"#"+s[6];//�������ӵļ�ʻԱ��Ϣ
						System.out.println("�޸ļ�ʻԱ��Ϣ:"+S);
						
						String message=Driver_Database.modifyDriverInfo(S);//������Ϣ����ڽ�������
						JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
					
						self.driver_modifytext1.setText("");
						self.driver_modifytext2.setText("");
						self.driver_modifytext3.setText("");
						self.driver_modifytext4.setText("");
						self.driver_modifytext5.setText("");
						self.driver_modifytext6.setText("");
						self.driver_modifytext7.setText("");
						
					}
				});
				t.start();
			}
		});
		self.driver_mpanel.add(p);
		
		//�ڶ���
		self.driver_panel2=new JPanel();
		self.driver_panel2.setOpaque(false);
		
		self.driver_bobox=new JComboBox();
		self.driver_bobox.setOpaque(false);
		self.driver_bobox.setBorder(BorderFactory.createTitledBorder("��ʻԱ���ģ����ѯ���"));
		self.driver_bobox.setSelectedIndex(-1);//���ò�ѡ��
		
		//��Ӽ�������
		self.driver_bobox.addItemListener(itemListener2);
		
		self.driver_panel2.add(self.driver_bobox);
		
		self.driver_mpanel.add(self.driver_panel2);
		
		self.add(self.driver_mpanel);
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
		
		//��������panel
		JPanel spanel=new JPanel(new GridLayout(1,2,0,0));
		spanel.setOpaque(false);
		panel.add(spanel,BorderLayout.NORTH);
		
		//������ʾ���������
		JPanel cpanel=new JPanel();
		cpanel.setOpaque(false);
		panel.add(cpanel,BorderLayout.CENTER);
		
		self.driver_deltext=new JTextField(20);
		self.driver_deltext.setEditable(true);
		self.driver_deltext.setOpaque(false);
		spanel.add(self.driver_deltext);
		
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
						String s=self.driver_deltext.getText();
						Driver[] array=Driver_Database.getMohuDriverInfo(s);
						
						if(array==null||array.length==0){
							//JOptionPane.showMessageDialog(self,"ϵͳ�����޼�ʻԱ��Ϣ","information",JOptionPane.INFORMATION_MESSAGE);
							cpanel.removeAll();
							cpanel.add(new JLabel("��ϵͳ�����޸ü�ʻԱ��Ϣ!"));
							cpanel.revalidate();
							cpanel.repaint();
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
												del_drivernum=self.driver_deltext.getText();//��ȡ��ǰ�ı����е�·�߱��
											}
											String message=Driver_Database.delDriverInfo(del_drivernum);
											
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											self.driver_deltext.setText("");//����ı���������
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
	
	public static void moddriver_textChange() {
		Document document2=self.driver_modifytext1.getDocument();
		document2.removeDocumentListener(documentListener2);
		String s=(String) self.driver_bobox.getSelectedItem();
		self.driver_modifytext1.setText(s);
		document2.addDocumentListener(documentListener2);
	}
	
	public static void moddriver_comboboxChange(){
		self.driver_bobox.removeItemListener(itemListener2);
		
		String s=self.driver_modifytext1.getText();
		
		String[] temp_driver=Driver_Database.getMohuDriverNumInfo(s);//��������ģ����ѯ�õ��ļ�ʻԱ���
		
		DefaultComboBoxModel<String> driver_model=new DefaultComboBoxModel<String>();
		for(int i=0;i<temp_driver.length;i++){
			driver_model.addElement(temp_driver[i]);
		}
		
		self.driver_bobox.setModel(driver_model);
		
		//self.car_bobox.setSelectedIndex(-1);//��ģ����ѯ����ı��ˣ�car����������ϢҲ����Ҫ�ı�
		self.driver_bobox.addItemListener(itemListener2);
	}
}