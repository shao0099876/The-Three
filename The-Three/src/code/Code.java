package code;

import java.io.UnsupportedEncodingException;

public class Code {
	//±à½âÂëº¯Êý
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
