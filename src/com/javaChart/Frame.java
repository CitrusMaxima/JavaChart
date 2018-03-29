package com.javaChart;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Frame {

	private static JFrame frame = new JFrame("JavaChart");
	private static FileOperation fileOperation;
	private static JScrollPane scrollPane;	// ��ͼ��
	private static JPanel mainButton;		// sin��cos��ť
	private static int chartData[];			// ���ݼ�
	private static int dataTimes = 128;		// ����sin��cos��������ݼ�����ı���
	
	// ��ʼ��Frame���
	public void initFrame() {
		int frameX = 500;
		int frameY = 300;
		int frameLength = 1000;
		int frameHeight = 500;
		
		// ����Frame����
		frame.setLayout(new BorderLayout());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBounds(frameX, frameY, frameLength, frameHeight);
	    
	    mainChart();		// ��ȡ��ͼ����
	    frame.setVisible(true);
	}
	
	// ��ͼ�������sin��cos��ť�Լ�ԭʼͼ��
	private static void mainChart() {
		fileOperation = new FileOperation();
	    chartData = byteArrayToIntArray(fileOperation.getBuffer());
	    
	    Chart chart = new Chart(chartData);
	    scrollPane = new JScrollPane(chart.getScrollPane());
	    
		mainButton = mainButton();
		JPanel mainChart = new JPanel(new BorderLayout());
		
		mainChart.add(mainButton, BorderLayout.NORTH);
		mainChart.add(scrollPane, BorderLayout.CENTER);
		
		frame.add(mainChart, BorderLayout.NORTH);
	}

	// sin��cos��ť
	private static JPanel mainButton() {
		JPanel mainButtonPannel = new JPanel(new GridLayout(1, 2));
		
		// sin��ť������Ӽ����¼�
		JButton button = new JButton("Sin");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sinChart();		// ����sin���������ͼ��
			}
		});
		mainButtonPannel.add(button);
		
		// cos��ť������Ӽ����¼�
		button = new JButton("Cos");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				cosChart();		// ����cos���������ͼ��
			}
		});
		mainButtonPannel.add(button);
		
		return mainButtonPannel;
	}
	
	// ����sin���������ͼ��
	private static void sinChart() {
		int sinChartData[] = new int[chartData.length];		// ����sin�������������
		for (int i = 0; i < chartData.length; ++i) {
			sinChartData[i] = (int)(Math.sin(Math.toRadians(chartData[i]))*dataTimes);
		}
		// ����ͼ��
		JScrollPane sinScrollPane = new JScrollPane(new Chart(sinChartData).getScrollPane());
		frame.add(sinScrollPane, BorderLayout.CENTER);		// ��ӵ�Frame��
		frame.revalidate();									// ˢ��Frame
	}
	
	// ����cos���������ͼ��
	private static void cosChart() {
		int cosChartData[] = new int[chartData.length];		// ����cos�������������
		for (int i = 0; i < chartData.length; ++i) {
			cosChartData[i] = (int)(Math.cos(Math.toRadians(chartData[i]))*dataTimes);
		}
		// ����ͼ��
		JScrollPane cosScrollPane = new JScrollPane(new Chart(cosChartData).getScrollPane());
		frame.add(cosScrollPane, BorderLayout.SOUTH);		// ��ӵ�Frame��
		frame.revalidate();									// ˢ��Frame
	}

	// ��byte������ת��Ϊint������
	private static int[] byteArrayToIntArray(byte[] b) {  
	    int a[] = new int[b.length];
	    
	    for (int i = 0; i < b.length; i++)
	    	a[i] = b[i];
	    
	    return a;
	}

}
