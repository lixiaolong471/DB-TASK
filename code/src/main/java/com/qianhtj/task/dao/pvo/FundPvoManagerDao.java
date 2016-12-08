package com.qianhtj.task.dao.pvo;
import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;
public class FundPvoManagerDao extends BaseDao{	
		
	public FundPvoManagerDao(){
		super(Context.DATASOUREC_LOCATION);
	}
	
	public Object[] getFundManager(Object fundid){
		StringBuffer selectSql = new StringBuffer("select fund_manager_id from pvo_fund_manager_mapping where fund_id= ? ");
		Object[] managerMappingArray =  queryOne(selectSql.toString(),new Object[]{fundid});
		if(managerMappingArray != null && managerMappingArray.length >0){
			selectSql = new StringBuffer("select personnel_id,personnel_name,avatar,profile from pvo_personnel_info where personnel_id = ? ");
			return  queryOne(selectSql.toString(),new Object[]{managerMappingArray[0]});
		}
		return null;
	}
	
	/**
	 * 获取关键人物信息
	 * @param companyId
	 * @return
	 */
	public Object[] findKeyPersonByCompanyId(Object companyId){
		StringBuffer selectSql = new StringBuffer("select personnel_id,personnel_name,avatar,profile from pvo_personnel_info where key_figure=1 and company_id=? ");
		return queryOne(selectSql.toString(),new Object[]{companyId});
		
	}
}
