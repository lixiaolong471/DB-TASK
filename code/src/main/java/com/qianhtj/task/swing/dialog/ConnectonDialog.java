package com.qianhtj.task.swing.dialog;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.qianhtj.task.utils.SysFont;

public class ConnectonDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private static String[] dbListLabel = new String[]{"MySQL5","DB2","SQLServer2005","Oracle11g"};
	private static String[] dbListValue = new String[]{"com.mysql.jdbc.Driver","com.mysql.jdbc.Driver","com.mysql.jdbc.Driver","com.mysql.jdbc.Driver"};
	
	JButton okBtn = new JButton("确定");
    JButton cancelBtn = new JButton("关闭");
    
    private JDialog dialog;
    
    private static final int LABLE_WIDTH = 110;
    private static final int LABLE_HEIGHT = 30;
    private static final int TEXT_WIDTH = 180;
    private static final int TEXT_HEIGHT = LABLE_HEIGHT;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = LABLE_HEIGHT;
    
    private static final int FIRST_X = 20;
    private static final int SECOND_X = 100;
    
    private static final int BUTTON_1_X = 70;
    private static final int BUTTON_2_X = 170;
    
    
    private static final int LABLE_1_Y = 20;
    private static final int LABLE_2_Y = 60;
    private static final int LABLE_3_Y = 100;
    private static final int LABLE_4_Y = 140;
    private static final int LABLE_5_Y = 180;
    private static final int LABLE_6_Y = 220;
    
    private static final int BUTTON_Y = 290;
    
    
	
    private static final int WIDTH = 320;
    
    private static final int HEIGHT = 400;
    
    
    public ConnectonDialog(JFrame parent){
    	dialog = this;
    	Point location = new Point();
    	location.setLocation((parent.getLocation().getX()+(parent.getWidth()-WIDTH)/2),
    			(parent.getLocationOnScreen().getY()+(parent.getHeight()-HEIGHT)/2));
    	this.setLocation(location);
    	setSize(WIDTH,HEIGHT);
    	setLayout(null);
    	setResizable(false);
    	setTitle("编辑数据源");
  
    	JLabel connLable = new JLabel("链接名称:");
    	connLable.setFont(SysFont.Infolab);
    	connLable.setBounds(FIRST_X, LABLE_1_Y, LABLE_WIDTH, LABLE_HEIGHT);
    	
    	JTextField connText = new JTextField();
    	connText.setFont(SysFont.Infolab);
    	connText.setBounds(SECOND_X, LABLE_1_Y, TEXT_WIDTH, TEXT_HEIGHT);
    	
      	JLabel driverLable = new JLabel("链接名称:");
      	driverLable.setFont(SysFont.Infolab);
      	driverLable.setBounds(FIRST_X, LABLE_2_Y, LABLE_WIDTH, LABLE_HEIGHT);
    	
    	JComboBox<String> driverBox = new JComboBox<>();
    	driverBox.setFont(SysFont.Infolab);
    	driverBox.setBounds(SECOND_X, LABLE_2_Y, TEXT_WIDTH, TEXT_HEIGHT);
    	driverBox.setModel(new DefaultComboBoxModel<>(dbListLabel));
    	
    	
    	JLabel nameLable = new JLabel("URL地址:");
    	nameLable.setFont(SysFont.Infolab);
    	nameLable.setBounds(FIRST_X, LABLE_3_Y, LABLE_WIDTH, LABLE_HEIGHT);
    	
    	JTextField nameText = new JTextField();
    	nameText.setFont(SysFont.Infolab);
    	nameText.setBounds(SECOND_X, LABLE_3_Y, TEXT_WIDTH, TEXT_HEIGHT);
    	
      	JLabel portLable = new JLabel("端口:");
      	portLable.setFont(SysFont.Infolab);
      	portLable.setBounds(FIRST_X, LABLE_4_Y, LABLE_WIDTH, LABLE_HEIGHT);
    	
    	JTextField portText = new JTextField();
    	portText.setFont(SysFont.Infolab);
    	portText.setBounds(SECOND_X, LABLE_4_Y, TEXT_WIDTH, TEXT_HEIGHT);
    	
    	JLabel userLable = new JLabel("用户名:");
    	userLable.setFont(SysFont.Infolab);
    	userLable.setBounds(FIRST_X, LABLE_5_Y, LABLE_WIDTH, LABLE_HEIGHT);
    	
    	JTextField userText = new JTextField();
    	userText.setFont(SysFont.Infolab);
    	userText.setBounds(SECOND_X, LABLE_5_Y, TEXT_WIDTH, TEXT_HEIGHT);
    	
    	JLabel pwdLable = new JLabel("密码:");
    	pwdLable.setFont(SysFont.Infolab);
    	pwdLable.setBounds(FIRST_X, LABLE_6_Y, LABLE_WIDTH, LABLE_HEIGHT);
    	
    	JTextField pwdText = new JTextField();
    	pwdText.setFont(SysFont.Infolab);
    	pwdText.setBounds(SECOND_X, LABLE_6_Y, TEXT_WIDTH, TEXT_HEIGHT);
    	
    	JButton cancelBtn = new JButton("取消");
    	cancelBtn.setFont(SysFont.Infolab);
    	cancelBtn.setBounds(BUTTON_1_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
    	
    	cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
    	
    	JButton okBtn = new JButton("确定");
    	okBtn.setFont(SysFont.Infolab);
    	okBtn.setBounds(BUTTON_2_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
    	
    	
    	this.setResizable(false);
    	this.add(connLable);
    	this.add(connText);
    	
    	this.add(driverLable);
    	this.add(driverBox);
    	
    	this.add(nameLable);
    	this.add(nameText);
    	
    	this.add(portLable);
    	this.add(portText);
    	
    	this.add(userLable);
    	this.add(userText);
    	
    	this.add(pwdLable);
    	this.add(pwdText);
    	
    	this.add(cancelBtn);
    	this.add(okBtn);
    	
    	this.setModal(true);
    	
    	
    }
    
    
    
	
}
