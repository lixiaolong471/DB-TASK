package com.qianhtj.task.main;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.qianhtj.task.dao.Context;

public class Util {
	/**
	 * <p>转换私募拍拍网的地址为本地地址</p>
	 * @param imgPath 私募拍拍网图片存储相对地址
	 * @return
	 */
	public static Object covImg(Object imgPath){
		if(imgPath != null && imgPath != "" && !imgPath.toString().contains(Context.getImgPath())){
			return  Context.getImgPath() + "/" + imgPath;
		}
		return imgPath;
	}
	
	/**
	 * 
	 * <p>将小数位表示的百分比 乘以 一百 得以转换为%显示形式的数字</p>
	 * @param data 小数位形式百分比数
	 * @return
	 */
	public static Object getPercentData(Object data){
		if(data == null && !(data instanceof BigDecimal)){
			return data;
		}
		BigDecimal num = (BigDecimal)data;
		return num.multiply(new BigDecimal(100));
	}
	
	public static Date addDay(Date date, int num) {  
        Calendar startDT = Calendar.getInstance();  
        startDT.setTime(date);  
        startDT.add(Calendar.DAY_OF_MONTH, num);  
        return startDT.getTime();  
    } 
	
}
