package it.edu.majoranapa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

import it.edu.majoranapa.kernel.json.JSONParser;
import it.edu.majoranapa.lists.BillCollection;

public class Userdata {
	private static BillCollection bills;
	private static User profile;
	private static File profileJson;
	private static boolean lock = false;
	private static int loginType = User.PWDONLY;
	
	public static void load()
	{
		File directory = new File("./database/" + (DigestUtils.md5Hex("profiledata") + "/").toUpperCase());
		if(!directory.exists())
			directory.mkdirs();
		directory = null;
		profileJson = new File("./database/" + (DigestUtils.md5Hex("profiledata") + "/" + DigestUtils.md5Hex("profile")).toUpperCase() + ".json");			
		if(profileJson != null && profileJson.exists())
		{
			profile = new User();
			JSONParser<User> parser = new JSONParser<User>(profileJson, profile);
			profile = parser.instance();
			loginType = profile.getLoginType();
		}
			
		
		if(lock)
			return;
		bills = new BillCollection();
		bills.loadBills();
	}
	
	public static int compareUsers(User user)
	{
		if(profileJson == null || !profileJson.exists())
		{
			addUser(user);
			return 0;
		}
		else if((loginType == 0 || user.getName().toUpperCase().equals(profile.getName())) && user.getPassword().toUpperCase().equals(profile.getPassword()))
			return 0;
		else if((loginType == 0 || !user.getName().toUpperCase().equals(profile.getName())) && user.getPassword().toUpperCase().equals(profile.getPassword()))
			return 1;
		else if((loginType == 0 || user.getName().toUpperCase().equals(profile.getName())) && !user.getPassword().toUpperCase().equals(profile.getPassword()))
			return 2;
		else
			return 3;
		
	}
	
	private static void addUser(User user)
	{
		JSONParser<User> parser = new JSONParser<User>(profileJson, user);
		parser.store();
	}

	public static int getLoginType() {
		return loginType;
	}

	public static void setLoginType(int loginType) {
		Userdata.loginType = loginType;
	}

	public static BillCollection getBills() {
		return bills;
	}
	
	
	
}
