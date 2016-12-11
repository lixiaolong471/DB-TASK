package com.qianhtj.task.swing;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.qianhtj.task.dao.Context;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.View;

import com.qianhtj.task.swing.dialog.ConnectonDialog;
import com.qianhtj.task.swing.dialog.DayTaskDialog;
import com.qianhtj.task.swing.dialog.InitTaskDialog;
import com.qianhtj.task.swing.dialog.ThreadDialog;

/**
 * Created by lixl on 2016/11/26.
 */
public class Windows extends SingleFrameApplication {

	static final Logger logger = Logger.getLogger(Windows.class.getName());
	
	public static final int MAIN_FRAME_WIDTH = 880;
    public static final int MAIN_FRAME_HEIGHT = 640;

    // GUI components
    private JPanel mainPanel;
    
    @Override
    protected void initialize(String[] args) {
        super.initialize(args);
        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("RootPane.setupButtonVisible", false);
            Image icon = new ImageIcon("images/logo.png").getImage();
			getMainFrame().setIconImage(icon);
			getMainFrame().setTitle("数据采集系统");
			getMainFrame().setResizable(false);
            Context.loadConfig();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }

    @Override
    protected void startup() {
        View view = getMainView();
        view.setComponent(createMainPanel());
        view.setMenuBar(createMenuBar());
        show(view);
    }

    protected JComponent createMainPanel() {
        // Create main panel with demo selection on left and demo/source on right
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return mainPanel;
    }

    protected JMenuBar createMenuBar() {
    	JMenuBar menubar = new JMenuBar();
        menubar.setName("menubar");
        
        menubar.add(getTaskMenu());
        menubar.add(getDatasourceMenu());
        menubar.add(getThreadMenu());
        menubar.add(getSystemMenu());
        return menubar;
    }
    
    private JMenu getTaskMenu(){
    	JMenu taskMenu = new JMenu("任务管理");
    	JMenuItem initMenu = new JMenuItem("初始化");
    	initMenu.addActionListener(new ActionListener() {
            JDialog initDialog;
			@Override
			public void actionPerformed(ActionEvent e) {
                if(initDialog == null){
                    initDialog = new InitTaskDialog(getMainFrame());
                }
				initDialog.setVisible(true);
			}
		});
    	JMenuItem dayMenu = new JMenuItem("定时任务");
    	dayMenu.addActionListener(new ActionListener() {
            JDialog dayDialog;
			@Override
			public void actionPerformed(ActionEvent e) {
                if(dayDialog == null){
                    dayDialog = new DayTaskDialog(getMainFrame());
                }
				dayDialog.setVisible(true);
			}
		});
    	taskMenu.add(initMenu);
    	taskMenu.add(dayMenu);
    	return taskMenu;
    }
    
    private JMenu getSystemMenu(){
    	JMenu systemMenu = new JMenu("系统");
    	JMenuItem quitItem = new JMenuItem("退出");
        Action exit = getAction("quit");
        quitItem.setAction(exit);
        systemMenu.add(quitItem);
    	return systemMenu;
    }
    
    private JMenu getThreadMenu(){
    	JMenu threadMenu = new JMenu("线程池");
    	JMenuItem configItem = new JMenuItem("线程池配置");
    	configItem.addActionListener(new ActionListener() {

            JDialog t;

			@Override
			public void actionPerformed(ActionEvent e) {
				if(t == null){
                    t = new ThreadDialog(getMainFrame());
                }
				t.setVisible(true);
			}
		});
    	threadMenu.add(configItem);
    	return threadMenu;
    }
    
    //数据源配置菜单
    private JMenu getDatasourceMenu(){
    	 JMenu datasourceMenu = new JMenu("数据源");
    	//文件菜单
         JMenuItem paipaiConnItem = new JMenuItem("私募排排");
         
         paipaiConnItem.addActionListener(new ActionListener() {
             JDialog addConn;

 			@Override
 			public void actionPerformed(ActionEvent e) {
 				if(addConn == null){
                    addConn = new ConnectonDialog("私募拍拍网","location",getMainFrame());
                }
 				addConn.setVisible(true);
 			}
 		});
         
         JMenuItem location = new JMenuItem("应用系统");
         
         location.addActionListener(new ActionListener() {
            JDialog addConn;

 			@Override
 			public void actionPerformed(ActionEvent e) {
 				if(addConn == null){
                    addConn = new ConnectonDialog("网站数据","data",getMainFrame());
                }
 				addConn.setVisible(true);
 			}
 		});
        
        datasourceMenu.add(paipaiConnItem);
        datasourceMenu.add(location);
    	return datasourceMenu;
    }
    
    private javax.swing.Action getAction(String actionName) {
        return getContext().getActionMap().get(actionName);
    }

    public static void main(String[] args){
        launch(Windows.class,args);
    }

}
