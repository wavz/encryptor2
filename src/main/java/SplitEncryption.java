import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SplitEncryption  implements Encryption  {
	private XORencryption xor;
	private String key1;
	private String key2;
	
	public SplitEncryption(){ // need to check if the keys are different
		xor=new XORencryption();
		
		
				
		
	}

	public String encryption(String text,String key){
		
		String[] s= key.split(",");
		String key1=s[0];
		String key2=s[1];
		
		
		
		
		String even="";
		String odd="";
		String evenEncrypted="";
		String oddEncrypted="";
		String finalEncrypted="";
		for (int i = 0; i < text.length(); i+=2) {
			even+=text.charAt(i);
			
		}
		for (int i = 1; i < text.length(); i+=2) {
			odd+=text.charAt(i);
			
		}
		if(checkLength(text)==0){
			evenEncrypted=xor.encryption(even, key1);
			oddEncrypted=xor.encryption(odd, key2);
			for (int i = 0; i < text.length(); i++) {
				finalEncrypted+=evenEncrypted.charAt(i);
				finalEncrypted+=oddEncrypted.charAt(i);
				
				
			}
			return finalEncrypted;
		}else{
			if(even.length()>odd.length()){
				evenEncrypted=xor.encryption(even,key1);
				oddEncrypted=xor.encryption(odd, key2);
				for (int i = 0; i < odd.length(); i++) {
					finalEncrypted+=evenEncrypted.charAt(i);
					finalEncrypted+=oddEncrypted.charAt(i);
					
				}
				
				finalEncrypted+=even.charAt(even.length()-1);
				return finalEncrypted;
			}
		}
		return finalEncrypted;
		
	}

	public String decryption(String text,String key){
		String[] s=key.split(",");
		String key1=s[0];
		String key2=s[1];
		String even="";
		String odd="";
		String evenEncrypted="";
		String oddEncrypted="";
		String finalEncrypted="";
		for (int i = 0; i < text.length(); i+=2) {
			even+=text.charAt(i);
			
		}
		for (int i = 1; i < text.length(); i+=2) {
			odd+=text.charAt(i);
			
		}
		if(checkLength(text)==0){
			evenEncrypted=xor.decryption(even, key1);
			oddEncrypted=xor.decryption(odd, key2);
			for (int i = 0; i < text.length(); i++) {
				finalEncrypted+=evenEncrypted.charAt(i);
				finalEncrypted+=oddEncrypted.charAt(i);
				
				
			}
			return finalEncrypted;
		}else{
			if(even.length()>odd.length()){
				evenEncrypted=xor.decryption(even, key1);
				oddEncrypted=xor.decryption(odd, key2);
				for (int i = 0; i < odd.length(); i++) {
					finalEncrypted+=evenEncrypted.charAt(i);
					finalEncrypted+=oddEncrypted.charAt(i);
					
				}
				
				finalEncrypted+=even.charAt(even.length()-1);
				return finalEncrypted;
			}
		}
		return finalEncrypted;
	}
	
	public int checkLength(String s1){
		if(s1.length()%2==0){
			return 0;
		}
		else{
			return 1;
		}
		
	}

	public String generateKey() {
		String firstKey,secondtKey="";
		char[] allChar = new char[26];
		ArrayList<Character> charArr = new ArrayList<Character>();
		Random rand = new Random();
		int k = 0;
		for (char i = 'a'; i <= 'z'; i++) {
			allChar[k] = i;
			k++;

		}
		for (int i = 0; i < allChar.length; i++) {
			charArr.add(allChar[i]);

		}
		
		int n = rand.nextInt(charArr.size());
		firstKey=Character.toString(charArr.get(n));
		charArr.remove(n);	
		
		int n1 = rand.nextInt(charArr.size());
		secondtKey=Character.toString(charArr.get(n1));
		charArr.remove(n1);
	
		
		this.key1=firstKey;
		this.key2=secondtKey;
		
		return firstKey+","+secondtKey;
	}

	public int checkKey(String key) {
		String[] s=key.split(",");
		int firstCheck=xor.checkKey(s[0]);
		int secondCheck=xor.checkKey(s[1]);
		if(firstCheck==1&&secondCheck==1){
			return 1;
		}
		return 0;
	}
	
	
	

}
