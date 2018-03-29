package com.javaChart;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Chart extends JLabel {
	
	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrollPane = new JScrollPane(this);
	private int step = 5;					// ����������ѹ������չ��ʾX������
	private double yTimes = 1.0;			// Y����չ����
	private int chartData[] = null;			// ���ݵ�
	private int yTranslation = 128;			// Y��ƽ�Ʒ���
	private int labelHeight = 300;			// JLabel�ؼ��߶�
	private Color lineColor = Color.RED;	// ������ɫ
	
	// �Զ����ã���ʼ��JScrollPane��ܲ���Ӽ����¼�
	public Chart(int chartData[]) {
		
		// �ж��ļ��Ƿ�Ϊ�գ���Ϊ���򵯳����󴰿ڲ��˳�
		if (chartData==null || chartData.length==0) {
			JOptionPane.showMessageDialog(null, "�ļ�Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		this.chartData = chartData;
		initScrollPane();			// ��ʼ��JScrollPane���
		
		// ��������ּ����¼�����������
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
		
		//�����갴�������¼�������������Y����չ����
		this.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				// �������м�ʱ���ָ���ʼ״̬
				if(e.getButton()==MouseEvent.BUTTON2) {
					reset();
				}
				// ���������ʱ����չY��
				if(e.getButton()==MouseEvent.BUTTON1) {
					yTimes = yTimes * 2;
					yTranslation *= 2;
				}
				// �������Ҽ�ʱ��ѹ��Y��
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
	
	// �ָ���ʼ״̬
	private void reset() {
		step = 5;
		yTimes = 1.0;
		yTranslation = 128;
	}
	
	// ��ʼ�����������
	private void initScrollPane() {
		int scrollPaneX = 10;			// ͼ��λ��X����
		int scrollPaneY = 10;			// ͼ��λ��Y����
		int scrollPaneLength = 500;		// ͼ����
		int scrollPaneHeight = 300;		// ͼ��߶�
		
		// ����Label�ؼ�����Ϊ���ݼ������벽���ĳ˻�
	    this.setPreferredSize(new Dimension(chartData.length*step, (int)(labelHeight*yTimes)));
	    scrollPane.setBounds(scrollPaneX, scrollPaneY, scrollPaneLength, scrollPaneHeight);
	    scrollPane.revalidate();		// ˢ����ʾͼ��
	}
	
	// �Զ����ã����ƺ���
	public void paint(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(lineColor);
		
		int i = 0;
		int j = 0;
		// ѭ����������
		for (i=1; i<chartData.length; ++i) {
			g2.drawLine(j, (int)(chartData[i-1]*yTimes)+yTranslation, j+step, (int)(chartData[i]*yTimes)+yTranslation);
        	j += step;
		}
	}
	
	// ��ȡ����JScrollPane���
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}
