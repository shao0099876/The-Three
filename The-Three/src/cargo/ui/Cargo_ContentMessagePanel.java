package cargo.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cargo.db.Cargo_Database;
import entity.Cargo;
import ui.ContentMessagePanel;

public class Cargo_ContentMessagePanel {
	public static void showCargoList(ContentMessagePanel contentPanel) {
		// TODO Auto-generated method stub
		ContentMessagePanel self=contentPanel;
		self.removeAll();
		String[] name= {"��������","������","Ŀ�ĵ�","·�߱��"};
		Cargo[] array=Cargo_Database.getCargoList();
		if(array==null||array.length==0) {
			JOptionPane.showMessageDialog(self,"��ǰϵͳ�޿�����Ϣ��","information",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String[][] data=new String[array.length][4];
		for(int i=0;i<array.length;i++) {
			data[i]=array[i].toStringArray();
		}
		JTable table=new JTable(data,name);
		table.setOpaque(false);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setOpaque(false);
		
	}

}
