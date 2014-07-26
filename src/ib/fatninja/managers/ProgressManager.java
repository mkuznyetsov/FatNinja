package ib.fatninja.managers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ProgressManager {

	private static ProgressManager instance;
	
	public static ProgressManager Instance(){
		if(instance == null)
			instance = new ProgressManager();
		return instance;
	}
	
	public void Save(){
		try {
			FileOutputStream fos = new FileOutputStream("progress.dat");
		//	fos.write(buffer)
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void Load(){
		
		
	}

}
