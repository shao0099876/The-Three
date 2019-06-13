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
	private static boolean flag;//用来标记删除路线信息时的路线编号来自哪里
	//false表明删除的路线编号来自于文本框
	//true表明删除的路线编号来自于点击的模糊搜索结果
	
	private static String del_routenum;//标记被删除的路线编号
	
	public static JTextField route_deltext;//用于路线删除
	
	public static JTextField route_text1,route_text2,route_text3,route_text4;//增删
	
	public static JComboBox route_bobox;
	
	//下面参数用来显示站点查询结果
	public static JComboBox station_bobox2,station_bobox3,station_bobox4;
	
	private static ItemListener itemListener1=new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("route1bobox项被选中！");
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
	
	public static void setRouteInfo(ContentMessagePanel p_self) {//查看路线信息 不包含GPS信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制路线概要信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		array=Route_Database.getAllRouteInfo();//查询所有路线信息

		if(array==null||array.length==0) {
			self.removeAll();//将面板上面的组件全部清空
			JOptionPane.showMessageDialog(self,"当前系统无可用路线信息！","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		String[] name= {"路线编号","起始站点","终点站","中转站点"};
		
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
				DebugInfo.DebugInfo("路线信息表格项被点击！");
				
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
		DebugInfo.DebugInfo("完成绘制查看路线信息Panel");
		return;
	}

	public static void delRouteInfo(ContentMessagePanel p_self) {//删除路线信息
		// TODO Auto-generated method stub
		self=p_self;
		DebugInfo.DebugInfo("开始绘制删除路线信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		flag=true;//初始化为删除路线编号来自于文本框
		del_routenum=" ";//初始化
		
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
		
		route_deltext=new JTextField(20);
		route_deltext.setFont(new Font("宋体",Font.PLAIN,30));
		route_deltext.setEditable(true);
		route_deltext.setOpaque(false);
		spanel.add(route_deltext);
		
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
						String s=route_deltext.getText();
						Route[] array=Route_Database.getMohuRouteInfo(s);
						
						if(array==null||array.length==0){
							JOptionPane.showMessageDialog(self,"该系统中暂无该路线信息!","information",JOptionPane.INFORMATION_MESSAGE);
							route_deltext.setText("");
							return;
						}
						else{
							cpanel.removeAll();
							
							String[] name= {"路线编号","起始站点","终点站","中转站点"};
							
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
									        	del_routenum=table.getValueAt(row, 0).toString();//获取当前点击的路线的编号
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
												del_routenum=route_deltext.getText();//获取当前文本框中的路线编号
											}
											String message=Route_Database.delRouteInfo(del_routenum);
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											route_deltext.setText("");//清空文本框中内容
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
		DebugInfo.DebugInfo("完成绘制查看路线信息Panel");
		return;
	}

	public static void addRouteInfo(ContentMessagePanel p_self){//增加路线信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制增加路线信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		if(newstationbobox()==false){//调用stationbobox初始化函数
			self.removeAll();//将面板上面的组件全部清空
			JOptionPane.showMessageDialog(self,"系统现在无站点信息，不能进行路线添加，请先去添加站点信息","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}

		//有站点信息，进行添加
		JPanel p = new JPanel(new GridLayout(9,3,5,0));
		p.setOpaque(false);
		
		//第一行 路线编号
		JLabel lab1=new JLabel("路线编号");
		lab1.setFont(new Font("宋体",Font.PLAIN,25));
		lab1.setOpaque(false);
		p.add(lab1);
		
		route_text1=new JTextField(20);
		route_text1.setText("");
		route_text1.setFont(new Font("宋体",Font.PLAIN,25));
		route_text1.setEditable(true);//设置为可编辑
		route_text1.setOpaque(false);
		p.add(route_text1);
		p.add(new JLabel("     "));
		
		//
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		
		//第二行 起始站台
		JLabel lab2=new JLabel("起始站台");
		lab2.setFont(new Font("宋体",Font.PLAIN,25));
		lab2.setOpaque(false);
		p.add(lab2);
		
		route_text2=new JTextField();
		route_text2.setText("");
		route_text2.setFont(new Font("宋体",Font.PLAIN,25));
		route_text2.setEditable(false);//设置为可编辑
		route_text2.setOpaque(false);
		p.add(route_text2);
		p.add(station_bobox2);
		
		//
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		
		//第三行 终点站
		JLabel lab3=new JLabel("终点站");
		lab3.setFont(new Font("宋体",Font.PLAIN,25));
		lab3.setOpaque(false);
		p.add(lab3);
		
		route_text3=new JTextField();
		route_text3.setText("");
		route_text3.setFont(new Font("宋体",Font.PLAIN,25));
		route_text3.setEditable(false);//设置为可编辑
		route_text3.setOpaque(false);
		p.add(route_text3);
		p.add(station_bobox3);
		
		//
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		
		//第四行 中转站
		JLabel lab4=new JLabel("中转站");
		lab4.setFont(new Font("宋体",Font.PLAIN,25));
		lab4.setOpaque(false);
		p.add(lab4);
		
		route_text4=new JTextField();
		route_text4.setText("");
		route_text4.setFont(new Font("宋体",Font.PLAIN,25));
		route_text4.setEditable(false);//设置为可编辑
		route_text4.setOpaque(false);
		p.add(route_text4);
		p.add(station_bobox4);
		
		//
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		p.add(new JLabel("     "));
		
		//第五行 增加按钮
		JLabel l=new JLabel("站点信息都是从右边选择");
		l.setFont(new Font("宋体",Font.PLAIN,25));
		p.add(l);
		
		p.add(new JLabel("     "));
		
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
						DebugInfo.DebugInfo("添加路线信息的按钮被按下");
						String[] s=new String[4]; 
						s[0]=route_text1.getText();
						s[1]=route_text2.getText();
						s[2]=route_text3.getText();
						s[3]=route_text4.getText();
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3];//保存增加的路径信息
						System.out.println("增加路径信息:"+S);
						
						String message=Route_Database.addRouteInfo(S);//将该信息输出在界面上面
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
		DebugInfo.DebugInfo("完成绘制增加路线信息Panel");
		return;
	}

	public static void modifyRouteInfo(ContentMessagePanel p_self){//修改路线信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制修改路线信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		if(newstationbobox()==false){//调用stationbobox初始化函数
			self.removeAll();//将面板上面的组件全部清空
			JOptionPane.showMessageDialog(self,"系统现在无站点信息，不能进行路线修改，请先去添加站点信息","information",JOptionPane.INFORMATION_MESSAGE);
			self.revalidate();
			self.repaint();
			return;
		}
		
		//有站点信息,进行修改
		JPanel panel=new JPanel(new GridLayout(9,3,5,5));
		panel.setOpaque(false);
		
		//第一行 路线编号
		JLabel label1=new JLabel("路线编号");
		label1.setFont(new Font("宋体",Font.PLAIN,25));
		label1.setOpaque(false);
		panel.add(label1);
		
		route_text1=new JTextField(20);
		route_text1.setText("");
		route_text1.setFont(new Font("宋体",Font.PLAIN,25));
		route_text1.setEditable(false);//设置为可编辑
		route_text1.setOpaque(false);
		panel.add(route_text1);
		
		if(newroutebobox()==false){
			JOptionPane.showMessageDialog(self,"系统没有路线信息，不能进行路线修改，请先去添加路线信息","information",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		panel.add(route_bobox);
		
		//
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第二行 起始站台
		JLabel label2=new JLabel("起始站台");
		label2.setFont(new Font("宋体",Font.PLAIN,25));
		label2.setOpaque(false);
		panel.add(label2);
		
		route_text2=new JTextField(20);
		route_text2.setText("");
		route_text2.setFont(new Font("宋体",Font.PLAIN,25));
		route_text2.setEditable(false);//设置为可编辑
		route_text2.setOpaque(false);
		panel.add(route_text2);
		panel.add(station_bobox2);
		
		//
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第三行 终点站
		JLabel label3=new JLabel("终点站");
		label3.setFont(new Font("宋体",Font.PLAIN,25));
		label3.setOpaque(false);
		panel.add(label3);
		
		route_text3=new JTextField(20);
		route_text3.setText("");
		route_text3.setFont(new Font("宋体",Font.PLAIN,25));
		route_text3.setEditable(false);//设置为可编辑
		route_text3.setOpaque(false);
		panel.add(route_text3);
		panel.add(station_bobox3);
		
		//
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第四行 中转站
		JLabel label4=new JLabel("中转站");
		label4.setFont(new Font("宋体",Font.PLAIN,25));
		label4.setOpaque(false);
		panel.add(label4);
		
		route_text4=new JTextField(20);
		route_text4.setText("");
		route_text4.setFont(new Font("宋体",Font.PLAIN,25));
		route_text4.setEditable(false);//设置为可编辑
		route_text4.setOpaque(false);
		panel.add(route_text4);
		panel.add(station_bobox4);
		
		//
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第五行 增加按钮
		JLabel l=new JLabel("文本框内容来自于右边搜索框s");
		l.setFont(new Font("宋体",Font.PLAIN,18));
		panel.add(l);
		
		panel.add(new JLabel("    "));
		
		JButton b=new JButton("确认");
		b.setFont(new Font("宋体",Font.PLAIN,18));
		b.setSize(4, 1);
		b.setOpaque(false);
		panel.add(b);
		
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("修改路线信息的按钮被按下");
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
						System.out.println("修改信息为："+S);
						
						String message=Route_Database.modRouteInfo(S);//将该信息输出在界面上面
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
		DebugInfo.DebugInfo("完成绘制修改路线信息Panel");
		return;
	}

	
	private static ItemListener station_itemListener2=new ItemListener() {//给stationbobox2增加响应函数

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED){
					DebugInfo.DebugInfo("Combobox2项被选中！");
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
	private static ItemListener station_itemListener3=new ItemListener() {//给stationbobox3增加响应函数
	
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("Combobox3项被选中！");
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
	private static ItemListener station_itemListener4=new ItemListener() {//给stationbobox4增加响应函数
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getStateChange() == ItemEvent.SELECTED){
				DebugInfo.DebugInfo("Combobox4项被选中！");
				
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
	
	public static boolean newstationbobox(){//对station bobox初始化
		//将站名添加进列表中
		station_bobox2=new JComboBox();
		station_bobox3=new JComboBox();
		station_bobox4=new JComboBox();
		
		station_bobox2.setFont(new Font("宋体",Font.PLAIN,25));station_bobox2.setOpaque(false);
		station_bobox3.setFont(new Font("宋体",Font.PLAIN,25));station_bobox3.setOpaque(false);
		station_bobox4.setFont(new Font("宋体",Font.PLAIN,25));station_bobox4.setOpaque(false);
		
		station_bobox2.setSelectedIndex(-1);//设置不选中
		station_bobox3.setSelectedIndex(-1);//设置不选中
		station_bobox4.setSelectedIndex(-1);//设置不选中
		
		//获取所有站名
		Station[] sta=Station_Database.getStationList();
		if(sta==null||sta.length==0){
			return false;//无站点信息
		}
		
		String[] s=new String[sta.length];
		for(int i=0;i<sta.length;i++){
			s[i]=sta[i].stationName;//获取地名
		}
		
		//将信息添加到列表中
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
		
		//添加响应函数
		station_bobox2.addItemListener(station_itemListener2);
		station_bobox3.addItemListener(station_itemListener3);
		station_bobox4.addItemListener(station_itemListener4);
		return true;
	}
	
	public static boolean newroutebobox(){
		//添加路线编号搜索框
		route_bobox=new JComboBox();
		route_bobox.setFont(new Font("宋体",Font.PLAIN,25));
		route_bobox.setOpaque(false);
		route_bobox.setSelectedIndex(-1);//设置不选中
		
		//将信息添加到列表中
		Route[] stat1=Route_Database.getAllRouteInfo();//查询所有路线信息

		if(stat1==null||stat1.length==0) {
			return false;//无可用路线
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
		
		//添加监听函数
		route_bobox.addItemListener(itemListener1);
		return true;
	}
}
