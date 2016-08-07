import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XMLReport
{
	String status;
	String fileName;
	double time;
	String exceptionName;
	String excMsg;
	String stackTrace;
	public XMLReport(){}
	
	public String getFileName()
	{
		return fileName;
	}
	@XmlElement
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	
	
	

	public String getStatus()
	{
		return status;
	}

	public double getTime()
	{
		return time;
	}

	public String getExceptionName()
	{
		return exceptionName;
	}

	public String getExcMsg()
	{
		return excMsg;
	}

	public String getStackTrace()
	{
		return stackTrace;
	}
	@XmlElement
	public void setStatus(String status)
	{
		this.status = status;
	}
	@XmlElement
	public void setTime(double time)
	{
		this.time=time;
	}
	@XmlElement
	public void setExceptionName(String exceptionName)
	{
		this.exceptionName = exceptionName;
	}
	@XmlElement
	public void setExcMsg(String excMsg)
	{
		this.excMsg = excMsg;
	}
	@XmlElement
	public void setStackTrace(String stackTrace)
	{
		this.stackTrace = stackTrace;
	}
	
	public String stackToString(Exception e)
	{
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
	
	
}
