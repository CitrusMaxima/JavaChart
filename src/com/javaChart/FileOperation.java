package com.javaChart;

import javax.swing.JFileChooser;
import java.io.*;

public class FileOperation {
	
	private File file = null;
	private int bufSize = 0;
	private byte buffer[] = null;
	
	// 调用类时自动打开文件
	public FileOperation() {
		openFile();
		if (file != null)
			buffer = readFile();
		else {
			System.exit(0);
		}
	}
	
	// 打开文件
	private void openFile() {
		JFileChooser jfc=new JFileChooser(".");
	    int returnVal = jfc.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
	        // 获得打开的文件
	        file = jfc.getSelectedFile();
        }
	}
	
	// 读取文件
	private byte[] readFile() {
		
		byte buffer[] = null;
		
		try {
			InputStream in = new FileInputStream(file);
			
			bufSize = in.available();
			buffer = new byte[bufSize];
			
			in.read(buffer);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return buffer;
	}

	// 获取buffer大小
	public int getBufSize() {
		return bufSize;
	}

	// 获取buffer
	public byte[] getBuffer() {
		return buffer;
	}
}
