package station.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import entity.Station;
import station.db.Station_Database;
import ui.ContentMessagePanel;

public class Station_ContentMessagePanel {
	private static ContentMessagePanel self;
	public static void showStations(ContentMessagePanel p_self) {
		self=p_self;
		self.removeAll();
		String[] name= {"��תվ��","��תվ��ַ","��תվGPS����"};
		Station[] stationArray=Station_Database.getStationList();
		String[][] data=new String[stationArray.length][3];
		for(int i=0;i<stationArray.length;i++) {
			data[i]=stationArray[i].toStringArray();
		}
		JTable table=new JTable(data,name);
		table.setOpaque(false);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setOpaque(false);
		
		self.add(scroll);
		self.revalidate();
		self.repaint();
	}
	public static void addStation(ContentMessagePanel p_self) {
		// TODO Auto-generated method stub
		self=p_self;
		self.removeAll();
		JPanel panel=new JPanel(new GridLayout(3,2,5,5));
		panel.setOpaque(false);
		
		JLabel label1=new JLabel("��תվ��");
		label1.setFont(new Font("����",Font.PLAIN,20));
		label1.setOpaque(false);
		panel.add(label1);
		
		JTextField stationName_text=new JTextField(20);
		stationName_text.setEditable(true);
		stationName_text.setOpaque(false);
		panel.add(stationName_text);
		
		JLabel label2=new JLabel("��תվ��ַ");
		label2.setFont(new Font("����",Font.PLAIN,20));
		label2.setOpaque(false);
		panel.add(label2);
		
		JTextField stationAddr_text=new JTextField(50);
		stationAddr_text.setEditable(true);
		stationAddr_text.setOpaque(false);
		panel.add(stationAddr_text);
		

		panel.add(new JLabel("    "));
		JButton button=new JButton("ȷ��");
		button.setFont(new Font("����",Font.PLAIN,20));
		button.setOpaque(false);
		panel.add(button);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String s=Station_Database.AddStation(stationName_text.getText(), stationAddr_text.getText());
						JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
						stationName_text.setText("");
						stationAddr_text.setText("");
					}
					
				});
				t.start();
			}
			
		});
		self.add(panel);
		self.revalidate();
		self.repaint();
		
	}
	private static String del_stationName;
	private static boolean flag;
	public static void delStation(ContentMessagePanel contentPanel) {
		// TODO Auto-generated method stub
		self=contentPanel;
		self.removeAll();
		flag=true;
		del_stationName="";
		
		JPanel panel=new JPanel(new BorderLayout());
		panel.setOpaque(false);
		
		JPanel spanel=new JPanel(new GridLayout(1,2,0,0));
		spanel.setOpaque(false);
		panel.add(spanel,BorderLayout.NORTH);
		
		JPanel cpanel=new JPanel();
		cpanel.setOpaque(false);
		panel.add(cpanel,BorderLayout.CENTER);
		
		JTextField station_deltext=new JTextField(20);
		station_deltext.setEditable(true);
		station_deltext.setOpaque(false);
		spanel.add(station_deltext);
		
		JButton search=new JButton("����");
		search.setFont(new Font("����",Font.PLAIN,14));
		search.setSize(4,1);
		search.setOpaque(false);
		spanel.add(search);
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String s=station_deltext.getText();
						Station[] array=Station_Database.getMohuStationInfo(s);
						if(array==null||array.length==0){
							cpanel.removeAll();
							cpanel.add(new JLabel("��ϵͳ�����޸ó�����Ϣ!"));
							cpanel.revalidate();
							cpanel.repaint();
						}
						else{
							cpanel.removeAll();
							String[] name= {"��תվ��","��תվ��ַ","��תվGPS����"};
							String[][] data=new String[array.length][4];
							for(int i=0;i<array.length;i++) {
								data[i]=array[i].toStringArray();
							}
							JTable table=new JTable(data,name);
							table.setOpaque(false);
							JScrollPane scroll=new JScrollPane(table);
							scroll.setOpaque(false);
							
							table.addMouseListener(new MouseListener() {

								@Override
								public void mouseClicked(MouseEvent arg0) {
									// TODO Auto-generated method stub
									Thread t=new Thread(new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub
											int row=table.getSelectedRow();
											int col=table.getSelectedColumn();
											if(col==0) {
												del_stationName=table.getValueAt(row, 0).toString();
												flag=false;
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
							JButton del=new JButton("ɾ��");
							del.setFont(new Font("����",Font.PLAIN,14));
							del.setSize(4,1);
							del.setOpaque(false);
							cpanel.add(del,BorderLayout.SOUTH);
							del.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									// TODO Auto-generated method stub
									Thread t=new Thread(new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub
											if(flag) {
												del_stationName=station_deltext.getText();
											}
											String message=Station_Database.delStation(del_stationName);

											JOptionPane.showMessageDialog(self,message,"information",JOptionPane.INFORMATION_MESSAGE);
											station_deltext.setText("");//����ı���������
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
	}
	public static void departCar(ContentMessagePanel contentPanel) {
		// TODO Auto-generated method stub
		self=contentPanel;
		self.removeAll();
		
		JPanel panel=new JPanel(new GridLayout(3,2,5,5));
		panel.setOpaque(false);
		
		JLabel label1=new JLabel("������ţ�");
		label1.setFont(new Font("����",Font.PLAIN,20));
		panel.add(label1);
		
		JTextField carNumberText=new JTextField(20);
		carNumberText.setFont(new Font("����",Font.PLAIN,20));
		panel.add(carNumberText);
		
		JLabel label2=new JLabel("·�߱�ţ�");
		label2.setFont(new Font("����",Font.PLAIN,20));
		panel.add(label2);
		
		JTextField routeNumberText=new JTextField(20);
		routeNumberText.setFont(new Font("����",Font.PLAIN,20));
		panel.add(routeNumberText);
		
		panel.add(new JLabel("    "));
		
		JButton departButton=new JButton("����");
		departButton.setFont(new Font("����",Font.PLAIN,20));
		departButton.setSize(4,1);
		panel.add(departButton);
		departButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String carNumber=carNumberText.getText();
						String routeNumber=routeNumberText.getText();
						Station_Database.addStationRecord(carNumber,routeNumber,1);//����
					}
					
				});
				t.start();
			}
			
		});
		self.add(panel);
		self.revalidate();
		self.repaint();
	}
	public static void receiveCar(ContentMessagePanel contentPanel) {
		// TODO Auto-generated method stub
		self=contentPanel;
		self.removeAll();
		
		JPanel panel=new JPanel(new GridLayout(2,2,5,5));
		panel.setOpaque(false);
		
		JLabel label1=new JLabel("������ţ�");
		label1.setFont(new Font("����",Font.PLAIN,20));
		panel.add(label1);
		
		JTextField carNumberText=new JTextField(20);
		carNumberText.setFont(new Font("����",Font.PLAIN,20));
		panel.add(carNumberText);
		
		panel.add(new JLabel("    "));
		
		JButton departButton=new JButton("��վ");
		departButton.setFont(new Font("����",Font.PLAIN,20));
		departButton.setSize(4,1);
		panel.add(departButton);
		departButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String carNumber=carNumberText.getText();
						Station_Database.addStationRecord(carNumber,"",2);//��վ
					}
					
				});
				t.start();
			}
			
		});
		self.add(panel);
		self.revalidate();
		self.repaint();
	}
	
}
