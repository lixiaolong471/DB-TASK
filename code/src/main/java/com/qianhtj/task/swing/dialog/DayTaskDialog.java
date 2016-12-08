package com.qianhtj.task.swing.dialog;

import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.qianhtj.task.utils.SysFont;

public class DayTaskDialog extends JDialog {
	private static final int WINDOW_WIDTH = 300;
	private static final int WINDOW_HEIGHT = 200;
	
	private static final int X_1 = 50;
	private static final int X_2 = 150;
	private static final int Y_1 = 30;
	private static final int Y_2 = 70;
	
	private static final int B_X_1 = 60;
	private static final int B_X_2 = 160;
	private static final int B_Y_1 = 110;
	
	
	
	private static final int LABLE_WIDTH = 100;
	private static final int LABLE_HEIGHT = 30;
	
	private static final int BUTTON_WIDTH = 80;
	private static final int BUTTON_HEIGHT = 30;
	
	private JDialog dialog;
	private static final long serialVersionUID = 1L;
	
	public DayTaskDialog(JFrame parent) {
		dialog = this;
		Point location = new Point();
		location.setLocation((parent.getLocation().getX() + (parent.getWidth() - WINDOW_WIDTH) / 2),
				(parent.getLocationOnScreen().getY() + (parent.getHeight() - WINDOW_HEIGHT) / 2));
		this.setLocation(location);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(null);
		setResizable(false);
		setTitle("数据初始化");

		JLabel statusLable = new JLabel("任务状态：");
		statusLable.setFont(SysFont.Infolab);
		statusLable.setBounds(X_1, Y_1, LABLE_WIDTH, LABLE_HEIGHT);
		
		JLabel statusText = new JLabel("停止运行");
		statusText.setFont(SysFont.Infolab);
		statusText.setBounds(X_2, Y_1, LABLE_WIDTH, LABLE_HEIGHT);
		
		JLabel processLable = new JLabel("进度：");
		processLable.setFont(SysFont.Infolab);
		processLable.setBounds(X_1, Y_2, LABLE_WIDTH, LABLE_HEIGHT);
		
		JLabel processText = new JLabel("20%");
		processText.setFont(SysFont.Infolab);
		processText.setBounds(X_2, Y_2, LABLE_WIDTH, LABLE_HEIGHT);
		
		JButton startBt = new JButton("启动任务");
		startBt.setFont(SysFont.Infolab);
		startBt.setBounds(B_X_1, B_Y_1, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		JButton stopBt = new JButton("停止任务");
		stopBt.setFont(SysFont.Infolab);
		stopBt.setBounds(B_X_2, B_Y_1, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		
		this.add(statusLable);
		this.add(statusText);
		
		this.add(processLable);
		this.add(processText);
		
		this.add(statusLable);
		this.add(statusText);
		
		this.add(startBt);
		this.add(stopBt);
		this.setModal(true);
	}

}
