package it.edu.majoranapa.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class DHCPManager {
	private static String deviceIP;
	private static String networkInterface = "";

	public static String updateIP()
	{
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface iface = interfaces.nextElement();
				// filters out 127.0.0.1 and inactive interfaces
				if (iface.isLoopback() || !iface.isUp())
					continue;

				Enumeration<InetAddress> addresses = iface.getInetAddresses();
				while(addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();

					// *EDIT*
					if(addr instanceof Inet6Address) continue;

					deviceIP = addr.getHostAddress();
				}
			}
			return deviceIP;
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
	}
	
	private boolean testIP(InetAddress address) throws IOException
	{
		return (address.isReachable(3000))?true:false;
	}
	
	public static String[] getNetworkScan() throws IOException
	{
		evaluateNetworkInterface();
		System.out.println(networkInterface);
		Process scanner = Runtime.getRuntime().exec("nmcli dev wifi");
		BufferedReader input = new BufferedReader(new InputStreamReader(scanner.getInputStream()));
		String output;
		String[] array = new String[50];
		int i = 0;
		output = input.readLine();
		while (((output = input.readLine()) != null) && i < 50)
		{
			array[i] = output.substring(7, output.indexOf("Infra "));
			array[i] = array[i].replaceAll("\\s", "");
			i++;
		}
		
		for(i = 0; i < 50 && array[i] != null; i++)
			System.out.println(array[i]);
		
		
		return array;
	}
	
	private static String getSudoCommand(String password)
	{
		return "echo " + password + " | sudo -S ";
	}
	
	private static void evaluateNetworkInterface() throws IOException
	{
		Process scanner = Runtime.getRuntime().exec("iw dev");
		BufferedReader input = new BufferedReader(new InputStreamReader(scanner.getInputStream()));
		String output;
		while (((output = input.readLine()) != null))
		{
			if(output.contains("Interface"))
				networkInterface = output.substring(output.indexOf("I") + 10);
		}
	}
	
	public static void connectToNetwork(String key)
	{
		
	}
	
	

}
