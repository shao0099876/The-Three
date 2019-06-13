package indexui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ui.BaseUI;
import user.db.User_Database;
import browser.BrowserDialog;
import cargo.db.Cargo_Database;
import client.Client;
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
	public IndexUI self;
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
						
						JDialog dialog=new JDialog();
						dialog.setTitle("查看物流单状态");
						dialog.setLayout(new GridLayout(5,2,5,5));
						
						JLabel label1=new JLabel("物流单号");
						label1.setFont(new Font("宋体",Font.BOLD,20));
						dialog.add(label1);
						
						JTextField cargoNumberText=new JTextField(20);
						cargoNumberText.setFont(new Font("宋体",Font.BOLD,20));
						dialog.add(cargoNumberText);
						
						
						JLabel label2=new JLabel("出发地");
						label2.setFont(new Font("宋体",Font.BOLD,20));
						dialog.add(label2);
						
						JTextField startText=new JTextField(20);
						startText.setFont(new Font("宋体",Font.BOLD,20));
						startText.setEditable(false);
						dialog.add(startText);
						
						
						JLabel label3=new JLabel("目的地");
						label3.setFont(new Font("宋体",Font.BOLD,20));
						dialog.add(label3);
						
						JTextField endText=new JTextField(20);
						endText.setFont(new Font("宋体",Font.BOLD,20));
						endText.setEditable(false);
						dialog.add(endText);
						
						
						JLabel label4=new JLabel("状态");
						label4.setFont(new Font("宋体",Font.BOLD,20));
						dialog.add(label4);
						
						JTextField statusText=new JTextField(20);
						statusText.setFont(new Font("宋体",Font.BOLD,20));
						statusText.setEditable(false);
						dialog.add(statusText);
						
						
						JButton button=new JButton("查询");
						button.setFont(new Font("宋体",Font.BOLD,20));
						button.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								Thread t=new Thread(new Runnable() {

									@Override
									public void run() {
										// TODO Auto-generated method stub
										String[] data=Cargo_Database.getCargoStatus(cargoNumberText.getText());
										startText.setText(data[0]);
										endText.setText(data[1]);
										statusText.setText(data[2]);
										dialog.revalidate();
										dialog.repaint();
									}
									
								});
								t.start();
								
							}
							
						});
						
						dialog.setSize(2000,1000);
						dialog.setVisible(true);
						
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
						BrowserDialog.frame=new BaseUI();//开启员工登陆界面
						JDialog dialog=new JDialog();
						dialog.setTitle("登录");
						dialog.setLayout(new GridLayout(3,2,5,5));
						dialog.setSize(700,200);
						
						JLabel label1=new JLabel("用户名：");
						label1.setFont(new Font("宋体",Font.BOLD,20));
						dialog.add(label1);
						
						JTextField usernameTextField=new JTextField(20);
						usernameTextField.setFont(new Font("宋体",Font.BOLD,20));
						dialog.add(usernameTextField);
						
						JLabel label2=new JLabel("密码：");
						label2.setFont(new Font("宋体",Font.BOLD,20));
						dialog.add(label2);
						
						JPasswordField passwordTextField=new JPasswordField();
						passwordTextField.setFont(new Font("宋体",Font.BOLD,20));
						dialog.add(passwordTextField);
						
						dialog.add(new JLabel(" 		"));
						
						JButton loginButton=new JButton("登录");
						loginButton.setFont(new Font("宋体",Font.BOLD,20));
						loginButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								String username=usernameTextField.getText();
								String password=passwordTextField.getText();
								boolean status=User_Database.login(username,password);
								if(status) {
									Client.user=User_Database.getUser(username);
									dialog.dispose();
									self.setVisible(false);
									BrowserDialog.frame=new BaseUI();//开启员工登陆界面
								}
							}
							
						});
						dialog.add(loginButton);
						Dimension dm = self.getToolkit().getScreenSize();
						dialog.setLocation((int)(dm.getWidth()-600)/2,(int)(dm.getHeight()-650)/2);
						dialog.setVisible(true);
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
		self=this;
		setmenu();//添加菜单
		getScreenSize();//获取屏幕大小
		addaction_viewWuliu();//给客户查看物流信息增加相应函数
		addaction_Login();//给登陆增加相应函数
		
		setVisible(true);
		setSize(500,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setlocation();
	}
}