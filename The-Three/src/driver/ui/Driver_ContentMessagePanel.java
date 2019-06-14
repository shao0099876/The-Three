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
	
	public static JTextField driver_deltext;//用于驾驶员删除
	
	public static JTextField driver_text1,driver_text2,driver_text3,driver_text4,
							driver_text5,driver_text6,driver_text7;//增加修改
	
	public static JComboBox drivernum_bobox,driverbian_bobox;
	
	private static ItemListener driver_itemListener1=new ItemListener() {//给stationbobox2增加响应函数

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("驾驶员编号项被选中！");
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
	
	private static ItemListener driver_itemListener2=new ItemListener() {//给stationbobox2增加响应函数

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("驾驶zheng编号项被选中！");
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
	
	public static void setDriverInfo(ContentMessagePanel p_self) {//驾驶员信息概览
		self=p_self;
		DebugInfo.DebugInfo("开始绘制车队管理概要信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		array=Driver_Database.getAllDriverInfo();//获取所有驾驶员的所有信息
		if(array==null||array.length==0){
			self.removeAll();//将面板上面的组件全部清空
			JOptionPane.showMessageDialog(self,"当前系统无驾驶员信息","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		String[] name= {"驾驶员编号","驾驶员姓名","工作状态"};
		
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
		lab1.setFont(new Font("宋体",Font.PLAIN,30));
		lab1.setOpaque(false);
		p.add(lab1);
		
		driver_text1=new JTextField(20);
		driver_text1.setText("");
		driver_text1.setFont(new Font("宋体",Font.PLAIN,30));
		driver_text1.setEditable(true);//设置为可编辑
		driver_text1.setOpaque(false);
		p.add(driver_text1);
		
		//第二行 驾驶证编号
		JLabel lab2=new JLabel("驾驶证编号");
		lab2.setFont(new Font("宋体",Font.PLAIN,30));
		lab2.setOpaque(false);
		p.add(lab2);
		
		driver_text2=new JTextField();
		driver_text2.setText("");

		driver_text2.setFont(new Font("宋体",Font.PLAIN,30));
		driver_text2.setEditable(true);//设置为可编辑
		driver_text2.setOpaque(false);
		p.add(driver_text2);
		
		//第三行 驾驶员姓名
		JLabel lab3=new JLabel("驾驶员姓名");
		lab3.setFont(new Font("宋体",Font.PLAIN,30));
		lab3.setOpaque(false);
		p.add(lab3);
		
		driver_text3=new JTextField();
		driver_text3.setText("");
		driver_text3.setFont(new Font("宋体",Font.PLAIN,30));
		driver_text3.setEditable(true);//设置为可编辑
		driver_text3.setOpaque(false);
		p.add(driver_text3);
		
		//第四行 驾驶员年纪
		JLabel lab4=new JLabel("驾驶员年纪");
		lab4.setFont(new Font("宋体",Font.PLAIN,30));
		lab4.setOpaque(false);
		p.add(lab4);
		
		driver_text4=new JTextField();
		driver_text4.setText("");
		driver_text4.setFont(new Font("宋体",Font.PLAIN,30));
		driver_text4.setEditable(true);//设置为可编辑
		driver_text4.setOpaque(false);
		p.add(driver_text4);
		
		//第五行   驾龄
		JLabel lab5=new JLabel("驾龄");
		lab5.setFont(new Font("宋体",Font.PLAIN,30));
		lab5.setOpaque(false);
		p.add(lab5);
		
		driver_text5=new JTextField();
		driver_text5.setText("");
		driver_text5.setFont(new Font("宋体",Font.PLAIN,30));
		driver_text5.setEditable(true);//设置为可编辑
		driver_text5.setOpaque(false);
		p.add(driver_text5);
		
		//第六行  驾驶员联系方式
		JLabel lab6=new JLabel("联系方式");
		lab6.setFont(new Font("宋体",Font.PLAIN,30));
		lab6.setOpaque(false);
		p.add(lab6);
		
		driver_text6=new JTextField();
		driver_text6.setText("");

		driver_text6.setFont(new Font("宋体",Font.PLAIN,30));
		driver_text6.setEditable(true);//设置为可编辑
		driver_text6.setOpaque(false);
		p.add(driver_text6);
		
		//第七行  驾驶员当前状态
		JLabel lab7=new JLabel("当前工作状态");
		lab7.setFont(new Font("宋体",Font.PLAIN,30));
		lab7.setOpaque(false);
		p.add(lab7);
		
		driver_text7=new JTextField();
		driver_text7.setText("");
		driver_text7.setFont(new Font("宋体",Font.PLAIN,30));
		driver_text7.setEditable(true);//设置为可编辑
		driver_text7.setOpaque(false);
		p.add(driver_text7);
		
		//第八行 增加按钮
		p.add(new JLabel(" 		"));
		//中转站格式待修改完善
		
		JButton b=new JButton("确认");
		b.setFont(new Font("宋体",Font.PLAIN,18));
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
							JOptionPane.showMessageDialog(self,"请将信息填写完整","information",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3]+"#"+s[4]+"#"+s[5]+"#"+s[6];//保存增加的驾驶员信息
						System.out.println("增加驾驶员信息:"+S);
						
						String message=Driver_Database.addDriverInfo(S);//将该信息输出在界面上面
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
		DebugInfo.DebugInfo("完成绘制增加驾驶员信息Panel");
		return;
		
	}

	public static void modifyDriverInfo(ContentMessagePanel p_self){//修改驾驶员信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制修改驾驶员信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		if(newdriverbobox()==false){
			self.removeAll();//将面板上面的组件全部清空
			JOptionPane.showMessageDialog(self,"系统现在无驾驶员信息，不能进行修改，请先去添加驾驶员信息","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		JPanel p = new JPanel(new GridLayout(8,3,5,5));
		p.setOpaque(false);
		
		//第一行 驾驶员编号
		JLabel lab1=new JLabel("驾驶员编号");
		lab1.setFont(new Font("宋体",Font.PLAIN,25));
		lab1.setOpaque(false);
		p.add(lab1);
		
		driver_text1=new JTextField(20);
		driver_text1.setText("");

		driver_text1.setFont(new Font("宋体",Font.PLAIN,25));
		driver_text1.setEditable(false);//设置为可编辑
		driver_text1.setOpaque(false);
		p.add(driver_text1);
		p.add(drivernum_bobox);
		
		//第二行 驾驶证编号
		JLabel lab2=new JLabel("驾驶证编号");
		lab2.setFont(new Font("宋体",Font.PLAIN,25));
		lab2.setOpaque(false);
		p.add(lab2);
		
		driver_text2=new JTextField(20);
		driver_text2.setText("");

		driver_text2.setFont(new Font("宋体",Font.PLAIN,25));
		driver_text2.setEditable(false);//设置为可编辑
		driver_text2.setOpaque(false);
		p.add(driver_text2);
		p.add(driverbian_bobox);
		
		//第三行 驾驶员姓名
		JLabel lab3=new JLabel("驾驶员姓名");
		lab3.setFont(new Font("宋体",Font.PLAIN,25));
		lab3.setOpaque(false);
		p.add(lab3);
		
		driver_text3=new JTextField(20);
		driver_text3.setText("");

		driver_text3.setFont(new Font("宋体",Font.PLAIN,25));
		driver_text3.setEditable(true);//设置为可编辑
		driver_text3.setOpaque(false);
		p.add(driver_text3);
		p.add(new JLabel("    "));
		
		//第四行 驾驶员年纪
		JLabel lab4=new JLabel("驾驶员年纪");
		lab4.setFont(new Font("宋体",Font.PLAIN,25));
		lab4.setOpaque(false);
		p.add(lab4);
		
		driver_text4=new JTextField(20);
		driver_text4.setText("");

		driver_text4.setFont(new Font("宋体",Font.PLAIN,25));
		driver_text4.setEditable(true);//设置为可编辑
		driver_text4.setOpaque(false);
		p.add(driver_text4);
		p.add(new JLabel("    "));
		
		//第五行   驾龄
		JLabel lab5=new JLabel("驾龄");
		lab5.setFont(new Font("宋体",Font.PLAIN,25));
		lab5.setOpaque(false);
		p.add(lab5);
		
		driver_text5=new JTextField(20);
		driver_text5.setText("");

		driver_text5.setFont(new Font("宋体",Font.PLAIN,25));
		driver_text5.setEditable(true);//设置为可编辑
		driver_text5.setOpaque(false);
		p.add(driver_text5);
		p.add(new JLabel("    "));
		
		//第六行  驾驶员联系方式
		JLabel lab6=new JLabel("联系方式");
		lab6.setFont(new Font("宋体",Font.PLAIN,25));
		lab6.setOpaque(false);
		p.add(lab6);
		
		driver_text6=new JTextField(20);
		driver_text6.setText("");

		driver_text6.setFont(new Font("宋体",Font.PLAIN,25));
		driver_text6.setEditable(true);//设置为可编辑
		driver_text6.setOpaque(false);
		p.add(driver_text6);
		p.add(new JLabel("    "));
		
		//第七行  驾驶员当前状态
		JLabel lab7=new JLabel("当前工作状态");
		lab7.setFont(new Font("宋体",Font.PLAIN,25));
		lab7.setOpaque(false);
		p.add(lab7);
		
		driver_text7=new JTextField(20);
		driver_text7.setText("");

		driver_text7.setFont(new Font("宋体",Font.PLAIN,25));
		driver_text7.setEditable(true);//设置为可编辑
		driver_text7.setOpaque(false);
		p.add(driver_text7);
		p.add(new JLabel("    "));
		
		//第八行 增加按钮
		p.add(new JLabel(" 		"));
		p.add(new JLabel("    "));
		JButton b=new JButton("确认");
		b.setFont(new Font("宋体",Font.PLAIN,18));
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
							JOptionPane.showMessageDialog(self,"请将信息填写完整","information",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3]+"#"+s[4]+"#"+s[5]+"#"+s[6];//保存增加的驾驶员信息
						System.out.println("修改驾驶员信息:"+S);
						
						String message=Driver_Database.modifyDriverInfo(S);//将该信息输出在界面上面
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
		panel.setLayout(new BorderLayout());
		
		//设置搜索panel
		JPanel spanel=new JPanel(new GridLayout(1,2,0,0));
		spanel.setOpaque(false);
		panel.add(spanel,BorderLayout.NORTH);
		
		//设置显示搜索的组件
		JPanel cpanel=new JPanel();
		cpanel.setOpaque(false);
		panel.add(cpanel,BorderLayout.CENTER);
		
		driver_deltext=new JTextField(20);
		driver_deltext.setText("");

		driver_deltext.setFont(new Font("宋体",Font.PLAIN,30));
		driver_deltext.setEditable(true);
		driver_deltext.setOpaque(false);
		spanel.add(driver_deltext);
		
		JButton b=new JButton("搜索");
		b.setFont(new Font("宋体",Font.PLAIN,18));
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
						String s=driver_deltext.getText();
						Driver[] array=Driver_Database.getMohuDriverInfo(s);
						
						if(array==null||array.length==0){
							JOptionPane.showMessageDialog(self,"该系统中暂无该驾驶员信息!","information",JOptionPane.INFORMATION_MESSAGE);
							driver_deltext.setText("");
							return;
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
							bb.setFont(new Font("宋体",Font.PLAIN,18));
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
												del_drivernum=driver_deltext.getText();//获取当前文本框中的路线编号
											}
											String message=Driver_Database.delDriverInfo(del_drivernum);
											
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											driver_deltext.setText("");//清空文本框中内容
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
	
	public static boolean newdriverbobox(){//对driver bobox进行设置
		drivernum_bobox=new JComboBox();//驾驶员编号
		driverbian_bobox=new JComboBox();//驾驶证编号
		
		drivernum_bobox.setFont(new Font("宋体",Font.PLAIN,25));drivernum_bobox.setOpaque(false);
		driverbian_bobox.setFont(new Font("宋体",Font.PLAIN,25));driverbian_bobox.setOpaque(false);
		
		drivernum_bobox.setSelectedIndex(-1);//设置不选中
		driverbian_bobox.setSelectedIndex(-1);//设置不选中
		
		//获取信息
		Driver[] dri=Driver_Database.getAllDriverInfo();
		if(dri==null||dri.length==0){
			return false;//无驾驶员信息
		}
		String[] s1=new String[dri.length];
		String[] s2=new String[dri.length];
		for(int i=0;i<dri.length;i++){
			s1[i]=String.valueOf(dri[i].peopleNumber);//获取驾驶员编号
			s2[i]=dri[i].driverNumber;//获取驾驶证编号
		}
		
		//将信息添加到列表中
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
		
		//添加响应函数
		drivernum_bobox.addItemListener(driver_itemListener1);
		driverbian_bobox.addItemListener(driver_itemListener2);
		return true;
	}
}