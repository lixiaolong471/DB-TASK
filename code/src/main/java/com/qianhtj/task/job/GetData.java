package com.qianhtj.task.job;

import java.util.Date;

public interface GetData {
	
	void init(Date startDate, Date end);

    boolean isRun();

	void getData();

    void stop();

    void pauseOrRestart();

    int getSumCount();

    int getIndex();
}
