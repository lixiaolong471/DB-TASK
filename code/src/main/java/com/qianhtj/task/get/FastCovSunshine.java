package com.qianhtj.task.get;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import com.qianhtj.task.job.Task;
import org.apache.log4j.Logger;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.Context;
import com.qianhtj.task.dao.fund.FundNavDao;
import com.qianhtj.task.dao.fund.FundPerformanceDao;
import com.qianhtj.task.dao.fund.FundRiskadjretStatsDao;
import com.qianhtj.task.dao.fund.FundSunshineDao;
import com.qianhtj.task.dao.pvo.FundPvoSunshineDao;
import com.qianhtj.task.dao.pvo.MarketPvoDao;
import com.qianhtj.task.get.fund.QueryThreadPoolExecutor;
import com.qianhtj.task.get.fund.StorageHisNavProcess;
import com.qianhtj.task.get.fund.StorageSunshineProcess;
import com.qianhtj.task.get.fund.TakeSunshineProcess;
import com.qianhtj.task.job.GetData;

public class FastCovSunshine extends GeneralGetData implements GetData {
	
	ExecutorService threadPool = QueryThreadPoolExecutor.newFixedThreadPoolExecutor();
    BlockingQueue<SunshinepublicFund> baseDatas = new ArrayBlockingQueue<SunshinepublicFund>(50);
	static Logger log = Logger.getLogger(Task.class);

	FundPvoSunshineDao covSunshineDao;
	MarketPvoDao marketDao;
	FundNavDao fundNavDao;
	FundPerformanceDao fundPerformanceDao;
	FundRiskadjretStatsDao fundRiskadjretStatsDao;
	FundSunshineDao fundSunshineDao;
	AtomicInteger saveIndex = new AtomicInteger(0);
	String shCode;

	private Object close;
	
	
	public FastCovSunshine(String shCode){
		this.shCode = shCode; 
		covSunshineDao = new FundPvoSunshineDao();
		marketDao = new MarketPvoDao();
		fundNavDao = new FundNavDao();
		fundPerformanceDao = new FundPerformanceDao();
		fundRiskadjretStatsDao = new FundRiskadjretStatsDao();
		fundSunshineDao = new FundSunshineDao();
	}
		



	private void startSave(){
		for(;saveIndex.get()<Context.getSavePool();saveIndex.getAndIncrement()){
			threadPool.execute(new Thread(){
				@Override
				public void run() {
					super.run();
					while(isRun.get()){
                       	SunshinepublicFund fund = baseDatas.poll();
						if(fund != null){
							long startTimeMillis = System.currentTimeMillis();
							new StorageSunshineProcess().exec(fund);
							new StorageHisNavProcess().exec(fund);
							index.incrementAndGet();
                            log("====阳光私募数据存储完成【saveOrUpdate】耗时："+(System.currentTimeMillis() - startTimeMillis) + "ms");
						}else {
							try {
								Thread.sleep(1000L);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					saveIndex.getAndDecrement();
				}
			});
		}
	}

	@Override
	public void stop() {
		dataList = new ArrayList<>();
		sumcount.set(0);
		index.set(0);
		isRun.set(false);
	}

	private void get(final SunshinepublicFund fund){
		threadPool.execute(new Thread(){
			@Override
			public void run() {
				super.run();
				try {
					System.out.println("get data");
					long startTimeMillis = System.currentTimeMillis();
					baseDatas.put(new TakeSunshineProcess().exec(fund));
					System.out.println("====阳光私募数据存储完成【GET】耗时："+(System.currentTimeMillis() - startTimeMillis) + "ms");
					log("====阳光私募数据存储完成【GET】耗时："+(System.currentTimeMillis() - startTimeMillis) + "ms");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

    @Override
    public List<Object[]> getList(Date startDate, Date endDate) {
        return covSunshineDao.getListByStatus(startDate,endDate);
    }

    @Override
    public void processList() {
        close = marketDao.getLastMarketClose(shCode);
		if(saveIndex.get() < Context.getSavePool()){
			System.out.println("start save"+saveIndex.get()+"|"+Context.getSavePool());
			startSave();
		}
        super.processList();
    }

    @Override
    public void process(Object[] data) {
        SunshinepublicFund fund = new SunshinepublicFund();
        if(close!=null){
            fund.close = close;
        }
        fund.setBaseFundInfo(data);
        get(fund);
    }
}
