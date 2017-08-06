package iss.nus.edu.sg.SouvinierStore.system; 

/**
 * Name					Date
 * ---------------------------------
 * Xing Zibo		13.03.2014
 * 
 * @author Xing Zibo 
 * Since 15.03.2014
 * 
 * Modified By Xing Zibo*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtility
{
    /**
     * return
     * file dataList that splited by '\n', return null if failed
     */
    public static ArrayList<String> readFile(File file) {
    	if(file == null || !file.isFile()){
    		// invalid param
    		System.out.println("error - FileUtility readFile() invalid param");
    		return null;
    	}
    	ArrayList<String> dataList = new ArrayList<String>();
    	try{
    		Scanner scanner = new Scanner(file);
            while ( scanner.hasNextLine() ) {
                String line = scanner.nextLine();
                dataList.add(line);
            }
            scanner.close();
        } catch ( FileNotFoundException e ) {
        	System.out.println("error - FileUtility readFile() " + e.toString());
        	return null;
        }
		return dataList;
    }
    
    /**
     * return
     * true - success
     * false - failed
     */
    public static boolean writeFile(File file, String data, boolean isAppend) {
    	if(file == null || data == null){
    		// invalid param
    		System.out.print("error - FileUtility writeFile() invalid param");
    		return false;
    	}
    	if(!file.exists()){
    		try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.print("error - FileUtility writeFile() " + e.toString());
	    		return false;
			}
    	}
    	
		try {
			FileWriter writer = new FileWriter(file, isAppend);
			writer.write(data);
	        writer.flush();
	        writer.close();
		} catch (IOException e) {
			System.out.println("error - FileUtility writeFile() " + e.toString());
			return false;
		} 
        return true;
    }
}
