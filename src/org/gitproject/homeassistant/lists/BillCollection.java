package org.gitproject.homeassistant.lists;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Vector;

import org.apache.commons.codec.digest.DigestUtils;
import org.gitproject.homeassistant.kernel.json.JSONParser;

public class BillCollection{
	private Vector<MapValue> bills;
	
	public BillCollection() {
		bills = new Vector<MapValue>();
	}
	
	public void addBill(Bill bill)
	{
		bills.add(new MapValue(bill, bill.store()));
	}
	
	public int removeBill(int i)
	{
		if(bills.get(i).getFile().delete())
			bills.remove(i);
		else
		{
			System.err.println("Could not delete file: " + bills.get(i).getFile().getAbsolutePath());
			return -1;
		}
		return 0;
	}
	
	public int loadBills()
	{
		File directory = new File("database/" + DigestUtils.md5Hex("bills").toUpperCase() + "/");
		if(directory == null || !directory.exists())
		{
			directory.mkdirs();
			return -1;
		}
		bills.clear();
		for(int i = 0; i < directory.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".json");
			}
		}).length; i++)
		{
			Bill temp = new Bill();
			bills.add(new MapValue(new JSONParser<Bill>(directory.listFiles()[i].toString(), temp).instance(), directory.listFiles()[i]));
		}
		
		return 0;
	}
	
	public String toString()
	{
		String value = "";
		for(int i = 0; i < bills.size(); i++)
		{
			value += 
				"---------- Index " + i + " ----------\n" +
				"Path: " + bills.get(i).getFile().getAbsolutePath() + "\n" + 
				"Name: " + bills.get(i).getBill().getName() + "\n" +
				"Type: " + bills.get(i).getBill().getType() + "\n" +
				"Value: " + bills.get(i).getBill().getPrice() + "\n" +
				"Recurrency: " + bills.get(i).getBill().getRecurrency() + "\n";
		}
		value += "---------- END ----------";
		return value;
	}
}
