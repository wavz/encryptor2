import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class test
{

	public static void main(String[] args) throws Exception
	{

		int numOfFiles;
		AlgorithmFactory algoFactory = new AlgorithmFactory();
		System.out.println("please choose the type of algorithm you want:");
		Scanner in = new Scanner(System.in);
		Encryption algo = algoFactory.getShape(in.nextLine());
		System.out.println("please enter encryption/dectyprion:");
		Scanner in1 = new Scanner(System.in);
		String action = in1.nextLine();
		long startTime = System.nanoTime();
		if (action.equals("encryption"))
		{

			String key = algo.generateKey();

			DataOutputStream out = openOutputStream("key.bin");
			writeToBinary(out, key);
			out.close();
			numOfFiles = (new File("src/main/java").listFiles().length) - 1;
			ExecutorService executor = Executors.newFixedThreadPool(numOfFiles);
			File[] arr = new File("src/main/java").listFiles();
			String[] contentOfFiles = new String[arr.length];
			createFolder(1);
			for (int i = 1; i < contentOfFiles.length; i++)
			{
				contentOfFiles[i] = getContent(arr[i]);

			}
			for (int i = 1; i < arr.length; i++)
			{
				Runnable Encrypted = new EncryptionThread(algo, key, contentOfFiles[i], arr[i].getName(),null,0);
				executor.execute(Encrypted);
			}
			executor.shutdown();
			System.out.println("Finished all threads");

		} else
		{

			File file = new File("key.bin");
			DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			String key = readFromBinary(input);
		
			int check = algo.checkKey(key);
			if (check == 1)
			{
				createFolder(0);
				numOfFiles = (new File("src/main/java").listFiles().length) - 1;
				ExecutorService executor = Executors.newFixedThreadPool(numOfFiles);
				File[] arr = new File("src/main/java").listFiles();
				String[] contentOfFiles = new String[arr.length];
				for (int i = 1; i < contentOfFiles.length; i++)
				{
					contentOfFiles[i] = getContent(arr[i]);
				}
				for (int i = 1; i < arr.length; i++)
				{
					Runnable Decrypted = new DecryptionThread(algo, key, contentOfFiles[i],arr[i].getName(),null,0);
					executor.execute(Decrypted);
				}
				executor.shutdown();
				System.out.println("Finished all threads");
			} else
			{

				System.out.println("the key is illegal!!");
			}

		}

		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		double seconds = (double) elapsedTime / 1000000000.0;
		System.out.println("the proccess took: " + seconds + " seconds");
	}

	public static String getContent(File file) throws IOException
	{
		String content = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null)
			{
				content += line;
			}

		} catch (FileNotFoundException e)
		{
			System.out.println("incorrect file path or file dont exists");
			
		}
		return content;
	}

	public static String contentFromFileAndpath()
	{
		System.out.println("please enter file path: ");
		Scanner input = new Scanner(System.in);
		String path = input.nextLine();
		String content = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null)
			{
				content += line;
			}

		} catch (FileNotFoundException e)
		{
			System.out.println("incorrect file path or file dont exists");
			contentFromFileAndpath();
		} catch (IOException e)
		{
			System.out.println("IO Exception");
			e.printStackTrace();
		}
		return content + "+" + path;
	}

	public static String content(String contentAndFilePath)
	{
		String[] s = contentAndFilePath.split("\\+");
		return s[0];
	}

	public static DataOutputStream openOutputStream(String name) throws Exception
	{
		DataOutputStream out = null;
		File file = new File(name);
		out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		return out;
	}

	public static void writeToBinary(DataOutputStream out, String key) throws Exception
	{
		out.writeUTF(key);

	}

	public static String readFromBinary(DataInputStream in)
	{
		String text = "";

		try
		{
			text = in.readUTF();
		} catch (IOException e)
		{
			System.out.println("I/O Error");
			System.exit(0);
		}
		return text;
	}

	public static String filePath(String contentAndPath)
	{
		String[] s = contentAndPath.split("\\+");
		String path = s[1];
		return path;
	}

	public static void writeToNewFile(String text, int flag, String filePath)
	{
		/*
		 * String path = filePath; String[] s = fileName(path).split("\\.");
		 * 
		 * String file = s[0]; String typeOfFile = s[1];
		 */
		File fout = null;
		if (flag == 1)
		{
			fout = new File(filePath + "_encrypted");
		} else
		{
			fout = new File(filePath + "_decrypted");
		}
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream(fout);
		} catch (FileNotFoundException e)
		{
			System.out.println("faild to create new file");
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		try
		{
			bw.write(text);
			bw.close();
		} catch (IOException e)
		{
			System.out.println("faild to write into the file");
		}

	}

	public static String fileName(String path)
	{
		String[] s = path.split("/");
		String finalPath = s[s.length - 1];
		return finalPath;
	}

	public static void createFolder(int flag)
	{
		if (flag == 1)
		{
			File theDir = new File("Encrypted");

			// if the directory does not exist, create it
			if (!theDir.exists())
			{
				System.out.println("creating directory");
				boolean result = false;

				try
				{
					theDir.mkdir();
					result = true;
				} catch (SecurityException se)
				{
					// handle it
				}
				if (result)
				{
					System.out.println("DIR created");
				}
			}
		}
		else
		{
			File theDir = new File("Decrypted");

			
			if (!theDir.exists())
			{
				System.out.println("creating directory");
				boolean result = false;

				try
				{
					theDir.mkdir();
					result = true;
				} catch (SecurityException se)
				{
					
				}
				if (result)
				{
					System.out.println("DIR created");
				}
			}
		}

	}

	

}
