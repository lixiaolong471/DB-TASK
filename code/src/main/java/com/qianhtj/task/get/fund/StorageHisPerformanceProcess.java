package com.qianhtj.task.get.fund;

import org.apache.log4j.Logger;

import com.qianhtj.task.bean.FundHisPerformance;
import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.fund.FundPerformanceDao;

public class StorageHisPerformanceProcess implements FundProcess<SunshinepublicFund>{
	private static Logger log = Logger.getLogger(StorageHisPerformanceProcess.class);
	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		FundPerformanceDao perDao = new FundPerformanceDao();
		for(FundHisPerformance per:fund.hisPerformance){
			try{
				perDao.saveOrUpdate(per);	
			}catch(Exception e){
				log.error("====阳光私募数据存储-平均收益"+e.getMessage());
			}
			
		}
		return fund;
	}

}
