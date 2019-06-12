package indexui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ui.BaseUI;
import user.db.User_Database;
import browser.BrowserDialog;
import client.Client;
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
	public IndexUI self;
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
						BrowserDialog.frame=new BaseUI();//����Ա����½����
						/*JDialog dialog=new JDialog();
						dialog.setTitle("��¼");
						dialog.setLayout(new GridLayout(3,2,5,5));
						dialog.setSize(700,200);
						
						JLabel label1=new JLabel("�û�����");
						label1.setFont(new Font("����",Font.BOLD,20));
						dialog.add(label1);
						
						JTextField usernameTextField=new JTextField(20);
						usernameTextField.setFont(new Font("����",Font.BOLD,20));
						dialog.add(usernameTextField);
						
						JLabel label2=new JLabel("���룺");
						label2.setFont(new Font("����",Font.BOLD,20));
						dialog.add(label2);
						
						JPasswordField passwordTextField=new JPasswordField();
						passwordTextField.setFont(new Font("����",Font.BOLD,20));
						dialog.add(passwordTextField);
						
						p.add(new JLabel(" 		"));
						
						JButton loginButton=new JButton("��¼");
						loginButton.setFont(new Font("����",Font.BOLD,20));
						loginButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								String username=usernameTextField.getText();
								String password=passwordTextField.getText();
								boolean status=User_Database.login(username,password);
								if(status) {
									Client.user=User_Database.getUser(username);
									dialog.dispose();
									self.setVisible(false);
									BrowserDialog.frame=new BaseUI();//����Ա����½����
								}
							}
							
						});
						dialog.add(loginButton);
						Dimension dm = self.getToolkit().getScreenSize();
						dialog.setLocation((int)(dm.getWidth()-600)/2,(int)(dm.getHeight()-650)/2);
						dialog.setVisible(true);
						*/
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
		self=this;
		setmenu();//��Ӳ˵�
		getScreenSize();//��ȡ��Ļ��С
		addaction_viewWuliu();//���ͻ��鿴������Ϣ������Ӧ����
		addaction_Login();//����½������Ӧ����
		
		setVisible(true);
		setSize(500,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setlocation();
	}
}