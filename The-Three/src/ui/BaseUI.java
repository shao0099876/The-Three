package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class BaseUI extends JFrame{
	public static NaviPanel naviPanel;//导航栏
	public static ContentMessagePanel contentPanel;//显示内容栏
	private JPanel mainPanel;
	private JLayeredPane layer;
	private ImageIcon image;
	private static String backgroundImagePath="/background.jpg";
	
	private void setBackgroundImage() {
		image=new ImageIcon(getClass().getResource(backgroundImagePath));
		JPanel background=new JPanel();
		background.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
		JLabel jl=new JLabel(image);
		background.add(jl);
		layer.add(background,JLayeredPane.DEFAULT_LAYER);//底层图片
		return;
	}
	
	private void setLocation() {
		Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
	    Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
	    int screenWidth = screenSize.width;                     //获取屏幕的宽
	    int screenHeight = screenSize.height;                   //获取屏幕的高
	    this.setLocation(screenWidth/2-image.getIconWidth()/2, screenHeight/2-image.getIconHeight()/2);//设置窗口居中显示
	}
	
	public BaseUI() {
		super("物流综合信息平台");
		layer=new JLayeredPane();
		setBackgroundImage();
		int width=image.getIconWidth();
		int height=image.getIconHeight();
		naviPanel=new NaviPanel(width,(int)(height*0.3));
		contentPanel=new ContentMessagePanel();
		naviPanel.setBounds(0,0,width,(int)(height*0.3));
		contentPanel.setBounds(0, (int)(height*0.3), width, (int)(height*0.7));
		
		layer.add(naviPanel,JLayeredPane.MODAL_LAYER);
		layer.add(contentPanel,JLayeredPane.MODAL_LAYER);
		
		setLayeredPane(layer);
		setVisible(true);
		setSize(width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation();
	}
}
