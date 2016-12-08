package com.qianhtj.task.dao.pvo;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class FundPvoRiskadjretStatsDao extends BaseDao {
	
	public FundPvoRiskadjretStatsDao(){
		super(Context.DATASOUREC_LOCATION);
	}
	
	public Object getMaxDate(Object fundId){
		StringBuffer selectSql = new StringBuffer("select max(end_date) from pvo_fund_riskadjret_stats where fund_id = ? ");
		Object[] riskStatsAjdExArray =  queryOne(selectSql.toString(),new Object[]{fundId});
		if(riskStatsAjdExArray != null && riskStatsAjdExArray.length>0 && riskStatsAjdExArray[0]!=null){
			return riskStatsAjdExArray[0];
		}
		return null;
	}
	
	public Object[] getRiskadjretStats(Object fundId,Object date){
		StringBuffer selectSql = new StringBuffer("select sharperatio_1y,sharperatio_2y,sharperatio_3y,sharperatio_incep,");
		selectSql.append("calmarratio_1y,calmarratio_2y,calmarratio_3y,calmarratio_incep,");
		selectSql.append("sortinoratio_1y,sortinoratio_2y,sortinoratio_3y,sortinoratio_incep ");
		selectSql.append(" from pvo_fund_riskadjret_stats where fund_id = ? and end_date = ? ");
		return queryOne(selectSql.toString(),new Object[]{fundId,date});
	}	
}
