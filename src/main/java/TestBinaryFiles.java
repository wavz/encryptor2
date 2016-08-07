import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TestBinaryFiles {

	public static void main(String[] args) throws Exception {
		DataOutputStream out = openOutputStream("test.bin");
		writeMovie(out);
		out.close();

		File file = new File("test.bin");
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		System.out.println(readMovie(in));
	}

	private static DataOutputStream openOutputStream(String name) throws Exception {
		DataOutputStream out = null;
		File file = new File(name);
		out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		return out;
	}

	private static void writeMovie(DataOutputStream out) throws Exception {
		out.writeUTF("hi, my name is itay");

	}

	private static String readMovie(DataInputStream in) {
		String text = "";

		try {
			text = in.readUTF();
		} catch (IOException e) {
			System.out.println("I/O Error");
			System.exit(0);
		}
		return text;
	}

}
