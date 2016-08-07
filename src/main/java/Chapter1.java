/**
 * Created by wavz on 25/04/2016.
 */

import java.util.Scanner;
import lombok.Data;

import java.io.FileReader;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public @Data class Chapter1 {

    public static void main(String[] args) {
        readFromFile();

    }

    public static void readFromFile() {

        System.out.println("please enter the path for the file:");
        Scanner inputPath = new Scanner(System.in);
        String path = inputPath.nextLine();

        try {
            Scanner in = new Scanner(new FileReader(path));

            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();
            String[] file = path.split("/");
            String fileName = file[file.length - 1];

            Cipher desCipher;
            desCipher = Cipher.getInstance("DES");

            byte[] text = in.nextLine().getBytes("UTF8");

            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);

            String s = new String(textEncrypted);
            System.out.println("Encryption Simulation for file " + fileName);
            System.out.println("the encrypted text is: " + s);

            System.out.println("if you want to decrypt the text, enter yes:");
            Scanner answer = new Scanner(System.in);
            String ans = answer.nextLine();
            if (ans.equals("yes")) {
                desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
                byte[] textDecrypted = desCipher.doFinal(textEncrypted);
                s = new String(textDecrypted);
                System.out.println("decrypting simulation for file " + fileName);

                System.out.println("the decrypted text is: " + s);
            } else {
                System.out.println("end of the simulation");
            }

        } catch (Exception e) {

            System.out.println("the path or file is incorrect");
            readFromFile();

        }
    }


}
