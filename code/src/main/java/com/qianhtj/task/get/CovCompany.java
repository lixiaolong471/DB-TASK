package com.qianhtj.task.get;

import java.util.Date;
import java.util.List;

import com.qianhtj.task.dao.fund.CompanyDao;
import com.qianhtj.task.get.company.CompanyRepProductProcess;
import com.qianhtj.task.job.GetData;
import com.qianhtj.task.bean.FundCompanyInfo;
import com.qianhtj.task.get.company.CompanySeoInfoProcess;
import com.qianhtj.task.dao.fund.FundSunshineDao;
import com.qianhtj.task.dao.pvo.CompanyPvoDao;
import com.qianhtj.task.get.company.CompanyIncepCountProcess;
import com.qianhtj.task.get.company.CompanyIncepProcess;
import com.qianhtj.task.get.company.CompanyKeyPersonProcess;
import com.qianhtj.task.main.Util;
import com.qianhtj.task.utils.DateUtils;

public class CovCompany extends GeneralGetData implements GetData {

	FundSunshineDao fundSunshineDao;
	CompanyPvoDao companyPvoDao;
	CompanyDao companyDao;
	CompanyRepProductProcess companyRepProductProcess;
	CompanyKeyPersonProcess companyKeyPersonProcess;
	CompanyIncepProcess companyIncepProcess;
	CompanyIncepCountProcess companyIncepCountProcess;
	CompanySeoInfoProcess companySeoInfoProcess;

	
	public CovCompany(){
		fundSunshineDao = new FundSunshineDao();
		companyRepProductProcess = new CompanyRepProductProcess();
		companyKeyPersonProcess = new CompanyKeyPersonProcess();
		companyIncepProcess = new CompanyIncepProcess();
		companyIncepCountProcess = new CompanyIncepCountProcess();
		companySeoInfoProcess = new CompanySeoInfoProcess();
		companyPvoDao = new CompanyPvoDao();
		companyDao = new CompanyDao();
	}

    @Override
    public List<Object[]> getList(Date startDate, Date endDate) {
        return companyPvoDao.getList(startDate,endDate);
    }

    @Override
    public void process(Object[] data){
        Object[] companyArray = dataList.get(index.get());

        FundCompanyInfo info = setBaseInof(companyArray);
        info = companyKeyPersonProcess.exec(info);
        info = companyRepProductProcess.exec(info);
        info = companyIncepProcess.exec(info);
        info = companyIncepCountProcess.exec(info);
        info = companySeoInfoProcess.exec(info);
        info.logo = Util.covImg(info.logo);
        info.keyPersonLogo = Util.covImg(info.keyPersonLogo);
        //只存储存在产品的公司
        if(info.repProduct != null && info.companyId != null &&
                !info.companyId.toString().trim().equals("")){
            companyDao.saveOrUpdate(info);
        }
    }

    private FundCompanyInfo setBaseInof(Object[] companyArray){
		FundCompanyInfo info = new FundCompanyInfo();
		info.companyId = companyArray[0];
		info.companyName = companyArray[1];
		info.companyShortName = companyArray[2];
		info.registerNumber = companyArray[3];
		info.registeredCapital = companyArray[4];
		info.city = companyArray[5];
		info.establishDate = companyArray[6];
		info.companyAssetSize = companyArray[7];
		info.companyProfile = companyArray[8];
		info.philosopy = companyArray[9];
		info.teamProfile = companyArray[10];
		info.logo = companyArray[11];
		info.upDown = 1;
		return info;
	}

}
