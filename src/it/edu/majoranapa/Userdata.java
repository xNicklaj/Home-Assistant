package it.edu.majoranapa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

import it.edu.majoranapa.kernel.json.JSONParser;
import it.edu.majoranapa.lists.BillCollection;

public class Userdata {
	public static BillCollection bills;
	private static Profile profile;
	private static File profileJson;
	private static boolean lock = false;
	
	public static void load()
	{
		File directory = new File("./database/" + (DigestUtils.md5Hex("profiledata") + "/").toUpperCase());
		if(!directory.exists())
			directory.mkdirs();
		directory = null;
		profileJson = new File("./database/" + (DigestUtils.md5Hex("profiledata") + "/" + DigestUtils.md5Hex("profile")).toUpperCase() + ".json");			
		if(profileJson != null && profileJson.exists())
		{
			profile = new Profile();
			JSONParser<Profile> parser = new JSONParser<Profile>(profileJson, profile);
			profile = parser.instance();
		}
			
		
		if(lock)
			return;
		bills = new BillCollection();
		bills.loadBills();
	}
	
	public static int compareUsers(Profile user)
	{
		if(profileJson == null || !profileJson.exists())
		{
			addUser(user);
			return 0;
		}
		else if(user.getName().toUpperCase().equals(profile.getName()) && user.getPassword().toUpperCase().equals(profile.getPassword()))
			return 0;
		else if(!user.getName().toUpperCase().equals(profile.getName()) && user.getPassword().toUpperCase().equals(profile.getPassword()))
			return 1;
		else if(user.getName().toUpperCase().equals(profile.getName()) && !user.getPassword().toUpperCase().equals(profile.getPassword()))
			return 2;
		else
			return 3;
		
	}
	
	private static void addUser(Profile user)
	{
		JSONParser<Profile> parser = new JSONParser<Profile>(profileJson, user);
		parser.store();
	}
	
}
