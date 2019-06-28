package browser;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import car.db.Car_Database;
import client.DebugInfo;
import entity.GPS;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

public class BrowserDialog extends JDialog {
	private static Scene scene;
	private static Browser browser;
	private static JFXPanel fxPanel;
	public static JFrame frame;
	public static BrowserDialog self;
	public static JTextField carNumberText,driverText,subdriverText,carStatusText,fuelText;
	public void ShowGUI(String[] array) {
		this.add(fxPanel,BorderLayout.CENTER);
		JPanel listPanel=new JPanel();
		DefaultListModel<String> model=new DefaultListModel<String>();
		for(String i:array) {
			model.addElement(i);
		}
		JList<String> list=new JList<String>(model);
		list.setFont(new Font("宋体",Font.BOLD,20));
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				BrowserUI.FocusOn(list.getSelectedValue(),self);
			}
			
		});
		listPanel.add(list);
		this.add(listPanel,BorderLayout.WEST);
		JPanel infoPanel=new JPanel(new GridLayout(6,2,5,5));
		
		JLabel label1=new JLabel("车辆号");
		label1.setFont(new Font("宋体",Font.BOLD,20));
		infoPanel.add(label1);
		
		carNumberText=new JTextField(20);
		carNumberText.setFont(new Font("宋体",Font.BOLD,20));
		carNumberText.setEditable(false);
		infoPanel.add(carNumberText);
		
		JLabel label2=new JLabel("驾驶员");
		label2.setFont(new Font("宋体",Font.BOLD,20));
		infoPanel.add(label2);
		
		driverText=new JTextField(20);
		driverText.setFont(new Font("宋体",Font.BOLD,20));
		driverText.setEditable(false);
		infoPanel.add(driverText);
		
		JLabel label3=new JLabel("副驾驶");
		label3.setFont(new Font("宋体",Font.BOLD,20));
		infoPanel.add(label3);
		
		subdriverText=new JTextField(20);
		subdriverText.setFont(new Font("宋体",Font.BOLD,20));
		subdriverText.setEditable(false);
		infoPanel.add(subdriverText);
		
		JLabel label5=new JLabel("车辆状态");
		label5.setFont(new Font("宋体",Font.BOLD,20));
		infoPanel.add(label5);
		//carNumberText,driverText,subdriverText,driverTimeLength,carStatusText
		carStatusText=new JTextField(20);
		carStatusText.setFont(new Font("宋体",Font.BOLD,20));
		carStatusText.setEditable(false);
		infoPanel.add(carStatusText);
		
		JLabel label6=new JLabel("剩余油量");
		label6.setFont(new Font("宋体",Font.BOLD,20));
		infoPanel.add(label6);
		
		fuelText=new JTextField(20);
		fuelText.setFont(new Font("宋体",Font.BOLD,20));
		fuelText.setEditable(false);
		infoPanel.add(fuelText);
		
		this.add(infoPanel,BorderLayout.SOUTH);
		
		this.setSize(2000,1300);
		this.setVisible(true);
	}
	public static void init() {
		fxPanel=new JFXPanel();
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				browser =new Browser();
				scene=new Scene(browser);
		        fxPanel.setScene(scene);
			}
			
		});
	}
	public BrowserDialog() {
		super(frame,"浏览器",false);
		this.setLayout(new BorderLayout());
		self=this;
	}
	public void focuson(String gps) {
		browser.execute("FocusOn("+gps+")");
	}
	public void clean() {
		browser.execute("ErasePoints()");
	}
	public void Add_Cars_Point(String s) {
		StringBuilder sb=new StringBuilder();
		sb.append("AddPoint(");sb.append(s);sb.append(")");
		browser.execute(sb.toString());
	}
	public void DrawPoints() {
		browser.execute("DrawPoints()");
	}
	public void DrawRoute(String start_GPS,String end_GPS) {
		StringBuilder sb=new StringBuilder();
		sb.append("[");sb.append(start_GPS);sb.append("]");sb.append(",[");sb.append(end_GPS);sb.append("]");
		DebugInfo.DebugInfo(sb.toString());
		browser.execute("DrawRoute("+sb.toString()+")");
	}
	public void DrawRoute(String start_GPS, String end_GPS, String[] mid) {
		StringBuilder sb=new StringBuilder();
		sb.append("[");sb.append(start_GPS);sb.append("]");sb.append(",[");sb.append(end_GPS);sb.append("],[");
		boolean flag=true;
		for(int i=0;i<mid.length;i++) {
			if(!flag) {
				sb.append(",");
			}
			flag=false;
			sb.append("[");sb.append(mid[i]);sb.append("]");
		}
		sb.append("]");
		browser.execute("DrawRoute_mid("+sb.toString()+")");
	}
}
