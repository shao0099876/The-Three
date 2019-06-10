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
	
	private static boolean flag;//用来标记删除驾驶员信息时的驾驶员编号来自哪里
	//false表明删除的路线编号来自于文本框
	//true表明删除的路线编号来自于点击的模糊搜索结果
	
	private static String del_drivernum;//标记被删除的驾驶员编号
	
	private static DocumentListener documentListener2=new DocumentListener() {

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			DebugInfo.DebugInfo("文本框内容增加！");
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
			DebugInfo.DebugInfo("文本框内容减少！");
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
			DebugInfo.DebugInfo("Combobox项被选中！");
			Thread t=new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					moddriver_textChange();
				}
				
			});
			t.start();
		}};
	
	public static void setDriverInfo(ContentMessagePanel p_self) {//驾驶员信息概览
		self=p_self;
		DebugInfo.DebugInfo("开始绘制车队管理概要信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		String[] name= {"驾驶员编号","驾驶员姓名","工作状态"};
		array=Driver_Database.getAllDriverInfo();//获取所有驾驶员的所有信息
		
		if(array==null||array.length==0){
			JOptionPane.showMessageDialog(self,"当前系统无驾驶员信息","information",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		String[][] data=new String[array.length][3];//只保存部分信息
		
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
				DebugInfo.DebugInfo("驾驶员概要信息表格项被点击！");
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
		DebugInfo.DebugInfo("完成绘制驾驶员概要信息Panel");
		return;
	}

	public static void setDriverDetailInfo(ContentMessagePanel p_self,Driver dri){//具体驾驶员的详细信息
		self=p_self;
		self.removeAll();
		
		//显示具体驾驶员的详细信息
		String[] name1={"驾驶员编号","驾驶证编号","驾驶员姓名","驾驶员年纪","驾驶员驾龄","联系方式","工作状态"};
		String[][] data1=new String[1][7];
		data1[0]=dri.toStringArray();
		
		JTable table1=new JTable(data1,name1);
		table1.setOpaque(false);
		JScrollPane scroll1=new JScrollPane(table1);
		scroll1.setOpaque(false);
		
		self.add(scroll1);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("完成绘制驾驶员详细信息Panel");
		return;
	}

	public static void addDriverInfo(ContentMessagePanel p_self){//增加驾驶员信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制增加驾驶员信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		JPanel p = new JPanel(new GridLayout(8,2,5,5));
		p.setOpaque(false);
		
		//第一行 驾驶员编号
		JLabel lab1=new JLabel("驾驶员编号");
		lab1.setFont(new Font("宋体",Font.PLAIN,20));
		lab1.setOpaque(false);
		p.add(lab1);
		
		self.driver_addtext1=new JTextField(20);
		self.driver_addtext1.setEditable(true);//设置为可编辑
		self.driver_addtext1.setOpaque(false);
		p.add(self.driver_addtext1);
		
		//第二行 驾驶证编号
		JLabel lab2=new JLabel("驾驶证编号");
		lab2.setFont(new Font("宋体",Font.PLAIN,20));
		lab2.setOpaque(false);
		p.add(lab2);
		
		self.driver_addtext2=new JTextField(20);
		self.driver_addtext2.setEditable(true);//设置为可编辑
		self.driver_addtext2.setOpaque(false);
		p.add(self.driver_addtext2);
		
		//第三行 驾驶员姓名
		JLabel lab3=new JLabel("驾驶员姓名");
		lab3.setFont(new Font("宋体",Font.PLAIN,20));
		lab3.setOpaque(false);
		p.add(lab3);
		
		self.driver_addtext3=new JTextField(20);
		self.driver_addtext3.setEditable(true);//设置为可编辑
		self.driver_addtext3.setOpaque(false);
		p.add(self.driver_addtext3);
		
		//第四行 驾驶员年纪
		JLabel lab4=new JLabel("驾驶员年纪");
		lab4.setFont(new Font("宋体",Font.PLAIN,20));
		lab4.setOpaque(false);
		p.add(lab4);
		
		self.driver_addtext4=new JTextField(20);
		self.driver_addtext4.setEditable(true);//设置为可编辑
		self.driver_addtext4.setOpaque(false);
		p.add(self.driver_addtext4);
		
		//第五行   驾龄
		JLabel lab5=new JLabel("驾龄");
		lab5.setFont(new Font("宋体",Font.PLAIN,20));
		lab5.setOpaque(false);
		p.add(lab5);
		
		self.driver_addtext5=new JTextField(20);
		self.driver_addtext5.setEditable(true);//设置为可编辑
		self.driver_addtext5.setOpaque(false);
		p.add(self.driver_addtext5);
		
		//第六行  驾驶员联系方式
		JLabel lab6=new JLabel("驾龄");
		lab6.setFont(new Font("宋体",Font.PLAIN,20));
		lab6.setOpaque(false);
		p.add(lab6);
		
		self.driver_addtext6=new JTextField(20);
		self.driver_addtext6.setEditable(true);//设置为可编辑
		self.driver_addtext6.setOpaque(false);
		p.add(self.driver_addtext6);
		
		//第七行  驾驶员当前状态
		JLabel lab7=new JLabel("驾龄");
		lab7.setFont(new Font("宋体",Font.PLAIN,20));
		lab7.setOpaque(false);
		p.add(lab7);
		
		self.driver_addtext7=new JTextField(20);
		self.driver_addtext7.setEditable(true);//设置为可编辑
		self.driver_addtext7.setOpaque(false);
		p.add(self.driver_addtext7);
		
		//第八行 增加按钮
		p.add(new JLabel(" 		"));
		//中转站格式待修改完善
		
		JButton b=new JButton("确认");
		b.setFont(new Font("宋体",Font.PLAIN,14));
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
						DebugInfo.DebugInfo("添加驾驶员信息的按钮被按下");
						String[] s=new String[7]; 
						
						s[0]=self.driver_addtext1.getText();
						s[1]=self.driver_addtext2.getText();
						s[2]=self.driver_addtext3.getText();
						s[3]=self.driver_addtext4.getText();
						s[4]=self.driver_addtext5.getText();
						s[5]=self.driver_addtext6.getText();
						s[6]=self.driver_addtext7.getText();
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3]+"#"+s[4]+"#"+s[5]+"#"+s[6];//保存增加的驾驶员信息
						System.out.println("增加驾驶员信息:"+S);
						
						String message=Driver_Database.addDriverInfo(S);//将该信息输出在界面上面
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
		DebugInfo.DebugInfo("完成绘制增加驾驶员信息Panel");
		return;
		
	}

	public static void modifyDriverInfo(ContentMessagePanel p_self){//修改驾驶员信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制修改驾驶员信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		self.driver_mpanel=new JPanel(new GridLayout(1,2,0,0));
		self.driver_mpanel.setOpaque(false);
		
		//第一列
		JPanel p = new JPanel(new GridLayout(8,2,5,5));
		p.setOpaque(false);
		
		//第一行 驾驶员编号
		JLabel lab1=new JLabel("驾驶员编号");
		lab1.setFont(new Font("宋体",Font.PLAIN,20));
		lab1.setOpaque(false);
		p.add(lab1);
		
		self.driver_modifytext1=new JTextField(20);
		self.driver_modifytext1.setEditable(true);//设置为可编辑
		self.driver_modifytext1.setOpaque(false);
		p.add(self.driver_modifytext1);
		
		//对文本框增加响应函数
		Document document2 = self.driver_modifytext1.getDocument();
		document2.addDocumentListener(documentListener2);
		
		//第二行 驾驶证编号
		JLabel lab2=new JLabel("驾驶证编号");
		lab2.setFont(new Font("宋体",Font.PLAIN,20));
		lab2.setOpaque(false);
		p.add(lab2);
		
		self.driver_modifytext2=new JTextField(20);
		self.driver_modifytext2.setEditable(true);//设置为可编辑
		self.driver_modifytext2.setOpaque(false);
		p.add(self.driver_modifytext2);
		
		//第三行 驾驶员姓名
		JLabel lab3=new JLabel("驾驶员姓名");
		lab3.setFont(new Font("宋体",Font.PLAIN,20));
		lab3.setOpaque(false);
		p.add(lab3);
		
		self.driver_modifytext3=new JTextField(20);
		self.driver_modifytext3.setEditable(true);//设置为可编辑
		self.driver_modifytext3.setOpaque(false);
		p.add(self.driver_modifytext3);
		
		//第四行 驾驶员年纪
		JLabel lab4=new JLabel("驾驶员年纪");
		lab4.setFont(new Font("宋体",Font.PLAIN,20));
		lab4.setOpaque(false);
		p.add(lab4);
		
		self.driver_modifytext4=new JTextField(20);
		self.driver_modifytext4.setEditable(true);//设置为可编辑
		self.driver_modifytext4.setOpaque(false);
		p.add(self.driver_modifytext4);
		
		//第五行   驾龄
		JLabel lab5=new JLabel("驾龄");
		lab5.setFont(new Font("宋体",Font.PLAIN,20));
		lab5.setOpaque(false);
		p.add(lab5);
		
		self.driver_modifytext5=new JTextField(20);
		self.driver_modifytext5.setEditable(true);//设置为可编辑
		self.driver_modifytext5.setOpaque(false);
		p.add(self.driver_modifytext5);
		
		//第六行  驾驶员联系方式
		JLabel lab6=new JLabel("驾龄");
		lab6.setFont(new Font("宋体",Font.PLAIN,20));
		lab6.setOpaque(false);
		p.add(lab6);
		
		self.driver_modifytext6=new JTextField(20);
		self.driver_modifytext6.setEditable(true);//设置为可编辑
		self.driver_modifytext6.setOpaque(false);
		p.add(self.driver_modifytext6);
		
		//第七行  驾驶员当前状态
		JLabel lab7=new JLabel("驾龄");
		lab7.setFont(new Font("宋体",Font.PLAIN,20));
		lab7.setOpaque(false);
		p.add(lab7);
		
		self.driver_modifytext7=new JTextField(20);
		self.driver_modifytext7.setEditable(true);//设置为可编辑
		self.driver_modifytext7.setOpaque(false);
		p.add(self.driver_modifytext7);
		
		//第八行 增加按钮
		p.add(new JLabel(" 		"));
		//中转站格式待修改完善
		
		JButton b=new JButton("确认");
		b.setFont(new Font("宋体",Font.PLAIN,14));
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
						DebugInfo.DebugInfo("修改驾驶员信息的按钮被按下");
						String[] s=new String[7]; 
						
						s[0]=self.driver_modifytext1.getText();
						s[1]=self.driver_modifytext2.getText();
						s[2]=self.driver_modifytext3.getText();
						s[3]=self.driver_modifytext4.getText();
						s[4]=self.driver_modifytext5.getText();
						s[5]=self.driver_modifytext6.getText();
						s[6]=self.driver_modifytext7.getText();
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3]+"#"+s[4]+"#"+s[5]+"#"+s[6];//保存增加的驾驶员信息
						System.out.println("修改驾驶员信息:"+S);
						
						String message=Driver_Database.modifyDriverInfo(S);//将该信息输出在界面上面
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
		
		//第二列
		self.driver_panel2=new JPanel();
		self.driver_panel2.setOpaque(false);
		
		self.driver_bobox=new JComboBox();
		self.driver_bobox.setOpaque(false);
		self.driver_bobox.setBorder(BorderFactory.createTitledBorder("驾驶员编号模糊查询结果"));
		self.driver_bobox.setSelectedIndex(-1);//设置不选中
		
		//添加监听函数
		self.driver_bobox.addItemListener(itemListener2);
		
		self.driver_panel2.add(self.driver_bobox);
		
		self.driver_mpanel.add(self.driver_panel2);
		
		self.add(self.driver_mpanel);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("完成绘制修改驾驶员信息Panel");
		return;
	}

	public static void delDriverInfo(ContentMessagePanel p_self){//删除驾驶员信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制删除路线信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		flag=true;//初始化为删除路线编号来自于文本框
		del_drivernum=" ";//初始化
		
		//设置panel
		JPanel panel=new JPanel();
		panel.setOpaque(false);
		
		//设置搜索panel
		JPanel spanel=new JPanel(new GridLayout(1,2,0,0));
		spanel.setOpaque(false);
		panel.add(spanel,BorderLayout.NORTH);
		
		//设置显示搜索的组件
		JPanel cpanel=new JPanel();
		cpanel.setOpaque(false);
		panel.add(cpanel,BorderLayout.CENTER);
		
		self.driver_deltext=new JTextField(20);
		self.driver_deltext.setEditable(true);
		self.driver_deltext.setOpaque(false);
		spanel.add(self.driver_deltext);
		
		JButton b=new JButton("搜索");
		b.setFont(new Font("宋体",Font.PLAIN,14));
		b.setSize(4, 1);
		b.setOpaque(false);
		spanel.add(b);
		
		//按钮添加响应函数
		b.addActionListener(new ActionListener(){//增加按钮添加响应函数
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						DebugInfo.DebugInfo("搜索按钮被按下");
						String s=self.driver_deltext.getText();
						Driver[] array=Driver_Database.getMohuDriverInfo(s);
						
						if(array==null||array.length==0){
							JOptionPane.showMessageDialog(self,"系统中暂无驾驶员信息","information",JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							cpanel.removeAll();
							
							String[] name= {"驾驶员编号","驾驶员姓名","工作状态"};
							
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
									        	del_drivernum=table.getValueAt(row, 0).toString();//获取当前点击的驾驶员的编号
									        	flag=false;//点击搜索结果后，删除信息就来自于模糊搜索结果
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
							
							JButton bb=new JButton("删除");
							bb.setFont(new Font("宋体",Font.PLAIN,14));
							bb.setSize(4, 1);
							bb.setOpaque(false);
							cpanel.add(bb,BorderLayout.SOUTH);
							
							bb.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent arg0) {	
									Thread t=new Thread(new Runnable(){
										@Override
										public void run() {
											if(flag){//删除信息来自文本框
												del_drivernum=self.driver_deltext.getText();//获取当前文本框中的路线编号
											}
											String message=Driver_Database.delDriverInfo(del_drivernum);
											
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											self.driver_deltext.setText("");//清空文本框中内容
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
		DebugInfo.DebugInfo("完成绘制删除驾驶员信息Panel");
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
		
		String[] temp_driver=Driver_Database.getMohuDriverNumInfo(s);//用来保存模糊查询得到的驾驶员编号
		
		DefaultComboBoxModel<String> driver_model=new DefaultComboBoxModel<String>();
		for(int i=0;i<temp_driver.length;i++){
			driver_model.addElement(temp_driver[i]);
		}
		
		self.driver_bobox.setModel(driver_model);
		
		//self.car_bobox.setSelectedIndex(-1);//当模糊查询结果改变了，car面板上面的信息也就需要改变
		self.driver_bobox.addItemListener(itemListener2);
	}
}