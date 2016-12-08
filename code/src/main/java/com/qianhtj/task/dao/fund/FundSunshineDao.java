package com.qianhtj.task.dao.fund;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.Context;
import com.qianhtj.task.dao.sys.IdDao;

public class FundSunshineDao extends BaseDao {
	
	public FundSunshineDao(){
		super(Context.DATASOUREC_DATA);
	}

	/**
	 * 清空表数据
	 * @return
	 */
	public int empty(){
		StringBuffer updateSql = new StringBuffer(" truncate table si_sunshine_private_fund");
		return update(updateSql.toString(), new Object[]{});
	}
	
	/**
	 * 查询公司收益情况
	 * @param id
	 * @return
	 */
	public Object[] findiIncepByCompanyId(Object id){
		//收益
		StringBuffer selectSql = new StringBuffer("select sum(fund.ret_incep),sum(fund.ret_ytd),count(1) from si_sunshine_private_fund fund where fund.advisor_id = ? ");
		return  queryOne(selectSql.toString(),new Object[]{id});
	}
	
	/**
	 * 查询代表产品
	 * @param id
	 * @return
	 */
	public Object[] findRepProductByCompanyId(Object id){
		//代表产品
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-6);
		StringBuffer selectSql = new StringBuffer("select max(fund.ret_incep),fund.id,fund.fund_name,fund.price_date,fund.nav,fund.inception_date,fund.ret_incep,fund.ret_ytd from si_sunshine_private_fund fund where fund.advisor_id = ? and inception_date < ? ");
		return queryOne(selectSql.toString(),new Object[]{id,cal.getTime()});
	}
	
	/**
	 * 获取盈利产品数
	 * @param id
	 * @return
	 */
	public Object[] findIncepCountByCompanyId(Object id){
		//盈利产品数
		StringBuffer selectSql = new StringBuffer("select count(1) from si_sunshine_private_fund fund where fund.advisor_id = ? and fund.ret_incep > 0 ");
		return  queryOne(selectSql.toString(),new Object[]{id});
		
	}

	
	public List<SunshinepublicFund> getList(Date startDate,Date endDate){
		List<Object> queryParam = new ArrayList<Object>();
		//pvo_fund_info，插入主表消息
		StringBuffer selectSql = new StringBuffer("select ");
		selectSql.append("id,fund_name,fund_short_name,inception_date,advisor_id,open_day,");//6
		selectSql.append("lockup_period,min_investment_share,subscription_fee,management_fee,redemption_fee,performance_fee,");//6
		selectSql.append("ret_incep,ret_ytd,ret_1y,6m,this_year_order,last_year_order,");//6
		selectSql.append("fund_manager_name,head_portrait,advisor_name,price_date,isvalid,nav,");//6
		selectSql.append("cumulative_nav_withdrawal,cumulative_nav,close,fund_manager_id,advisor_profile,custodian_id,");//6
		selectSql.append("custodian_name,fund_type,fund_status,fund_manager_profile,fund_manager_logo,update_time,");//6
		selectSql.append("strategy,run_time,advisor_short_name,custodian_short_name,seo_title,seo_keywords,");//6
		selectSql.append("seo_description,sub_strategy,initial_unit_value,fund_id "); //4
		selectSql.append(" from si_sunshine_private_fund where 1=1 ");
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
		List<SunshinepublicFund> datas = new ArrayList<SunshinepublicFund>();
		for(Object[] array:list){
			datas.add(covSunshinepublicFund(array));
		}
		return datas;
	}
	
	private int save(SunshinepublicFund fund){
		StringBuffer updateSql = new StringBuffer("");
		updateSql.append("insert into si_sunshine_private_fund(");
		updateSql.append("id,fund_id,fund_name,fund_short_name,inception_date,advisor_id,open_day,");//6
		updateSql.append("lockup_period,min_investment_share,subscription_fee,management_fee,redemption_fee,performance_fee,");//6
		updateSql.append("ret_incep,ret_ytd,ret_1y,6m,this_year_order,last_year_order,");//6
		updateSql.append("fund_manager_name,head_portrait,advisor_name,price_date,isvalid,nav,");//6
		updateSql.append("cumulative_nav_withdrawal,cumulative_nav,close,fund_manager_id,advisor_profile,custodian_id,");//6
		updateSql.append("custodian_name,fund_type,fund_status,fund_manager_profile,fund_manager_logo,update_time,strategy,");
		updateSql.append("run_time,advisor_short_name,custodian_short_name,seo_title,seo_keywords,seo_description,sub_strategy,initial_unit_value,up_down) ");
		updateSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		return update(updateSql.toString(),new Object[]{fund.id,fund.fundId,fund.fundName,fund.fundShortName,fund.inceptionDate,fund.advisorId,fund.openDay,
				fund.lockupPeriod,fund.minInvestmentShare,fund.subscriptionFee,fund.managementFee,fund.redemptionFee,fund.performanceFee,
				fund.retincep,fund.retYtd,fund.ret1y,fund.sixm,fund.thisYearOrder,fund.lastYearOrder,fund.fundManagerName,fund.headPortrait,fund.advisorName,
				fund.priceDate,fund.valid,fund.nav,fund.cumulativeNavWithdrawal,fund.cumulativeNav,fund.close,fund.fundManagerId,
				fund.advisorProfile,fund.custodianId,fund.custodianName,fund.fundType,fund.fundStatus,fund.fundManagerProfile,fund.fundManagerLogo,fund.updateTime,
				fund.strategy,fund.runtime,fund.advisorShortName,fund.custodianShortName,fund.seoTitle,fund.seoKeywords,fund.seoDescription,fund.substrategy,
				fund.initialUnitValue,fund.upDown});
			
	}
	
	private int update(SunshinepublicFund fund){
		StringBuffer updateSql = new StringBuffer("");
		updateSql.append("update si_sunshine_private_fund set ");
		updateSql.append("fund_id=?,fund_name=?,fund_short_name=?,inception_date=?,advisor_id=?,open_day=?,");
		updateSql.append("lockup_period=?,min_investment_share=?,subscription_fee=?,management_fee=?,redemption_fee=?,performance_fee=?,");
		updateSql.append("ret_incep=?,ret_ytd=?,ret_1y=?,6m=?,this_year_order=?,last_year_order=?,");
		updateSql.append("fund_manager_name=?,head_portrait=?,advisor_name=?,");
		updateSql.append("price_date=?,isvalid=?,nav=?,cumulative_nav_withdrawal=?,cumulative_nav=?,close=?,dchange=IFNULL(nav,(?-nav)/nav),");
		updateSql.append("fund_manager_id=?,advisor_profile=?,custodian_id=?,custodian_name=?,fund_type=?,fund_status=?,fund_manager_profile=?,fund_manager_logo=?,update_time=?,");
		updateSql.append("strategy=?,run_time=?,advisor_short_name=?,custodian_short_name=?,seo_title=?,seo_keywords=?,seo_description=?,sub_strategy=?,initial_unit_value=? ");
		
		updateSql.append(" where fund_id = '"+ fund.fundId +"'");
		return update(updateSql.toString(),new Object[]{fund.fundId,fund.fundName,fund.fundShortName,fund.inceptionDate,fund.advisorId,fund.openDay,
				fund.lockupPeriod,fund.minInvestmentShare,fund.subscriptionFee,fund.managementFee,fund.redemptionFee,fund.performanceFee,
				fund.retincep,fund.retYtd,fund.ret1y,fund.sixm,fund.thisYearOrder,fund.lastYearOrder,fund.fundManagerName,fund.headPortrait,fund.advisorName,
				fund.priceDate,fund.valid,fund.nav,fund.cumulativeNavWithdrawal,fund.cumulativeNav,fund.close,fund.nav,fund.fundManagerId,fund.advisorProfile,
				fund.custodianId,fund.custodianName,fund.fundType,fund.fundStatus,fund.fundManagerProfile,fund.fundManagerLogo,fund.updateTime,
				fund.strategy,fund.runtime,fund.advisorShortName,fund.custodianShortName,fund.seoTitle,fund.seoKeywords,fund.seoDescription,fund.substrategy,fund.initialUnitValue});
			
	}
	
	public int saveOrUpdate(SunshinepublicFund fund){
		String existSql = "select count(1) from si_sunshine_private_fund where fund_id = ? ";
		Object[] countArray =  queryOne(existSql,new Object[]{fund.fundId});
		if(countArray != null && countArray.length >0 && countArray[0] != null && ((Long)countArray[0]) != 0 ){//update
			return update(fund);
		}else{ //insert
			IdDao idDao = IdDao.getInstancess();
			fund.id = idDao.getFundId();
			return save(fund);
			
		}
	}
	
	private SunshinepublicFund covSunshinepublicFund(Object[] array){
		SunshinepublicFund fund = new SunshinepublicFund();
		fund.id = array[0];
		fund.fundName = array[1];
		fund.fundShortName = array[2];
		fund.inceptionDate = array[3];
		fund.advisorId = array[4];
		fund.openDay = array[5];
		
		fund.lockupPeriod = array[6];
		fund.minInvestmentShare = array[7];
		fund.subscriptionFee = array[8];
		fund.managementFee = array[9];
		fund.redemptionFee = array[10];
		fund.performanceFee = array[11];
		
		fund.retincep = array[12];
		fund.retYtd = array[13];
		fund.ret1y = array[14];
		fund.sixm = array[15];
		fund.thisYearOrder = array[16];
		fund.lastYearOrder = array[17];
		
		fund.fundManagerName = array[18];
		fund.headPortrait = array[19];
		fund.advisorName = array[20];
		fund.priceDate = array[21];
		fund.valid = array[22];
		fund.nav = array[23];
		
		fund.cumulativeNavWithdrawal = array[24];
		fund.cumulativeNav = array[25];
		fund.close = array[26];
		fund.fundManagerId = array[27];
		fund.advisorProfile = array[28];
		fund.custodianId = array[29];
		//custodian_name,fund_type,fund_status,fund_manager_profile,fund_manager_logo,update_time,
		fund.custodianName = array[30];
		fund.fundType = array[31];
		fund.fundStatus = array[32];
		fund.fundManagerProfile = array[33];
		fund.fundManagerLogo = array[34];
		fund.updateTime = array[35];
		
		
		//strategy,run_time,advisor_short_name,custodian_short_name,seo_title,seo_keywords,
		fund.strategy = array[36];
		fund.runtime = array[37];
		fund.advisorShortName = array[38];
		fund.custodianShortName = array[39];
		fund.seoTitle = array[40];
		fund.seoKeywords = array[41];
		
		
		//seo_description,sub_strategy,initial_unit_value,fund_id
		fund.seoDescription = array[42];
		fund.substrategy = array[43];
		fund.initialUnitValue = array[44];
		fund.fundId = array[45];
		return fund;
	}
}
