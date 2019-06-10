package code;

import java.io.UnsupportedEncodingException;

public class Code {
	//编解码函数
	public static byte[] encode(String s){
		try {
			return s.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String decode(byte[] s) {
		try {
			return new String(s,"GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
