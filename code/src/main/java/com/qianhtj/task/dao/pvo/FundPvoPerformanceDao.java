package com.qianhtj.task.dao.pvo;

import java.util.Date;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class FundPvoPerformanceDao extends BaseDao {
		
	public FundPvoPerformanceDao(){
		super(Context.DATASOUREC_LOCATION);
	}
	
	public Date getMaxDate(Object fundId){
		StringBuffer selectSql = new StringBuffer("select max(price_date) from pvo_fund_performance where fund_id = ? ");
		Object[] maxDate = queryOne(selectSql.toString(),new Object[]{fundId});
		if(maxDate != null && maxDate.length>0 && maxDate[0] != null){
			return  (Date)maxDate[0];
		}
		return null;
	}
	
	public Object[] getPerformance(Object fundId,Object pricateDate){
		//ret_incep,ret_>ytd,ret_1y,ret_6m,ret_2y,ret_3y,
		StringBuffer selectSql = new StringBuffer("select ret_ytd,ret_1m,ret_3m,ret_6m,ret_1y,ret_3y,ret_5y,ret_incep,");//8
		selectSql.append("ret_ytd_bm1,ret_1m_bm1,ret_3m_bm1,ret_6m_bm1,ret_1y_bm1,ret_3y_bm1,ret_5y_bm1,ret_incep_bm1, ");//8
		selectSql.append("ret_incep,ret_ytd,ret_1y,ret_6m,ret_2y_a,ret_3y_a,ret_5y_a,ret_incep_a ");//8
		selectSql.append(" from pvo_fund_performance where fund_id= ? and price_date = ? ");
		Object[] performanceArray = queryOne(selectSql.toString(),new Object[]{fundId,pricateDate});
		return performanceArray;
	}
	
	public Object[] getPerformanceStage(Object fundId,Object pricateDate){
		//ret_incep,ret_>ytd,ret_1y,ret_6m,ret_2y,ret_3y,
		StringBuffer selectSql = new StringBuffer("select ret_ytd,ret_1m,ret_3m,ret_6m,ret_1y,ret_3y,ret_5y,ret_incep,");//8
		selectSql.append("ret_ytd_bm1,ret_1m_bm1,ret_3m_bm1,ret_6m_bm1,ret_1y_bm1,ret_3y_bm1,ret_5y_bm1,ret_incep_bm1, ");//8
		selectSql.append("ret_incep,ret_ytd,ret_1y,ret_6m,ret_2y_a,ret_3y_a,ret_5y_a,ret_incep_a ");//8
		selectSql.append(" from pvo_fund_performance where fund_id= ? and price_date = ? ");
		Object[] performanceArray = queryOne(selectSql.toString(),new Object[]{fundId,pricateDate});
		return performanceArray;
	}
	
	
}
