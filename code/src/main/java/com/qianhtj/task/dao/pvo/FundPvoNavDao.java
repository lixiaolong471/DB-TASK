package com.qianhtj.task.dao.pvo;

import java.util.List;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;
public class FundPvoNavDao extends BaseDao{	
	
	public FundPvoNavDao(){
		super(Context.DATASOUREC_LOCATION);
	}
	public Object[] getFundNav(Object fundId,Object date){
		StringBuffer selectSql = new StringBuffer("select price_date,isvalid,nav,cumulative_nav_withdrawal,cumulative_nav from pvo_nav where fund_id= ? and price_date = ? ");
		return queryOne(selectSql.toString(),new Object[]{fundId,date});
	}
	
	public Object getMaxDate(Object fundId){
		StringBuffer selectSql = new StringBuffer("select max(price_date) from pvo_nav where fund_id = ? ");
		Object[] maxDate = queryOne(selectSql.toString(),new Object[]{fundId});
		if(maxDate!=null && maxDate.length > 0 && maxDate[0] != null){
			return maxDate[0];
		}
		return null;
	}
	
	public List<Object[]> getList(Object fundId){
		StringBuffer selectSql = new StringBuffer("select price_date,nav,cumulative_nav ");
		selectSql.append("from pvo_nav where fund_id = ?  order by price_date desc ");
		return query(selectSql.toString(),new Object[]{fundId});
	}
	

}
