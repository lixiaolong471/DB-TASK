package com.qianhtj.task.bean;

public class FundRiskadjretStats {
	
	public Object  fundId;
	
	/*
	 * 
	 *  年化收益率 0
		年化波动率 1
		最大回撤 2
		夏普比率 3
		CLMAR比率 4
		索提诺比率 5
		月胜率 6
	 */
	public Object  type;
	
	public Object y1;
	
	public Object y2;
	
	public Object y3;
	
	public Object y5;
	
	public Object all;
	//更新时间
	public Object updateTime;
	
	public static FundRiskadjretStats[] getFundRiskadjretStats(Object fundId){
		FundRiskadjretStats[] array = new FundRiskadjretStats[7];
		for(int i = 0;i<array.length;i++){
			array[i] = new FundRiskadjretStats();
			array[i].type = i;
			array[i].fundId = fundId;
		}
		return array;
	}
}
