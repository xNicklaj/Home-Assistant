package it.edu.majoranapa.network;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class DHCPManager {
	private static String deviceIP;

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

}