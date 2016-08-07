import java.util.Random;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CeasarCipher  implements Encryption
{

	public CeasarCipher()
	{

	};
	
	public static String Decrypt(String enc, int key)
	{
		return Encrypt(enc, 26 - key);
	}
	
	public static String Encrypt(String enc, int key)
	{
		key = key % 26 + 26;
		StringBuilder encoded = new StringBuilder();
		for (char i : enc.toCharArray())
		{
			if (Character.isLetter(i))
			{
				if (Character.isUpperCase(i))
				{
					encoded.append((char) ('A' + (i - 'A' + key) % 26));
				} else
				{
					encoded.append((char) ('a' + (i - 'a' + key) % 26));
				}
			} else
			{
				encoded.append(i);
			}
		}

		return encoded.toString();
	}
	
	public int randomizeKey()
	{
		Random rand = new Random();
		int n = rand.nextInt(25) + 1;
		System.out.println("the random key is: " + n);
		return n;
	}


	
	public String encryption(String text, String key)
	{

		int offset = Integer.parseInt(key);

		offset = offset % 26 + 26;
		StringBuilder encoded = new StringBuilder();
		for (char i : text.toCharArray())
		{
			if (Character.isLetter(i))
			{
				if (Character.isUpperCase(i))
				{
					encoded.append((char) ('A' + (i - 'A' + offset) % 26));
				} else
				{
					encoded.append((char) ('a' + (i - 'a' + offset) % 26));
				}
			} else
			{
				encoded.append(i);
			}
		}

		return encoded.toString();
	}


	
	public String decryption(String text, String key)
	{

		int offset = Integer.parseInt(key);
		return Encrypt(text, 26 - offset);
	}


	
	public int checkKey(String key)
	{

		int n = Integer.parseInt(key);

		for (int i = 1; i < 26; i++)
		{

			if (n == i)
			{
				return 1;
			}

		}
		return 0;

	}


	
	public String generateKey()
	{
		Random rand = new Random();
		int n = rand.nextInt(25) + 1;
		String key = Integer.toString(n);
		return key;
	}

}
