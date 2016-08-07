import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReverseEncryption  implements Encryption{
	
	
	
	
	public ReverseEncryption(){
		
		
	}

	public String encryption(String text,String key){
		XORencryption xor=new XORencryption();
		String encrypted=xor.decryption(text,key);
		
		return encrypted;
	}

	public String decryption(String text,String key){
		XORencryption xor=new XORencryption();
		String decrypted=xor.encryption(text,key);
		return decrypted;
	}

	public String generateKey() {
		XORencryption xor=new XORencryption();
		String xorKey;
		xorKey=xor.generateKey();
		return xorKey;
	}

	public int checkKey(String key) {
		XORencryption xor=new XORencryption();
		return xor.checkKey(key);
		
		
	}

}
