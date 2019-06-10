package indexui;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.DebugInfo;

public class IndexUI extends JFrame{
	private JLayeredPane layer;
	private ImageIcon image;
	private static String backgroundImagePath="/background.jpg";
	
	//设置界面显示
	private JPanel bpanel;
	private JPanel cpanel1;
	private JPanel cpanel2;
	private JButton[] button=new JButton[2];
	private JTextField text;//订单编号模糊搜索文本框
	private JButton b1;//搜索按钮
	private JButton b2;//绘制该订单的地图
	
	
	private void setBackgroundImage() {
		image=new ImageIcon(getClass().getResource(backgroundImagePath));
		JPanel background=new JPanel();
		background.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
		JLabel jl=new JLabel(image);
		background.add(jl);
		layer.add(background,JLayeredPane.DEFAULT_LAYER);//底层图片
		return;
	}
	
	private void setLocation() {
		Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
	    Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
	    int screenWidth = screenSize.width;                     //获取屏幕的宽
	    int screenHeight = screenSize.height;                   //获取屏幕的高
	    this.setLocation(screenWidth/2-image.getIconWidth()/2, screenHeight/2-image.getIconHeight()/2);//设置窗口居中显示
	}
	
	private void setButton(int x,int y,int w,int h){//添加按钮
		bpanel=new JPanel();//显示按钮的面板
		bpanel.setOpaque(false);
		bpanel.setBounds(0, 0, x, y);
		
		//按钮1
		button[0]=new JButton("查询物流信息");
		button[0].setFont(new Font("宋体",Font.PLAIN,14));
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		
		button[0].addActionListener(new ActionListener(){//添加响应函数
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("查看物流信息按钮按下");
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						//创建模糊搜索订单编号的搜索框
						setText(w,h);
					}
				});
				t.start();
			}
			
		});		
		bpanel.add(button[0]);
		
		//按钮2
		button[1]=new JButton("登陆");
		button[1].setFont(new Font("宋体",Font.PLAIN,14));
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		
		button[1].addActionListener(new ActionListener(){//添加响应函数
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("登陆按钮按下");
				
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub						
						
						//待添加
						
						
						//添加登陆的响应函数
						/*
						 要求1：
						 	将整个的panel上面的内容清空,显示出登陆界面
						 要求2：
						 	登陆不成功，弹出对话框给出提示，回到未登录界面
						 要求3：
						 	如果是员工登陆成功，回到BaseUI创建的界面
						 要求4：
						 	如果是中转站的登陆成功，重新创建一个界面
						 */
						
					}
					
				});
				t.start();
			}
		});		
		bpanel.add(button[1]);

		layer.add(bpanel,JLayeredPane.MODAL_LAYER);
		System.out.println("添加安妮结束");
	}
	
	public void setText(int x,int y){//设置物流信息的搜索框模糊查询文本与按钮
		cpanel1=new JPanel();//显示按钮的面板
		cpanel1.setOpaque(false);
		cpanel1.setBounds(0, (int)(y*0.1), x, (int)(y*0.1));
		
		text=new JTextField(20);
		text.setOpaque(false);
		cpanel1.add(text);
		
		b1=new JButton("搜素");
		b1.setFont(new Font("宋体",Font.PLAIN,14));
		b1.setSize(4, 1);
		b1.setOpaque(false);
		
		b1.addActionListener(new ActionListener(){//添加响应函数
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("搜索按钮按下");
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						
						//待添加
						
						//将文本框中的内容读出来，进行物流订单编号的模糊搜索
						
						/*
						 cpanel2在这里创建，用来显示模糊搜索的结果，cpanel位置如下
						 cpanel.setbounds(0,(int)(y*0.2),x,(int)(y*0.8))
						 
						  1、cpanel2中包含一个jcombobox，用来显示搜索结果
							结果中，将一个订单编号的信息作为一条显示,一条中应该包括：
						 	订单编号，起始地点，终点，途径地，订单的状态
						 	
						 2、cpanel中包含一个按钮，就是b2
						 按下按钮b2后，绘制该订单在地图中的路线，还需要将中转站的信息表明出来
						 
						 注意：
						 	此时要判断jcombobox里面是否有项被选中，如果有选中就在地图中绘制被选中的项
						 	如果没有被选中的，就绘制文本框中的
						 	
						 	可以参考路径删除那一块的写法
						 
						 **/
						
						
					}
					
				});
				t.start();
			}
			
		});		
		cpanel1.add(b1);
		layer.add(cpanel1,JLayeredPane.MODAL_LAYER);
	}
	
	public IndexUI() {
		super("物流综合信息平台");
		layer=new JLayeredPane();
		setBackgroundImage();
		
		int width=image.getIconWidth();
		int height=image.getIconHeight();
		
		setButton(width,(int)(height*0.1),width,height);//添加按钮
		
		
		setLayeredPane(layer);
		setVisible(true);
		setSize(width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation();
	}
}
