package code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class IO {
	public static String read(DataInputStream input) {
		BufferedReader reader=new BufferedReader(new InputStreamReader(input));
		try {
			return reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void write(DataOutputStream output,String s) {
		BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(output));
		try {
			writer.write(s+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	public static int readInt(DataInputStream input) {
		String tmp=read(input);
		return Integer.valueOf(tmp);
	}
}
