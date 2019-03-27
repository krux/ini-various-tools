package com.sf.utils.util;

public class ToolUtils {


	

//	public static HttpPost addHeaderInfo(HttpPost post, String criteoID){
//		
//		post.addHeader("Accept", "image/png,image/*;q=0.8,*/*;q=0.5");
//		post.addHeader("Accept-Encoding", "gzip, deflate");
//		post.addHeader("Accept-Language", "en-US,en;q=0.5");
//		post.addHeader("Cache-Control", "no-cache");
//		post.addHeader("Cookie", "uid=" + criteoID + ";");
//		post.addHeader("Host", "dis.us.criteo.com");
//		post.addHeader("Referer", "https://usts.criteo.com");
//		post.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:29.0) Gecko/20100101 Firefox/29.0");
//		
//		return post;
//
//	}
//	
//	public static HttpPost addHeaderInfo(HttpPost post){
//		
//		post.addHeader("Accept", "image/png,image/*;q=0.8,*/*;q=0.5");
//		post.addHeader("Accept-Encoding", "gzip, deflate");
//		post.addHeader("Accept-Language", "en-US,en;q=0.5");
//		post.addHeader("Cache-Control", "no-cache");
//		post.addHeader("Host", "dis.us.criteo.com");
//		post.addHeader("Referer", "https://usts.criteo.com");
//		post.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:29.0) Gecko/20100101 Firefox/29.0");
//		
//		return post;
//
//	}

	public static String getRuntime(long ms){
		try {
			int SECOND = 1000;
			int MINUTE = 60 * SECOND;
			int HOUR = 60 * MINUTE;
			int DAY = 24 * HOUR;

			StringBuffer text = new StringBuffer("");
			if (ms > DAY) {
			  text.append(ms / DAY).append(" days ");
			  ms %= DAY;
			}
			if (ms > HOUR) {
			  text.append(ms / HOUR).append(" hours ");
			  ms %= HOUR;
			}
			if (ms > MINUTE) {
			  text.append(ms / MINUTE).append(" minutes ");
			  ms %= MINUTE;
			}
			if (ms > SECOND) {
			  text.append(ms / SECOND).append(" seconds ");
			  ms %= SECOND;
			}
			text.append(ms + " ms");
			
			return text.toString();
		} catch (Exception e) {
			return (ms + " ms");
		}
	}


}
