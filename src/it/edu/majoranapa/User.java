package it.edu.majoranapa;

import java.time.format.DateTimeFormatter;

import org.apache.commons.codec.digest.DigestUtils;

import it.edu.majoranapa.kernel.json.JSONParser;

public class User {
	private String name;
	private String password;
	private String dateCreated;
	private int loginType;
	private transient boolean passwordLock;
	
	public static int PWDONLY = 0;
	public static int USERANDPWD = 1;
	
	public User() {
		super();
		this.name = "";
		this.password = "";
		this.loginType = 0;
		this.dateCreated = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(SystemInfo.getSystemDate());
	}
	
	public User(String name) {
		this.name = DigestUtils.sha256Hex(name).toUpperCase();
		this.password = "";
		this.loginType = 0;
		this.dateCreated = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(SystemInfo.getSystemDate());
	}
	
	public void setPassword(String password)
	{
		this.password = DigestUtils.sha256Hex(password).toUpperCase();
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	public int getLoginType()
	{
		return loginType;
	}
	
	
	
}
