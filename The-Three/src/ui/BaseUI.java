package ui;

import indexui.IndexUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import browser.BrowserDialog;
import route.ui.Route_ContentMessagePanel;
import station.ui.Station_ContentMessagePanel;
import car.ui.Car_ContentMessagePanel;
import driver.ui.Driver_ContentMessagePanel;

public class BaseUI extends JFrame{
	//显示内容栏
	public static ContentMessagePanel contentPanel;
	
	//菜单条
	public static JMenuBar menubar;
	//菜单定义
	public static JMenu m1,m2,m3,m4,m5;
	//菜单项定义
	public static JMenuItem m1_1,m1_2,m1_3,m1_4,m1_5;//车辆菜单项
	public static JMenuItem m2_1,m2_2,m2_3,m2_4,m2_5,m2_6,m2_7;//路线菜单项
	public static JMenuItem m3_1,m3_2,m3_3,m3_4;//驾驶员菜单项
	public static JMenuItem m4_1,m4_2,m4_3,m4_4;//货物菜单项
	public static JMenuItem m5_1;//退出
	
	//定义获取屏幕长度变量
	public static int width,height;
	
	public BaseUI() {
		super("物流综合信息平台");
		
		setMenu();//设置菜单
		getscreenSize();//获取屏幕大小
		
		//添加响应函数
		setaction_Car();//车辆
		setaction_Route();//路线
		setaction_Driver();//驾驶员
		setaction_Wuliu();//物流
		setaction_Logout();//退出
		
		contentPanel=new ContentMessagePanel();
		this.add(contentPanel);
	
		setVisible(true);
		setSize(width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setlocation();//设置位置
	}

	public void setMenu(){//添加菜单与相应的菜单项
		//添加菜单条
		menubar = new JMenuBar();//菜单条
		this.setJMenuBar(menubar);//增加菜单条
		 
		//添加菜单
		 m1=new JMenu("车辆管理");//菜单
		 m1.setFont(new Font("宋体",Font.BOLD,24));//设置字体
		 menubar.add(m1);//添加菜单
		 
		 m1_1=new JMenuItem("查看车辆信息");
		 m1_1.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m1.add(m1_1);
		 
		 m1_2=new JMenuItem("修改车辆信息");
		 m1_2.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m1.add(m1_2);
		 
		 m1_3=new JMenuItem("增加车辆信息");
		 m1_3.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m1.add(m1_3);
		 
		 m1_4=new JMenuItem("删除车辆信息");
		 m1_4.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m1.add(m1_4);
		 
		 m1_5=new JMenuItem("绘制GPS");
		 m1_5.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m1.add(m1_5);
		 
		 
		 m2=new JMenu("路线管理");//菜单
		 m2.setFont(new Font("宋体",Font.BOLD,24));//设置字体
		 menubar.add(m2);//添加菜单
		 
		 m2_1=new JMenuItem("查看路线信息");
		 m2_1.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m2.add(m2_1);
		 
		 m2_2=new JMenuItem("修改路线信息");
		 m2_2.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m2.add(m2_2);
		 
		 m2_3=new JMenuItem("增加路线信息");
		 m2_3.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m2.add(m2_3);
		 
		 m2_4=new JMenuItem("删除路线信息");
		 m2_4.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m2.add(m2_4);
		 
		 m2_5=new JMenuItem("查看站点列表");
		 m2_5.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m2.add(m2_5);
		 
		 m2_6=new JMenuItem("增加站点");
		 m2_6.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m2.add(m2_6);
		 
		 m2_7=new JMenuItem("删除站点");
		 m2_7.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m2.add(m2_7);
		 
		 
		 m3=new JMenu("驾驶员管理");//菜单
		 m3.setFont(new Font("宋体",Font.BOLD,24));//设置字体
		 menubar.add(m3);//添加菜单
		 
		 m3_1=new JMenuItem("查看驾驶员信息");
		 m3_1.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m3.add(m3_1);
		 
		 m3_2=new JMenuItem("修改驾驶员信息");
		 m3_2.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m3.add(m3_2);
		 
		 m3_3=new JMenuItem("增加驾驶员信息");
		 m3_3.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m3.add(m3_3);
		 
		 m3_4=new JMenuItem("删除驾驶员信息");
		 m3_4.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m3.add(m3_4);
		 
		 m4=new JMenu("物流管理");//菜单
		 m4.setFont(new Font("宋体",Font.BOLD,24));//设置字体
		 menubar.add(m4);//添加菜单
		 
		 m4_1=new JMenuItem("查询货物信息");
		 m4_1.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m4.add(m4_1);
		 
		 m4_2=new JMenuItem("修改货物信息");
		 m4_2.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m4.add(m4_2);
		 
		 m4_3=new JMenuItem("增加货物信息");
		 m4_3.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m4.add(m4_3);
		 
		 m4_4=new JMenuItem("删除货物信息");
		 m4_4.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m4.add(m4_4);
		 
		 m5=new JMenu("退出");//菜单
		 m5.setFont(new Font("宋体",Font.BOLD,24));//设置字体
		 menubar.add(m5);//添加菜单
		 
		 m5_1=new JMenuItem("退出登录");
		 m5_1.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 m5.add(m5_1);
		 
		 
	}

	public void getscreenSize(){//获取屏幕大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width=(int) (screenSize.getWidth()*0.8);//获取屏幕宽
		height=(int) (screenSize.getHeight()*0.6);//获取屏幕高
	}

	public static void setaction_Car(){//给车辆管理添加响应函数
		//查看车辆信息
		m1_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("查看车辆信息");
						Car_ContentMessagePanel.setCarInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
			
		});
		
		//修改车辆信息
		m1_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("修改车辆信息");
						Car_ContentMessagePanel.modfiycarInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
			
		});
		
		//增加车辆信息
		m1_3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("增加车辆信息");
						Car_ContentMessagePanel.addcarInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
			
		});
		
		//删除车辆信息
		m1_4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("删除车辆信息");
						Car_ContentMessagePanel.delcarInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
			
		});
		
		//绘制GPS
		m1_5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("绘制GPS");
						
						//待添加
						/*
						 以车辆为主体，添加绘制路线函数
						 * 
						 * 
						 * */
						
						
					}});
				t.start();
			}
			
		});
	}

	public static void setaction_Route(){//给路线管理添加响应函数
		//查看路线2-1
		m2_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("查看路线信息");
						Route_ContentMessagePanel.setRouteInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//修改路线2-2
		m2_2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("修改路线信息");
						Route_ContentMessagePanel.modifyRouteInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//增加路线2-3
		m2_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("增加路线信息");
						Route_ContentMessagePanel.addRouteInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//删除路线2-4
		m2_4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("删除路线信息");
						Route_ContentMessagePanel.delRouteInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		m2_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Station_ContentMessagePanel.showStations(BaseUI.contentPanel);
					}
					
				});
				t.start();
			}
			
		});
		m2_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Station_ContentMessagePanel.addStation(BaseUI.contentPanel);
					}
					
				});
				t.start();
			}
			
		});
		m2_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Station_ContentMessagePanel.delStation(BaseUI.contentPanel);
					}
					
				});
				t.start();
			}
			
		});
	}
	
	public static void setaction_Driver(){//给驾驶员增加相应函数
		//查看驾驶员信息3-1
		m3_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("查看驾驶员信息");
						Driver_ContentMessagePanel.setDriverInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//修改驾驶员信息3-2
		m3_2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("修改驾驶员信息");
						Driver_ContentMessagePanel.modifyDriverInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//增加驾驶员信息3-3
		m3_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("增加驾驶员信息");
						Driver_ContentMessagePanel.addDriverInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//删除驾驶员信息3-4
		m3_4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("删除驾驶员信息");
						Driver_ContentMessagePanel.delDriverInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
	}
	
	public static void setaction_Wuliu(){//给物流管理增加相应函数
		//查询货物信息4-1
		m4_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("查看物流信息");
						
						/*
						 *待添加*/
						
					}});
				t.start();
			}
		});
		
		//修改货物信息4-2
		m4_2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("修改物流信息");
						/*
						 *待添加*/
					}});
				t.start();
			}
		});
		
		//增加货物信息4-3
		m4_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("增加物流信息");
						/*
						 *待添加*/
					}});
				t.start();
			}
		});
		
		//删除货物信息4-4
		m4_4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("删除物流信息");
						/*
						 *待添加*/
					}});
				t.start();
			}
		});
	}
	
	public void setaction_Logout(){//给退出添加响应函数
		m5_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("退出");
						
						setVisible(false);
						BrowserDialog.frame=new IndexUI();//回到未登录界面
						
					}});
				t.start();
			}
		});
	}
	
	public void setlocation(){//设置窗口位置
		Dimension dm = this.getToolkit().getScreenSize();
		this.setLocation((int)(dm.getWidth()-1500)/2,(int)(dm.getHeight()-650)/2);//显示在屏幕中央
	}
}
