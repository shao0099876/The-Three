package browser;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

public class BrowserDialog extends JDialog {
	private Scene scene;
	private void initAndShowGUI() {
		final JFXPanel fxPanel=new JFXPanel();
		this.add(fxPanel,BorderLayout.CENTER);
		
		JPanel listPanel=new JPanel();
		DefaultListModel model=new DefaultListModel();
		
		
		
		JList list=new JList(model);
		list.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,30));
		list.setSize(200,200);
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
