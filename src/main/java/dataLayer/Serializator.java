package dataLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializator {
	public static void serialize(String fileName, Object ob) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(ob);
		fos.close();
		oos.close();
	}
	public static void serializeUnshared(String fileName, Object ob) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeUnshared(ob);
		fos.close();
		oos.close();
	}
	
	public static void deserialize(String fileName) throws Exception {
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		ois.readObject();
		fis.close();
		ois.close();
	}
	public static Object deserializeObj(String fileName) throws Exception{
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object ob = ois.readObject();
		fis.close();
		ois.close();
		return ob;
	}
}
