package ui;

import indexui.IndexUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import browser.BrowserDialog;
import route.ui.Route_ContentMessagePanel;
import station.ui.Station_ContentMessagePanel;
import car.ui.Car_ContentMessagePanel;
import driver.ui.Driver_ContentMessagePanel;

public class BaseUI extends JFrame{
	//��ʾ������
	public static ContentMessagePanel contentPanel;
	
	//�˵���
	public static JMenuBar menubar;
	//�˵�����
	public static JMenu m1,m2,m3,m4,m5;
	//�˵����
	public static JMenuItem m1_1,m1_2,m1_3,m1_4,m1_5;//�����˵���
	public static JMenuItem m2_1,m2_2,m2_3,m2_4,m2_5,m2_6,m2_7;//·�߲˵���
	public static JMenuItem m3_1,m3_2,m3_3,m3_4;//��ʻԱ�˵���
	public static JMenuItem m4_1,m4_2,m4_3,m4_4;//����˵���
	public static JMenuItem m5_1;//�˳�
	
	//�����ȡ��Ļ���ȱ���
	public static int width,height;
	
	public BaseUI() {
		super("�����ۺ���Ϣƽ̨");
		
		setMenu();//���ò˵�
		getscreenSize();//��ȡ��Ļ��С
		
		//�����Ӧ����
		setaction_Car();//����
		setaction_Route();//·��
		setaction_Driver();//��ʻԱ
		setaction_Wuliu();//����
		setaction_Logout();//�˳�
		
		contentPanel=new ContentMessagePanel();
		this.add(contentPanel);
	
		setVisible(true);
		setSize(width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setlocation();//����λ��
	}

	public void setMenu(){//��Ӳ˵�����Ӧ�Ĳ˵���
		//��Ӳ˵���
		menubar = new JMenuBar();//�˵���
		this.setJMenuBar(menubar);//���Ӳ˵���
		 
		//��Ӳ˵�
		 m1=new JMenu("��������");//�˵�
		 m1.setFont(new Font("����",Font.BOLD,24));//��������
		 menubar.add(m1);//��Ӳ˵�
		 
		 m1_1=new JMenuItem("�鿴������Ϣ");
		 m1_1.setFont(new Font("����",Font.BOLD,20));//��������
		 m1.add(m1_1);
		 
		 m1_2=new JMenuItem("�޸ĳ�����Ϣ");
		 m1_2.setFont(new Font("����",Font.BOLD,20));//��������
		 m1.add(m1_2);
		 
		 m1_3=new JMenuItem("���ӳ�����Ϣ");
		 m1_3.setFont(new Font("����",Font.BOLD,20));//��������
		 m1.add(m1_3);
		 
		 m1_4=new JMenuItem("ɾ��������Ϣ");
		 m1_4.setFont(new Font("����",Font.BOLD,20));//��������
		 m1.add(m1_4);
		 
		 m1_5=new JMenuItem("����GPS");
		 m1_5.setFont(new Font("����",Font.BOLD,20));//��������
		 m1.add(m1_5);
		 
		 
		 m2=new JMenu("·�߹���");//�˵�
		 m2.setFont(new Font("����",Font.BOLD,24));//��������
		 menubar.add(m2);//��Ӳ˵�
		 
		 m2_1=new JMenuItem("�鿴·����Ϣ");
		 m2_1.setFont(new Font("����",Font.BOLD,20));//��������
		 m2.add(m2_1);
		 
		 m2_2=new JMenuItem("�޸�·����Ϣ");
		 m2_2.setFont(new Font("����",Font.BOLD,20));//��������
		 m2.add(m2_2);
		 
		 m2_3=new JMenuItem("����·����Ϣ");
		 m2_3.setFont(new Font("����",Font.BOLD,20));//��������
		 m2.add(m2_3);
		 
		 m2_4=new JMenuItem("ɾ��·����Ϣ");
		 m2_4.setFont(new Font("����",Font.BOLD,20));//��������
		 m2.add(m2_4);
		 
		 m2_5=new JMenuItem("�鿴վ���б�");
		 m2_5.setFont(new Font("����",Font.BOLD,20));//��������
		 m2.add(m2_5);
		 
		 m2_6=new JMenuItem("����վ��");
		 m2_6.setFont(new Font("����",Font.BOLD,20));//��������
		 m2.add(m2_6);
		 
		 m2_7=new JMenuItem("ɾ��վ��");
		 m2_7.setFont(new Font("����",Font.BOLD,20));//��������
		 m2.add(m2_7);
		 
		 
		 m3=new JMenu("��ʻԱ����");//�˵�
		 m3.setFont(new Font("����",Font.BOLD,24));//��������
		 menubar.add(m3);//��Ӳ˵�
		 
		 m3_1=new JMenuItem("�鿴��ʻԱ��Ϣ");
		 m3_1.setFont(new Font("����",Font.BOLD,20));//��������
		 m3.add(m3_1);
		 
		 m3_2=new JMenuItem("�޸ļ�ʻԱ��Ϣ");
		 m3_2.setFont(new Font("����",Font.BOLD,20));//��������
		 m3.add(m3_2);
		 
		 m3_3=new JMenuItem("���Ӽ�ʻԱ��Ϣ");
		 m3_3.setFont(new Font("����",Font.BOLD,20));//��������
		 m3.add(m3_3);
		 
		 m3_4=new JMenuItem("ɾ����ʻԱ��Ϣ");
		 m3_4.setFont(new Font("����",Font.BOLD,20));//��������
		 m3.add(m3_4);
		 
		 m4=new JMenu("��������");//�˵�
		 m4.setFont(new Font("����",Font.BOLD,24));//��������
		 menubar.add(m4);//��Ӳ˵�
		 
		 m4_1=new JMenuItem("��ѯ������Ϣ");
		 m4_1.setFont(new Font("����",Font.BOLD,20));//��������
		 m4.add(m4_1);
		 
		 m4_2=new JMenuItem("�޸Ļ�����Ϣ");
		 m4_2.setFont(new Font("����",Font.BOLD,20));//��������
		 m4.add(m4_2);
		 
		 m4_3=new JMenuItem("���ӻ�����Ϣ");
		 m4_3.setFont(new Font("����",Font.BOLD,20));//��������
		 m4.add(m4_3);
		 
		 m4_4=new JMenuItem("ɾ��������Ϣ");
		 m4_4.setFont(new Font("����",Font.BOLD,20));//��������
		 m4.add(m4_4);
		 
		 m5=new JMenu("�˳�");//�˵�
		 m5.setFont(new Font("����",Font.BOLD,24));//��������
		 menubar.add(m5);//��Ӳ˵�
		 
		 m5_1=new JMenuItem("�˳���¼");
		 m5_1.setFont(new Font("����",Font.BOLD,20));//��������
		 m5.add(m5_1);
		 
		 
	}

	public void getscreenSize(){//��ȡ��Ļ��С
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width=(int) (screenSize.getWidth()*0.8);//��ȡ��Ļ��
		height=(int) (screenSize.getHeight()*0.6);//��ȡ��Ļ��
	}

	public static void setaction_Car(){//���������������Ӧ����
		//�鿴������Ϣ
		m1_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("�鿴������Ϣ");
						Car_ContentMessagePanel.setCarInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
			
		});
		
		//�޸ĳ�����Ϣ
		m1_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("�޸ĳ�����Ϣ");
						Car_ContentMessagePanel.modfiycarInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
			
		});
		
		//���ӳ�����Ϣ
		m1_3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("���ӳ�����Ϣ");
						Car_ContentMessagePanel.addcarInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
			
		});
		
		//ɾ��������Ϣ
		m1_4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("ɾ��������Ϣ");
						Car_ContentMessagePanel.delcarInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
			
		});
		
		//����GPS
		m1_5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("����GPS");
						
						//�����
						/*
						 �Գ���Ϊ���壬��ӻ���·�ߺ���
						 * 
						 * 
						 * */
						
						
					}});
				t.start();
			}
			
		});
	}

	public static void setaction_Route(){//��·�߹��������Ӧ����
		//�鿴·��2-1
		m2_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("�鿴·����Ϣ");
						Route_ContentMessagePanel.setRouteInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//�޸�·��2-2
		m2_2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("�޸�·����Ϣ");
						Route_ContentMessagePanel.modifyRouteInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//����·��2-3
		m2_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("����·����Ϣ");
						Route_ContentMessagePanel.addRouteInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//ɾ��·��2-4
		m2_4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("ɾ��·����Ϣ");
						Route_ContentMessagePanel.delRouteInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		m2_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Station_ContentMessagePanel.showStations(BaseUI.contentPanel);
					}
					
				});
				t.start();
			}
			
		});
		m2_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Station_ContentMessagePanel.addStation(BaseUI.contentPanel);
					}
					
				});
				t.start();
			}
			
		});
		m2_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Station_ContentMessagePanel.delStation(BaseUI.contentPanel);
					}
					
				});
				t.start();
			}
			
		});
	}
	
	public static void setaction_Driver(){//����ʻԱ������Ӧ����
		//�鿴��ʻԱ��Ϣ3-1
		m3_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("�鿴��ʻԱ��Ϣ");
						Driver_ContentMessagePanel.setDriverInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//�޸ļ�ʻԱ��Ϣ3-2
		m3_2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("�޸ļ�ʻԱ��Ϣ");
						Driver_ContentMessagePanel.modifyDriverInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//���Ӽ�ʻԱ��Ϣ3-3
		m3_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("���Ӽ�ʻԱ��Ϣ");
						Driver_ContentMessagePanel.addDriverInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
		
		//ɾ����ʻԱ��Ϣ3-4
		m3_4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("ɾ����ʻԱ��Ϣ");
						Driver_ContentMessagePanel.delDriverInfo(BaseUI.contentPanel);
					}});
				t.start();
			}
		});
	}
	
	public static void setaction_Wuliu(){//����������������Ӧ����
		//��ѯ������Ϣ4-1
		m4_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("�鿴������Ϣ");
						
						/*
						 *�����*/
						
					}});
				t.start();
			}
		});
		
		//�޸Ļ�����Ϣ4-2
		m4_2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("�޸�������Ϣ");
						/*
						 *�����*/
					}});
				t.start();
			}
		});
		
		//���ӻ�����Ϣ4-3
		m4_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("����������Ϣ");
						/*
						 *�����*/
					}});
				t.start();
			}
		});
		
		//ɾ��������Ϣ4-4
		m4_4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("ɾ��������Ϣ");
						/*
						 *�����*/
					}});
				t.start();
			}
		});
	}
	
	public void setaction_Logout(){//���˳������Ӧ����
		m5_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("�˳�");
						
						setVisible(false);
						BrowserDialog.frame=new IndexUI();//�ص�δ��¼����
						
					}});
				t.start();
			}
		});
	}
	
	public void setlocation(){//���ô���λ��
		Dimension dm = this.getToolkit().getScreenSize();
		this.setLocation((int)(dm.getWidth()-1500)/2,(int)(dm.getHeight()-650)/2);//��ʾ����Ļ����
	}
}
