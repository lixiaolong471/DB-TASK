package com.qianhtj.task.get.fund;

import com.qianhtj.task.bean.FundHisPerformance;
import com.qianhtj.task.bean.FundRiskadjretStats;
import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.fund.FundPerformanceDao;
import com.qianhtj.task.dao.fund.FundRiskadjretStatsDao;
import com.qianhtj.task.dao.fund.FundSunshineDao;

public class StorageSunshineProcess implements FundProcess<SunshinepublicFund>{
	
	public SunshinepublicFund exec(SunshinepublicFund fund){
		
		if(fund.fundId == null || fund.fundId.toString().trim().equals("")){
			return fund;
		}
		FundPerformanceDao fundPerformanceDao = new FundPerformanceDao();
//		FundNavDao fundNavDao = new FundNavDao();
		FundRiskadjretStatsDao fundRiskadjretStatsDao = new FundRiskadjretStatsDao();
		FundSunshineDao covSunshineDao = new FundSunshineDao();
		//清空列表数据
//		fundNavDao.empty(fund.fundId);
//		fundPerformanceDao.empty(fund.fundId);
//		fundRiskadjretStatsDao.empty(fund.fundId);
		
		for(FundHisPerformance hisPerfor:fund.hisPerformance){
			fundPerformanceDao.saveOrUpdate(hisPerfor);
		}
		for(FundRiskadjretStats peradjStats:fund.hisFundStats){
			fundRiskadjretStatsDao.saveOrUpdate(peradjStats);
		}
		covSunshineDao.saveOrUpdate(fund);
		return fund;
	}
}
