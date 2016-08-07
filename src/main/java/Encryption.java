public interface Encryption {
	
	public String encryption(String text, String key);
	public String decryption(String text, String key);
	public String generateKey();
	public int checkKey(String key);
	

}
