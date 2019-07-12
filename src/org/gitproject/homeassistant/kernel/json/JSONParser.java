package org.gitproject.homeassistant.kernel.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class JSONParser<T> {
	private T object;
	private File file;
	
	public JSONParser(File file, T object) {
		this.file = file;
		this.object = object;
	}
	
	public JSONParser(String file, T object) {
		this.file = new File(file);
		this.object = object;
	}
	
	public void store()
	{
		FileWriter writer = null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try{
			this.checkFolder();
			writer = new FileWriter(file);
			gson.toJson(object, writer);
			writer.close();
		} catch (JsonIOException e) {
			System.out.println(e.getClass().getName());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getClass().getName());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public T instance()
	{
		FileReader reader = null;
		Gson gson = new Gson();
		try {
			reader = new FileReader(file);
			this.object = (T) gson.fromJson(reader, object.getClass());
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getClass().getName());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getClass().getName());
			e.printStackTrace();
		}
		
		return this.object;
	}
	
	public File getFile()
	{
		return this.file;
	}
	
	private void checkFolder() throws IOException
	{
		File temp = new File(file.getPath().substring(0, file.getPath().lastIndexOf('\\')));
		if(!temp.exists())
			temp.mkdirs();
	}
}
