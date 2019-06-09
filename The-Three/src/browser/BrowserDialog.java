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
	private Scene scene;
	private static Browser browser;
	private static JFXPanel fxPanel;
	private static boolean init_complete=false;
	public void ShowGUI() {
		this.add(fxPanel,BorderLayout.CENTER);
		JPanel listPanel=new JPanel();
		JButton button=new JButton("test");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				drawCarPoint(116.404,39.915);
			}
			
		});
		
		listPanel.add(button);
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
				Scene scene=new Scene(browser);
		        fxPanel.setScene(scene);
		        init_complete=true;
			}
			
		});
	}
	public BrowserDialog(JFrame father) {
		super(father,"ä¯ÀÀÆ÷",false);
		this.setLayout(new BorderLayout());
	}
	public void drawCarPoint(double x,double y) {
		StringBuilder sb=new StringBuilder();//DrawPoint
		sb.append("DrawPoint(");
		sb.append(x);
		sb.append(",");
		sb.append(y);
		sb.append(")");
		browser.execute(sb.toString());
	}
}
