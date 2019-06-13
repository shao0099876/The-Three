package client;

import indexui.IndexUI;
import browser.BrowserDialog;
import entity.User;
import ui.BaseUI;

public class Client {
	public static boolean DEBUG=true;
	public static User user=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BrowserDialog.init();
		
		//改为未登录的界面
		BrowserDialog.frame=new IndexUI();
	}
}
