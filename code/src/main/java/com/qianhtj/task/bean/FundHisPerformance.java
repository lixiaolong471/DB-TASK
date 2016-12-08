package com.qianhtj.task.bean;

/**
 * 历史净值表
 * @author lixl
 *
 */
public class FundHisPerformance {
	
		public static final int TYPE_JJYJ   =  0;
		public static final int TYPE_HS300   =  1;
		public static final int TYPE_TLPJ   =  2;
		public static final int TYPE_TLPM   =  3;
		public static final int TYPE_SWPM   =  4;
	
		public Object fundId;
		/*
		 *  基金业绩
			沪深300
			同类平均
			同类排名
			四分位排名

		 */
		public Object retType;
		
		//基金业绩
		public Object fundPerformance;
		//沪深300
		public Object threeCsi;
		//同类平均
		public Object average;
		//同类排名
		public Object absrank;
		//四分位排名
		public Object fourRanking;
		//更新时间
		public Object updateTime;
		
		/**
		 * 获取历史平均业绩表
		 * @param data 
		 * @return
		 */
		public static FundHisPerformance[]  getInstances(Object data){
			FundHisPerformance[] array = new FundHisPerformance[8];
			for(int i = 0;i<array.length;i++){
				array[i] = new FundHisPerformance();
				array[i].retType = i;
				array[i].fundId = data;
			}
			return array;
		}
		
}
