import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class DecryptionThread extends Thread
{
	Encryption algo;
	String key;
	String msg;
	String fileName;
	MyApp app;
	int flag;
	static Logger logger;
	public DecryptionThread(Encryption algo, String key, String msg,String fileName,MyApp app,int flag)
	{
		super();
		this.algo = algo;
		this.key = key;
		this.msg = msg;
		this.fileName=fileName;
		this.app=app;
		this.flag=flag;
		this.logger=Logger.getLogger(DecryptionThread.class);
	}
	
	public void run(){
		if(this.flag==1){
			long startTime = System.nanoTime();
			String decrypted=app.decryption(this.msg, this.key);
			long endTime = System.nanoTime();
			if("".equals(decrypted)){
				try
				{
					throw new DecryptionFaildException("decryption faild!!");
				} catch (DecryptionFaildException e)
				{
					XMLReport report=new XMLReport();
					report.setExceptionName("DecryptionFaildException");
					report.setExcMsg("decryption faild!");
					report.setFileName(this.fileName);
					String stackTrace=report.stackToString(e);
					report.setStackTrace(stackTrace);
					reportObjectToXML(report);

				}




			}
			org.apache.log4j.BasicConfigurator.configure();
			logToFile("Succeed to encrypt the file: "+this.fileName);
			long elapsedTime = endTime - startTime;
			double seconds = (double) elapsedTime / 1000000000.0;
			XMLReport report=new XMLReport();
			report.setStatus("encryption secceed");
			report.setTime(seconds);
			report.setFileName(this.fileName);
			reportObjectToXML(report);
			System.out.println(decrypted);
			writeToNewFile(decrypted, 1, this.fileName);
			System.out.println(decrypted);
		}
		else
		{
			long startTime = System.nanoTime();
			String decrypted=algo.decryption(this.msg, this.key);
			long endTime = System.nanoTime();
			if("".equals(decrypted)){
				try
				{
					throw new DecryptionFaildException("decryption faild!!");
				} catch (DecryptionFaildException e)
				{
					XMLReport report=new XMLReport();
					report.setExceptionName("DecryptionFaildException");
					report.setExcMsg("decryption faild!");
					report.setFileName(this.fileName);
					String stackTrace=report.stackToString(e);
					report.setStackTrace(stackTrace);
					reportObjectToXML(report);

				}




			}
			org.apache.log4j.BasicConfigurator.configure();
			logToFile("Succeed to encrypt the file: "+this.fileName);
			long elapsedTime = endTime - startTime;
			double seconds = (double) elapsedTime / 1000000000.0;
			XMLReport report=new XMLReport();
			report.setStatus("encryption secceed");
			report.setTime(seconds);
			report.setFileName(this.fileName);
			reportObjectToXML(report);
			System.out.println(decrypted);
			writeToNewFile(decrypted, 1, this.fileName);
			System.out.println(decrypted);
		}

		
		
	}
	
	public static void writeToNewFile(String text, int flag, String fileName)
	{
		/*String path = filePath;
		String[] s = fileName(path).split("\\.");

		String file = s[0];
		String typeOfFile = s[1];*/
		String path= "Encrypted"+File.separator+fileName+"_encrypted";
		File fout = null;
		if (flag == 1)
		{
			fout = new File(path);
		} else
		{
			fout = new File(path) ;
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
	public  void reportObjectToXML(XMLReport report){
		createXMLReportFolder();
		try {

			File file = new File("XMLReports/"+fileName+"_Report"+".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(XMLReport.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(report, file);
			jaxbMarshaller.marshal(report, System.out);

		      } catch (JAXBException e) {
		    	  e.printStackTrace();
		      }
	}
	
	public static void createXMLReportFolder(){
		File theDir = new File("XMLReports");

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
	public static void logToFile(String msg){
		if(logger.isInfoEnabled()){
			logger.info(msg);
		}

	}
}
