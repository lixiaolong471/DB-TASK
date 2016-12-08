package com.qianhtj.task.get.company;

import com.qianhtj.task.dao.pvo.FundPvoManagerDao;
import com.qianhtj.task.bean.FundCompanyInfo;
import com.qianhtj.task.get.fund.FundProcess;

public class CompanyKeyPersonProcess implements FundProcess<FundCompanyInfo> {
	
	FundPvoManagerDao fundPvoManagerDao = new FundPvoManagerDao();
	
	@Override
	public FundCompanyInfo exec(FundCompanyInfo fund) {
		//公司关键人物
		Object[] keyPersonnelArray =  fundPvoManagerDao.findKeyPersonByCompanyId(fund.companyId);
		if(keyPersonnelArray != null && keyPersonnelArray.length>0){
			fund.keyPerson = keyPersonnelArray[0];
			fund.keyPersonName = keyPersonnelArray[1];
			fund.keyPersonLogo = keyPersonnelArray[2];
			fund.keyPersonProfile = keyPersonnelArray[3];
		}
		return fund;
	}

}
