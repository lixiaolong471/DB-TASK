package com.qianhtj.task.dao.pvo;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class FundPvoStatusDao extends BaseDao{	
	
	public FundPvoStatusDao(){
		super(Context.DATASOUREC_LOCATION);
	}
	
	public Object[] getStatus(Object fundId){
		StringBuffer selectSql = new StringBuffer("select fund_status ");
		selectSql.append("from pvo_fund_status where fund_id = ? ");
		return queryOne(selectSql.toString(),new Object[]{fundId});
	}
}
