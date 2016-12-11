package com.qianhtj.task.swing.dialog;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.qianhtj.task.job.DayTask;
import com.qianhtj.task.job.Task;
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
    private JButton startBt;
    private JButton stopBt;
    private JLabel processText;
    private DayTask dayTask;
    private Timer timer;
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
		
		processText = new JLabel("未开始");
		processText.setFont(SysFont.Infolab);
		processText.setBounds(X_2, Y_2, LABLE_WIDTH, LABLE_HEIGHT);
		
		startBt = new JButton("启动任务");
		startBt.setFont(SysFont.Infolab);
		startBt.setBounds(B_X_1, B_Y_1, BUTTON_WIDTH, BUTTON_HEIGHT);
        startBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dayTask = new DayTask();
                dayTask.start();
                startBt.setEnabled(false);
                stopBt.setEnabled(true);
                processText.setText("运行中...");
                write();
            }
        });


		stopBt = new JButton("停止任务");
		stopBt.setFont(SysFont.Infolab);
		stopBt.setBounds(B_X_2, B_Y_1, BUTTON_WIDTH, BUTTON_HEIGHT);
        stopBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dayTask.shutdown();
                startBt.setEnabled(true);
                stopBt.setEnabled(false);
                processText.setText("未开始");
            }
        });
		
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

	private void write(){
        if(timer == null){
            timer = new Timer();
            timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    if(Task.process > 0L){
                        processText.setText(Task.processInfo);
                    }else{
                        processText.setText("运行中...");
                    }
                }
            },1000);
        }
    }

}
