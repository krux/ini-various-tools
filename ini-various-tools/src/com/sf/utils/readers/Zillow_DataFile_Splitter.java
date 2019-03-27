package com.sf.utils.readers;

import java.io.File;
import java.util.ArrayList;

import com.sf.utils.util.DTLog;
import com.sf.utils.util.ToolUtils;

public class Zillow_DataFile_Splitter {

	public long startTime = 0;
	public long endTime = 0;

	public String folderPath = "";
	
	
	public Zillow_DataFile_Splitter() {
		
	}
	
	public void start() {
		
		
		startTime = System.currentTimeMillis();
		
		DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "Starting app...");
		
		ArrayList<Zillow_Thread> listOfThreads = new ArrayList<>();
		int countRunningOrDone = 0;
		
		
		File folder = new File(folderPath);
		if(folder != null && folder.isDirectory()) {
			File[] files = folder.listFiles();
			
			boolean atLeastOneRunningThread = true;
			while(atLeastOneRunningThread) {
				
				
				int tempActivelyRunning = 0;
				for(int i=0;i<listOfThreads.size();i++) {
					Zillow_Thread tempThread = listOfThreads.get(i);
					if(tempThread.isAlive()) {
						tempActivelyRunning++;
					}
				}
				
				if(tempActivelyRunning == 0 && countRunningOrDone == files.length) {
					atLeastOneRunningThread = false;
				}
				
				if(tempActivelyRunning <5 && (countRunningOrDone < files.length)) {
				
					File aFile = files[countRunningOrDone];
					countRunningOrDone++;
					Zillow_Thread aThread = new Zillow_Thread(aFile);
					listOfThreads.add(aThread);
					aThread.start();
				}
				
				
				try {
					// thread to sleep for 1000 milliseconds plus 500 nanoseconds
					Thread.sleep(1000);
				} catch (Exception e) {
					System.out.println(e);
				}
				
				
			}
			
//			while((countRunningOrDone < files.length) && (activelyRunning < 5)){
//			
//				File aFile = files[countRunningOrDone];
//				String fileName = aFile.getAbsolutePath();
//				countRunningOrDone++;
//				Zillow_Thread aThread = new Zillow_Thread(aFile);
//				listOfThreads.add(aThread);
//				aThread.start();
//				activelyRunning++;
//
//				
//				
//				int tempActivelyRunning = 0;
//				
//				boolean atleastonethreadrunning = false;
//				for(int i=0;i<listOfThreads.size();i++) {
//					Zillow_Thread tempThread = listOfThreads.get(i);
//					if(tempThread.isAlive()) {
//						atleastonethreadrunning = true;
//						tempActivelyRunning++;
//					}
//				}
//			
//				activelyRunning = tempActivelyRunning;
//				
//				
//				
//
//					
//					try {
//						// thread to sleep for 1000 milliseconds plus 500 nanoseconds
//						Thread.sleep(1000);
//					} catch (Exception e) {
//						System.out.println(e);
//					}
//
//				
//			}
			
		}
		
		
		endTime = System.currentTimeMillis();
		DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "Completed " + listOfThreads.size() + " Files");
		
		DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "     Processing Time: " +  ToolUtils.getRuntime(endTime - startTime));
		
		
	}

	public static void main(String[] args) {
		
		if(args != null && args.length == 1) {
			String folder = args[0];
			if(folder == null || folder.trim().equals("")) {
				DTLog.Print("Ziff", DTLog.error, "Folder not specified, please add the full file path as an argument to running the tool");
				System.exit(0);
			}
			
		
			Zillow_DataFile_Splitter ziff = new Zillow_DataFile_Splitter();
			ziff.folderPath = folder;
			ziff.start();
			
			
		}else {
			DTLog.Print("ImportFileChecker", DTLog.error, "File not specified, please add the full file path as an argument to running the file");
		}	
		
		

	}

}
