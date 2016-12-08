package com.qianhtj.task.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SunshinepublicFund{
	 //数据ID
	 public Object id;
	 //阳光私募id
	 public Object fundId;
	 //产品名称
	 public Object  fundName;
	 //产品简称
	 public Object fundShortName;
	  
	 //最新净值、当前基金净资产与基金总份额的比值
	 public Object nav;
	 
	 //基金最近1年的累计收益
	 public Object ret1y;
	 
	 //现在的每一份的价格加上其他分红或者派出的收益的综合，一般是从发行之初开始算到现在的累计
	 public Object cumulativeNavWithdrawal;
	 
	 //基金的正式运作日期
	 public Object  inceptionDate;
	 
	 //基金最近6个月的累计收益
	 public Object  sixm;
	 
	 //今年收益/基金从今年1月1日起至今的累计收益。
	 public Object retYtd;
	 
	 //运行时间,根据成立日期计算,天。
	 public Object  runtime;
	 
	 //基金从发行之日起至今的累计收益，包括了分给私募的提成
	 public Object  retincep;
	 
	 //基金经理id
	 public Object fundManagerId;
	 
	 //基金经理名称
	 public Object fundManagerName;
	 
	 //由pvo_company_info获取ID，再获取 company_name到页面
	 public Object  advisorId;
	 
	 // 基金公司名称 、 由pvo_company_info获取名称，再获取 company_name到页面
	 public Object  advisorName;
	 
	// 基金公司名称 、 由pvo_company_info获取名称，再获取 company_short_name到页面
	 public Object  advisorShortName;
	 
	 //'融智策略分类，1-股票策略，2-宏观策略，3-管理期货，4-事件驱动，5-相对价值策略，6-债券策略，7-组合基金，8-复合策略，-1-其它策略',
	 public Object strategy;
	 //分级策略
	 public Object substrategy;
	 
	 //'基金运行状态：1-募集中、2-开放运行、3-封闭运行、4-提前清算、5-到期清算、6-发行失败、7-更换管理人、-1-其他'
	 public Object fundStatus;

	 
	 //基金允许申购、赎回、转换或定投期间的交易日
	 public Object  openDay;
	
	 //封闭期，单位：月，-1：不确定，0：无封闭期
	 public Object  lockupPeriod;
	 //认购起点
	 public Object  minInvestmentShare;
	 
	 //最高认购费，量纲<%>
	 public Object  subscriptionFee;
	 
	 //'管理费，量纲<%>
	 public Object   managementFee;
	 
	 //最高赎回费，量纲<%>
	 public Object   redemptionFee;
	 
	 //最高绩效费，量纲<%>
	 public Object   performanceFee;
	 
	 //净值日期
	 public Object priceDate;
	 
	 //头像
	 public Object headPortrait;
	 
	 //沪深300收盘价
	 public Object close;
	 //涨跌幅
	 public Object change;
	 
	 //复权净值
	 public Object cumulativeNav;
	 
	 //标签
	 public Object tag;
	 
	 //总排行
	 public Object totalOrder;
	 
	 //今年排行
	 public Object thisYearOrder;
	 
	 //去年排行
	 public Object lastYearOrder;
	 
	 public Object valid;
	 
	 public Object updateTime;
	 //基金经理介绍
	 public Object fundManagerProfile;
	 //基金经理logo
	 public Object fundManagerLogo;
	 //公司介绍
	 public Object advisorProfile;
	 //基金类型
	 public Object fundType;
	 //托管银行
	 public Object custodianId;
	 //托管银行名称
	 public Object custodianName;
	//托管银行简称名称
	 public Object custodianShortName;
	 //seo关键字
	 public Object seoKeywords;
	 //seo描述
	 public Object seoDescription;
	 //seo标题
	 public Object seoTitle;
	 //历史统计数据
	 public FundRiskadjretStats[] hisFundStats;
	 //历史收益数据
	 public FundHisPerformance[] hisPerformance;
	 //历史净值数据
	 public List<FundPvoNav> navs;
	 //初始单位面值
	 public Object initialUnitValue;
	 //上下架
	 public Object upDown;
	 
	 /*
	  *
	  * fund_id,fund_name,fund_short_name,inception_date,advisor_id,open_day,");
			selectSql.append("lockup_period,min_investment_share,subscription_fee,management_fee,redemption_fee,performance_fee
	  *  设置 fundInfo表数据到基金数据表中。
	  */
	public void setBaseFundInfo(Object[] data) {
		this.fundId = data[0];
		this.fundName = data[1];
		this.fundShortName = data[2];
		this.inceptionDate = data[3];
		this.advisorId = data[4];
		this.openDay = data[5];
		this.lockupPeriod = data[6];
		this.minInvestmentShare = data[7];
		this.subscriptionFee = data[8];
		this.managementFee = data[9];
		this.redemptionFee = data[10];
		this.performanceFee = data[11];
		this.custodianId = data[12];
		this.fundType = data[13];
		//更新时间
		this.updateTime = data[14];
		this.initialUnitValue = data[15];
		this.upDown = 1;
		if(this.inceptionDate != null){
			Calendar date = Calendar.getInstance();
			Calendar now = Calendar.getInstance();
			date.setTime(((Date)this.inceptionDate));
			int dateMonth = date.get(Calendar.MONTH);
			int nowMonth = now.get(Calendar.MONTH);
			int year = now.get(Calendar.YEAR) - date.get(Calendar.YEAR);
			int month = 0;
			if(nowMonth < dateMonth){
				year = year - 1;
				nowMonth += 12;
			}
			month = nowMonth - dateMonth;
			if(year<1){
				this.runtime = month+"月";
			}else{
				this.runtime = year+"年"+month+"月";
			}
			
		}
		this.hisFundStats = FundRiskadjretStats.getFundRiskadjretStats(this.fundId);
		this.hisPerformance = FundHisPerformance.getInstances(this.fundId);
		this.navs = new ArrayList<FundPvoNav>();
	}

}
