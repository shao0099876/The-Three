package client;

import indexui.IndexUI;
import browser.BrowserDialog;
import entity.User;
import ui.BaseUI;

public class Client {
	public static boolean DEBUG=true;
	public static User user=null;
	public static void main(String[] args) {
		BrowserDialog.init();
		BrowserDialog.frame=new IndexUI();
	}
}
