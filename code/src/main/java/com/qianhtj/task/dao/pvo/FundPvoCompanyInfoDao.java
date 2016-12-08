package com.qianhtj.task.dao.pvo;

import com.qianhtj.task.dao.BaseDao;

public class FundPvoCompanyInfoDao extends BaseDao {
	
	public FundPvoCompanyInfoDao(){
		super("location");
	}
	
	public Object[] getCompanyInfo(Object fundId){
		StringBuffer selectSql = new StringBuffer("select company_id,company_name,company_short_name,company_profile ");
		selectSql.append("from pvo_company_info where company_id = ? ");
		return  queryOne(selectSql.toString(),new Object[]{fundId});
	}
}
