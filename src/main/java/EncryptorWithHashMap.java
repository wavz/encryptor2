import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class EncryptorWithHashMap {

	public static void main(String[] args) {

		initializeEncryption();

		String content = contentFromFile();

		String[] s = content.split("\\+");
		String contentOnly = s[0];
		String s2 = s[1];

		String[] s3 = s2.split("/");
		String fileName = s3[s3.length - 1];

		System.out.println("please enter encryption or decryption for the file:");
		Scanner in = new Scanner(System.in);

		if (in.nextLine().equals("encryption")) {
			System.out.println("Encryption simulation for file: "+fileName);
			String encrypted = "";
			BufferedReader br = null;

			String line = null;
			try {
				for (int i = 0; i < contentOnly.length(); i++) {
					br = new BufferedReader(new FileReader("map.txt"));
					String contentS = Character.toString(contentOnly.charAt(i));
					
					while ((line = br.readLine()) != null) {

						String[] s1 = line.split(",");
						String letter = s1[0];

						if (contentS.equals(letter)) {
							encrypted += s1[1];

						}
					}
				}

			} catch (IOException e) {
				System.out.println("cannot initialize the file");
			}

			System.out.println(encrypted);
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
			System.out.println("Decryption simulation for file: "+fileName);
			String decrypted = "";
			BufferedReader br = null;

			String line = null;
			try {
				for (int i = 0; i < contentOnly.length(); i++) {
					br = new BufferedReader(new FileReader("map.txt"));

					String contentS = Character.toString(contentOnly.charAt(i));
					
					while ((line = br.readLine()) != null) {

						String[] s1 = line.split(",");
						String letter = s1[1];

						if (contentS.equals(letter)) {
							decrypted += s1[0];

						}
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(decrypted);
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("faild to close the file");
			}

		}

	}

	public static void initializeEncryption()  {  //initializing the hashmap with the letters.
		char[] allChar = new char[53];
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		ArrayList<Character> charArr = new ArrayList<Character>();
		int k = 0;
		for (char i = 'a'; i <= 'z'; i++) {
			allChar[k] = i;
			k++;

		}
		for (char i = 'A'; i <= 'Z'; i++) {
			allChar[k] = i;
			k++;

		}
		allChar[k] = ' ';

		for (int i = 0; i < allChar.length; i++) {
			charArr.add(allChar[i]);

		}

		Random rand = new Random();
		for (int i = 0; i < allChar.length; i++) {
			int n = rand.nextInt(charArr.size());

			map.put(allChar[i], charArr.get(n));
			charArr.remove(n);
		}

		File fout = new File("map.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fout);
		} catch (FileNotFoundException e) {
			System.out.println("cannot initalize the file");
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		for (Character key : map.keySet()) {

			String key1 = key.toString();

			String value = map.get(key).toString();
			try{
			bw.write(key1 + "," + value);
			bw.newLine();
			}catch(IOException e){
				System.out.println("cannot write to the file");
			}

		}
		try {
			bw.close();
		} catch (IOException e) {
			System.out.println("faild to close the file");
		}

	}

	public static String contentFromFile() { //reading the text from the file
		System.out.println("please enter the path for the file:");
		Scanner inputPath = new Scanner(System.in);
		String path = inputPath.nextLine();
		String content = "";
		try {
			Scanner in = new Scanner(new FileReader(path));
			content += in.nextLine();

		} catch (FileNotFoundException e) {
			System.out.println("incorrect file path");
			contentFromFile();
		}
		return content + "+" + path;
	}

}
