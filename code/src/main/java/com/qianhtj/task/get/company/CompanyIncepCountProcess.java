package com.qianhtj.task.get.company;

import com.qianhtj.task.bean.FundCompanyInfo;
import com.qianhtj.task.dao.fund.FundSunshineDao;
import com.qianhtj.task.get.fund.FundProcess;

public class CompanyIncepCountProcess implements FundProcess<FundCompanyInfo> {

	FundSunshineDao fundSunshineDao;
	
	public CompanyIncepCountProcess() {
		fundSunshineDao = new FundSunshineDao();
	}
	
	
	@Override
	public FundCompanyInfo exec(FundCompanyInfo info) {
		//盈利产品数
		Object[] incepCountArray =  fundSunshineDao.findIncepCountByCompanyId(info.companyId);
		if(incepCountArray != null && incepCountArray.length>0){
			info.proProductCount = incepCountArray[0];
		}
		return info;
	}

}
