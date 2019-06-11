package indexui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.BaseUI;
import browser.BrowserDialog;
import client.DebugInfo;

public class IndexUI extends JFrame{
	//�˵���
	public static JMenuBar men;
	//�˵�����
	public static JMenu men1,men2;
	//�˵����
	public static JMenuItem men1_1;//�ͻ���ѯ������Ϣ
	public static JMenuItem men2_1;//��½
	
	public int w,h;//������Ļ��С
	
	public void setmenu(){//���ò˵�
		//��Ӳ˵���
		men = new JMenuBar();//�˵���
		this.setJMenuBar(men);//���Ӳ˵���
		
		//��Ӳ˵�
		 men1=new JMenu("������Ϣ");//�˵�
		 men1.setFont(new Font("����",Font.BOLD,24));//��������
		 men.add(men1);//��Ӳ˵�
		 
		 men1_1=new JMenuItem("�鿴����������Ϣ");
		 men1_1.setFont(new Font("����",Font.BOLD,20));//��������
		 men1.add(men1_1);
		 
		 men2=new JMenu("��½");//�˵�
		 men2.setFont(new Font("����",Font.BOLD,24));//��������
		 men.add(men2);//��Ӳ˵�
		 
		 men2_1=new JMenuItem("��½ϵͳ");
		 men2_1.setFont(new Font("����",Font.BOLD,20));//��������
		 men2.add(men2_1);
	}
	
	public void getScreenSize(){//��ȡ��Ļ��С
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		w=(int) (screenSize.getWidth()*0.8);//��ȡ��Ļ��
		h=(int) (screenSize.getHeight()*0.6);//��ȡ��Ļ��
	}

	public void addaction_viewWuliu(){//���ͻ��鿴������Ϣ�����Ӧ����
		//�鿴������Ϣ
		men1_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						
						//�����
						/*
						 �����ڴ�С�ػ���
						 
						 * */
						
						System.out.println("�ͻ��鿴������Ϣ�鿴������Ϣ");
					}});
				t.start();
			}
		});
	}
	
	public void addaction_Login(){//����½�����Ӧ����
		//��½
		men2_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						//����
						setVisible(false);
						BrowserDialog.frame=new BaseUI();//����Ա����½����
						//�����
					
						/*
						 �����ڴ�С�ػ���
						 * */
//						/*
//						 Ҫ��1��
//						 	��½���ɹ��������Ի��������ʾ���ص�δ��¼����
//						 Ҫ��2��
//						 	�����Ա����½�ɹ��رյ�ǰ���棬��BaseUI�����Ľ���
//						 Ҫ��3��
//						 	�������תվ�ĵ�½�ɹ����رյ�ǰ���棬����������Ľ���
//						 */
						
						System.out.println("��½");
					}});
				t.start();
			}
		});
	}
	
	public void setlocation(){//���ô���λ��
		Dimension dm = this.getToolkit().getScreenSize();
		this.setLocation((int)(dm.getWidth()-600)/2,(int)(dm.getHeight()-650)/2);//��ʾ����Ļ����
	}
	
	public IndexUI() {
		super("�����ۺ���Ϣƽ̨");
		setmenu();//��Ӳ˵�
		getScreenSize();//��ȡ��Ļ��С
		addaction_viewWuliu();//���ͻ��鿴������Ϣ������Ӧ����
		addaction_Login();//����½������Ӧ����
		
		setVisible(true);
		setSize(500,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setlocation();
	}
}