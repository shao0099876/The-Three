package client;

import indexui.IndexUI;
import browser.BrowserDialog;
import ui.BaseUI;

public class Client {
	public static boolean DEBUG=true;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BrowserDialog.init();
		
		//改为未登录的界面
		BrowserDialog.frame=new IndexUI();
	}
}
