package indexui;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.DebugInfo;

public class IndexUI extends JFrame{
	private JLayeredPane layer;
	private ImageIcon image;
	private static String backgroundImagePath="/background.jpg";
	
	//���ý�����ʾ
	private JPanel bpanel;
	private JPanel cpanel1;
	private JPanel cpanel2;
	private JButton[] button=new JButton[2];
	private JTextField text;//�������ģ�������ı���
	private JButton b1;//������ť
	private JButton b2;//���Ƹö����ĵ�ͼ
	
	
	private void setBackgroundImage() {
		image=new ImageIcon(getClass().getResource(backgroundImagePath));
		JPanel background=new JPanel();
		background.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
		JLabel jl=new JLabel(image);
		background.add(jl);
		layer.add(background,JLayeredPane.DEFAULT_LAYER);//�ײ�ͼƬ
		return;
	}
	
	private void setLocation() {
		Toolkit kit = Toolkit.getDefaultToolkit();              //���幤�߰�
	    Dimension screenSize = kit.getScreenSize();             //��ȡ��Ļ�ĳߴ�
	    int screenWidth = screenSize.width;                     //��ȡ��Ļ�Ŀ�
	    int screenHeight = screenSize.height;                   //��ȡ��Ļ�ĸ�
	    this.setLocation(screenWidth/2-image.getIconWidth()/2, screenHeight/2-image.getIconHeight()/2);//���ô��ھ�����ʾ
	}
	
	private void setButton(int x,int y,int w,int h){//��Ӱ�ť
		bpanel=new JPanel();//��ʾ��ť�����
		bpanel.setOpaque(false);
		bpanel.setBounds(0, 0, x, y);
		
		//��ť1
		button[0]=new JButton("��ѯ������Ϣ");
		button[0].setFont(new Font("����",Font.PLAIN,14));
		button[0].setSize(4, 1);
		button[0].setOpaque(false);
		
		button[0].addActionListener(new ActionListener(){//�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("�鿴������Ϣ��ť����");
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						//����ģ������������ŵ�������
						setText(w,h);
					}
				});
				t.start();
			}
			
		});		
		bpanel.add(button[0]);
		
		//��ť2
		button[1]=new JButton("��½");
		button[1].setFont(new Font("����",Font.PLAIN,14));
		button[1].setSize(4, 1);
		button[1].setOpaque(false);
		
		button[1].addActionListener(new ActionListener(){//�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("��½��ť����");
				
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub						
						
						//�����
						
						
						//��ӵ�½����Ӧ����
						/*
						 Ҫ��1��
						 	��������panel������������,��ʾ����½����
						 Ҫ��2��
						 	��½���ɹ��������Ի��������ʾ���ص�δ��¼����
						 Ҫ��3��
						 	�����Ա����½�ɹ����ص�BaseUI�����Ľ���
						 Ҫ��4��
						 	�������תվ�ĵ�½�ɹ������´���һ������
						 */
						
					}
					
				});
				t.start();
			}
		});		
		bpanel.add(button[1]);

		layer.add(bpanel,JLayeredPane.MODAL_LAYER);
		System.out.println("��Ӱ��ݽ���");
	}
	
	public void setText(int x,int y){//����������Ϣ��������ģ����ѯ�ı��밴ť
		cpanel1=new JPanel();//��ʾ��ť�����
		cpanel1.setOpaque(false);
		cpanel1.setBounds(0, (int)(y*0.1), x, (int)(y*0.1));
		
		text=new JTextField(20);
		text.setOpaque(false);
		cpanel1.add(text);
		
		b1=new JButton("����");
		b1.setFont(new Font("����",Font.PLAIN,14));
		b1.setSize(4, 1);
		b1.setOpaque(false);
		
		b1.addActionListener(new ActionListener(){//�����Ӧ����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DebugInfo.DebugInfo("������ť����");
				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {
						
						//�����
						
						//���ı����е����ݶ���������������������ŵ�ģ������
						
						/*
						 cpanel2�����ﴴ����������ʾģ�������Ľ����cpanelλ������
						 cpanel.setbounds(0,(int)(y*0.2),x,(int)(y*0.8))
						 
						  1��cpanel2�а���һ��jcombobox��������ʾ�������
							����У���һ��������ŵ���Ϣ��Ϊһ����ʾ,һ����Ӧ�ð�����
						 	������ţ���ʼ�ص㣬�յ㣬;���أ�������״̬
						 	
						 2��cpanel�а���һ����ť������b2
						 ���°�ťb2�󣬻��Ƹö����ڵ�ͼ�е�·�ߣ�����Ҫ����תվ����Ϣ��������
						 
						 ע�⣺
						 	��ʱҪ�ж�jcombobox�����Ƿ����ѡ�У������ѡ�о��ڵ�ͼ�л��Ʊ�ѡ�е���
						 	���û�б�ѡ�еģ��ͻ����ı����е�
						 	
						 	���Բο�·��ɾ����һ���д��
						 
						 **/
						
						
					}
					
				});
				t.start();
			}
			
		});		
		cpanel1.add(b1);
		layer.add(cpanel1,JLayeredPane.MODAL_LAYER);
	}
	
	public IndexUI() {
		super("�����ۺ���Ϣƽ̨");
		layer=new JLayeredPane();
		setBackgroundImage();
		
		int width=image.getIconWidth();
		int height=image.getIconHeight();
		
		setButton(width,(int)(height*0.1),width,height);//��Ӱ�ť
		
		
		setLayeredPane(layer);
		setVisible(true);
		setSize(width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation();
	}
}
