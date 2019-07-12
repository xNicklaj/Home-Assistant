package org.gitproject.homeassistant.network;

import java.net.*;

public class WakeOnLan {

	private static String ipAddress;
    private static String macAddress;
    private static final int PORT = 9;  
    
    public static int sendPacket()
    {
    	try {
            byte[] macBytes = getMacBytes(macAddress);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }
            
            InetAddress address = InetAddress.getByName(ipAddress);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
            
            System.out.println("Pacchetto WOL inviato.");
            return 0;
        }
        catch (Exception e) {
            System.out.println("Failed to send Wake-on-LAN packet: " + e);
            return -1;
        }
    }
    
    private static byte[] getMacBytes(String macAddress) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macAddress.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Indirizzo MAC non valido.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cifre esadecimali non valide nell'indirizzo MAC.");
        }
        return bytes;
    }

	
    public static String getIpAddress() {
		return ipAddress;
	}

	public static void setIpAddress(String ipAddress) {
		WakeOnLan.ipAddress = ipAddress;
	}

	public static String getMacAddress() {
		return macAddress;
	}

	public static void setMacAddress(String macAddress) {
		WakeOnLan.macAddress = macAddress;
	}

	public static int getPort() {
		return PORT;
	}
}
