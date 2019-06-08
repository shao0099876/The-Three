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
	private static boolean flag;//用来标记删除路线信息时的路线编号来自哪里
	//false表明删除的路线编号来自于文本框
	//true表明删除的路线编号来自于点击的模糊搜索结果
	private static String del_routenum;//标记被删除的路线编号
	
	private static DocumentListener documentListener1=new DocumentListener() {

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
					modroute_comboboxChange();
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
			DebugInfo.DebugInfo("Combobox项被选中！");
			Thread t=new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					modroute_textChange();
				}
				
			});
			t.start();
		}};
	
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
		
		flag=true;//初始化为删除路线编号来自于文本框
		del_routenum=" ";//初始化
		
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
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
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
												del_routenum=self.route_deltext.getText();//获取当前文本框中的路线编号
											}
											String message=Route_Database.delRouteInfo(del_routenum);
											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											self.route_deltext.setText("");//清空文本框中内容
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
		
		JPanel p = new JPanel(new GridLayout(5,2,5,5));
		p.setOpaque(false);
		
		//第一行 路线编号
		JLabel lab1=new JLabel("路线编号");
		lab1.setFont(new Font("宋体",Font.PLAIN,20));
		lab1.setOpaque(false);
		p.add(lab1);
		
		self.route_addtext1=new JTextField(20);
		self.route_addtext1.setEditable(true);//设置为可编辑
		self.route_addtext1.setOpaque(false);
		p.add(self.route_addtext1);
		
		//第二行 起始站台
		JLabel lab2=new JLabel("起始站台");
		lab2.setFont(new Font("宋体",Font.PLAIN,20));
		lab2.setOpaque(false);
		p.add(lab2);
		
		self.route_addtext2=new JTextField(20);
		self.route_addtext2.setEditable(true);//设置为可编辑
		self.route_addtext2.setOpaque(false);
		p.add(self.route_addtext2);
		
		//第三行 终点站
		JLabel lab3=new JLabel("终点站");
		lab3.setFont(new Font("宋体",Font.PLAIN,20));
		lab3.setOpaque(false);
		p.add(lab3);
		
		self.route_addtext3=new JTextField(20);
		self.route_addtext3.setEditable(true);//设置为可编辑
		self.route_addtext3.setOpaque(false);
		p.add(self.route_addtext3);
		
		//第四行 中转站
		JLabel lab4=new JLabel("中转站");
		lab4.setFont(new Font("宋体",Font.PLAIN,20));
		lab4.setOpaque(false);
		p.add(lab4);
		
		self.route_addtext4=new JTextField(20);
		self.route_addtext4.setEditable(true);//设置为可编辑
		self.route_addtext4.setOpaque(false);
		p.add(self.route_addtext4);
		
		//第五行 增加按钮
		p.add(new JLabel("添加中转站信息格式：青岛-成都-济南"));
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
						DebugInfo.DebugInfo("添加路线信息的按钮被按下");
						String[] s=new String[4]; 
						s[0]=self.route_addtext1.getText();
						s[1]=self.route_addtext2.getText();
						s[2]=self.route_addtext3.getText();
						s[3]=self.route_addtext4.getText();
						
						String S=s[0]+"#"+s[1]+"#"+s[2]+"#"+s[3];//保存增加的路径信息
						System.out.println("增加路径信息:"+S);
						
						String message=Route_Database.addRouteInfo(S);//将该信息输出在界面上面
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
		DebugInfo.DebugInfo("完成绘制增加路线信息Panel");
		return;
	}

	public static void modifyRouteInfo(ContentMessagePanel p_self){//修改路线信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制修改路线信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		self.route_mpanel=new JPanel(new GridLayout(1,2,0,0));
		self.route_mpanel.setOpaque(false);
		
		//第一列
		JPanel panel=new JPanel(new GridLayout(5,2,5,5));
		panel.setOpaque(false);
		
		//第一行 路线编号
		JLabel label1=new JLabel("路线编号");
		label1.setFont(new Font("宋体",Font.PLAIN,20));
		label1.setOpaque(false);
		panel.add(label1);
		
		self.route_modifytext1=new JTextField(20);
		self.route_modifytext1.setEditable(true);//设置为可编辑
		self.route_modifytext1.setOpaque(false);
		panel.add(self.route_modifytext1);
		
		//对文本框增加响应函数
		Document document = self.route_modifytext1.getDocument();
		document.addDocumentListener(documentListener1);
		
		//第二行 起始站台
		JLabel label2=new JLabel("起始站台");
		label2.setFont(new Font("宋体",Font.PLAIN,20));
		label2.setOpaque(false);
		panel.add(label2);
		
		self.route_modifytext2=new JTextField(20);
		self.route_modifytext2.setEditable(true);//设置为可编辑
		self.route_modifytext2.setOpaque(false);
		panel.add(self.route_modifytext2);
		
		//第三行 终点站
		JLabel label3=new JLabel("终点站");
		label3.setFont(new Font("宋体",Font.PLAIN,20));
		label3.setOpaque(false);
		panel.add(label3);
		
		self.route_modifytext3=new JTextField(20);
		self.route_modifytext3.setEditable(true);//设置为可编辑
		self.route_modifytext3.setOpaque(false);
		panel.add(self.route_modifytext3);
		
		//第四行 中转站
		JLabel label4=new JLabel("中转站");
		label4.setFont(new Font("宋体",Font.PLAIN,20));
		label4.setOpaque(false);
		panel.add(label4);
		
		self.route_modifytext4=new JTextField(20);
		self.route_modifytext4.setEditable(true);//设置为可编辑
		self.route_modifytext4.setOpaque(false);
		panel.add(self.route_modifytext4);
		
		//第五行 增加按钮
		panel.add(new JLabel("修改中转站信息格式：青岛-成都-济南"));
		//中转站格式待修改完善
		
		JButton b=new JButton("确认");
		b.setFont(new Font("宋体",Font.PLAIN,14));
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
						s[0]=self.route_modifytext1.getText();
						s[1]=self.route_modifytext2.getText();
						s[2]=self.route_modifytext3.getText();
						s[3]=self.route_modifytext4.getText();
						
						String message=Route_Database.modRouteInfo(s);//将该信息输出在界面上面
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
		
		//第二列
		self.route_panel2=new JPanel();
		self.route_panel2.setOpaque(false);
		
		self.route_bobox=new JComboBox();
		self.route_bobox.setOpaque(false);
		self.route_bobox.setBorder(BorderFactory.createTitledBorder("路线编号模糊查询结果"));
		self.route_bobox.setSelectedIndex(-1);//设置不选中
		
		//添加监听函数
		self.route_bobox.addItemListener(itemListener1);
		
		self.route_panel2.add(self.route_bobox);
		
		self.route_mpanel.add(self.route_panel2);
	
		
		self.add(self.route_mpanel);
		self.revalidate();
		self.repaint();
		DebugInfo.DebugInfo("完成绘制修改路线信息Panel");
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
		String[] temp_route=Route_Database.getMohuRouteNumInfo(s);//用来保存模糊查询得到的路线编号
		
		DefaultComboBoxModel<String> route_model=new DefaultComboBoxModel<String>();
		for(int i=0;i<temp_route.length;i++){
			route_model.addElement(temp_route[i]);
		}
		self.route_bobox.setModel(route_model);
		//self.car_bobox.setSelectedIndex(-1);//当模糊查询结果改变了，car面板上面的信息也就需要改变
		self.route_bobox.addItemListener(itemListener1);
	}
}
