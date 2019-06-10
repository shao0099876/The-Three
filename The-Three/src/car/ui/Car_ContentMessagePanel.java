package car.ui;

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
	public static void carInfo_Add_Del(ContentMessagePanel p_self){//车辆信息的增加修改删除操作
		DebugInfo.DebugInfo("开始绘制车辆增删改Panel");
		self=p_self;
		self.removeAll();//清除面板上面的所有组件

		self.car_mpanel=new JPanel(new GridLayout(1,2,0,0));//一行两列
		self.car_mpanel.setOpaque(false);
		
		//第一列
		JPanel panel=new JPanel(new GridLayout(6,3,5,5));//六行三列
		panel.setOpaque(false);
		
		//第一行 车辆编号
		JLabel label1=new JLabel("车辆编号");
		label1.setFont(new Font("宋体",Font.PLAIN,20));
		label1.setOpaque(false);
		panel.add(label1);
		
		self.car_text1=new JTextField(20);
		self.car_text1.setEditable(true);//设置为可编辑
		self.car_text1.setOpaque(false);
		panel.add(self.car_text1);
		panel.add(new JLabel("    "));
		
		//对文本框增加相应函数
		Document document = self.car_text1.getDocument();
		document.addDocumentListener(documentListener);
		
		//第二行 驾驶员1号
		JLabel label2=new JLabel("驾驶员一号编号");
		label2.setFont(new Font("宋体",Font.PLAIN,20));
		label2.setOpaque(false);
		panel.add(label2);
		
		self.car_text2=new JTextField();
		self.car_text2.setEditable(true);//设置为可编辑
		self.car_text2.setOpaque(false);
		panel.add(self.car_text2);
		panel.add(new JLabel("    "));
		
		//第三行 驾驶员2号
		JLabel label3=new JLabel("驾驶员二号编号");
		label3.setFont(new Font("宋体",Font.PLAIN,20));
		label3.setOpaque(false);
		panel.add(label3);
		
		self.car_text3=new JTextField();
		self.car_text3.setEditable(true);//设置为可编辑
		self.car_text3.setOpaque(false);
		panel.add(self.car_text3);
		panel.add(new JLabel("    "));
		
		//第四行 路线编号
		JLabel label4=new JLabel("路线编号");
		label4.setFont(new Font("宋体",Font.PLAIN,20));
		label4.setOpaque(false);
		panel.add(label4);
		
		self.car_text4=new JTextField();
		self.car_text4.setEditable(true);//设置为可编辑
		self.car_text4.setOpaque(false);
		panel.add(self.car_text4);
		panel.add(new JLabel("    "));
		
		//第五行 增加空行
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//第六行 添加按钮
		JButton[] button=new JButton[3];
		
		button[0]=new JButton("添加");
		button[0].setFont(new Font("宋体",Font.PLAIN,14));
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		panel.add(button[0]);
		
		button[0].addActionListener(new ActionListener(){//增加按钮添加响应函数
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DebugInfo.DebugInfo("添加按钮被按下");
						StringBuilder carinfo=new StringBuilder();//保存文本框中对应的信息
						carinfo.append(self.car_text1.getText());
						carinfo.append("#");
						carinfo.append(self.car_text2.getText());
						carinfo.append("#");
						carinfo.append(self.car_text3.getText());
						carinfo.append("#");
						carinfo.append(self.car_text4.getText());
						
						String newcarinfo=carinfo.toString();
						
						String s=carInfo_addCarInfo(newcarinfo);//将该信息输出在界面上面
						JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
						
						self.car_text1.setText("");
						self.car_text2.setText("");
						self.car_text3.setText("");
						self.car_text4.setText("");
					}
				});
				t.start();
			}
			
		});
		
		button[1]=new JButton("修改");
		button[1].setFont(new Font("宋体",Font.PLAIN,14));
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		panel.add(button[1]);
		
		button[1].addActionListener(new ActionListener(){//修改按钮添加响应函数

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("修改按钮被按下");
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						StringBuilder carinfo=new StringBuilder();//保存文本框中对应的信息
						carinfo.append(self.car_text1.getText());
						carinfo.append("#");
						carinfo.append(self.car_text2.getText());
						carinfo.append("#");
						carinfo.append(self.car_text3.getText());
						carinfo.append("#");
						carinfo.append(self.car_text4.getText());
						
						String newcarinfo=carinfo.toString();
						String s=carInfo_ModifyCarInfo(newcarinfo);//将该信息输出在界面上面
						JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
						
						self.car_text1.setText("");
						self.car_text2.setText("");
						self.car_text3.setText("");
						self.car_text4.setText("");
					}
					
				});
				t.start();
			}
			
		});
		
		button[2]=new JButton("删除");
		button[2].setFont(new Font("宋体",Font.PLAIN,14));
		button[2].setSize(4, 1);
		button[2].setOpaque(false);
		panel.add(button[2]);
		
		button[2].addActionListener(new ActionListener(){//删除按钮添加响应函数
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("删除按钮被按下");
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						StringBuilder carinfo=new StringBuilder();//保存文本框中对应的信息
						carinfo.append(self.car_text1.getText());
						carinfo.append("#");
						carinfo.append(self.car_text2.getText());
						carinfo.append("#");
						carinfo.append(self.car_text3.getText());
						carinfo.append("#");
						carinfo.append(self.car_text4.getText());
						
						String newcarinfo=carinfo.toString();
						String s=carInfo_deletCarInfo(newcarinfo);//将该信息输出在界面上面
						JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
					
						self.car_text1.setText("");
						self.car_text2.setText("");
						self.car_text3.setText("");
						self.car_text4.setText("");
					}
					
				});
				t.start();
				
			}
			
		});
		
		self.car_mpanel.add(panel);
		
		//第二列
		self.car_panel2=new JPanel();
		self.car_panel2.setOpaque(false);
		
		self.car_bobox=new JComboBox();
		self.car_bobox.setOpaque(false);
		self.car_bobox.setBorder(BorderFactory.createTitledBorder("车牌号模糊查询结果"));
		self.car_bobox.setSelectedIndex(-1);//设置不选中
		
		//添加监听函数
		self.car_bobox.addItemListener(itemListener);
		
		self.car_panel2.add(self.car_bobox);
		self.car_mpanel.add(self.car_panel2);
		
		self.add(self.car_mpanel);
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
	public static void comboboxChange(){
		self.car_bobox.removeItemListener(itemListener);
		String s=self.car_text1.getText();
		String[] temp_car=Car_Database.getCarNumber(s);//用来保存模糊查询得到的车牌号信息
		DefaultComboBoxModel<String> car_model=new DefaultComboBoxModel<String>();
		for(int i=0;i<temp_car.length;i++){
			car_model.addElement(temp_car[i]);
		}
		self.car_bobox.setModel(car_model);
		//self.car_bobox.setSelectedIndex(-1);//当模糊查询结果改变了，car面板上面的信息也就需要改变
		self.car_bobox.addItemListener(itemListener);
	}
	public static void textChange() {
		Document document=self.car_text1.getDocument();
		document.removeDocumentListener(documentListener);
		String s=(String) self.car_bobox.getSelectedItem();
		self.car_text1.setText(s);
		document.addDocumentListener(documentListener);
	}
	public static String carInfo_addCarInfo(String newcarinfo){//增加或者修改车辆信息
		String s=Car_Database.AddCarInfo(newcarinfo);
		return s;
	}
	public static String carInfo_ModifyCarInfo(String newcarinfo){//修改车辆信息
		String s=Car_Database.ModifyCarInfo(newcarinfo);
		return s;
	}
	public static String carInfo_deletCarInfo(String newcarinfo){//删除车辆信息
		String s=Car_Database.DeleteCarInfo(newcarinfo);
		return s;
	}
//	public static void setCarSecondPanel(ContentMessagePanel self,ArrayList<String> num){//动态显示carpanel界面的第二列
//		self.car_model=new DefaultComboBoxModel();//先清空
//		self.car_num=num;
//		int len=self.car_num.size();
//		System.out.println(len);
//		
//		for(int i=0;i<len;i++){
//			System.out.println(self.car_num.get(i));
//			self.car_model.addElement(self.car_num.get(i));
//		}
//		self.car_bobox.setModel(self.car_model);
//		self.car_bobox.setSelectedIndex(-1);
//	}
}
