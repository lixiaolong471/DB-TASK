package com.qianhtj.task.swing.dialog;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.qianhtj.task.utils.SysFont;

public class ThreadDialog extends JDialog {

	private static final long serialVersionUID = -1415308105803738619L;
	private static final int WINDOW_WIDTH = 300;
	private static final int WINDOW_HEIGHT = 200;
	
	private static final int X_1 = 30;
	private static final int X_2 = 150;
	private static final int Y_1 = 30;
	private static final int Y_2 = 70;
	
	private static final int B_X_1 = 110;
	private static final int B_Y_1 = 120;
	
	
	
	private static final int LABLE_WIDTH = 130;
	private static final int LABLE_HEIGHT = 30;
	
	private static final int BUTTON_WIDTH = 80;
	private static final int BUTTON_HEIGHT = 30;
	
	private JDialog dialog;
	
	
	public ThreadDialog(JFrame parent) {
		dialog = this;
		Point location = new Point();
		location.setLocation((parent.getLocation().getX() + (parent.getWidth() - WINDOW_WIDTH) / 2),
				(parent.getLocationOnScreen().getY() + (parent.getHeight() - WINDOW_HEIGHT) / 2));
		this.setLocation(location);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(null);
		setResizable(false);
		setTitle("数据初始化");

		JLabel getLable = new JLabel("采集数据线程数：");
		getLable.setFont(SysFont.Infolab);
		getLable.setBounds(X_1, Y_1, LABLE_WIDTH, LABLE_HEIGHT);
		
		JTextField getText = new JTextField();
		getText.setFont(SysFont.Infolab);
		getText.setBounds(X_2, Y_1, LABLE_WIDTH, LABLE_HEIGHT);
		
		JLabel saveLable = new JLabel("存储数据线程数：");
		saveLable.setFont(SysFont.Infolab);
		saveLable.setBounds(X_1, Y_2, LABLE_WIDTH, LABLE_HEIGHT);
		
		JTextField saveText = new JTextField();
		saveText.setFont(SysFont.Infolab);
		saveText.setBounds(X_2, Y_2, LABLE_WIDTH, LABLE_HEIGHT);
		
		JButton claoseBt = new JButton("保存");
		claoseBt.setFont(SysFont.Infolab);
		claoseBt.setBounds(B_X_1, B_Y_1, BUTTON_WIDTH, BUTTON_HEIGHT);
		claoseBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		
		JButton saveBt = new JButton("保存");
		saveBt.setFont(SysFont.Infolab);
		saveBt.setBounds(B_X_1, B_Y_1, BUTTON_WIDTH, BUTTON_HEIGHT);
		

		
		
		this.add(getLable);
		this.add(getText);
		
		this.add(saveLable);
		this.add(saveText);

		
		this.add(saveBt);

		this.setModal(true);
	}

}
