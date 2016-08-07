import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestListFiles
{

	public static void main(String[] args) throws IOException
	{
		int numOfFiles = new File("src").listFiles().length;
		System.out.println(numOfFiles);
		File[] arr = new File("src").listFiles();
		String[] temp = new String[arr.length];
		for (int i = 1; i < temp.length; i++)
		{
			temp[i] = getContent(arr[i]);
		}
		for (int i = 1; i < temp.length; i++)
		{
			System.out.println(temp[i]);
		}

	}
	private static String getContent(File file) throws IOException
	{
		String content="";
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

}
