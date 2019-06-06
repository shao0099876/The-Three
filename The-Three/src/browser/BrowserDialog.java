package browser;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import db.Database;
import entity.GPS;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

public class BrowserDialog extends JDialog {
	private Scene scene;
	private void initAndShowGUI() {
		final JFXPanel fxPanel=new JFXPanel();
		this.add(fxPanel,BorderLayout.CENTER);
		
		JPanel listPanel=new JPanel();
		DefaultListModel<String> model=new DefaultListModel<String>();
		String[] data=Database.getCarNumList();
		for(int i=0;i<data.length;i++) {
			model.add(i, data[i]);
		}
		JList<String> list=new JList<String>(model);
		list.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,30));
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				String carNum=(String) list.getSelectedValue();
				GPS gps=Database.getCarLatestGPS(carNum);
				System.out.println(gps.toString());
			}
			
		});
		ArrayList<String> test=new ArrayList<String>();
		listPanel.add(list);
		this.add(listPanel,BorderLayout.WEST);
		this.setSize(2000,1300);
		this.setVisible(true);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				initFX(fxPanel);
			}
			
		});
	}
	private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
		Scene scene=new Scene(new Browser());
        fxPanel.setScene(scene);
    }
	public BrowserDialog(JFrame father) {
		super(father,"ä¯ÀÀÆ÷",false);
		this.setLayout(new BorderLayout());
		initAndShowGUI();
	}
}
