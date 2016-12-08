package com.qianhtj.task.get.company;
import com.qianhtj.task.bean.FundCompanyInfo;
import com.qianhtj.task.dao.fund.FundSunshineDao;
import com.qianhtj.task.get.fund.FundProcess;

public class CompanyRepProductProcess implements FundProcess<FundCompanyInfo> {

	FundSunshineDao fundSunshineDao;
	
	public CompanyRepProductProcess() {
		fundSunshineDao = new FundSunshineDao();
	}
	
	@Override
	public FundCompanyInfo exec(FundCompanyInfo info) {
		//代表产品
		Object[] repProductArray =  fundSunshineDao.findRepProductByCompanyId(info.companyId);
		if(repProductArray != null && repProductArray.length>0){
			info.repProduct = repProductArray[1];
			info.repProductName = repProductArray[2];
			info.priceDate = repProductArray[3];
			info.nav = repProductArray[4];
			info.inceptionDate = repProductArray[5];
			info.retIncep =  repProductArray[6];
			info.retYtd = repProductArray[7];
		}
		return info;
	}

}
