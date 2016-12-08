package com.qianhtj.task.get.fund;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.pvo.FundPvoStatusDao;

public class FundStatusPocess implements FundProcess<SunshinepublicFund> {
	
	FundPvoStatusDao fundStatusDao= new FundPvoStatusDao();
	
	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		Object[] fundStatus =  fundStatusDao.getStatus(fund.fundId);
		if(fundStatus != null && fundStatus.length > 0 && fundStatus[0] != null)  {
			fund.fundStatus = fundStatus[0];
		}
		return fund;
	}

}
