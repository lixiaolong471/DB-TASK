package com.qianhtj.task.main;

import org.apache.log4j.Logger;

import com.qianhtj.task.get.FastModifySunshine;

public class ModifyTaskMain {

	private static Logger log = Logger.getLogger(ModifyTaskMain.class);
	
	public static boolean run = true;

	
	public static void main(String[] args) {	
		try{
			//首次执行
			long process = 0;
			
			new Thread(){				
				public void run() {
					log.info("阳光私募数据重新处理开始！");
					new FastModifySunshine().loadData();
					log.info("阳光私募数据重新处理完成！");
					run = false;
				}
			}.start(); 
	        while(run){
	        	if(FastModifySunshine.sumcount.get() >0){
	    			process = FastModifySunshine.index.get() * 10000/FastModifySunshine.sumcount.get();
	    			log.info("阳光私募执行已完成："+(((float)process)/100)+"%.总数="+FastModifySunshine.sumcount.get()+"当前="+FastModifySunshine.index.get());
	    		}
	        	Thread.sleep(60*1000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
