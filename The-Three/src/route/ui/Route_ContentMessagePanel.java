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

import route.db.Route_Database;
import client.DebugInfo;
import ui.ContentMessagePanel;
import entity.Route;

public class Route_ContentMessagePanel {
	private static Route[] array=null;
	private static ContentMessagePanel self;
	private static boolean flag;//�������ɾ��·����Ϣʱ��·�߱����������
	//false����ɾ����·�߱���������ı���
	//true����ɾ����·�߱�������ڵ����ģ���������
	private static String del_routenum;//��Ǳ�ɾ����·�߱��
	
	private static DocumentListener documentListener1=new DocumentListener() {

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
					modroute_comboboxChange();
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
					modroute_comboboxChange();
				}
			});
			t.start();
		}
		
	};
	private static ItemListener itemListener1=new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			DebugInfo.DebugInfo("Combobox�ѡ�У�");
			Thread t=new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					modroute_textChange();
				}
				
			});
			t.start();
		}};
	
	public static void setRouteInfo(ContentMessagePanel p_self) {//�鿴·����Ϣ ������GPS��Ϣ
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
		
		flag=true;//��ʼ��Ϊɾ��·�߱���������ı���
		del_routenum=" ";//��ʼ��
		
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
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
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
												del_routenum=self.route_deltext.getText();//��ȡ��ǰ�ı����е�·�߱��
											}
											String message=Route_Database.delRouteInfo(del_routenum);
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											self.route_deltext.setText("");//����ı���������
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
		
		JPanel p = new JPanel(new GridLayout(5,2,5,5));
		p.setOpaque(false);
		
		//��һ�� ·�߱��
		JLabel lab1=new JLabel("·�߱��");
		lab1.setFont(new Font("����",Font.PLAIN,20));
		lab1.setOpaque(false);
		p.add(lab1);
		
		self.route_addtext1=new JTextField(20);
		self.route_addtext1.setEditable(true);//����Ϊ�ɱ༭
		self.route_addtext1.setOpaque(false);
		p.add(self.route_addtext1);
		
		//�ڶ��� ��ʼվ̨
		JLabel lab2=new JLabel("��ʼվ̨");
		lab2.setFont(new Font("����",Font.PLAIN,20));
		lab2.setOpaque(false);
		p.add(lab2);
		
		self.route_addtext2=new JTextField(20);
		self.route_addtext2.setEditable(true);//����Ϊ�ɱ༭
		self.route_addtext2.setOpaque(false);
		p.add(self.route_addtext2);
		
		//������ �յ�վ
		JLabel lab3=new JLabel("�յ�վ");
		lab3.setFont(new Font("����",Font.PLAIN,20));
		lab3.setOpaque(false);
		p.add(lab3);
		
		self.route_addtext3=new JTextField(20);
		self.route_addtext3.setEditable(true);//����Ϊ�ɱ༭
		self.route_addtext3.setOpaque(false);
		p.add(self.route_addtext3);
		
		//������ ��תվ
		JLabel lab4=new JLabel("��תվ");
		lab4.setFont(new Font("����",Font.PLAIN,20));
		lab4.setOpaque(false);
		p.add(lab4);
		
		self.route_addtext4=new JTextField(20);
		self.route_addtext4.setEditable(true);//����Ϊ�ɱ༭
		self.route_addtext4.setOpaque(false);
		p.add(self.route_addtext4);
		
		//������ ���Ӱ�ť
		p.add(new JLabel("�����תվ��Ϣ��ʽ���ൺ-�ɶ�-����"));
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
						DebugInfo.DebugInfo("���·����Ϣ�İ�ť������");
						String[] s=new String[4]; 
						s[0]=self.route_addtext1.getText();
						s[1]=self.route_addtext2.getText();
						s[2]=self.route_addtext3.getText();
						s[3]=self.route_addtext4.getText();
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3];//�������ӵ�·����Ϣ
						System.out.println("����·����Ϣ:"+S);
						
						String message=Route_Database.addRouteInfo(S);//������Ϣ����ڽ�������
						JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
					
						self.route_addtext1.setText("");
						self.route_addtext2.setText("");
						self.route_addtext3.setText("");
						self.route_addtext4.setText("");
						
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
		
		self.route_mpanel=new JPanel(new GridLayout(1,2,0,0));
		self.route_mpanel.setOpaque(false);
		
		//��һ��
		JPanel panel=new JPanel(new GridLayout(5,2,5,5));
		panel.setOpaque(false);
		
		//��һ�� ·�߱��
		JLabel label1=new JLabel("·�߱��");
		label1.setFont(new Font("����",Font.PLAIN,20));
		label1.setOpaque(false);
		panel.add(label1);
		
		self.route_modifytext1=new JTextField(20);
		self.route_modifytext1.setEditable(true);//����Ϊ�ɱ༭
		self.route_modifytext1.setOpaque(false);
		panel.add(self.route_modifytext1);
		
		//���ı���������Ӧ����
		Document document = self.route_modifytext1.getDocument();
		document.addDocumentListener(documentListener1);
		
		//�ڶ��� ��ʼվ̨
		JLabel label2=new JLabel("��ʼվ̨");
		label2.setFont(new Font("����",Font.PLAIN,20));
		label2.setOpaque(false);
		panel.add(label2);
		
		self.route_modifytext2=new JTextField(20);
		self.route_modifytext2.setEditable(true);//����Ϊ�ɱ༭
		self.route_modifytext2.setOpaque(false);
		panel.add(self.route_modifytext2);
		
		//������ �յ�վ
		JLabel label3=new JLabel("�յ�վ");
		label3.setFont(new Font("����",Font.PLAIN,20));
		label3.setOpaque(false);
		panel.add(label3);
		
		self.route_modifytext3=new JTextField(20);
		self.route_modifytext3.setEditable(true);//����Ϊ�ɱ༭
		self.route_modifytext3.setOpaque(false);
		panel.add(self.route_modifytext3);
		
		//������ ��תվ
		JLabel label4=new JLabel("��תվ");
		label4.setFont(new Font("����",Font.PLAIN,20));
		label4.setOpaque(false);
		panel.add(label4);
		
		self.route_modifytext4=new JTextField(20);
		self.route_modifytext4.setEditable(true);//����Ϊ�ɱ༭
		self.route_modifytext4.setOpaque(false);
		panel.add(self.route_modifytext4);
		
		//������ ���Ӱ�ť
		panel.add(new JLabel("�޸���תվ��Ϣ��ʽ���ൺ-�ɶ�-����"));
		//��תվ��ʽ���޸�����
		
		JButton b=new JButton("ȷ��");
		b.setFont(new Font("����",Font.PLAIN,14));
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
						s[0]=self.route_modifytext1.getText();
						s[1]=self.route_modifytext2.getText();
						s[2]=self.route_modifytext3.getText();
						s[3]=self.route_modifytext4.getText();
						
						String message=Route_Database.modRouteInfo(s);//������Ϣ����ڽ�������
						JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
					
						self.route_modifytext1.setText("");
						self.route_modifytext2.setText("");
						self.route_modifytext3.setText("");
						self.route_modifytext4.setText("");
					}
				});
				t.start();
			}	
		});
		self.route_mpanel.add(panel);
		
		//�ڶ���
		self.route_panel2=new JPanel();
		self.route_panel2.setOpaque(false);
		
		self.route_bobox=new JComboBox();
		self.route_bobox.setOpaque(false);
		self.route_bobox.setBorder(BorderFactory.createTitledBorder("·�߱��ģ����ѯ���"));
		self.route_bobox.setSelectedIndex(-1);//���ò�ѡ��
		
		//��Ӽ�������
		self.route_bobox.addItemListener(itemListener1);
		
		self.route_panel2.add(self.route_bobox);
		
		self.route_mpanel.add(self.route_panel2);
	
		
		self.add(self.route_mpanel);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("��ɻ����޸�·����ϢPanel");
		return;
	}
	
	public static void modroute_textChange() {
		Document document=self.route_modifytext1.getDocument();
		document.removeDocumentListener(documentListener1);
		String s=(String) self.route_bobox.getSelectedItem();
		self.route_modifytext1.setText(s);
		document.addDocumentListener(documentListener1);
	}
	
	public static void modroute_comboboxChange(){
		self.route_bobox.removeItemListener(itemListener1);
		String s=self.route_modifytext1.getText();
		String[] temp_route=Route_Database.getMohuRouteNumInfo(s);//��������ģ����ѯ�õ���·�߱��
		
		DefaultComboBoxModel<String> route_model=new DefaultComboBoxModel<String>();
		for(int i=0;i<temp_route.length;i++){
			route_model.addElement(temp_route[i]);
		}
		self.route_bobox.setModel(route_model);
		//self.car_bobox.setSelectedIndex(-1);//��ģ����ѯ����ı��ˣ�car����������ϢҲ����Ҫ�ı�
		self.route_bobox.addItemListener(itemListener1);
	}
}
