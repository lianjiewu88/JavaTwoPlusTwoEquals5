package fileTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileTest {

	public static void main(String[] args) {
		Path file_dir = Paths.get("C:\\Users\\i042416\\Desktop\\VISA");
        Path file = file_dir.resolve("Jerry.txt");
        BasicFileAttributes attrs;
		try {
			attrs = Files.readAttributes(file, BasicFileAttributes.class);
			System.out.println("Last accessed at:" + attrs.lastAccessTime());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
        

	}

}
