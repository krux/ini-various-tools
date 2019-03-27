package com.sf.utils.readers;

import java.io.File;
import java.util.ArrayList;

import com.sf.utils.util.DTLog;
import com.sf.utils.util.ToolUtils;

public class Replacer_Main {

	public long startTime = 0;
	public long endTime = 0;

	public String folderPath = "";
	public String textToFind = "";
	public String textToReplace = "";
	
	
	public Replacer_Main() {
		
	}
	
	public void start() {
		
		
		startTime = System.currentTimeMillis();
		
		DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "Starting Tool with following values: ");
		DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "Folder: " + folderPath);
		DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "Text To Find: " + textToFind);
		DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "Text To Replace: " + textToReplace);
		
		ArrayList<Replacer_Thread> listOfThreads = new ArrayList<>();
		int countRunningOrDone = 0;
		int activelyRunning = 0;
		
		
		File folder = new File(folderPath);
		if(folder != null && folder.isDirectory()) {
			File[] files = folder.listFiles();
			
			boolean atLeastOneRunningThread = true;
			while(atLeastOneRunningThread) {
				
				
				int tempActivelyRunning = 0;
				for(int i=0;i<listOfThreads.size();i++) {
					Replacer_Thread tempThread = listOfThreads.get(i);
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
					Replacer_Thread aThread = new Replacer_Thread(aFile, textToFind, textToReplace);
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
			
		}
		
		
		endTime = System.currentTimeMillis();
		DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "Completed " + listOfThreads.size() + " Files");
		
		DTLog.Print(this.getClass().getSimpleName(), DTLog.info, "     Processing Time: " +  ToolUtils.getRuntime(endTime - startTime));
		
		
	}

	public static void main(String[] args) {
		
		if(args != null && args.length == 3) {
			String folder = args[0];
			if(folder == null || folder.trim().equals("")) {
				DTLog.Print("Replacer_Main", DTLog.error, "Folder not specified, please add the full file path as an argument to running the tool");
				System.exit(0);
			}
			
			String textToFind = args[1];
			if(textToFind == null || textToFind.trim().equals("")) {
				DTLog.Print("Replacer_Main", DTLog.error, "TextToFind not specified, please add in the 2nd argument a value you'd like to find and run the tool again");
				System.exit(0);
			}
			
//			if(textToFind.startsWith("\\")){
//				textToFind = textToFind.replaceAll("\\/", "\\");
//				DTLog.Print("Replacer_Main", DTLog.info, "Found a backslash in the texttofind, adding a second one for java compatibility");
//			}
			
			String textToReplace = args[2];
			if(textToReplace == null || textToReplace.trim().equals("")) {
				DTLog.Print("Replacer_Main", DTLog.error, "TextToReplace not specified, please add in the 3rd argument a value you'd like to replace with and run the tool again");
				System.exit(0);
			}
			
			Replacer_Main main = new Replacer_Main();
			main.folderPath = folder;
			main.textToFind = textToFind;
			main.textToReplace = textToReplace;
			main.start();
			
			
		}else {
			DTLog.Print("Replacer_Main", DTLog.error, "You did not supply the right number of arguments for the tool (3 needed), filedirectory absolute path, textTofind, textToReplace");
		}	
		
		

	}

}
