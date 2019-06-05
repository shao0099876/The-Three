package code;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

public class IO {
	public static String read(DataInputStream input) {
		ArrayList<Byte> array=new ArrayList<Byte>();
		while(true) {
			try {
				array.add(input.readByte());
			} catch (EOFException e) {
				// TODO Auto-generated catch block
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		byte[] byte_tmp=new byte[array.size()];
		for(int i=0;i<array.size();i++) {
			byte_tmp[i]=array.get(i);
		}
		return Code.decode(byte_tmp);
	}
	public static void write(DataOutputStream output,String s) {
		try {
			output.write(Code.encode(s));
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
