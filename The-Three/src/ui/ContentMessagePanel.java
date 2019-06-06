package ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import db.Database;
import entity.Car;
import entity.Driver;
import entity.Route;

public class ContentMessagePanel extends JPanel{
	private static Car[] array;
	private static ContentMessagePanel self;
	private JTextField car_text1,car_text2,car_text3,car_text4;
	private JComboBox car_bobox;
	private ArrayList<String> car_num=new ArrayList<String>();//���ڱ��泵�����ƺ�ģ����ѯ�Ľ��
	private JPanel car_mpanel,car_panel2;
	private DefaultComboBoxModel car_model;
	private String car_lastmessage="";
	public ContentMessagePanel() {
		super();
		self=this;
		this.setOpaque(false);
	}
	
	public static void setCarDetailInfo(int n){//�鿴������ϸ��Ϣ
		self.removeAll();
		
		//��ʾ������Ϣ
		String[] name1= {"���ƺ�","��ʻԱ1","��ʻԱ2","·��"};
		Car carDetail=new Car();
		carDetail=array[n];
		String[][] data1=new String[1][4];
		data1[0]=carDetail.toStringArray();
		JTable table1=new JTable(data1,name1);
		table1.setOpaque(false);
		JScrollPane scroll1=new JScrollPane(table1);
		scroll1.setOpaque(false);
		
		//��ʾ��ʻԱ����ϸ��Ϣ
		String[] name2={"��ʻԱ���","��ʻ֤","��ʻԱ����","��ʻԱ����","��ʻ�곤","��ʻԱ��ϵ��ʽ","��ʻԱĿǰ״̬"};
		Driver driInfo1=new Driver();
		driInfo1=Database.getDriverInfo(carDetail.people1Number);//��ѯ��ʻԱ1����Ϣ
		
		Driver driInfo2=new Driver();
		driInfo2=Database.getDriverInfo(carDetail.people2Number);//��ѯ��ʻԱ2����Ϣ
		
		String[][] data2=new String[2][7];
		data2[0]=driInfo1.toStringArray();
		data2[1]=driInfo2.toStringArray();
		
		JTable table2=new JTable(data2,name2);
		table2.setOpaque(false);
		JScrollPane scroll2=new JScrollPane(table2);
		scroll2.setOpaque(false);
		
		//��ʾ·�ߵ���ϸ��Ϣ
		String[] name3={"·�߱��","��ʼվ��","�յ�վ"};
		Route routeInfo=new Route();
		routeInfo=Database.getRouteInfo(carDetail.routeNumber);//��ѯ·�ߵ���ϸ��Ϣ
		
		String[][] data3=new String[1][4];
		data3[0]=routeInfo.toStringArray();
		
		JTable table3=new JTable(data3,name3);
		table3.setOpaque(false);
		JScrollPane scroll3=new JScrollPane(table3);
		scroll3.setOpaque(false);
		
		JPanel panel=new JPanel(new GridLayout(1,3,0,0));//һ������ ��ʾ�б�
		panel.setOpaque(false);
		
		panel.add(scroll1);
		panel.add(scroll2);
		panel.add(scroll3);
		
		self.add(panel);
		self.revalidate();
		self.repaint();
		return;
	}
	
	public void setCarInfo() {//���ӹ����Ҫ��Ϣ
		this.removeAll();//�������������ȫ�����
		
		String[] name= {"���ƺ�","��ʻԱ1","��ʻԱ2","·��"};
		array=Database.getCarInfo();
		if(array==null||array.length==0) {
			JLabel label=new JLabel("��ǰϵͳ�޿��ó�����Ϣ��");
			this.add(label);
			this.revalidate();
			this.repaint();
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
				int row = table.getSelectedRow();
		        int col = table.getSelectedColumn();
		        String value = (String) table.getValueAt(row, col);
		        
		        if(col==0){
		        	setCarDetailInfo(row);
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
		
		this.add(scroll);
		this.revalidate();
		this.repaint();
		return;
	}

	public void carInfo_Add_Del(){//������Ϣ�������޸�ɾ������
		self.removeAll();//������������������

		car_mpanel=new JPanel(new GridLayout(1,2,0,0));//һ������
		
		//��һ��
		JPanel panel=new JPanel(new GridLayout(6,3,5,5));//��������
		panel.setOpaque(false);
		
		//��һ�� �������
		JLabel label1=new JLabel("�������");
		label1.setFont(new Font("����",Font.PLAIN,20));
		label1.setOpaque(false);
		panel.add(label1);
		
		car_text1=new JTextField(20);
		car_text1.setEditable(true);//����Ϊ�ɱ༭
		car_text1.setOpaque(false);
		panel.add(car_text1);
		panel.add(new JLabel("    "));
		
		//���ı���������Ӧ����
		Document document = car_text1.getDocument();
		document.addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				test_change();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				test_change();
			}
			
		});
		
		//�ڶ��� ��ʻԱ1��
		JLabel label2=new JLabel("��ʻԱһ�ű��");
		label2.setFont(new Font("����",Font.PLAIN,20));
		label2.setOpaque(false);
		panel.add(label2);
		
		car_text2=new JTextField();
		car_text2.setEditable(true);//����Ϊ�ɱ༭
		car_text2.setOpaque(false);
		panel.add(car_text2);
		panel.add(new JLabel("    "));
		
		//������ ��ʻԱ2��
		JLabel label3=new JLabel("��ʻԱ���ű��");
		label3.setFont(new Font("����",Font.PLAIN,20));
		label3.setOpaque(false);
		panel.add(label3);
		
		car_text3=new JTextField();
		car_text3.setEditable(true);//����Ϊ�ɱ༭
		car_text3.setOpaque(false);
		panel.add(car_text3);
		panel.add(new JLabel("    "));
		
		//������ ·�߱��
		JLabel label4=new JLabel("·�߱��");
		label4.setFont(new Font("����",Font.PLAIN,20));
		label4.setOpaque(false);
		panel.add(label4);
		
		car_text4=new JTextField();
		car_text4.setEditable(true);//����Ϊ�ɱ༭
		car_text4.setOpaque(false);
		panel.add(car_text4);
		panel.add(new JLabel("    "));
		
		//������ ���ӿ���
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		panel.add(new JLabel("    "));
		
		//������ ��Ӱ�ť
		JButton[] button=new JButton[3];
		
		button[0]=new JButton("���");
		button[0].setFont(new Font("����",Font.PLAIN,14));
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		panel.add(button[0]);
		
		button[0].addActionListener(new ActionListener(){//���Ӱ�ť�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				StringBuilder carinfo=new StringBuilder();//�����ı����ж�Ӧ����Ϣ
				carinfo.append(car_text1.getText());
				carinfo.append("#");
				carinfo.append(car_text2.getText());
				carinfo.append("#");
				carinfo.append(car_text3.getText());
				carinfo.append("#");
				carinfo.append(car_text4.getText());
				
				String newcarinfo=carinfo.toString();
				System.out.println(newcarinfo);
				
				String s=carInfo_addCarInfo(newcarinfo);//������Ϣ����ڽ�������
				JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
				
				car_text1.setText("");
				car_text2.setText("");
				car_text3.setText("");
				car_text4.setText("");
			}
			
		});
		
		button[1]=new JButton("�޸�");
		button[1].setFont(new Font("����",Font.PLAIN,14));
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		panel.add(button[1]);
		
		button[1].addActionListener(new ActionListener(){//�޸İ�ť�����Ӧ����

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StringBuilder carinfo=new StringBuilder();//�����ı����ж�Ӧ����Ϣ
				carinfo.append(car_text1.getText());
				carinfo.append("#");
				carinfo.append(car_text2.getText());
				carinfo.append("#");
				carinfo.append(car_text3.getText());
				carinfo.append("#");
				carinfo.append(car_text4.getText());
				
				String newcarinfo=carinfo.toString();
				System.out.println(newcarinfo);
				String s=carInfo_ModifyCarInfo(newcarinfo);//������Ϣ����ڽ�������
				JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
				
				car_text1.setText("");
				car_text2.setText("");
				car_text3.setText("");
				car_text4.setText("");
			}
			
		});
		
		button[2]=new JButton("ɾ��");
		button[2].setFont(new Font("����",Font.PLAIN,14));
		button[2].setSize(4, 1);
		button[2].setOpaque(false);
		panel.add(button[2]);
		
		button[2].addActionListener(new ActionListener(){//ɾ����ť�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StringBuilder carinfo=new StringBuilder();//�����ı����ж�Ӧ����Ϣ
				carinfo.append(car_text1.getText());
				carinfo.append("#");
				carinfo.append(car_text2.getText());
				carinfo.append("#");
				carinfo.append(car_text3.getText());
				carinfo.append("#");
				carinfo.append(car_text4.getText());
				
				String newcarinfo=carinfo.toString();
				System.out.println(newcarinfo);
				String s=carInfo_deletCarInfo(newcarinfo);//������Ϣ����ڽ�������
				JOptionPane.showMessageDialog(self,s,"information",JOptionPane.INFORMATION_MESSAGE);
			
				car_text1.setText("");
				car_text2.setText("");
				car_text3.setText("");
				car_text4.setText("");
			}
			
		});
		
		car_mpanel.add(panel);
		
		//�ڶ���
		car_panel2=new JPanel();
		
		car_model=new DefaultComboBoxModel();
		
		setCarSecondPanel(car_num);
		
		car_bobox=new JComboBox(car_model);
		car_bobox.setBorder(BorderFactory.createTitledBorder("���ƺ�ģ����ѯ���"));
		
		//��Ӽ�������
		car_bobox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED){
					Thread t=new Thread(new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							car_text1.setText(String.valueOf(car_model.getSelectedItem()));
						}
						
					});
					t.start();
				}
			}
		});
		
		car_panel2.add(car_bobox);
		car_mpanel.add(car_panel2);
		
		self.add(car_mpanel);
		self.revalidate();
		self.repaint();
		return;
		
	}
	
	public void test_change(){
		String s=car_text1.getText();//��ȡ��ǰ�ı����е�����
		System.out.println(s);
		if(!(s.equals(car_lastmessage))){//�ж��ı����Ƿ�ı�
			String[] temp_car=Database.getCarNumber(s);//��������ģ����ѯ�õ��ĳ��ƺ���Ϣ
			System.out.println("ģ����ѯ����");
			
			car_num.clear();
			for(int i=0;i<temp_car.length;i++){
				car_num.add(temp_car[i]);
			}	
			setCarSecondPanel(car_num);//��ģ����ѯ����ı��ˣ�car����������ϢҲ����Ҫ�ı�
		}
		car_lastmessage=s;//��ֵ���ֵ
	}
	
	public String carInfo_addCarInfo(String newcarinfo){//���ӻ����޸ĳ�����Ϣ
		String s=Database.AddCarInfo(newcarinfo);
		return s;
	}

	public String carInfo_deletCarInfo(String newcarinfo){//ɾ��������Ϣ
		String s=Database.DeleteCarInfo(newcarinfo);
		return s;
	}
	
	public String carInfo_ModifyCarInfo(String newcarinfo){//�޸ĳ�����Ϣ
		String s=Database.ModifyCarInfo(newcarinfo);
		return s;
	}

	public void setCarSecondPanel(ArrayList<String> num){//��̬��ʾcarpanel����ĵڶ���
		System.out.println("���ڶ�̬��ʾ���");
		car_model.removeAllElements();//�����
		car_num=num;
		int len=car_num.size();
		System.out.println(len);
		
		for(int i=0;i<len;i++){
			System.out.println(car_num.get(i));
			car_model.addElement(car_num.get(i));
		}
	}
}
