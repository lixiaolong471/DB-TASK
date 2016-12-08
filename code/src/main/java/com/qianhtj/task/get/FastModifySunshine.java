package com.qianhtj.task.get;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import com.qianhtj.task.bean.FundPvoNav;
import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.Context;
import com.qianhtj.task.dao.fund.FundNavDao;
import com.qianhtj.task.dao.fund.FundPerformanceDao;
import com.qianhtj.task.dao.fund.FundRiskadjretStatsDao;
import com.qianhtj.task.dao.fund.FundSunshineDao;
import com.qianhtj.task.dao.pvo.FundPvoSunshineDao;
import com.qianhtj.task.dao.pvo.MarketPvoDao;
import com.qianhtj.task.get.fund.FundHisNavProcess;
import com.qianhtj.task.get.fund.StorageHisNavProcess;
import com.qianhtj.task.job.Task;
import org.apache.log4j.Logger;
import com.qianhtj.task.get.fund.QueryThreadPoolExecutor;

public class FastModifySunshine{
	
	ExecutorService threadPool = QueryThreadPoolExecutor.newFixedThreadPoolExecutor();
	static Logger log = Logger.getLogger(Task.class);
	public volatile static AtomicLong sumcount = new AtomicLong(0);
	public volatile static AtomicLong index = new AtomicLong(0);
	public volatile static  AtomicBoolean isRun = new AtomicBoolean(false);
	FundPvoSunshineDao covSunshineDao;
	MarketPvoDao marketDao;
	FundNavDao fundNavDao;
	FundPerformanceDao fundPerformanceDao;
	FundRiskadjretStatsDao fundRiskadjretStatsDao;
	FundSunshineDao fundSunshineDao;
	
	BlockingQueue<SunshinepublicFund> baseDatas = new ArrayBlockingQueue<SunshinepublicFund>(50);
	
	
	public FastModifySunshine(){
		covSunshineDao = new FundPvoSunshineDao();
		marketDao = new MarketPvoDao();
		fundNavDao = new FundNavDao();
		fundPerformanceDao = new FundPerformanceDao();
		fundRiskadjretStatsDao = new FundRiskadjretStatsDao();
		fundSunshineDao = new FundSunshineDao();
	}
		
	
	public void loadData(){
		try{
			//查询基金详情
			FundSunshineDao dao = new FundSunshineDao();
			List<SunshinepublicFund> lists = dao.getList(null, null);
			FastModifySunshine.sumcount.set(lists.size());
			log.info("====阳光私募数据【SUM】："+FastModifySunshine.sumcount.get());
			isRun.set(true);
			long startTimeMillis = System.currentTimeMillis();
			//创建保存线程
			startSave();
			for(SunshinepublicFund fund:lists){
				//创建读取线程
				get(fund);
			}
			log.info("====阳光私募数据存储完成【ALL】耗时："+(System.currentTimeMillis() - startTimeMillis) + "ms");
			FastModifySunshine.index.set(0);
			FastModifySunshine.sumcount.set(0);
			log.info("采集总数："+sumcount);
		}catch(Exception e){
			System.err.println("==== pvo_company_info数据表更新异常 ====");
			e.printStackTrace();
		}
	}
	
	private void startSave(){
		for(int i = 0; i< Context.getSavePool(); i++){
			threadPool.execute(new Thread(){
				@Override
				public void run() {
					super.run();
					while(isRun.get()){
						SunshinepublicFund fund = baseDatas.poll();
						if(fund != null){
//							long startTimeMillis = System.currentTimeMillis();
//							new StorageHisPerformanceProcess().exec(fund);
							new StorageHisNavProcess().exec(fund);
							FastModifySunshine.index.incrementAndGet();
//							log.info("====阳光私募数据存储完成【saveOrUpdate】耗时："+(System.currentTimeMillis() - startTimeMillis) + "ms");
						}
						if(baseDatas.size() <= 0 && FastModifySunshine.sumcount.get() <= 0){
							isRun.set(false);
						}
					}
				}
			});
		}
	}
	
	private void get(final SunshinepublicFund fund){
		threadPool.execute(new Thread(){
			@Override
			public void run() {
				super.run();
				try {
//					long startTimeMillis = System.currentTimeMillis();
//					fund.hisPerformance = FundHisPerformance.getInstances(fund.id);
//					fund.hisFundStats = FundRiskadjretStats.getFundRiskadjretStats(fund.id);
					fund.navs = new ArrayList<FundPvoNav>();
//					new FundHisPerformanceProcess().exec(fund);
					new FundHisNavProcess().exec(fund);
					baseDatas.put(fund);
//					log.info("====阳光私募数据存储完成【GET】耗时："+(System.currentTimeMillis() - startTimeMillis) + "ms");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	
}
