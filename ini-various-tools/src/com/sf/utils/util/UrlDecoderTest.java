package com.sf.utils.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlDecoderTest {

	public UrlDecoderTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
//		String url = "https://servedby.flashtalking.com/map/?key=KZ32zLQRVRxV8DgS&url=https://beacon.krxd.net/usermatch.gif?partner=flashtalking&partner_uid=[%FT_GUID%]";
		String[] urlsToTest = new String[] {
				"https://pixel.tapad.com/idsync/ex/receive?partner_id=1969&partner_device_id=insert_kuid&partner_url=https%3A%2F%2Fbeacon.krxd.net%2Fusermatch.gif%3Fpartner%3Dtapad%26partner_uid%3D%24%7BTA_DEVICE_ID%7D",
				"https://p.adsymptotic.com/d/px/?_pid=15571&_psign=fbab9de3673937603cddeab03f31b899&_puuid=insert_kuid&_redirect=https%3A%2F%2Fbeacon.krxd.net%2Fusermatch.gif%3Fpartner%3Ddrawbridge%26partner_uid%3D${UUID}"
				};
		
		for(int i=0;i<urlsToTest.length;i++) {
			
			String url = urlsToTest[i];
			String decodedUrl = "";
			
			System.out.println("Decoding URL: " + url);
			
			try {
				
				decodedUrl = URLDecoder.decode(url, "utf-8");
				
			}catch(UnsupportedEncodingException usee) {
				System.err.println("Error Decoding URL: " + url);
				System.err.println("error:" + usee.toString());
			}

			System.out.println("Result: " + decodedUrl);
			System.out.println("");
			
		}
		

	}

}
