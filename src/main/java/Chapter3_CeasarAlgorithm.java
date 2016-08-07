import java.util.Scanner;

public class Chapter3_CeasarAlgorithm {

	public static void main(String[] args) {
		CeasarCipher ceasar = new CeasarCipher();
		System.out.println("please enter encryption/decryption:");
		Scanner in = new Scanner(System.in);

		if (in.nextLine().equals("encryption")) {
			String contentAndfilePath = ceasar.contentFromFileAndpath();
			String content = ceasar.content(contentAndfilePath);
			String filePath = ceasar.filePath(contentAndfilePath); // full file
																	// path

			String fileName = ceasar.fileName(filePath);
			int flag = 1;
			int key = ceasar.randomizeKey();
			String ceasarKey=Integer.toString(key);
			String encrypted = ceasar.encryption(content,ceasarKey);

		} else {
			String contentAndfilePath = ceasar.contentFromFileAndpath();
			String content=ceasar.content(contentAndfilePath);
			String filePath =ceasar.filePath(contentAndfilePath); // full file path
			
			String fileName = ceasar.fileName(filePath);
			int flag = 0;
			System.out.println("please enter the key:");
			Scanner in1 = new Scanner(System.in);
			String decrypted = ceasar.Decrypt(ceasar.Encrypt(content, Integer.parseInt(in1.nextLine())),
					Integer.parseInt(in1.nextLine()));
			ceasar.writeToNewFile(decrypted, 0,filePath);

		}

	}

}
