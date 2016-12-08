package com.qianhtj.task.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.commons.lang.StringUtils;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.View;

import com.qianhtj.task.utils.SysFont;

public class Login extends SingleFrameApplication{
	
	private static final int APP_WIDTH = 400;
	private static final int APP_HEIGHT = 250;
	
	private JTextField userText;
	
	private JPasswordField passwdText;
	
	private JPanel mainPanel;

	@Override
	protected void initialize(String[] args) {
		super.initialize(args);
        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("RootPane.setupButtonVisible", false);
            getMainFrame().setTitle("登录");
            getMainFrame().setResizable(false);
			Image icon = new ImageIcon("images/logo.png").getImage();
			getMainFrame().setIconImage(icon);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
	
	@Override
	protected void startup() {
		View view = getMainView();
	
		view.setComponent(createMainPanel());
		show(view);
	}
	
	protected JComponent createMainPanel() {
		mainPanel = new JPanel();
		
		mainPanel.setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
		mainPanel.setLayout(null);
		JLabel userLabel = new JLabel("用户名：");
		userLabel.setFont(SysFont.login);
		userLabel.setBounds(80, 80, 60, 30);
		userLabel.setFont(SysFont.login);
		
		userText = new JTextField();
		userText.setFont(SysFont.login);
		userText.setBounds(160, 80, 140, 30);
		
		
		JLabel passwdLabel = new JLabel("密 码：");
		passwdLabel.setFont(SysFont.login);
		passwdLabel.setBounds(80, 120, 60, 30);
		
		passwdText = new JPasswordField();
		passwdText.setFont(SysFont.login);
		passwdText.setBounds(160, 120, 140, 30);
		
		
		JButton login = new JButton("登录");
		login.setFont(SysFont.login);
		login.setBounds(110, 180, 60, 25);
		
		JButton cancel = new JButton("取消");
		cancel.setFont(SysFont.login);
		cancel.setBounds(210, 180, 60, 25);
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(login()){
					launch(Windows.class, null);
				}else{
					JOptionPane.showMessageDialog(mainPanel, "登录失败");
				}
				
			}
		});
		
		
		mainPanel.add(userLabel);
		mainPanel.add(userText);
		mainPanel.add(passwdLabel);
		mainPanel.add(passwdText);
		mainPanel.add(login);
		mainPanel.add(cancel);
		return mainPanel;
	} 
	
	
	public static void main(String[] args){
		launch(Login.class, args);
	}
	
	
	private boolean login(){
		String password = "123456";
		String userName = "admin";
		System.out.println(userText.getText().toString());
		System.out.println(passwdText.getPassword().toString());
		
		if(StringUtils.isNotBlank(userText.getText().toString()) && userText.getText().toString().equals(userName)
				&& StringUtils.isNotBlank(passwdText.getPassword().toString()) && new String(passwdText.getPassword()).equals(password)){
			return true;
		}
		return false;
	}
	

}
