import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.log4j.Logger;

public class EncryptionThread extends Thread{
	Encryption algo;
	String key;
	String msg;
	String fileName;
	MyApp app;
	int flag;
	Logger logger;

	
	public Encryption getAlgo() {
		return algo;
	}
	public void setAlgo(Encryption algo) {
		this.algo = algo;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public EncryptionThread(Encryption algo, String key, String msg, String fileName,MyApp app,int flag) {
		super();
		this.algo = algo;
		this.key = key;
		this.msg = msg;
		this.fileName=fileName;
		this.app=app;
		this.flag=flag;
		this.logger= Logger.getLogger(EncryptionThread.class);
		
	}
	public void run(){
		if(this.flag==1){
			long startTime = System.nanoTime();
			String encrypted=this.app.encryption(this.msg, this.key);
			long endTime = System.nanoTime();
			if("".equals(encrypted)){
				try
				{
					throw new EncryptionFaildException("encryption faild!");
				} catch (EncryptionFaildException e)
				{
					XMLReport report=new XMLReport();
					report.setExceptionName("EncryptionFaildException");
					report.setExcMsg("encryption faild!");
					report.setFileName(this.fileName);
					String stackTrace=report.stackToString(e);
					report.setStackTrace(stackTrace);
					reportObjectToXML(report);
				}
			}
			//org.apache.log4j.BasicConfigurator.configure();
			logToFile("Succeed to encrypt the file: "+this.fileName);

			long elapsedTime = endTime - startTime;
			double seconds = (double) elapsedTime / 1000000000.0;

			XMLReport report=new XMLReport();
			report.setStatus("encryption secceed");
			report.setTime(seconds);
			report.setFileName(this.fileName);
			reportObjectToXML(report);
			System.out.println(encrypted);
			writeToNewFile(encrypted, 1, this.fileName);
		}
		else
		{
			long startTime = System.nanoTime();
			String encrypted=this.algo.encryption(this.msg, this.key);
			long endTime = System.nanoTime();
			if("".equals(encrypted)){
				try
				{
					throw new EncryptionFaildException("encryption faild!");
				} catch (EncryptionFaildException e)
				{
					XMLReport report=new XMLReport();
					report.setExceptionName("EncryptionFaildException");
					report.setExcMsg("encryption faild!");
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
			System.out.println(encrypted);
			writeToNewFile(encrypted, 1, this.fileName);
		}

		
	}
	
	public  void writeToNewFile(String text, int flag, String fileName)
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
		System.out.println("in report method");
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
		System.out.println("create folder method");
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
	public void logToFile(String msg){
		if(this.logger.isInfoEnabled()){
			this.logger.info(msg);
		}

	}


}
