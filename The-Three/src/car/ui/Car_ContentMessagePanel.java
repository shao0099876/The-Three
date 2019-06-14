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
	
	private static boolean flag;//用来标记删除车辆信息时的车辆编号来自哪里
	//false表明删除的车辆编号来自于文本框
	//true表明删除的车辆编号来自于点击的模糊搜索结果
	
	private static String del_carnum;//标记被删除的路线编号
	
	public static JTextField car_text1,car_text2,car_text3,car_text4;
	public static JComboBox car_bobox1,car_bobox2,car_bobox3,car_bobox4;
	
	public static JTextField car_deltext;//用于车辆删除

	private static ItemListener car_itemListener=new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("车牌号项被选中！");
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
				DebugInfo.DebugInfo("驾驶员1项被选中！");
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
				DebugInfo.DebugInfo("驾驶员2项被选中！");
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
				DebugInfo.DebugInfo("路线编号项被选中！");
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
	
	public static void setCarInfo(ContentMessagePanel p_self) {//车队管理概要信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制车队管理概要信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		array=Car_Database.getCarInfo();
		
		if(array==null||array.length==0) {
			self.removeAll();//将面板上面的组件全部清空
			JOptionPane.showMessageDialog(self,"当前系统无可用车辆信息！","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		String[] name= {"车牌号","驾驶员1","驾驶员2","路线"};
		
		String[][] data=new String[array.length][4];
		for(int i=0;i<array.length;i++) {
			data[i]=array[i].toStringArray();
		}
		JTable table=new JTable(data,name);
		//table.setFont(new Font("宋体",Font.PLAIN,18));//设置字体
		table.setOpaque(false);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setOpaque(false);
		
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("车队概要信息表格项被点击！");
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
		DebugInfo.DebugInfo("完成绘制车辆概要信息Panel");
		return;
	}

	public static void addcarInfo(ContentMessagePanel p_self){//车辆信息的增加操作
		System.out.println("开始判断驾驶员信息");
		if(setcarbobox()==1){
			self.removeAll();//将面板上面的组件全部清空
			JOptionPane.showMessageDialog(self,"系统现在无驾驶员信息，不能进行车辆信息添加，请先去添加驾驶员信息","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		System.out.println("开始判断路线信息");
		if(setcarbobox()==2){
			self.removeAll();//将面板上面的组件全部清空
			JOptionPane.showMessageDialog(self,"系统现在无路线信息，不能进行车辆信息添加，请先去添加路线信息","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		DebugInfo.DebugInfo("开始绘制车辆增删改Panel");
		self=p_self;
		self.removeAll();//清除面板上面的所有组件

		System.out.println("开始绘图");
		
		JPanel panel=new JPanel(new GridLayout(9,3,5,5));//六行三列
		panel.setOpaque(false);
		
		//第一行 车辆编号
		JLabel label1=new JLabel("车辆编号");
		label1.setFont(new Font("宋体",Font.PLAIN,25));
		label1.setOpaque(false);
		panel.add(label1);
		
		car_text1=new JTextField(30);
		car_text1.setText("");
		car_text1.setFont(new Font("宋体",Font.PLAIN,25));
		car_text1.setEditable(true);//设置为可编辑
		car_text1.setOpaque(false);
		panel.add(car_text1);
		panel.add(new JLabel("    "));
		
		//新的空行
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第二行 驾驶员1号
		JLabel label2=new JLabel("驾驶员一号编号");
		label2.setFont(new Font("宋体",Font.PLAIN,25));
		label2.setOpaque(false);
		panel.add(label2);
		
		car_text2=new JTextField();
		car_text2.setText("");

		car_text2.setFont(new Font("宋体",Font.PLAIN,25));
		car_text2.setEditable(false);//设置为可编辑
		car_text2.setOpaque(false);
		panel.add(car_text2);
		panel.add(car_bobox2);
		
		//新的空行
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第三行 驾驶员2号
		JLabel label3=new JLabel("驾驶员二号编号");
		label3.setFont(new Font("宋体",Font.PLAIN,25));
		label3.setOpaque(false);
		panel.add(label3);
		
		car_text3=new JTextField();
		car_text3.setText("");

		car_text3.setFont(new Font("宋体",Font.PLAIN,25));
		car_text3.setEditable(false);//设置为可编辑
		car_text3.setOpaque(false);
		panel.add(car_text3);
		panel.add(car_bobox3);
		
		//新的空行
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第四行 路线编号
		JLabel label4=new JLabel("路线编号");
		label4.setFont(new Font("宋体",Font.PLAIN,25));
		label4.setOpaque(false);
		panel.add(label4);
		
		car_text4=new JTextField();
		car_text4.setText("");
		car_text4.setFont(new Font("宋体",Font.PLAIN,25));
		car_text4.setEditable(false);//设置为可编辑
		car_text4.setOpaque(false);
		panel.add(car_text4);
		panel.add(car_bobox4);
		
		//第五行 增加空行
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第六行 添加按钮
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		JButton button=new JButton();
		
		button=new JButton("确认");
		button.setFont(new Font("宋体",Font.PLAIN,14));
		button.setSize(4, 1);
		button.setOpaque(false);
		panel.add(button);
		
		button.addActionListener(new ActionListener(){//增加按钮添加响应函数
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DebugInfo.DebugInfo("添加按钮被按下");
						if(car_text1.getText().length()==0||car_text1.equals("")){
							JOptionPane.showMessageDialog(self,"请填写车辆编号","information",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						StringBuilder carinfo=new StringBuilder();//保存文本框中对应的信息
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
		DebugInfo.DebugInfo("完成绘制车辆增删改Panel");
		return;
	}
	
	public static void setCarDetailInfo(ContentMessagePanel p_self,Car carDetail){//查看车辆详细信息
		DebugInfo.DebugInfo("开始绘制车辆详细信息Panel");
		self=p_self;
		self.removeAll();
		
		//创建布局
		CardLayout c1=new CardLayout();
		JPanel panel=new JPanel();
		panel.setLayout(c1);
		
		JPanel p1=new JPanel();//大概信息
		p1.setOpaque(false);
		p1.setLayout(new BorderLayout());
		
		
		JPanel p2=new JPanel();//驾驶员信息
		p2.setOpaque(false);
		p2.setLayout(new BorderLayout());
		
		
		JPanel p3=new JPanel();//路线信息
		p3.setOpaque(false);
		p3.setLayout(new BorderLayout());
		
		
		//显示的标签信息
		JLabel l1=new JLabel("显示大概信息");
		l1.setFont(new Font("宋体",Font.PLAIN,18));//设置字体
		l1.setForeground(Color.red);
		
		JLabel l2=new JLabel("显示驾驶员信息");
		l2.setFont(new Font("宋体",Font.PLAIN,18));//设置字体
		l2.setForeground(Color.red);
		
		JLabel l3=new JLabel("显示路线信息");
		l3.setFont(new Font("宋体",Font.PLAIN,18));//设置字体
		l3.setForeground(Color.red);
		
		//添加响应函数
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
		
		//显示基本信息
		p1.add(l1,BorderLayout.NORTH);
		String[] name1= {"车牌号","驾驶员1","驾驶员2","路线"};
		String[][] data1=new String[1][4];
		data1[0]=carDetail.toStringArray();
		JTable table1=new JTable(data1,name1);
		//table1.setFont(new Font("宋体",Font.PLAIN,18));//设置字体
		table1.setOpaque(false);
		JScrollPane scroll1=new JScrollPane(table1);
		scroll1.setOpaque(false);
		p1.add(scroll1,BorderLayout.CENTER);
		
		//显示驾驶员的详细信息	
		p2.add(l2,BorderLayout.NORTH);
		String[] name2={"驾驶员编号","驾驶证","驾驶员姓名","驾驶员年龄","驾驶年长","驾驶员联系方式","驾驶员目前状态"};
		Driver driInfo1=new Driver();
		driInfo1=Driver_Database.getDriverInfo(carDetail.people1Number);//查询驾驶员1的信息
		
		Driver driInfo2=new Driver();
		driInfo2=Driver_Database.getDriverInfo(carDetail.people2Number);//查询驾驶员2的信息
		
		String[][] data2=new String[2][7];
		data2[0]=driInfo1.toStringArray();
		data2[1]=driInfo2.toStringArray();
		
		JTable table2=new JTable(data2,name2);
		table2.setOpaque(false);
		JScrollPane scroll2=new JScrollPane(table2);
		scroll2.setOpaque(false);
		p2.add(scroll2,BorderLayout.CENTER);
		
		//显示路线的详细信息	
		p3.add(l3,BorderLayout.NORTH);
		String[] name3={"路线编号","起始站点","终点站","中转站点"};
		Route routeInfo=new Route();
		routeInfo=Route_Database.getRouteInfo(carDetail.routeNumber);//查询路线的详细信息
		
		String[][] data3=new String[1][4];
		data3[0]=routeInfo.toStringArray();
		
		JTable table3=new JTable(data3,name3);
		//table3.setFont(new Font("宋体",Font.PLAIN,18));//设置字体
		table3.setOpaque(false);
		JScrollPane scroll3=new JScrollPane(table3);
		scroll3.setOpaque(false);
		p3.add(scroll3,BorderLayout.CENTER);
		
		//添加到布局里面
		panel.add(p1,"First");
		panel.add(p2,"Second");
		panel.add(p3,"Third");
	
		// 设置默认显示的卡片
		c1.show(panel, "First");
		
		self.add(panel);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("完成绘制车辆详细信息Panel");
		return;
	}
	
	public static void modfiycarInfo(ContentMessagePanel p_self){//车辆信息的修改操作
		DebugInfo.DebugInfo("开始绘制车辆修改Panel");
		self=p_self;
		self.removeAll();//清除面板上面的所有组件

		if(setcarbobox()==0){
			self.removeAll();//将面板上面的组件全部清空
			JOptionPane.showMessageDialog(self,"系统现在无车辆信息，不能进行车辆修改，请先去添加车辆信息","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		if(setcarbobox()==1){
			self.removeAll();//将面板上面的组件全部清空
			JOptionPane.showMessageDialog(self,"系统现在无驾驶员信息，不能进行车辆修改，请先去添加驾驶员信息","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		if(setcarbobox()==2){
			self.removeAll();//将面板上面的组件全部清空
			JOptionPane.showMessageDialog(self,"系统现在无路线信息，不能进行车辆修改，请先去添加路线信息","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		JPanel panel=new JPanel(new GridLayout(9,3,5,5));//六行三列
		panel.setOpaque(false);
		
		//第一行 车辆编号
		JLabel label1=new JLabel("车辆编号");
		label1.setFont(new Font("宋体",Font.PLAIN,25));
		label1.setOpaque(false);
		panel.add(label1);
		
		car_text1=new JTextField(20);
		car_text1.setText("");
		car_text1.setFont(new Font("宋体",Font.PLAIN,25));
		car_text1.setEditable(false);//设置为可编辑
		car_text1.setOpaque(false);
		panel.add(car_text1);
		panel.add(car_bobox1);
		
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第二行 驾驶员1号
		JLabel label2=new JLabel("驾驶员一号编号");
		label2.setFont(new Font("宋体",Font.PLAIN,25));
		label2.setOpaque(false);
		panel.add(label2);
		
		car_text2=new JTextField();
		car_text2.setText("");

		car_text2.setFont(new Font("宋体",Font.PLAIN,25));
		car_text2.setEditable(false);//设置为可编辑
		car_text2.setOpaque(false);
		panel.add(car_text2);
		panel.add(car_bobox2);
		
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第三行 驾驶员2号
		JLabel label3=new JLabel("驾驶员二号编号");
		label3.setFont(new Font("宋体",Font.PLAIN,25));
		label3.setOpaque(false);
		panel.add(label3);
		
		car_text3=new JTextField();
		car_text3.setText("");
		car_text3.setFont(new Font("宋体",Font.PLAIN,25));
		car_text3.setEditable(false);//设置为可编辑
		car_text3.setOpaque(false);
		panel.add(car_text3);
		panel.add(car_bobox3);
		
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第四行 路线编号
		JLabel label4=new JLabel("路线编号");
		label4.setFont(new Font("宋体",Font.PLAIN,25));
		label4.setOpaque(false);
		panel.add(label4);
		
		car_text4=new JTextField();
		car_text4.setText("");
		car_text4.setFont(new Font("宋体",Font.PLAIN,25));
		car_text4.setEditable(false);//设置为可编辑
		car_text4.setOpaque(false);
		panel.add(car_text4);
		panel.add(car_bobox4);
		
		//第五行 增加空行
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第六行 添加按钮
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		JButton button=new JButton();
		
		button=new JButton("确认");
		button.setFont(new Font("宋体",Font.PLAIN,14));
		button.setSize(4, 1);
		button.setOpaque(false);
		panel.add(button);
		
		button.addActionListener(new ActionListener(){//增加按钮添加响应函数
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DebugInfo.DebugInfo("添加按钮被按下");
						if(car_text1.getText().length()==0||car_text1.equals("")){
							JOptionPane.showMessageDialog(self,"请选择车辆编号","information",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						StringBuilder carinfo=new StringBuilder();//保存文本框中对应的信息
						carinfo.append(car_text1.getText());
						carinfo.append("#");
						carinfo.append(car_text2.getText());
						carinfo.append("#");
						carinfo.append(car_text3.getText());
						carinfo.append("#");
						carinfo.append(car_text4.getText());
						
						String newcarinfo=carinfo.toString();
						
						String s=Car_Database.ModifyCarInfo(newcarinfo);//将该信息输出在界面上面
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
		DebugInfo.DebugInfo("完成绘制车辆增删改Panel");
		return;
		
	}

	public static void delcarInfo(ContentMessagePanel p_self){//删除车辆信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制删除车辆信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		
		flag=true;//初始化为删除路线编号来自于文本框
		del_carnum=" ";//初始化	
		
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
		
		car_deltext=new JTextField(30);
		car_deltext.setText("");
		car_deltext.setFont(new Font("宋体",Font.PLAIN,30));
		car_deltext.setEditable(true);
		car_deltext.setOpaque(false);
		spanel.add(car_deltext);
		
		JButton b=new JButton("搜索");
		b.setFont(new Font("宋体",Font.PLAIN,20));
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
						String s=car_deltext.getText();
						
						
						Car[] array=Car_Database.getMohuCarInfo(s);
						
						if(array==null||array.length==0){
							JOptionPane.showMessageDialog(self,"该系统中暂无该车辆信息!","information",JOptionPane.INFORMATION_MESSAGE);
							car_deltext.setText("");
							return;
						}
						else{
							cpanel.removeAll();
							
							String[] name= {"车辆编号","驾驶员1","驾驶员2","路线编号"};
							
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
									        	del_carnum=table.getValueAt(row, 0).toString();//获取当前点击的路线的编号
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
							bb.setFont(new Font("宋体",Font.PLAIN,20));
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
												del_carnum=car_deltext.getText();//获取当前文本框中的车辆编号
											}
											String message=Car_Database.DeleteCarInfo(del_carnum);
											
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											car_deltext.setText("");//清空文本框中内容
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
		DebugInfo.DebugInfo("完成绘制删除车辆信息Panel");
		return;
	}

	public static int setcarbobox(){
		System.out.println("开始执行");
		car_bobox1=new JComboBox();
		car_bobox2=new JComboBox();
		car_bobox3=new JComboBox();
		car_bobox4=new JComboBox();
		
		car_bobox1.setFont(new Font("宋体",Font.PLAIN,25));car_bobox1.setOpaque(false);
		car_bobox2.setFont(new Font("宋体",Font.PLAIN,25));car_bobox2.setOpaque(false);
		car_bobox3.setFont(new Font("宋体",Font.PLAIN,25));car_bobox3.setOpaque(false);
		car_bobox4.setFont(new Font("宋体",Font.PLAIN,25));car_bobox4.setOpaque(false);
	
		car_bobox1.setSelectedIndex(-1);
		car_bobox2.setSelectedIndex(-1);
		car_bobox3.setSelectedIndex(-1);
		car_bobox4.setSelectedIndex(-1);
	
		
		System.out.println("获取驾驶员信息");
		Driver[] dri=Driver_Database.getAllDriverInfo();
		if(dri==null||dri.length==0){
			return 1;//无驾驶员信息
		}
		String[] s2=new String[dri.length];//驾驶员编号
		for(int i=0;i<dri.length;i++){
			System.out.println("驾驶员:"+s2[i]);
			s2[i]=String.valueOf(dri[i].peopleNumber);//获取驾驶员编号
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
		
		System.out.println("获取路线信息");
		Route[] ro=Route_Database.getAllRouteInfo();
		if(ro==null||ro.length==0){
			return 2;//无路线信息
		}
		String[] s3=new String[ro.length];//路线编号
		for(int i=0;i<ro.length;i++){
			System.out.println("路线:"+s3[i]);
			s3[i]=String.valueOf(ro[i].routeNumber);//获取路线编号
		}
		DefaultComboBoxModel<String> route_model=new DefaultComboBoxModel<String>();
		route_model.addElement("");
		for(int i=0;i<s3.length;i++){
			route_model.addElement(s3[i]);
		}
		car_bobox4.setModel(route_model);
		car_bobox4.addItemListener(route_itemListener);
		
		//获取车辆信息
		System.out.println("获取车辆信息");
		Car[] ca=Car_Database.getCarInfo();
		if(ca==null||ca.length==0){
			return 0;//无车辆信息
		}
		String[] s1=new String[ca.length];//车牌号
		for(int i=0;i<ca.length;i++){
			s1[i]=ca[i].carNumber;//获取车牌号
			System.out.println("车牌号:"+s1[i]);
		}
		//将信息添加到列表中
		DefaultComboBoxModel<String> car_model=new DefaultComboBoxModel<String>();	
		car_model.addElement("");
		for(int i=0;i<s1.length;i++){
			car_model.addElement(s1[i]);
		}
		car_bobox1.setModel(car_model);
		car_bobox1.addItemListener(car_itemListener);
		return -1;//成功
		
	}
}
