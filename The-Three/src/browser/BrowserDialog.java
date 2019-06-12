package browser;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
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
	public void ShowGUI(String[] array) {
		this.add(fxPanel,BorderLayout.CENTER);
		JPanel listPanel=new JPanel();
		DefaultListModel<String> model=new DefaultListModel<String>();
		for(String i:array) {
			model.addElement(i);
		}
		JList<String> list=new JList<String>(model);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				browser.execute("FocusOn("+Car_Database.getSpecifiedCarGPS(list.getSelectedValue())+")");
			}
			
		});
		listPanel.add(list);
		this.add(listPanel,BorderLayout.WEST);
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
	}
	//"路线编号","起始站点","终点站","中转站点"
	public void clean() {
		// TODO Auto-generated method stub
		browser.execute("ErasePoints()");
	}
	public void Add_Cars_Point(String[] res) {
		// TODO Auto-generated method stub
		for(String i:res) {
			String[] tmp=i.split(",");
			StringBuilder sb=new StringBuilder();
			sb.append("AddPoint(");
			sb.append(tmp[0]);
			sb.append(",");
			sb.append(tmp[1]);
			sb.append(")");
			browser.execute(sb.toString());
		}
	}
	public void DrawPoints() {
		// TODO Auto-generated method stub
		browser.execute("DrawPoints()");
	}
	public void DrawRoute(String start_GPS,String end_GPS) {
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		sb.append(start_GPS);
		sb.append("]");
		sb.append(",[");
		sb.append(end_GPS);
		sb.append("]");
		DebugInfo.DebugInfo(sb.toString());
		browser.execute("DrawRoute("+sb.toString()+")");
	}
	public void DrawRoute(String start_GPS, String end_GPS, String[] mid) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		sb.append(start_GPS);
		sb.append("]");
		sb.append(",[");
		sb.append(end_GPS);
		sb.append("],[");
		boolean flag=true;
		for(int i=0;i<mid.length;i++) {
			if(!flag) {
				sb.append(",");
			}
			flag=false;
			sb.append("[");
			sb.append(mid[i]);
			sb.append("]");
		}
		sb.append("]");
		browser.execute("DrawRoute_mid("+sb.toString()+")");
	}
}
