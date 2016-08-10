import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.FilenameFilter;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Chapter7WithXML
{

	public static void main(String[] args)
	{
		int flag=1;


		createFolder(2);
		createFolder(1);
		createFolder(0);
		String pathCeasar = "AlgoritmXML/ceasarCiper.xml";
		createCeasarXML(pathCeasar);
		System.out.println(
				"please enter yes if you want to load the algorithm from xml file, or, if you want to use the default algoritm please enter 'default':");
		Scanner in = new Scanner(System.in);
		String input1 = in.nextLine();
		if (input1.equals("default"))
		{
			boolean validated=false;
			try
			{
				validated= validateXMLSchema("AlgoritmXML/RSA.xsd", "AlgoritmXML/RSA.xml");
				System.out.println(validated);
			} catch (JAXBException e)
			{
				
				e.printStackTrace();
			} catch (IOException e)
			{
				
				e.printStackTrace();
			}
			if(validated==true)
			{
				/*Encryption algo = loadXMLtoObject("AlgoritmXML/RSA.xml");*/
				Injector injector = Guice.createInjector(new AppInjector());

				MyApp app = injector.getInstance(MyApp.class);

				System.out.println("please enter encryption/decryption:");
				Scanner in3 = new Scanner(System.in);

				String result = in3.nextLine();
				actionInjection(result,app);
				flag=0;
			}
			
		}
		
		if (input1.equals("yes")&&flag==1)
		{
			System.out.println("please enter the path of the XML file:");
			Scanner in2 = new Scanner(System.in);
			String path = in2.nextLine();
			Encryption algo = loadXMLtoObject(path);
			
			
			System.out.println("please enter encryption/decryption:");
			Scanner in3 = new Scanner(System.in);

			String result = in3.nextLine();
			action(result, algo);
		} else
		{
			if(flag==1){
				AlgorithmFactory algoFactory = new AlgorithmFactory();
				System.out.println("please choose the type of algorithm you want:");
				Scanner in1 = new Scanner(System.in);
				Encryption algo = algoFactory.getShape(in.nextLine());
				String path2 = "AlgoritmXML/" + algo.getClass().getSimpleName() + ".xml";
				AlgoToXML(path2, algo);
				

				System.out.println("please enter encryption/decryption:");
				Scanner in3 = new Scanner(System.in);
				String result = in3.nextLine();
				action(result, algo);
			}
			
		}
		

	}

	public static void createCeasarXML(String path)
	{

		CeasarCipher c = new CeasarCipher();
		try
		{

			File file = new File(path);
			JAXBContext jaxbContext = JAXBContext.newInstance(CeasarCipher.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(c, file);
			jaxbMarshaller.marshal(c, System.out);

		} catch (JAXBException e)
		{
			e.printStackTrace();
		}

	}

	public static void createFolder(int flag)
	{
		if (flag == 1)
		{
			File theDir = new File("Encrypted");


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
					System.out.println("Problem with creating the folder");
				}
				if (result)
				{
					System.out.println("DIR created");
				}
			}
		}
		if (flag == 0)
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

		if (flag == 2)
		{

			File theDir = new File("AlgoritmXML");

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

	public static void AlgoToXML(String path, Encryption algo)
	{

		try
		{

			File file = new File(path);
			JAXBContext jaxbContext = JAXBContext.newInstance(algo.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(algo, file);
			jaxbMarshaller.marshal(algo, System.out);

		} catch (JAXBException e)
		{
			e.printStackTrace();
		}
	}

	public static Encryption loadXMLtoObject(String path)
	{
		String s1 = path.substring(path.lastIndexOf('/') + 1, path.lastIndexOf('.'));
		System.out.println(s1);
		Class c;
		Encryption algo = null;

		try
		{
			c = Class.forName("Project." + s1);

			File file = new File(path);
			JAXBContext jaxbContext = JAXBContext.newInstance(c);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			algo = (Encryption) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e1)
		{

			e1.printStackTrace();
		}

		return algo;
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

	public static void writeToNewFile(String text, int flag, String fileName)
	{
		/*
		 * String path = filePath; String[] s = fileName(path).split("\\.");
		 * 
		 * String file = s[0]; String typeOfFile = s[1];
		 */
		String path = "Encrypted" + File.separator + fileName + "_encrypted";
		File fout = null;
		if (flag == 1)
		{
			fout = new File(path);
		} else
		{
			fout = new File(path);
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

	public static void action(String result, Encryption algo)
	{
		
		File[] arr = finder("src/main/java");

		int numOfFiles = arr.length ;
		ExecutorService executor = Executors.newFixedThreadPool(numOfFiles);
		if (result.equals("encryption"))
		{
			String key=algo.generateKey();
			DataOutputStream out = null;

			try
			{
				out = openOutputStream("key.bin");
			} catch (Exception e1)
			{

				e1.printStackTrace();
			}
			try
			{
				writeToBinary(out, key);
				out.close();
			} catch (Exception e1)
			{

				e1.printStackTrace();
			}
			String[] contentOfFiles = new String[arr.length];

			for (int i = 0; i < contentOfFiles.length; i++)
			{
				try
				{
					contentOfFiles[i] = getContent(arr[i]);
				} catch (IOException e)
				{

					e.printStackTrace();
				}

			}
			for (int i = 0; i < arr.length; i++)
			{
				System.out.println("in for");
				Runnable Encrypted = new EncryptionThread(algo, key, contentOfFiles[i], arr[i].getName(),null,0);
				executor.execute(Encrypted);
			}
			executor.shutdown();
			System.out.println("Finished all threads");
		} else
		{
			File file = new File("key.bin");
			DataInputStream input = null;

			try
			{
				input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			} catch (FileNotFoundException e)
			{

				e.printStackTrace();
			}
			String key = readFromBinary(input);
			String[] contentOfFiles = new String[arr.length];

			for (int i = 1; i < contentOfFiles.length; i++)
			{
				try
				{
					contentOfFiles[i] = getContent(arr[i]);
				} catch (IOException e)
				{

					e.printStackTrace();
				}

			}
			for (int i = 1; i < arr.length; i++)
			{
				Runnable Decrypted = new DecryptionThread(algo, key, contentOfFiles[i], arr[i].getName(),null,0);
				executor.execute(Decrypted);
			}
			executor.shutdown();
			System.out.println("Finished all threads");
		}
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
	public static boolean validateXMLSchema(String xsdPath, String xmlPath) throws JAXBException, IOException{
        
        try {
            SchemaFactory factory = 
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            
			javax.xml.validation.Validator validator =  schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
	public static void println(Object obj){
		System.out.println(obj);
	}

	public static File[] finder( String dirName){
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename)
			{ return filename.endsWith(".txt"); }
		} );

	}

	public static void actionInjection(String result, MyApp app){
		File[] arr = finder("src/main/java");

		int numOfFiles = arr.length ;
		ExecutorService executor = Executors.newFixedThreadPool(numOfFiles);
		if (result.equals("encryption"))
		{
			String key=app.generateKey();
			DataOutputStream out = null;

			try
			{
				out = openOutputStream("key.bin");
			} catch (Exception e1)
			{

				e1.printStackTrace();
			}
			try
			{
				writeToBinary(out, key);
				out.close();
			} catch (Exception e1)
			{

				e1.printStackTrace();
			}
			String[] contentOfFiles = new String[arr.length];

			for (int i = 0; i < contentOfFiles.length; i++)
			{
				try
				{
					contentOfFiles[i] = getContent(arr[i]);
				} catch (IOException e)
				{

					e.printStackTrace();
				}

			}
			for (int i = 0; i < arr.length; i++)
			{
				System.out.println("in for");
				Runnable Encrypted = new EncryptionThread(null, key, contentOfFiles[i], arr[i].getName(),app,1);
				executor.execute(Encrypted);
			}
			executor.shutdown();
			System.out.println("Finished all threads");
		} else
		{
			File file = new File("key.bin");
			DataInputStream input = null;

			try
			{
				input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			} catch (FileNotFoundException e)
			{

				e.printStackTrace();
			}
			String key = readFromBinary(input);
			String[] contentOfFiles = new String[arr.length];

			for (int i = 1; i < contentOfFiles.length; i++)
			{
				try
				{
					contentOfFiles[i] = getContent(arr[i]);
				} catch (IOException e)
				{

					e.printStackTrace();
				}

			}
			for (int i = 1; i < arr.length; i++)
			{
				Runnable Decrypted = new DecryptionThread(null, key, contentOfFiles[i], arr[i].getName(),app,1);
				executor.execute(Decrypted);
			}
			executor.shutdown();
			System.out.println("Finished all threads");
		}
	}

}
