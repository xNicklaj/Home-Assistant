package org.gitproject.homeassistant.lists;

import java.io.File;
import java.nio.file.Path;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.gitproject.homeassistant.kernel.json.JSONParser;

public class Bill {
	private String name = "";
	private BillType type;
	@SuppressWarnings("unused")
	private double price;
	@SuppressWarnings("unused")
	private int recurrency;
	
	public Bill() {
		super();
	}
	
	public Bill(Bill bill) {
		this.name = bill.name;
		this.type = bill.type;
		this.price = bill.price;
		this.recurrency = bill.recurrency;
	}
	
	public Bill(String name, BillType type, double price, int recurrency){
		this.name = name;
		this.type = type;
		this.price = price;
		this.recurrency = recurrency;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BillType getType() {
		return type;
	}
	
	public double getPrice() {
		return price;
	}

	public int getRecurrency() {
		return recurrency;
	}

	public void setType(BillType type) {
		this.type = type;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRecurrency(int recurrency) {
		this.recurrency = recurrency;
	}
	
	public void read(Path path)
	{
		JSONParser<Bill> parser = new JSONParser<Bill>(path.toString(), this);
		parser.instance();
	}
	
	public File store()
	{	
		JSONParser<Bill> parser = new JSONParser<Bill>("./database/" + DigestUtils.md5Hex("bills").toUpperCase() + "/" + DigestUtils.md5Hex(name + new Random().nextInt()).toUpperCase() + ".json", this);
		parser.store();
		
		return parser.getFile();
	}
	
}
