package com.qianhtj.task.dao.pvo;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class FundPvoStrategyDao extends BaseDao {
	
	public FundPvoStrategyDao(){
		super(Context.DATASOUREC_LOCATION);
	}

	
	public Object[] getStrategy(Object fundId){
		StringBuffer selectSql = new StringBuffer("select strategy,substrategy from pvo_fund_strategy where fund_id = ? ");
		return queryOne(selectSql.toString(),new Object[]{fundId}); 
	}
}
