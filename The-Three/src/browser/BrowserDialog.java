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
	public static ArrayList<Point> pointArray=new ArrayList<Point>();
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
	public void Draw_Cars_on_Route(String[] route,String[] cars) {
		//"路线编号","起始站点","终点站","中转站点"
		ArrayList<String> array=new ArrayList<String>();
		array.add(route[1]);
		String[] tmp=route[3].split("-");
		for(String i:tmp) {
			array.add(i);
		}
		array.add(route[2]);
		for(int i=1;i<array.size();i++) {
			browser.execute("Drawroute(\""+array.get(i-1)+"\",\""+array.get(i)+"\")");
		}
		for(String i:cars) {
			browser.execute("DrawPoint("+i+")");
		}
	}
	public void clean() {
		// TODO Auto-generated method stub
		pointArray.clear();
		browser.execute("ErasePoints()");
	}
	public void Add_Cars_Point(String[] res) {
		// TODO Auto-generated method stub
		for(String i:res) {
			pointArray.add(new Point(i));
		}
	}
	public void DrawPoints() {
		// TODO Auto-generated method stub
		for(Point i:pointArray) {
			browser.execute("DrawPoint("+Double.toString(i.x)+","+Double.toString(i.y)+")");
		}
	}
}
