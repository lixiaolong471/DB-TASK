package com.qianhtj.task.get.company;

import java.math.BigDecimal;

import com.qianhtj.task.bean.FundCompanyInfo;
import com.qianhtj.task.dao.fund.FundSunshineDao;
import com.qianhtj.task.get.fund.FundProcess;

public class CompanyIncepProcess implements FundProcess<FundCompanyInfo> {
	
	FundSunshineDao fundSunshineDao;
	
	public CompanyIncepProcess() {
		fundSunshineDao = new FundSunshineDao();
	}
	
	@Override
	public FundCompanyInfo exec(FundCompanyInfo info) {
		//收益
		Object[] incepProductArray =  fundSunshineDao.findiIncepByCompanyId(info.companyId);
		if(incepProductArray != null && incepProductArray.length>0){
			Object countObject = incepProductArray[2];
			if(incepProductArray[0] != null && countObject != null){
				info.retIncepAverage = ((BigDecimal)incepProductArray[0]).divide(new BigDecimal((Long)countObject),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
			}
			if(incepProductArray[1] != null && countObject != null){
				info.retYtdAverage = ((BigDecimal)incepProductArray[1]).divide(new BigDecimal((Long)countObject),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
			}
		}
		return info;
	}

}
