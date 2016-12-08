package com.qianhtj.task.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lixl on 2016/11/26.
 */
public class NavigationPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public String panelTitle;


    public NavigationPanel(String panelTitle){
        this.panelTitle = panelTitle;
        setLayout(new BorderLayout());
    }


}
