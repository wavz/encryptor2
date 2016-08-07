import java.io.File;
import java.io.IOException;

public class TestNewDirectory
{

	public static void main(String[] args) throws IOException
	{
		
		String path= "Encrypted"+File.separator+"hello.txt"+"_encrypted";
		// Use relative path for Unix systems
		File f = new File(path);

		f.getParentFile().mkdirs(); 
		f.createNewFile();

	}

}
