package it.edu.majoranapa;

import java.time.format.DateTimeFormatter;

import org.apache.commons.codec.digest.DigestUtils;

import it.edu.majoranapa.kernel.json.JSONParser;

public class Profile {
	private String name;
	private String password;
	private String dateCreated;
	private transient boolean passwordLock;
	
	public Profile() {
		super();
		this.dateCreated = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(SystemInfo.getSystemDate());
	}
	
	public Profile(String name) {
		this.name = DigestUtils.sha256Hex(name).toUpperCase();
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
	
	
	
}
