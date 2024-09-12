package com.eazybytes.loans.helper;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkHelper {

    public static String getIpAddress() {
        try {
            String hostname = InetAddress.getLocalHost().getHostAddress();
            InetAddress ipAddress = InetAddress.getByName(hostname);
            return ipAddress.getHostAddress();
        } catch (UnknownHostException ex) {
            return "No IP address retrieved";
        }
    }
}
