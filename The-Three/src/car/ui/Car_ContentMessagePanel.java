package car.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
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

public class Car_ContentMessagePanel {
	private static Car[] array=null;
	private static ContentMessagePanel self;
	
	private static boolean flag;//用来标记删除车辆信息时的车辆编号来自哪里
	//false表明删除的车辆编号来自于文本框
	//true表明删除的车辆编号来自于点击的模糊搜索结果
	
	private static String del_carnum;//标记被删除的路线编号
	
	public static JTextField car_text1,car_text2,car_text3,car_text4;
	public static JComboBox car_bobox;
	public static JPanel car_mpanel,car_panel2;
	public static JTextField car_deltext;//用于车辆删除
	
	public static void comboboxChange(){
		car_bobox.removeItemListener(itemListener);
		String s=car_text1.getText();
		String[] temp_car=Car_Database.getCarNumber(s);//用来保存模糊查询得到的车牌号信息
		DefaultComboBoxModel<String> car_model=new DefaultComboBoxModel<String>();
		for(int i=0;i<temp_car.length;i++){
			car_model.addElement(temp_car[i]);
		}
		car_bobox.setModel(car_model);
		car_bobox.addItemListener(itemListener);
	}
	public static void textChange() {
		Document document=car_text1.getDocument();
		document.removeDocumentListener(documentListener);
		String s=(String) car_bobox.getSelectedItem();
		car_text1.setText(s);
		document.addDocumentListener(documentListener);
	}

	private static DocumentListener documentListener=new DocumentListener() {

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
					comboboxChange();
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
					comboboxChange();
				}
			});
			t.start();
		}
		
	};
	private static ItemListener itemListener=new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			DebugInfo.DebugInfo("Combobox项被选中！");
			Thread t=new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					textChange();
				}
				
			});
			t.start();
		}};
		
	public static void setCarInfo(ContentMessagePanel p_self) {//车队管理概要信息
		self=p_self;
		DebugInfo.DebugInfo("开始绘制车队管理概要信息Panel");
		self.removeAll();//将面板上面的组件全部清空
		
		String[] name= {"车牌号","驾驶员1","驾驶员2","路线"};
		array=Car_Database.getCarInfo();
		
		if(array==null||array.length==0) {
			JOptionPane.showMessageDialog(self,"当前系统无可用车辆信息！","information",JOptionPane.INFORMATION_MESSAGE);
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
		DebugInfo.DebugInfo("开始绘制车辆增删改Panel");
		self=p_self;
		self.removeAll();//清除面板上面的所有组件

		JPanel panel=new JPanel(new GridLayout(6,2,5,5));//六行三列
		panel.setOpaque(false);
		
		//第一行 车辆编号
		JLabel label1=new JLabel("车辆编号");
		label1.setFont(new Font("宋体",Font.PLAIN,20));
		label1.setOpaque(false);
		panel.add(label1);
		
		car_text1=new JTextField(20);
		car_text1.setEditable(true);//设置为可编辑
		car_text1.setOpaque(false);
		panel.add(car_text1);
		
		//第二行 驾驶员1号
		JLabel label2=new JLabel("驾驶员一号编号");
		label2.setFont(new Font("宋体",Font.PLAIN,20));
		label2.setOpaque(false);
		panel.add(label2);
		
		car_text2=new JTextField();
		car_text2.setEditable(true);//设置为可编辑
		car_text2.setOpaque(false);
		panel.add(car_text2);
		
		//第三行 驾驶员2号
		JLabel label3=new JLabel("驾驶员二号编号");
		label3.setFont(new Font("宋体",Font.PLAIN,20));
		label3.setOpaque(false);
		panel.add(label3);
		
		car_text3=new JTextField();
		car_text3.setEditable(true);//设置为可编辑
		car_text3.setOpaque(false);
		panel.add(car_text3);
		
		//第四行 路线编号
		JLabel label4=new JLabel("路线编号");
		label4.setFont(new Font("宋体",Font.PLAIN,20));
		label4.setOpaque(false);
		panel.add(label4);
		
		car_text4=new JTextField();
		car_text4.setEditable(true);//设置为可编辑
		car_text4.setOpaque(false);
		panel.add(car_text4);
		
		//第五行 增加空行
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第六行 添加按钮
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
		
		//显示基本信息
		String[] name1= {"车牌号","驾驶员1","驾驶员2","路线"};
		String[][] data1=new String[1][4];
		data1[0]=carDetail.toStringArray();
		JTable table1=new JTable(data1,name1);
		table1.setOpaque(false);
		JScrollPane scroll1=new JScrollPane(table1);
		scroll1.setOpaque(false);
		
		//显示驾驶员的详细信息
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
		
		//显示路线的详细信息
		String[] name3={"路线编号","起始站点","终点站","中转站点"};
		Route routeInfo=new Route();
		routeInfo=Route_Database.getRouteInfo(carDetail.routeNumber);//查询路线的详细信息
		
		String[][] data3=new String[1][4];
		data3[0]=routeInfo.toStringArray();
		
		JTable table3=new JTable(data3,name3);
		table3.setOpaque(false);
		JScrollPane scroll3=new JScrollPane(table3);
		scroll3.setOpaque(false);
		
		JPanel panel=new JPanel(new GridLayout(1,3,0,0));//一行三列 显示列表
		panel.setOpaque(false);
		
		panel.add(scroll1);
		panel.add(scroll2);
		panel.add(scroll3);
		
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

		car_mpanel=new JPanel(new GridLayout(1,2,0,0));//一行两列
		car_mpanel.setOpaque(false);
		
		//第一列
		JPanel panel=new JPanel(new GridLayout(6,2,5,5));//六行三列
		panel.setOpaque(false);
		
		//第一行 车辆编号
		JLabel label1=new JLabel("车辆编号");
		label1.setFont(new Font("宋体",Font.PLAIN,20));
		label1.setOpaque(false);
		panel.add(label1);
		
		car_text1=new JTextField(20);
		car_text1.setEditable(true);//设置为可编辑
		car_text1.setOpaque(false);
		panel.add(car_text1);
		
		//对文本框增加相应函数
		Document document = car_text1.getDocument();
		document.addDocumentListener(documentListener);
		
		//第二行 驾驶员1号
		JLabel label2=new JLabel("驾驶员一号编号");
		label2.setFont(new Font("宋体",Font.PLAIN,20));
		label2.setOpaque(false);
		panel.add(label2);
		
		car_text2=new JTextField();
		car_text2.setEditable(true);//设置为可编辑
		car_text2.setOpaque(false);
		panel.add(car_text2);
		
		//第三行 驾驶员2号
		JLabel label3=new JLabel("驾驶员二号编号");
		label3.setFont(new Font("宋体",Font.PLAIN,20));
		label3.setOpaque(false);
		panel.add(label3);
		
		car_text3=new JTextField();
		car_text3.setEditable(true);//设置为可编辑
		car_text3.setOpaque(false);
		panel.add(car_text3);
		
		//第四行 路线编号
		JLabel label4=new JLabel("路线编号");
		label4.setFont(new Font("宋体",Font.PLAIN,20));
		label4.setOpaque(false);
		panel.add(label4);
		
		car_text4=new JTextField();
		car_text4.setEditable(true);//设置为可编辑
		car_text4.setOpaque(false);
		panel.add(car_text4);
		
		//第五行 增加空行
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第六行 添加按钮
		panel.add(new JLabel("    "));
		JButton button=new JButton();
		
		button=new JButton("添加");
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
		
		car_mpanel.add(panel);
		
		//第二列
		car_panel2=new JPanel();
		car_panel2.setOpaque(false);
		
		car_bobox=new JComboBox();
		car_bobox.setOpaque(false);
		car_bobox.setBorder(BorderFactory.createTitledBorder("车牌号模糊查询结果"));
		car_bobox.setSelectedIndex(-1);//设置不选中
		
		//添加监听函数
		car_bobox.addItemListener(itemListener);
		
		car_panel2.add(car_bobox);
		car_mpanel.add(car_panel2);
		
		self.add(car_mpanel);
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
		
		
		//设置搜索panel
		JPanel spanel=new JPanel(new GridLayout(1,2,0,0));
		spanel.setOpaque(false);
		panel.add(spanel,BorderLayout.NORTH);
		
		//设置显示搜索的组件
		JPanel cpanel=new JPanel();
		cpanel.setOpaque(false);
		panel.add(cpanel,BorderLayout.CENTER);
		
		car_deltext=new JTextField(20);
		car_deltext.setEditable(true);
		car_deltext.setOpaque(false);
		spanel.add(car_deltext);
		
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
						String s=car_deltext.getText();
						
						
						Car[] array=Car_Database.getMohuCarInfo(s);
						
						if(array==null||array.length==0){
							cpanel.removeAll();
							cpanel.add(new JLabel("该系统中暂无该车辆信息!"));
							cpanel.revalidate();
							cpanel.repaint();
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
												del_carnum=car_deltext.getText();//获取当前文本框中的路线编号
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
	
}
