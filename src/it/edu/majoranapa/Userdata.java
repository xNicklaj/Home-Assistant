package it.edu.majoranapa;

import it.edu.majoranapa.lists.BillCollection;

public class Userdata {
	public static BillCollection bills;
	private static boolean lock = false;
	
	public static void load()
	{
		if(lock)
			return;
		bills = new BillCollection();
		bills.loadBills();
	}
}
