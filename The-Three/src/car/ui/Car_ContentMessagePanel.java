package car.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
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
import driver.db.Driver_Database;
import entity.Car;
import entity.Driver;
import entity.Route;
import route.db.Route_Database;
import ui.ContentMessagePanel;

public class Car_ContentMessagePanel {
	public static void setCarInfo(ContentMessagePanel self) {//车队管理概要信息
		self.removeAll();//将面板上面的组件全部清空
		
		String[] name= {"车牌号","驾驶员1","驾驶员2","路线"};
		self.array=Car_Database.getCarInfo();
		if(self.array==null||self.array.length==0) {
			JLabel label=new JLabel("当前系统无可用车辆信息！");
			self.add(label);
			self.revalidate();
			self.repaint();
			return;
		}
		String[][] data=new String[self.array.length][4];
		for(int i=0;i<self.array.length;i++) {
			data[i]=self.array[i].toStringArray();
		}
		JTable table=new JTable(data,name);
		table.setOpaque(false);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setOpaque(false);
		
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
		        int col = table.getSelectedColumn();
		        String value = (String) table.getValueAt(row, col);
		        
		        if(col==0){
		        	setCarDetailInfo(self,row);
		        }
		        
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
		return;
	}
	public static void carInfo_Add_Del(ContentMessagePanel self){//车辆信息的增加修改删除操作
		self.removeAll();//清除面板上面的所有组件

		self.car_mpanel=new JPanel(new GridLayout(1,2,0,0));//一行两列
		
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
		document.addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("TextUpdate!");
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("TextChanged!");
						String s=self.car_text1.getText();
						if(s.equals(self.car_lastmessage)){
							return;
						}
						System.out.println("Text is diff");
						test_change(self,s);
						self.car_lastmessage=self.car_text1.getText();
					}
					
				});
				t.start();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("TextChanged!");
						String s=self.car_text1.getText();
						if(s.equals(self.car_lastmessage)){
							return;
						}
						System.out.println("Text is diff"+s);
						test_change(self,s);
						self.car_lastmessage=self.car_text1.getText();
					}
					
				});
				t.start();
			}
			
		});
		
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
				
				StringBuilder carinfo=new StringBuilder();//保存文本框中对应的信息
				carinfo.append(self.car_text1.getText());
				carinfo.append("#");
				carinfo.append(self.car_text2.getText());
				carinfo.append("#");
				carinfo.append(self.car_text3.getText());
				carinfo.append("#");
				carinfo.append(self.car_text4.getText());
				
				String newcarinfo=carinfo.toString();
				System.out.println(newcarinfo);
				
				String s=carInfo_addCarInfo(newcarinfo);//将该信息输出在界面上面
				JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
				
				self.car_text1.setText("");
				self.car_text2.setText("");
				self.car_text3.setText("");
				self.car_text4.setText("");
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
				StringBuilder carinfo=new StringBuilder();//保存文本框中对应的信息
				carinfo.append(self.car_text1.getText());
				carinfo.append("#");
				carinfo.append(self.car_text2.getText());
				carinfo.append("#");
				carinfo.append(self.car_text3.getText());
				carinfo.append("#");
				carinfo.append(self.car_text4.getText());
				
				String newcarinfo=carinfo.toString();
				System.out.println(newcarinfo);
				String s=carInfo_ModifyCarInfo(newcarinfo);//将该信息输出在界面上面
				JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
				
				self.car_text1.setText("");
				self.car_text2.setText("");
				self.car_text3.setText("");
				self.car_text4.setText("");
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
				StringBuilder carinfo=new StringBuilder();//保存文本框中对应的信息
				carinfo.append(self.car_text1.getText());
				carinfo.append("#");
				carinfo.append(self.car_text2.getText());
				carinfo.append("#");
				carinfo.append(self.car_text3.getText());
				carinfo.append("#");
				carinfo.append(self.car_text4.getText());
				
				String newcarinfo=carinfo.toString();
				System.out.println(newcarinfo);
				String s=carInfo_deletCarInfo(newcarinfo);//将该信息输出在界面上面
				JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
			
				self.car_text1.setText("");
				self.car_text2.setText("");
				self.car_text3.setText("");
				self.car_text4.setText("");
			}
			
		});
		
		self.car_mpanel.add(panel);
		
		//第二列
		self.car_panel2=new JPanel();
		
		self.car_model=new DefaultComboBoxModel();
		
		//setCarSecondPanel(self,self.car_num);
		
		self.car_bobox=new JComboBox(self.car_model);
		self.car_bobox.setBorder(BorderFactory.createTitledBorder("车牌号模糊查询结果"));
		self.car_bobox.setSelectedIndex(-1);//设置不选中
		
		//添加监听函数
		self.car_bobox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED){
					Thread t=new Thread(new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							self.car_text1.setText(String.valueOf(self.car_model.getSelectedItem()));
							System.out.println("ComboSelected...");
							self.car_bobox.setSelectedIndex(-1);
							self.car_lastmessage=self.car_text1.getText();
						}
						
					});
					t.start();
				}
			}
		});
		
		self.car_panel2.add(self.car_bobox);
		self.car_mpanel.add(self.car_panel2);
		
		self.add(self.car_mpanel);
		self.revalidate();
		self.repaint();
		return;
		
	}
	public static void setCarDetailInfo(ContentMessagePanel self,int n){//查看车辆详细信息
		self.removeAll();
		
		//显示基本信息
		String[] name1= {"车牌号","驾驶员1","驾驶员2","路线"};
		Car carDetail=new Car();
		carDetail=self.array[n];
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
		String[] name3={"路线编号","初始站点","终点站"};
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
		return;
	}
	public static void test_change(ContentMessagePanel self,String s){
		System.out.println("即将查询："+s);
		String[] temp_car=Car_Database.getCarNumber(s);//用来保存模糊查询得到的车牌号信息
		System.out.println("模糊查询结束");
		
		self.car_num.clear();
		for(int i=0;i<temp_car.length;i++){
			self.car_num.add(temp_car[i]);
		}	
		setCarSecondPanel(self,self.car_num);//当模糊查询结果改变了，car面板上面的信息也就需要改变
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
	public static void setCarSecondPanel(ContentMessagePanel self,ArrayList<String> num){//动态显示carpanel界面的第二列
		System.out.println("我在动态显示结果");
		self.car_model=new DefaultComboBoxModel();//先清空
		self.car_num=num;
		int len=self.car_num.size();
		System.out.println(len);
		
		for(int i=0;i<len;i++){
			System.out.println(self.car_num.get(i));
			self.car_model.addElement(self.car_num.get(i));
		}
		self.car_bobox.setModel(self.car_model);
		self.car_bobox.setSelectedIndex(-1);
	}
}
