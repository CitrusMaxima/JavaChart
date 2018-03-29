package com.javaChart;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Chart extends JLabel {
	
	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrollPane = new JScrollPane(this);
	private int step = 5;					// 步长，用来压缩和扩展显示X轴数据
	private double yTimes = 1.0;			// Y轴扩展倍数
	private int chartData[] = null;			// 数据点
	private int yTranslation = 128;			// Y轴平移幅度
	private int labelHeight = 300;			// JLabel控件高度
	private Color lineColor = Color.RED;	// 折线颜色
	
	// 自动调用，初始化JScrollPane框架并添加监听事件
	public Chart(int chartData[]) {
		
		// 判断文件是否为空，若为空则弹出错误窗口并退出
		if (chartData==null || chartData.length==0) {
			JOptionPane.showMessageDialog(null, "文件为空！", "错误", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		this.chartData = chartData;
		initScrollPane();			// 初始化JScrollPane框架
		
		// 添加鼠标滚轮监听事件，调整步长
		this.addMouseWheelListener(new MouseWheelListener() {
			
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (-3 == e.getUnitsToScroll()) {
					step++;
				} else if (3 == e.getUnitsToScroll()) {
					step--;
					if (step < 1)
						step = 1;
				}
				initScrollPane();
			}
		});
		
		//添加鼠标按键监听事件，调整步长和Y轴扩展倍数
		this.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				// 点击鼠标中键时，恢复初始状态
				if(e.getButton()==MouseEvent.BUTTON2) {
					reset();
				}
				// 点击鼠标左键时，扩展Y轴
				if(e.getButton()==MouseEvent.BUTTON1) {
					yTimes = yTimes * 2;
					yTranslation *= 2;
				}
				// 点击鼠标右键时，压缩Y轴
				if(e.getButton()==MouseEvent.BUTTON3) {
					yTimes = yTimes / 2;
					yTranslation /= 2;
				}
				initScrollPane();
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	// 恢复初始状态
	private void reset() {
		step = 5;
		yTimes = 1.0;
		yTranslation = 128;
	}
	
	// 初始化滚动条框架
	private void initScrollPane() {
		int scrollPaneX = 10;			// 图表位置X坐标
		int scrollPaneY = 10;			// 图表位置Y坐标
		int scrollPaneLength = 500;		// 图表宽度
		int scrollPaneHeight = 300;		// 图表高度
		
		// 设置Label控件长度为数据集长度与步长的乘积
	    this.setPreferredSize(new Dimension(chartData.length*step, (int)(labelHeight*yTimes)));
	    scrollPane.setBounds(scrollPaneX, scrollPaneY, scrollPaneLength, scrollPaneHeight);
	    scrollPane.revalidate();		// 刷新显示图表
	}
	
	// 自动调用，绘制函数
	public void paint(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(lineColor);
		
		int i = 0;
		int j = 0;
		// 循环绘制折线
		for (i=1; i<chartData.length; ++i) {
			g2.drawLine(j, (int)(chartData[i-1]*yTimes)+yTranslation, j+step, (int)(chartData[i]*yTimes)+yTranslation);
        	j += step;
		}
	}
	
	// 获取整个JScrollPane框架
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}
