package com.qianhtj.task.main;

import com.qianhtj.task.job.Task;

public class InitMain {

	/**            
	 * 
	 *  初始化程序入口。
	 * @param args 
	 */
	public static void main(String[] args){
		try{
			//首次执行
			new Thread(){				
				public void run() {
					new Task().init();
				}
			}.start(); 
	        while(true){
	        	Task.printProgress();
	        	Thread.sleep(60*1000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
