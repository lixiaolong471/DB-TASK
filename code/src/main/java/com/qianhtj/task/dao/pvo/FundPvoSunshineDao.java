package com.qianhtj.task.dao.pvo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class FundPvoSunshineDao extends BaseDao {
	
	public FundPvoSunshineDao(){
		super(Context.DATASOUREC_LOCATION);
	}
	
	public List<Object[]> getListInIds(Object ...ids){
		List<Object> queryParam = new ArrayList<Object>();
		//pvo_fund_info，插入主表消息
		StringBuffer selectSql = new StringBuffer("select ");
		selectSql.append("a.fund_id,a.fund_name,a.fund_short_name,a.inception_date,a.advisor_id,a.open_day,");
		selectSql.append("a.lockup_period,a.min_investment_share,a.subscription_fee,a.management_fee,a.redemption_fee,a.performance_fee, ");
		selectSql.append("a.custodian_id,a.fund_type,a.updatetime,a.initial_unit_value ");
		selectSql.append(" from pvo_fund_info a,pvo_fund_status b where a.fund_id=b.fund_id");
		StringBuffer idwhere = new StringBuffer("");
		for(Object id:ids){
			if(id != null){
				idwhere.append(" or a.fund_id = ? ");
				queryParam.add(id);
			}
		}
		if(idwhere.length()>0){
			idwhere.insert(0," and ( 1=2 ");
			idwhere.append(" ) ");
		}
		selectSql.append(idwhere);
		selectSql.append("and ( b.fund_status = '1' or b.fund_status = '2' or b.fund_status = '3') ");
		//查询基金详情
		List<Object[]> list = query(selectSql.toString(),queryParam.toArray());
		return list;
	}
	
	public List<Object[]> getListByStatus(Date startDate,Date endDate){
		List<Object> queryParam = new ArrayList<Object>();
		//pvo_fund_info，插入主表消息
		StringBuffer selectSql = new StringBuffer("select ");
		selectSql.append("a.fund_id,a.fund_name,a.fund_short_name,a.inception_date,a.advisor_id,a.open_day,");
		selectSql.append("a.lockup_period,a.min_investment_share,a.subscription_fee,a.management_fee,a.redemption_fee,a.performance_fee, ");
		selectSql.append("a.custodian_id,a.fund_type,a.updatetime,a.initial_unit_value ");
		selectSql.append(" from pvo_fund_info a,pvo_fund_status b where a.fund_id=b.fund_id ");
		if(startDate != null){
			selectSql.append(" and a.updatetime >= ? ");
			queryParam.add(startDate);
		}
		if(endDate != null){
			selectSql.append(" and a.updatetime <= ?");
			queryParam.add(endDate);
		}
		selectSql.append("and ( b.fund_status = '1' or b.fund_status = '2' or b.fund_status = '3') ");
		//查询基金详情
		List<Object[]> list = query(selectSql.toString(),queryParam.toArray());
		return list;
	}
	
	public List<Object[]> getList(Date startDate,Date endDate){
		List<Object> queryParam = new ArrayList<Object>();
		//pvo_fund_info，插入主表消息
		StringBuffer selectSql = new StringBuffer("select ");
		selectSql.append("fund_id,fund_name,fund_short_name,inception_date,advisor_id,open_day,");
		selectSql.append("lockup_period,min_investment_share,subscription_fee,management_fee,redemption_fee,performance_fee, ");
		selectSql.append("custodian_id,fund_type,updatetime,initial_unit_value ");
		selectSql.append(" from pvo_fund_info where 1=1 ");
		if(startDate != null){
			selectSql.append(" and updatetime >= ? ");
			queryParam.add(startDate);
		}
		if(endDate != null){
			selectSql.append(" and updatetime <= ?");
			queryParam.add(endDate);
		}
		//查询基金详情
		List<Object[]> list = query(selectSql.toString(),queryParam.toArray());
		return list;
	}
	
	
	public Date getMaxDate(){
		StringBuffer selectSql = new StringBuffer("");
		selectSql.append("select max(updatetime) from pvo_fund_info ");
		Object[] data = queryOne(selectSql.toString(),new Object[]{});
		return (Date)data[0];
	}
	
	public Date getMinDate(){
		StringBuffer selectSql = new StringBuffer("");
		selectSql.append("select min(updatetime) from pvo_fund_info ");
		Object[] data = queryOne(selectSql.toString(),new Object[]{});
		return (Date)data[0];
	}

}
