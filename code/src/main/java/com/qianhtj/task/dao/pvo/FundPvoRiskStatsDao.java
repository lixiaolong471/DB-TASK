package com.qianhtj.task.dao.pvo;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class FundPvoRiskStatsDao extends BaseDao {
	
	public FundPvoRiskStatsDao(){
		super(Context.DATASOUREC_LOCATION);
	}
	
	public Object getMaxDate(Object fundId){
		StringBuffer selectSql = new StringBuffer("select max(end_date) from pvo_fund_risk_stats where fund_id = ? ");
		Object[] riskStatsExArray =  queryOne(selectSql.toString(),new Object[]{fundId});
		if(riskStatsExArray!=null && riskStatsExArray.length > 0 && riskStatsExArray[0] != null){
			return riskStatsExArray[0];
		}
		return null;
	}
	
	public Object[] getFundRiskStats(Object fundId,Object maxDate){
		StringBuffer selectSql = new StringBuffer("select stddev_1y,stddev_2y,stddev_3y,stddev_incep,");
		selectSql.append("maxdrawdown_1y,maxdrawdown_2y,maxdrawdown_3y,maxdrawdown_incep, ");
		selectSql.append("battingaverage_1y,battingaverage_2y,battingaverage_3y,battingaverage_incep ");
		selectSql.append(" from pvo_fund_risk_stats where fund_id = ? and end_date = ? ");
		Object[] riskStatsArray =  queryOne(selectSql.toString(),new Object[]{fundId,maxDate});
		return riskStatsArray;
	}
}
