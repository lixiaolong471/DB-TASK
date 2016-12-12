package com.qianhtj.task.get;

import com.qianhtj.task.job.GetData;
import com.qianhtj.task.utils.DateUtils;
import com.sun.istack.internal.logging.Logger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lixl on 2016/12/8.
 */
public abstract class GeneralGetData implements GetData {

    private Logger logger = Logger.getLogger(GeneralGetData.class);

    public volatile AtomicInteger sumcount = new AtomicInteger(0);
    public volatile AtomicInteger index = new AtomicInteger(0);
    public volatile AtomicBoolean isRun = new AtomicBoolean(false);
    protected List<Object[]> dataList;

    private boolean logable = true;


    @Override
    public boolean isRun() {
        return isRun.get();
    }

    @Override
    public void init(final Date startDate, final Date endDate) {
        isRun.set(true);
        sumcount.set(0);
        index.set(0);

        new Thread(){
            @Override
            public void run() {
                super.run();
                getData(startDate,endDate);
            }
        }.start();

    }

    public void getData(Date startDate,Date endDate){
        dataList =  getList(startDate,endDate);
        System.out.println("采集总数"+dataList.size()+","+startDate+"~"+endDate);
        sumcount.set(dataList.size());
        processList();
    }

    public abstract  List<Object[]> getList(Date startDate,Date endDate);

    public void processList(){
        if(dataList != null && dataList.size() > 0)  {
            for(;index.get() < dataList.size();){
                Object[] data = dataList.get(index.getAndIncrement());
                process(data);
                if(!isRun.get()){
                    break;
                }
            }
        }
    }

    public abstract void process(Object[] data);

    @Override
    public void getData() {
        isRun.set(true);
        sumcount.set(0);
        index.set(0);
        getData(DateUtils.getYesterday(),null);
    }

    @Override
    public void stop() {
        isRun.set(false);
        dataList = new ArrayList<>();
        sumcount.set(0);
        index.set(0);
    }

    @Override
    public void pauseOrRestart() {
        if(isRun.get()){
            isRun.set(false);
        }else{
            isRun.set(true);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    System.out.println("任务重新启动");
                    processList();
                }
            }.start();
        }
    }



    @Override
    public int getSumCount(){
        return sumcount.get();
    }

    @Override
    public int getIndex(){
        return index.get();
    }

    public void log(String message){
        if(logable){
            System.out.println(message);
        }
    }
}
