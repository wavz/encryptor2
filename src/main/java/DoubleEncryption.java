import java.util.Scanner;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DoubleEncryption  implements Encryption{
	
	


	
	
	
	
	public DoubleEncryption(){
		
		
		
		
	}

	public String encryption(String text,String key){
		
		CeasarCipher c=new CeasarCipher();
		XORencryption xor=new XORencryption();
		String s[] = key.split(",");
		String middle=c.encryption(text,s[0]);
		
		String finalEncrypted=xor.encryption(middle,s[1]);
		
		return finalEncrypted;
		
		
	}

	public String decryption(String text,String key){
		CeasarCipher c=new CeasarCipher();
		XORencryption xor=new XORencryption();
		String s[] = key.split(",");
		String middle = xor.decryption(text,s[1]);
		
		String plainText=c.decryption(middle,s[0]);
		
		return plainText;
	}
	
	

	
	public String checkFirstKey(){
		System.out.println("please enter a key between 1 to 25:");
		Scanner in = new Scanner(System.in);
		String key=in.nextLine();
		int temp=Integer.parseInt(in.nextLine());
		if(temp<1||temp>25){
			System.out.println("invalid key");
			checkFirstKey();
		}
		return key;
	}
	
	public String checkSecondKey(){
		System.out.println("please enter a ket between a to z:");
		Scanner in = new Scanner(System.in);
		String key=in.nextLine();
		if(key.charAt(0)<'a'||key.charAt(0)>'z'){
			System.out.println("invalid key");
			checkSecondKey();
		}
		return key;
	}

	public String generateKey() {
		CeasarCipher c=new CeasarCipher();
		XORencryption xor=new XORencryption();
		String ceasarKey=c.generateKey();
		String xorkey=xor.generateKey();
		return ceasarKey+","+xorkey;
	}

	public int checkKey(String key) {
		CeasarCipher c=new CeasarCipher();
		XORencryption xor=new XORencryption();
		
		String[] s=key.split(",");
		
		int firstCheck=c.checkKey(s[0]);
		System.out.println(firstCheck);
		int secondCheck=xor.checkKey(s[1]);
		System.out.println(secondCheck);
	
		if(firstCheck==1&&secondCheck==1){
			return 1;
		}
		return 0;
	}
	
	

}
