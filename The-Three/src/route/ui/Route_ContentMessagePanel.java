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

import route.db.Route_Database;
import ui.ContentMessagePanel;
import car.db.Car_Database;
import client.DebugInfo;
import entity.Car;
import entity.Route;

public class Route_ContentMessagePanel {
	private static Route[] array=null;
	private static ContentMessagePanel self;
	private static boolean flag;//用来标记删除路线信息时的路线编号来自哪里
	//false表明删除的路线编号来自于文本框
	//true表明删除的路线编号来自于点击的模糊搜索结果
	private static String del_routenum;//标记被删除的路线编号
	
	public static void setRouteInfo(ContentMessagePanel p_self) {//查看路线信息 不包含GPS信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制路线概要信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		String[] name= {"路线编号","起始站点","终点站","中转站点"};
		array=Route_Database.getAllRouteInfo();//查询所有路线信息
		
		if(array==null||array.length==0) {
			JLabel label=new JLabel("当前系统无可用路线信息！");
			self.add(label);
			self.revalidate();
			self.repaint();
			DebugInfo.DebugInfo("完成绘制查看路线信息Panel");
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
				DebugInfo.DebugInfo("路线信息表格项被点击！");
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int row = table.getSelectedRow();
				        int col = table.getSelectedColumn();
				        if(col==0){
				        	//待添加 绘制具体路线上面的车辆的GPS信息
				        	
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
		
		flag=false;//初始化为删除路线编号来自于文本框
		del_routenum="";//初始化
		
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
		
		self.route_deltext=new JTextField(20);
		self.route_deltext.setEditable(true);
		self.route_deltext.setOpaque(false);
		spanel.add(self.route_deltext);
		
		JButton b=new JButton("搜索");
		b.setFont(new Font("宋体",Font.PLAIN,14));
		b.setSize(4, 1);
		b.setOpaque(false);
		spanel.add(b);
		
		//按钮添加响应函数
		b.addActionListener(new ActionListener(){//增加按钮添加响应函数
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DebugInfo.DebugInfo("搜索按钮被按下");
						String s=self.route_deltext.getText();
						
						Route[] array=Route_Database.getMohuRouteInfo(s);
						
						if(array==null||array.length==0){
							cpanel.removeAll();
							cpanel.add(new JLabel("该系统中暂无该车辆信息!"));
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
									// TODO Auto-generated method stub
									DebugInfo.DebugInfo("路线信息表格项被点击！");
									Thread t=new Thread(new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub
											int row = table.getSelectedRow();
									        int col = table.getSelectedColumn();
									        if(col==0){
									        	del_routenum=table.getValueAt(row, 0).toString();//获取当前点击的路线的编号
									        	flag=true;//点击搜索结果后，删除信息就来自于模糊搜索结果
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
									// TODO Auto-generated method stub
									Thread t=new Thread(new Runnable(){

										@Override
										public void run() {
											// TODO Auto-generated method stub
											if(!flag){//删除信息来自文本框
												del_routenum=self.route_deltext.getText();//获取当前文本框中的路线编号
											}
											String message=Route_Database.delRouteInfo(del_routenum);
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											self.route_deltext.setText("");//清空文本框中内容
										}
									});
								}
								
							});
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
		
		JPanel panel=new JPanel(new GridLayout(5,2,5,0));
		panel.setOpaque(false);
		
		//第一行 路线编号
		JLabel lab1=new JLabel("路线编号");
		lab1.setFont(new Font("宋体",Font.PLAIN,20));
		lab1.setOpaque(false);
		panel.add(lab1);
		
		self.route_addtext1=new JTextField(20);
		self.route_addtext1.setEditable(true);//设置为可编辑
		self.route_addtext1.setOpaque(false);
		panel.add(self.route_addtext1);
		
		//第二行 起始站台
		JLabel lab2=new JLabel("起始站台");
		lab2.setFont(new Font("宋体",Font.PLAIN,20));
		lab2.setOpaque(false);
		panel.add(lab2);
		
		self.route_addtext2=new JTextField(20);
		self.route_addtext2.setEditable(true);//设置为可编辑
		self.route_addtext2.setOpaque(false);
		panel.add(self.route_addtext2);
		
		//第三行 终点站
		JLabel lab3=new JLabel("终点站");
		lab3.setFont(new Font("宋体",Font.PLAIN,20));
		lab3.setOpaque(false);
		panel.add(lab3);
		
		self.route_addtext3=new JTextField(20);
		self.route_addtext3.setEditable(true);//设置为可编辑
		self.route_addtext3.setOpaque(false);
		panel.add(self.route_addtext3);
		
		//第四行 中转站
		JLabel lab4=new JLabel("终点站");
		lab4.setFont(new Font("宋体",Font.PLAIN,20));
		lab4.setOpaque(false);
		panel.add(lab4);
		
		self.route_addtext4=new JTextField(100);
		self.route_addtext4.setEditable(true);//设置为可编辑
		self.route_addtext4.setOpaque(false);
		panel.add(self.route_addtext4);
		
		//第五行 增加按钮
		panel.add(new JLabel("添加中转站信息格式：青岛-成都-济南"));
		//中转站格式待修改完善
		
		JButton b=new JButton("确认");
		b=new JButton("添加");
		b.setFont(new Font("宋体",Font.PLAIN,14));
		b.setSize(4, 1);
		b.setOpaque(false);
		panel.add(b);
		
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("添加路线信息的按钮被按下");
				Thread t=new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String[] s=new String[4]; 
						s[0]=self.route_addtext1.getText();
						s[1]=self.route_addtext2.getText();
						s[2]=self.route_addtext3.getText();
						s[3]=self.route_addtext4.getText();
						
						String message=Route_Database.addRouteInfo(s);//将该信息输出在界面上面
						JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
					
						self.route_addtext1.setText("");
						self.route_addtext2.setText("");
						self.route_addtext3.setText("");
						self.route_addtext4.setText("");
						
					}
					});
				}
			
		});
		
		self.add(panel);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("完成绘制增加路线信息Panel");
		return;
	}

}
