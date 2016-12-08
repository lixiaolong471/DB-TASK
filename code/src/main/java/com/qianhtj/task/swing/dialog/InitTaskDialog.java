package com.qianhtj.task.swing.dialog;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.DateFormatter;

import org.jdesktop.swingx.JXDatePicker;

import com.qianhtj.task.utils.SysFont;

public class InitTaskDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final int LABLE_WIDTH = 50;
	private static final int LABLE_HEIGHT = 30;

	private static final int TEXT_WIDTH = 100;
	private static final int TEXT_HEIGHT = LABLE_HEIGHT;

	private static final int BUTTON_WIDTH = 50;
	private static final int BUTTON_HEIGHT = LABLE_HEIGHT;
	
	public DateFormatter df;

	private static final int _1_X = 20;
	private static final int _2_X = 80;
	private static final int _3_X = 190;
	private static final int _4_X = 300;
	private static final int _5_X = 360;
	private static final int _6_X = 420;

	private static final int _1_Y = 20;
	private static final int _2_Y = 60;
	private static final int _3_Y = 100;
	
	private static final int _CLOSE_X = 210;
	
	private static final int _CLOSE_Y = 200;

	private static final int CLOSE_WIDTH = 100;
	private static final int CLOSE_HEIGHT = BUTTON_HEIGHT;
	
	private static final int WIDTH = 520;
	private static final int HEIGHT = 300;
	
	private JDialog dialog;

	public InitTaskDialog(JFrame parent) {
		df = new DateFormatter();
		DateFormat format = new SimpleDateFormat("YYYY-MM-DD");
		df.setFormat(format );
		dialog = this;
		Point location = new Point();
		location.setLocation((parent.getLocation().getX() + (parent.getWidth() - WIDTH) / 2),
				(parent.getLocationOnScreen().getY() + (parent.getHeight() - HEIGHT) / 2));
		this.setLocation(location);
		setSize(WIDTH, HEIGHT);
		setLayout(null);
		setResizable(false);
		setTitle("数据初始化");

		adCp();
		adGs();
		adHq();
		
		JButton closeBt = new JButton("关闭");
		closeBt.setFont(SysFont.Infolab);
		closeBt.setBounds(_CLOSE_X, _CLOSE_Y, CLOSE_WIDTH, CLOSE_HEIGHT);
		closeBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		
		this.add(closeBt);
		
		this.setModal(true);

	}
	
	private void adCp(){
		JLabel label = new JLabel("产品:");
		label.setFont(SysFont.Infolab);
		label.setBounds(_1_X, _1_Y, LABLE_WIDTH, LABLE_HEIGHT);

		JXDatePicker startDateText = new JXDatePicker();
		startDateText.setFont(SysFont.Infolab);
		startDateText.setBounds(_2_X, _1_Y, TEXT_WIDTH, TEXT_HEIGHT);
		startDateText.setFormats("yyyy-MM-dd");
		
		JXDatePicker endDateText = new JXDatePicker();
		endDateText.setFont(SysFont.Infolab);
		endDateText.setBounds(_3_X, _1_Y, TEXT_WIDTH, TEXT_HEIGHT);
		endDateText.setFormats("yyyy-MM-dd");
		
		JButton startBt = new JButton("启动");
		startBt.setFont(SysFont.Infolab);
		startBt.setBounds(_4_X, _1_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

		JButton stopBt = new JButton("停止");
		stopBt.setFont(SysFont.Infolab);
		stopBt.setBounds(_5_X, _1_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		stopBt.setEnabled(false);
		
		JButton pauseBt = new JButton("暂停");
		pauseBt.setFont(SysFont.Infolab);
		pauseBt.setBounds(_6_X, _1_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		pauseBt.setEnabled(false);
		
		this.add(label);
		this.add(startDateText);
		this.add(endDateText);
		this.add(startBt);
		this.add(stopBt);
		this.add(pauseBt);
	}
	
	private void adGs(){
		JLabel label = new JLabel("产品:");
		label.setFont(SysFont.Infolab);
		label.setBounds(_1_X, _2_Y, LABLE_WIDTH, LABLE_HEIGHT);

		JXDatePicker startDateText = new JXDatePicker();
		startDateText.setFont(SysFont.Infolab);
		startDateText.setBounds(_2_X, _2_Y, TEXT_WIDTH, TEXT_HEIGHT);
		startDateText.setFormats("yyyy-MM-dd");
		
		JXDatePicker endDateText = new JXDatePicker();
		endDateText.setFont(SysFont.Infolab);
		endDateText.setBounds(_3_X, _2_Y, TEXT_WIDTH, TEXT_HEIGHT);
		endDateText.setFormats("yyyy-MM-dd");
		
		JButton startBt = new JButton("启动");
		startBt.setFont(SysFont.Infolab);
		startBt.setBounds(_4_X, _2_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

		JButton stopBt = new JButton("停止");
		stopBt.setFont(SysFont.Infolab);
		stopBt.setBounds(_5_X, _2_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		stopBt.setEnabled(false);
		
		JButton pauseBt = new JButton("暂停");
		pauseBt.setFont(SysFont.Infolab);
		pauseBt.setBounds(_6_X, _2_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		pauseBt.setEnabled(false);
		
		this.add(label);
		this.add(startDateText);
		this.add(endDateText);
		this.add(startBt);
		this.add(stopBt);
		this.add(pauseBt);
	}

	private void adHq(){
		JLabel label = new JLabel("产品:");
		label.setFont(SysFont.Infolab);
		label.setBounds(_1_X, _3_Y, LABLE_WIDTH, LABLE_HEIGHT);

		JXDatePicker startDateText = new JXDatePicker();
		startDateText.setFont(SysFont.Infolab);
		startDateText.setBounds(_2_X, _3_Y, TEXT_WIDTH, TEXT_HEIGHT);
		startDateText.setFormats("yyyy-MM-dd");
		
		JXDatePicker endDateText = new JXDatePicker();
		endDateText.setFont(SysFont.Infolab);
		endDateText.setBounds(_3_X, _3_Y, TEXT_WIDTH, TEXT_HEIGHT);
		endDateText.setFormats("yyyy-MM-dd");
		
		JButton startBt = new JButton("启动");
		startBt.setFont(SysFont.Infolab);
		startBt.setBounds(_4_X, _3_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

		JButton stopBt = new JButton("停止");
		stopBt.setFont(SysFont.Infolab);
		stopBt.setBounds(_5_X, _3_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		stopBt.setEnabled(false);
		
		JButton pauseBt = new JButton("暂停");
		pauseBt.setFont(SysFont.Infolab);
		pauseBt.setBounds(_6_X, _3_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		pauseBt.setEnabled(false);
		
		this.add(label);
		this.add(startDateText);
		this.add(endDateText);
		this.add(startBt);
		this.add(stopBt);
		this.add(pauseBt);
	}

}
