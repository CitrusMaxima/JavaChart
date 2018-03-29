package com.javaChart;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Frame {

	private static JFrame frame = new JFrame("JavaChart");
	private static FileOperation fileOperation;
	private static JScrollPane scrollPane;	// 主图表
	private static JPanel mainButton;		// sin和cos按钮
	private static int chartData[];			// 数据集
	private static int dataTimes = 128;		// 经过sin和cos计算后数据集扩大的倍数
	
	// 初始化Frame框架
	public void initFrame() {
		int frameX = 500;
		int frameY = 300;
		int frameLength = 1000;
		int frameHeight = 500;
		
		// 设置Frame参数
		frame.setLayout(new BorderLayout());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBounds(frameX, frameY, frameLength, frameHeight);
	    
	    mainChart();		// 获取主图表项
	    frame.setVisible(true);
	}
	
	// 主图表项，包含sin和cos按钮以及原始图表
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

	// sin与cos按钮
	private static JPanel mainButton() {
		JPanel mainButtonPannel = new JPanel(new GridLayout(1, 2));
		
		// sin按钮，并添加监听事件
		JButton button = new JButton("Sin");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sinChart();		// 生成sin函数计算后图表
			}
		});
		mainButtonPannel.add(button);
		
		// cos按钮，并添加监听事件
		button = new JButton("Cos");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				cosChart();		// 生成cos函数计算后图表
			}
		});
		mainButtonPannel.add(button);
		
		return mainButtonPannel;
	}
	
	// 生成sin函数计算后图表
	private static void sinChart() {
		int sinChartData[] = new int[chartData.length];		// 储存sin函数计算后数据
		for (int i = 0; i < chartData.length; ++i) {
			sinChartData[i] = (int)(Math.sin(Math.toRadians(chartData[i]))*dataTimes);
		}
		// 生成图表
		JScrollPane sinScrollPane = new JScrollPane(new Chart(sinChartData).getScrollPane());
		frame.add(sinScrollPane, BorderLayout.CENTER);		// 添加到Frame中
		frame.revalidate();									// 刷新Frame
	}
	
	// 生成cos函数计算后图表
	private static void cosChart() {
		int cosChartData[] = new int[chartData.length];		// 储存cos函数计算后数据
		for (int i = 0; i < chartData.length; ++i) {
			cosChartData[i] = (int)(Math.cos(Math.toRadians(chartData[i]))*dataTimes);
		}
		// 生成图表
		JScrollPane cosScrollPane = new JScrollPane(new Chart(cosChartData).getScrollPane());
		frame.add(cosScrollPane, BorderLayout.SOUTH);		// 添加到Frame中
		frame.revalidate();									// 刷新Frame
	}

	// 将byte型数组转换为int型数组
	private static int[] byteArrayToIntArray(byte[] b) {  
	    int a[] = new int[b.length];
	    
	    for (int i = 0; i < b.length; i++)
	    	a[i] = b[i];
	    
	    return a;
	}

}
