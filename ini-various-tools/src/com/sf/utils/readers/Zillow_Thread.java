package com.sf.utils.readers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.sf.utils.util.DTLog;

public class Zillow_Thread extends Thread implements Runnable {

	
	File fileToRead = null;
	File fileToWrite_Page = null;
	File fileToWrite_User = null;
	
	File fileToWrite_MobilePage = null;
	File fileToWrite_MobileUser = null;
	
	PrintWriter pageWriter = null;
	PrintWriter userWriter = null;
	
	PrintWriter mobilePageWriter = null;
	PrintWriter mobileUserWriter = null;
	
	
	public Zillow_Thread() {
		
	}
	
	public Zillow_Thread(File readFile) {
		
		this.fileToRead = readFile;
		String fileName = readFile.getAbsolutePath();
		String baseName = readFile.getName();
		
		String fileNamePage = fileName.replace(baseName, "page_" + baseName);
		String fileNameUser = fileName.replace(baseName, "user_" + baseName);
		
		String fileNameMobilePage = fileName.replace(baseName, "mobile_page_" + baseName );
		String fileNameMobileUser = fileName.replace(baseName, "mobile_user_" + baseName);
		
		this.fileToWrite_Page = new File(fileNamePage);
		this.fileToWrite_User = new File(fileNameUser);
		
		this.fileToWrite_MobilePage = new File(fileNameMobilePage);
		this.fileToWrite_MobileUser = new File(fileNameMobileUser);
		
	}

	@Override
	public void run() {
		

		try {
			
			DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "Starting File: " + fileToRead.getName());

			FileInputStream fstream = new FileInputStream(fileToRead);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			pageWriter = new PrintWriter(fileToWrite_Page, "UTF-8");
			userWriter = new PrintWriter(fileToWrite_User, "UTF-8");
			mobilePageWriter = new PrintWriter(fileToWrite_MobilePage, "UTF-8");
			mobileUserWriter = new PrintWriter(fileToWrite_MobileUser, "UTF-8");
			
			int count = 0;
	
			
			String fullLine;
			while ((fullLine = br.readLine()) != null) {
				
				String pageAttrLine = "";
				String userAttrLine = "";
				
				count++;

				if(fullLine.startsWith("\"")) {
					fullLine = fullLine.substring(1);
				}
				
				if(fullLine.endsWith("\"")) {
					fullLine = fullLine.substring(0,fullLine.length()-1);
				}
				
				try {
				String[] values = fullLine.split("\\^");
				
				String maid = values[0];
				String ts = values[1];
				String ip = values[2];
				String fpid = values[3];
				String pageAttrs = values[4];
				String userAttrs = values[5];
				
				if(maid == null || maid.trim().equals("")) {
					maid = "";
				}
				if(fpid == null || fpid.trim().equals("")) {
					fpid = "";
				}
				fpid = fpid.replace("_kx_fpid:", "");
				
				if(pageAttrs == null) {
					pageAttrs = "";
				}
				if(userAttrs == null) {
					userAttrs = "";
				}
				if(pageAttrs.startsWith("_kx_page:")) {
					pageAttrs = pageAttrs.replace("_kx_page:", "");
				}
				if(userAttrs.startsWith("_kx_user:")) {
					userAttrs = userAttrs.replace("_kx_user:", "");
				}
				
				StringTokenizer pa = new StringTokenizer(pageAttrs, ";");
				ArrayList<String> arrayOfPageAttrs = new ArrayList<>();
				while(pa.hasMoreTokens()) {
					String paAttr = pa.nextToken();
					paAttr = paAttr.replace("=", ":");
					arrayOfPageAttrs.add(paAttr);
				}
				
				String pageAttrStr = "";
				for(int i=0;i<arrayOfPageAttrs.size();i++) {
					pageAttrStr = pageAttrStr + ";" + arrayOfPageAttrs.get(i);
				}
				if(pageAttrStr.startsWith(";")) {
					pageAttrStr = pageAttrStr.substring(1);
				}
				
				StringTokenizer ua = new StringTokenizer(userAttrs, ";");
				ArrayList<String> arrayOfUserAttrs = new ArrayList<>();
				while(ua.hasMoreTokens()) {
					String uaAttr = ua.nextToken();
					uaAttr = uaAttr.replace("=", ":");
					arrayOfUserAttrs.add(uaAttr);
				}
				
				String userAttrStr = "";
				for(int i=0;i<arrayOfUserAttrs.size();i++) {
					userAttrStr = userAttrStr + ";" + arrayOfUserAttrs.get(i);
				}
				if(userAttrStr.startsWith(";")) {
					userAttrStr = userAttrStr.substring(1);
				}
				
				boolean wasMaid = false;
				if(!maid.trim().equals("")) {
					// this is a maid
					pageAttrLine = maid + "^" + pageAttrStr;
					userAttrLine = maid + "^" + userAttrStr;
					writeToFile(mobilePageWriter, pageAttrLine);
					writeToFile(mobileUserWriter, userAttrLine);
					wasMaid = true;
				}
				
				if(!fpid.trim().equals("") && !wasMaid) {
					
					pageAttrLine = fpid + "^" + pageAttrStr;
					userAttrLine = fpid + "^" + userAttrStr;
					writeToFile(pageWriter, pageAttrLine);
					writeToFile(userWriter, userAttrLine);
					
				}

				}catch(Exception e) {
					DTLog.Print(this.getClass().getSimpleName(), DTLog.error, "Error Parsing Line " + e.getMessage());
				}
				
				
				
				
			} // ENd while()
			
			

			in.close();
			
			pageWriter.close();
			userWriter.close();
			mobilePageWriter.close();
			mobileUserWriter.close();
			
			DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "Completed Reading File, processed: " + count + " lines");


		} catch (Exception e) {
			DTLog.Print(this.getClass().getSimpleName(), DTLog.error, "Error Reading File: " + e.getMessage());

		}
		
	}
	
	private void writeToFile(PrintWriter writer, String line) {
		
		try {
			writer.println(line);
			
		}catch(Exception e) {
			DTLog.Print(this.getClass().getSimpleName(), DTLog.error, "Error writing file: e = " + e.getMessage());
		}
		
	}
	
	

}
