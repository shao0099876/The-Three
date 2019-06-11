package ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import entity.Car;
import entity.Driver;
import entity.Route;

public class ContentMessagePanel extends JPanel{
	private static ContentMessagePanel self;
	public JTextField car_text1,car_text2,car_text3,car_text4;
	public JComboBox car_bobox;
	public JPanel car_mpanel,car_panel2;
	public JTextField car_deltext;//用于车辆删除
	
	public JTextField route_deltext;//用于路线删除
	public JTextField route_addtext1,route_addtext2,route_addtext3,route_addtext4;//用于增加路线时
	public JTextField route_modifytext1,route_modifytext2,route_modifytext3,route_modifytext4;//用于修改路线时
	public JComboBox route_bobox;
	public JPanel route_mpanel,route_panel2;
	
	public JTextField driver_deltext;//用于驾驶员删除
	public JTextField driver_addtext1,driver_addtext2,driver_addtext3,driver_addtext4,
						driver_addtext5,driver_addtext6,driver_addtext7;//用于增加驾驶员
	public JTextField driver_modifytext1,driver_modifytext2,driver_modifytext3,driver_modifytext4,
						driver_modifytext5,driver_modifytext6,driver_modifytext7;//用于修改驾驶员
	
	public JComboBox driver_bobox;
	public JPanel driver_mpanel,driver_panel2;
	
	public ContentMessagePanel() {
		super();
		self=this;
		this.setOpaque(false);
	}
	
	
	
	

	
	
	
	
	

	
	
	

	
}
