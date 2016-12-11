package com.qianhtj.task.get;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.fund.FundPerformanceDao;
import com.qianhtj.task.dao.fund.FundSunshineDao;
import com.qianhtj.task.dao.pvo.MarketPvoDao;
import com.qianhtj.task.get.fund.StorageHisNavProcess;
import com.qianhtj.task.get.fund.StorageSunshineProcess;
import com.qianhtj.task.job.GetData;
import com.qianhtj.task.job.Task;
import com.qianhtj.task.utils.DateUtils;
import org.apache.log4j.Logger;

import com.qianhtj.task.dao.fund.FundNavDao;
import com.qianhtj.task.dao.fund.FundRiskadjretStatsDao;
import com.qianhtj.task.dao.pvo.FundPvoSunshineDao;
import com.qianhtj.task.get.fund.TakeSunshineProcess;
import com.qianhtj.task.main.Util;

public class CovSunshine extends GeneralGetData implements GetData {

    private Object close;

	FundPvoSunshineDao covSunshineDao;
	MarketPvoDao marketDao;
	FundNavDao fundNavDao;
	FundPerformanceDao fundPerformanceDao;
	FundRiskadjretStatsDao fundRiskadjretStatsDao;
	FundSunshineDao fundSunshineDao;
	String shCode;

	public CovSunshine(String shCode){
		this.shCode = shCode; 
		covSunshineDao = new FundPvoSunshineDao();
		marketDao = new MarketPvoDao();
		fundNavDao = new FundNavDao();
		fundPerformanceDao = new FundPerformanceDao();
		fundRiskadjretStatsDao = new FundRiskadjretStatsDao();
		fundSunshineDao = new FundSunshineDao();
	}

    @Override
    public void processList() {
        close = marketDao.getLastMarketClose(shCode);
        super.processList();
    }

    @Override
    public List<Object[]> getList(Date startDate, Date endDate) {
        return covSunshineDao.getList(startDate,endDate);
    }

    @Override
    public void process(Object[] data) {
        SunshinepublicFund fund = new SunshinepublicFund();
        if(close!=null){
            fund.close = close;
        }
        fund.setBaseFundInfo(data);
        fund = new TakeSunshineProcess().exec(fund);
        fund = new StorageSunshineProcess().exec(fund);
        new StorageHisNavProcess().exec(fund);
    }
}
