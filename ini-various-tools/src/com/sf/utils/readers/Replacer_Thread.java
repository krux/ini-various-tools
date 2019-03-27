package com.sf.utils.readers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.sf.utils.util.DTLog;

public class Replacer_Thread extends Thread implements Runnable {

	
	File fileToRead = null;
	File fileToWrite = null;
	String textToFind = "";
	String replacementText = "";
	
	PrintWriter writer = null;
	
	
	public Replacer_Thread() {
		
	}
	
	public Replacer_Thread(File readFile, String textToFind, String replacementText) {
		
		// This is the file that was picked from the folder (which the user entered)
		this.fileToRead = readFile;
		
		// let's create a new file with the updated contents
		String tempWriteFileName = fileToRead.getAbsolutePath();
		String tempWriteFileNameBase = readFile.getName();
		
		String writeFileNameStr = tempWriteFileName.replace(tempWriteFileNameBase, "updated_" + tempWriteFileNameBase);
		
		this.fileToWrite = new File(writeFileNameStr);

		this.textToFind = textToFind;
		this.replacementText = replacementText;
		
	}

	@Override
	public void run() {
		

		try {
			
			DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "Starting File: " + fileToRead.getName());

			FileInputStream fstream = new FileInputStream(fileToRead);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			writer = new PrintWriter(fileToWrite, "UTF-8");
			
			int totalCount = 0;
			int updatedCount = 0;
	
			String fullLine;
			while ((fullLine = br.readLine()) != null) {
				
				totalCount++;
//				
//				if(fullLine.contains(textToFind)) {
//					System.out.println("stop here");
//				}
//				
				String updatedLine = fullLine.replaceAll(this.textToFind, this.replacementText);
				if(!updatedLine.equals(fullLine)) {
					updatedCount++;
				}
			
				writeToFile(updatedLine);
				
				
				
			} // ENd while()
			
			

			in.close();
			
			writer.close();
			
			DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "Completed Reading File: " + fileToRead.getName() + " , Replaced " + updatedCount + " , of total " + totalCount + " Lines");


		} catch (Exception e) {
			DTLog.Print(this.getClass().getSimpleName(), DTLog.error, "Error Reading File: " + e.getMessage());

		}
		
	}
	
	private void writeToFile(String updatedLine) {
		
		try {
			this.writer.println(updatedLine);
			
		}catch(Exception e) {
			DTLog.Print(this.getClass().getSimpleName(), DTLog.error, "Error writing file: e = " + e.getMessage());
		}
		
	}

}
