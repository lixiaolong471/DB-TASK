package com.qianhtj.task.swing.dialog;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.qianhtj.task.dao.Context;
import com.qianhtj.task.utils.SysFont;

public class ConnectonDialog extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private static String[] dbListLabel = new String[]{"MySQL5","DB2","SQLServer2005","Oracle11g"};
	private static String[] dbListValue = new String[]{"com.mysql.jdbc.Driver","com.mysql.jdbc.Driver","com.mysql.jdbc.Driver","com.mysql.jdbc.Driver"};
	
	JButton okBtn = new JButton("确定");
    JButton cancelBtn = new JButton("关闭");

    private String sourceType;
    
    private JDialog dialog;

    private JTextField urlText;

    private JTextField userText;

    private JTextField pwdText;




    
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



    
    
    public ConnectonDialog(String name,String sourceType,JFrame parent){
    	dialog = this;
        this.sourceType = sourceType;
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
        connText.setText(name);
        connText.setEditable(false);
    	
      	JLabel driverLable = new JLabel("链接名称:");
      	driverLable.setFont(SysFont.Infolab);
      	driverLable.setBounds(FIRST_X, LABLE_2_Y, LABLE_WIDTH, LABLE_HEIGHT);
    	
    	JComboBox<String> driverBox = new JComboBox<>();
    	driverBox.setFont(SysFont.Infolab);
    	driverBox.setBounds(SECOND_X, LABLE_2_Y, TEXT_WIDTH, TEXT_HEIGHT);
    	driverBox.setModel(new DefaultComboBoxModel<>(dbListLabel));
    	
    	JLabel urlLable = new JLabel("URL地址:");
    	urlLable.setFont(SysFont.Infolab);
    	urlLable.setBounds(FIRST_X, LABLE_3_Y, LABLE_WIDTH, LABLE_HEIGHT);
    	
    	urlText = new JTextField();
    	urlText.setFont(SysFont.Infolab);
    	urlText.setBounds(SECOND_X, LABLE_3_Y, TEXT_WIDTH, TEXT_HEIGHT);
        urlText.setText(Context.getUrl(sourceType));
    	
    	JLabel userLable = new JLabel("用户名:");
    	userLable.setFont(SysFont.Infolab);
    	userLable.setBounds(FIRST_X, LABLE_4_Y, LABLE_WIDTH, LABLE_HEIGHT);
    	
    	userText = new JTextField();
    	userText.setFont(SysFont.Infolab);
    	userText.setBounds(SECOND_X, LABLE_4_Y, TEXT_WIDTH, TEXT_HEIGHT);
        userText.setText(Context.getUsername(sourceType));
    	
    	JLabel pwdLable = new JLabel("密码:");
    	pwdLable.setFont(SysFont.Infolab);
    	pwdLable.setBounds(FIRST_X, LABLE_5_Y, LABLE_WIDTH, LABLE_HEIGHT);
    	
    	pwdText = new JTextField();
    	pwdText.setFont(SysFont.Infolab);
    	pwdText.setBounds(SECOND_X, LABLE_5_Y, TEXT_WIDTH, TEXT_HEIGHT);
        pwdText.setText(Context.getPassword(sourceType));

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
    	
    	this.add(urlLable);
    	this.add(urlText);
    	
    	this.add(userLable);
    	this.add(userText);
    	
    	this.add(pwdLable);
    	this.add(pwdText);
    	
    	this.add(cancelBtn);
    	this.add(okBtn);
    	
    	this.setModal(true);

        okBtn.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Context.setUrl(sourceType,urlText.getText());
        Context.setUsername(sourceType,userText.getText());
        Context.setPassword(sourceType,pwdText.getText());
        Context.setUrl(sourceType,urlText.getText());
        Context.saveConfig();
        Context.reSetDataSourceByName(sourceType);
        JOptionPane.showMessageDialog(this,"保存成功");
        this.setVisible(false);
    }
}
