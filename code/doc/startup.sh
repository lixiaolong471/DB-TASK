#!/bin/bash
#chkconfig: 5 80 90
#description:check
#
 
# source function library
#. /etc/rc.d/init.d/functions

DIALUP_PID=dialup.pid
start()
{
    echo -n $"Starting $prog: "
    echo "Hello Linux Service check ..."
    nohup java -jar RoxTask.jar >console.log>&1 & new_agent_pid=$!
    echo "$new_agent_pid" > $DIALUP_PID
    tail -f console.log
}
 
stop()
{
       
	 if [ -f dialup.pid ];then
                    SPID=`cat dialup.pid`
					  if [ "$SPID" != "" ];then
						 kill -9  $SPID

						 echo  > $DIALUP_PID
						 echo "stop success"
					  fi
	 fi
}

CheckProcessStata()
{
    CPS_PID=$1
    if [ "$CPS_PID" != "" ] ;then
        CPS_PIDLIST=`ps -ef|grep $CPS_PID|grep -v grep|awk -F" " '{print $2}'`
    else
        CPS_PIDLIST=`ps -ef|grep "$CPS_PNAME"|grep -v grep|awk -F" " '{print $2}'`
    fi

    for CPS_i in `echo $CPS_PIDLIST`
    do
        if [ "$CPS_PID" = "" ] ;then
            CPS_i1="$CPS_PID"
        else
            CPS_i1="$CPS_i"
        fi

        if [ "$CPS_i1" = "$CPS_PID" ] ;then
            #kill -s 0 $CPS_i
            kill -0 $CPS_i >/dev/null 2>&1
            if [ $? != 0 ] ;then
                echo "[`date`] MC-10500: Process $i have Dead" 
                kill -9 $CPS_i >/dev/null 2>&1
               
                return 1
            else
                #echo "[`date`] MC-10501: Process is alive" 
                return 0
            fi
        fi
    done
    echo "[`date`] MC-10502: Process $CPS_i is not exists" 
    return 1
}

status()
{
  SPID=`cat dialup.pid` 
  CheckProcessStata $SPID >/dev/null
                             if [ $? != 0 ];then
                                echo "unixdialup:{$SPID}  Stopped ...."
                              else
                                echo "unixdialup:{$SPID} Running Normal."
                             fi

}
 
restart()
{
    echo "stoping ... "
    stop
    echo "staring ..."
    start
}
 
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    status)
         status
        ;;
    restart)
        restart
        ;;
    *)
        echo $"Usage: $0 {start|stop|restart}"
        RETVAL=1
esac
exit $RETVAL

