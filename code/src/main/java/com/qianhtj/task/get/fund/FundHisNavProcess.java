package com.qianhtj.task.get.fund;

import java.math.BigDecimal;
import java.util.List;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.pvo.FundPvoNavDao;
import com.qianhtj.task.bean.FundPvoNav;

public class FundHisNavProcess implements FundProcess<SunshinepublicFund> {

	FundPvoNavDao fundPvoNavDao = new FundPvoNavDao();
	
	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		//净值历史数据
		List<Object[]> navList =  fundPvoNavDao.getList(fund.fundId);
		if(navList != null && navList.size() > 0)  {
			for(int i=0;i<navList.size();i++){
				FundPvoNav nav = new FundPvoNav();
				Object[] navArray = navList.get(i);
				nav.fundId = fund.fundId;
				nav.priceDate = navArray[0];
				nav.nav = navArray[1];
				nav.cumulativeNav = navArray[2];
				if(i<navList.size()-1 && navArray[2] != null && navList.get(i+1) != null && navList.get(i+1)[2] !=null){
					Object[] lastDay = navList.get(i+1);
					if(nav.cumulativeNav instanceof BigDecimal && lastDay[2] instanceof BigDecimal){
						BigDecimal lastDayNav = (BigDecimal)lastDay[2];
						BigDecimal thisDayNav = (BigDecimal)navArray[2];
						nav.riseFall = thisDayNav.subtract(lastDayNav).divide(lastDayNav,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
//						System.out.println("lastDayNav="+lastDayNav+",thisDayNav="+thisDayNav+",riseFall="+nav.riseFall);
					}
				}
				fund.navs.add(nav);
			}						
		}
		return fund;
	}

}
