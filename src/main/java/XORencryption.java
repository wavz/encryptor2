import java.util.Random;
import java.util.Scanner;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XORencryption  implements Encryption {
	


	public XORencryption() {
	};

	public String encryption(String text,String key) {

		char key1 = key.charAt(0);
		String encrypted = "";
		int xor;

		for (int i = 0; i < text.length(); i++) {

			xor = text.charAt(i) ^ key1;

			encrypted += (char) xor;

		}

		return encrypted;

	}

	public String decryption(String text,String key) {
		char key1 = key.charAt(0);
		String decrypted = "";
		int xor;

		for (int i = 0; i < text.length(); i++) {

			xor = text.charAt(i) ^ key1;

			decrypted += (char) xor;

		}

		return decrypted;

	}
	
	public char randomKey(){
		Random r=new Random();
		char c=(char)(r.nextInt(122)+97);
		return c;
	}
	
	public String checkKey(){
		System.out.println("please enter a ket between a to z:");
		Scanner in = new Scanner(System.in);
		String key=in.nextLine();
		if(key.charAt(0)<'a'||key.charAt(0)>'z'){
			System.out.println("invalid key");
			checkKey();
		}
		return key;
	}
	
	

	public String generateKey() {
		String xorKey;
		char[] allChar = new char[26];
		int k=0;
		for (char i = 'a'; i <= 'z'; i++) {
			allChar[k] = i;
			k++;

		}
		Random rand = new Random();
		int  n = rand.nextInt(25) + 0;
		xorKey=Character.toString(allChar[n]);
		
		
		return xorKey;
	}

	public int checkKey(String key) {
		for (int i = 'a'; i <'z'; i++) {
			if(key.charAt(0)==i){
				return 1;
			}
			
		}
		return 0;
	}

	

}
