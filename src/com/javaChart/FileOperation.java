package com.javaChart;

import javax.swing.JFileChooser;
import java.io.*;

public class FileOperation {
	
	private File file = null;
	private int bufSize = 0;
	private byte buffer[] = null;
	
	// ������ʱ�Զ����ļ�
	public FileOperation() {
		openFile();
		if (file != null)
			buffer = readFile();
		else {
			System.exit(0);
		}
	}
	
	// ���ļ�
	private void openFile() {
		JFileChooser jfc=new JFileChooser(".");
	    int returnVal = jfc.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
	        // ��ô򿪵��ļ�
	        file = jfc.getSelectedFile();
        }
	}
	
	// ��ȡ�ļ�
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

	// ��ȡbuffer��С
	public int getBufSize() {
		return bufSize;
	}

	// ��ȡbuffer
	public byte[] getBuffer() {
		return buffer;
	}
}
