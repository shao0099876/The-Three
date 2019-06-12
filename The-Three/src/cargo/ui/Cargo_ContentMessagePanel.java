package cargo.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import car.db.Car_Database;
import cargo.db.Cargo_Database;
import client.Client;
import entity.Cargo;
import entity.Route;
import entity.Station;
import route.db.Route_Database;
import station.db.Station_Database;
import ui.ContentMessagePanel;

public class Cargo_ContentMessagePanel {
	public static void showCargoList(ContentMessagePanel contentPanel) {
		// TODO Auto-generated method stub
		ContentMessagePanel self=contentPanel;
		self.removeAll();
		String[] name= {"物流单号","出发地","目的地","路线编号"};
		Cargo[] array=Cargo_Database.getCargoList();
		if(array==null||array.length==0) {
			JOptionPane.showMessageDialog(self,"当前系统无可用信息！","information",JOptionPane.INFORMATION_MESSAGE);
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
		
		self.add(scroll);
		self.revalidate();
		self.repaint();
	}

	public static void addCargo(ContentMessagePanel contentPanel) {
		// TODO Auto-generated method stub
		ContentMessagePanel self=contentPanel;
		self.removeAll();
		JPanel panel=new JPanel(new GridLayout(4,2,5,5));
		panel.setOpaque(false);
		
		JLabel label1=new JLabel("出发站");
		label1.setFont(new Font("宋体",Font.PLAIN,20));
		label1.setOpaque(false);
		panel.add(label1);
		
		JTextField startTextField=new JTextField(20);
		startTextField.setFont(new Font("宋体",Font.PLAIN,20));
		startTextField.setEditable(false);
		panel.add(startTextField);

		Station[] stationList=Station_Database.getStationList();
		JLabel label2=new JLabel("终点站");
		label2.setFont(new Font("宋体",Font.PLAIN,20));
		label2.setOpaque(false);
		panel.add(label2);
		
		DefaultComboBoxModel<String> endModel=new DefaultComboBoxModel<String>();
		for(Station i:stationList) {
			endModel.addElement(i.stationName);
		}
		JComboBox<String> endBox=new JComboBox<String>(endModel);
		endBox.setFont(new Font("宋体",Font.PLAIN,20));
		panel.add(endBox);
		
		JLabel label3=new JLabel("路线");
		label3.setFont(new Font("宋体",Font.PLAIN,20));
		label3.setOpaque(false);
		panel.add(label3);
		
		JComboBox<String> routeBox=new JComboBox<String>();
		routeBox.setFont(new Font("宋体",Font.PLAIN,20));
		panel.add(routeBox);
		
		JButton queryButton=new JButton("查询");
		queryButton.setFont(new Font("宋体",Font.PLAIN,20));
		panel.add(queryButton);
		queryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String start=startTextField.getText();
						String end=(String) endBox.getSelectedItem();
						if(start==null||end==null) {
							JOptionPane.showMessageDialog(self,"出发地与目的地不可为空","information",JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							Route[] res=Route_Database.queryRouteInfo(start,end);
							DefaultComboBoxModel<String> model=new DefaultComboBoxModel<String>();
							for(Route i:res) {
								model.addElement(i.toString());
							}
							routeBox.setModel(model);
							routeBox.revalidate();
							routeBox.repaint();
						}
					}
					
				});
				t.start();
			}
			
		});
		
		JButton addButton=new JButton("添加");
		addButton.setFont(new Font("宋体",Font.PLAIN,20));
		panel.add(addButton);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String start=startTextField.getText();
						String end=(String) endBox.getSelectedItem();
						String route=(String) routeBox.getSelectedItem();
						if(start==null||end==null||route==null) {
							JOptionPane.showMessageDialog(self,"信息框不可为空","information",JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							int routeNumber=Integer.valueOf(route.split(":")[0]);
							String cargoNumber=Cargo_Database.addCargo(start,end,routeNumber);
							Cargo_Database.addCargoRecord(cargoNumber,start,"", 1);//收件
							JOptionPane.showMessageDialog(self,"物流单号："+cargoNumber,"information",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
				});
				t.start();
			}
			
		});
		self.add(panel);
		self.revalidate();
		self.repaint();
	}

	public static void loadCargo(ContentMessagePanel contentPanel) {
		// TODO Auto-generated method stub
		ContentMessagePanel self=contentPanel;
		self.removeAll();
		JPanel panel=new JPanel(new GridLayout(4,2,5,5));
		panel.setOpaque(false);
		
		JLabel label1=new JLabel("车辆编号：");
		label1.setFont(new Font("宋体",Font.PLAIN,20));
		label1.setOpaque(false);
		panel.add(label1);
		
		DefaultComboBoxModel<String> model=new DefaultComboBoxModel<String>();
		String[] carNumberList=Car_Database.getAllCarNumber();
		for(String i:carNumberList) {
			model.addElement(i);
		}
		JComboBox<String> carNameComboBox=new JComboBox<String>(model);
		carNameComboBox.setFont(new Font("宋体",Font.PLAIN,20));
		panel.add(carNameComboBox);
		
		JLabel label2=new JLabel("货物编号：");
		label2.setFont(new Font("宋体",Font.PLAIN,20));
		panel.add(label2);
		
		JTextField cargoNumberText=new JTextField(20);
		cargoNumberText.setFont(new Font("宋体",Font.PLAIN,20));
		panel.add(cargoNumberText);
		
		panel.add(new JLabel(" 		"));
		
		JButton loadButton=new JButton("装载");
		loadButton.setFont(new Font("宋体",Font.PLAIN,20));
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String carName=(String) carNameComboBox.getSelectedItem();
						String cargoNumber=cargoNumberText.getText();
						Cargo_Database.addCargoRecord(cargoNumber,Client.user.stationName,carName,1);//收件
					}
					
				});
				t.start();
			}
			
		});
		panel.add(loadButton);
		self.add(panel);
		self.revalidate();
		self.repaint();
	}

	public static void unloadCargo(ContentMessagePanel contentPanel) {
		// TODO Auto-generated method stub
		ContentMessagePanel self=contentPanel;
		self.removeAll();
		JPanel panel=new JPanel(new GridLayout(3,2,5,5));
		panel.setOpaque(false);
		
		JLabel label2=new JLabel("货物编号：");
		label2.setFont(new Font("宋体",Font.PLAIN,20));
		panel.add(label2);
		
		JTextField cargoNumberText=new JTextField(20);
		cargoNumberText.setFont(new Font("宋体",Font.PLAIN,20));
		panel.add(cargoNumberText);
		
		panel.add(new JLabel(" 		"));
		
		JButton loadButton=new JButton("卸车");
		loadButton.setFont(new Font("宋体",Font.PLAIN,20));
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String cargoNumber=cargoNumberText.getText();
						Cargo_Database.addCargoRecord(cargoNumber,Client.user.stationName,"",2);//卸车
					}
					
				});
				t.start();
			}
			
		});
		panel.add(loadButton);
		self.add(panel);
		self.revalidate();
		self.repaint();
	}

	public static void delCargo(ContentMessagePanel contentPanel) {
		// TODO Auto-generated method stub
		ContentMessagePanel self=contentPanel;
		self.removeAll();
		JPanel panel=new JPanel(new GridLayout(3,2,5,5));
		panel.setOpaque(false);
		
		JLabel label2=new JLabel("货物编号：");
		label2.setFont(new Font("宋体",Font.PLAIN,20));
		panel.add(label2);
		
		JTextField cargoNumberText=new JTextField(20);
		cargoNumberText.setFont(new Font("宋体",Font.PLAIN,20));
		panel.add(cargoNumberText);
		
		panel.add(new JLabel(" 		"));
		
		JButton loadButton=new JButton("派送");
		loadButton.setFont(new Font("宋体",Font.PLAIN,20));
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String cargoNumber=cargoNumberText.getText();
						Cargo_Database.addCargoRecord(cargoNumber,Client.user.stationName,"",3);//派送
					}
					
				});
				t.start();
			}
			
		});
		panel.add(loadButton);
		self.add(panel);
		self.revalidate();
		self.repaint();
	}

}
