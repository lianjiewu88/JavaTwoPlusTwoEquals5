package serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class SerializableDemo {
	public static void main(String[] arg) {

		Employee jerry = new Employee();
		jerry.setName("Jerry Wang");
		jerry.setID("I042416");
		jerry.setGender("male");
		jerry.setBirthday(new Date());
		System.out.println(jerry);

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("c:\\temp\\tempFile"));
			oos.writeObject(jerry);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		File file = new File("c:\\temp\\tempFile");
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			Employee newUser = (Employee) ois.readObject();
			System.out.println(newUser);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
				file.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
