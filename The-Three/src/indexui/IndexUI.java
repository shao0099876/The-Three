package indexui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.BaseUI;
import browser.BrowserDialog;
import client.DebugInfo;

public class IndexUI extends JFrame{
	//菜单条
	public static JMenuBar men;
	//菜单定义
	public static JMenu men1,men2;
	//菜单项定义
	public static JMenuItem men1_1;//客户查询物流信息
	public static JMenuItem men2_1;//登陆
	
	public int w,h;//定义屏幕大小
	
	public void setmenu(){//设置菜单
		//添加菜单条
		men = new JMenuBar();//菜单条
		this.setJMenuBar(men);//增加菜单条
		
		//添加菜单
		 men1=new JMenu("订单信息");//菜单
		 men1.setFont(new Font("宋体",Font.BOLD,24));//设置字体
		 men.add(men1);//添加菜单
		 
		 men1_1=new JMenuItem("查看订单物流信息");
		 men1_1.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 men1.add(men1_1);
		 
		 men2=new JMenu("登陆");//菜单
		 men2.setFont(new Font("宋体",Font.BOLD,24));//设置字体
		 men.add(men2);//添加菜单
		 
		 men2_1=new JMenuItem("登陆系统");
		 men2_1.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		 men2.add(men2_1);
	}
	
	public void getScreenSize(){//获取屏幕大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		w=(int) (screenSize.getWidth()*0.8);//获取屏幕宽
		h=(int) (screenSize.getHeight()*0.6);//获取屏幕高
	}

	public void addaction_viewWuliu(){//给客户查看物流信息添加响应函数
		//查看物流信息
		men1_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						
						//待添加
						/*
						 将窗口大小重绘制
						 
						 * */
						
						System.out.println("客户查看物流信息查看物流信息");
					}});
				t.start();
			}
		});
	}
	
	public void addaction_Login(){//给登陆添加相应函数
		//登陆
		men2_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						//测试
						setVisible(false);
						BrowserDialog.frame=new BaseUI();//开启员工登陆界面
						//待添加
					
						/*
						 将窗口大小重绘制
						 * */
//						/*
//						 要求1：
//						 	登陆不成功，弹出对话框给出提示，回到未登录界面
//						 要求2：
//						 	如果是员工登陆成功关闭当前界面，打开BaseUI创建的界面
//						 要求3：
//						 	如果是中转站的登陆成功，关闭当前界面，打开物流管理的界面
//						 */
						
						System.out.println("登陆");
					}});
				t.start();
			}
		});
	}
	
	public void setlocation(){//设置窗口位置
		Dimension dm = this.getToolkit().getScreenSize();
		this.setLocation((int)(dm.getWidth()-600)/2,(int)(dm.getHeight()-650)/2);//显示在屏幕中央
	}
	
	public IndexUI() {
		super("物流综合信息平台");
		setmenu();//添加菜单
		getScreenSize();//获取屏幕大小
		addaction_viewWuliu();//给客户查看物流信息增加相应函数
		addaction_Login();//给登陆增加相应函数
		
		setVisible(true);
		setSize(500,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setlocation();
	}
}