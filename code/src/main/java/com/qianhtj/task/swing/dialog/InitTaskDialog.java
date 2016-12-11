package com.qianhtj.task.swing.dialog;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.DateFormatter;

import com.qianhtj.task.get.CovCompany;
import com.qianhtj.task.get.CovMarketIndex;
import com.qianhtj.task.get.FastCovSunshine;
import com.qianhtj.task.job.GetData;
import com.qianhtj.task.utils.DateUtils;
import org.jdesktop.swingx.JXDatePicker;

import com.qianhtj.task.utils.SysFont;

public class InitTaskDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final int LABLE_WIDTH = 50;
	private static final int LABLE_HEIGHT = 30;

	private static final int TEXT_WIDTH = 100;
	private static final int TEXT_HEIGHT = LABLE_HEIGHT;

	private static final int BUTTON_WIDTH = 50;
	private static final int BUTTON_HEIGHT = LABLE_HEIGHT;
	
	public DateFormatter df;

	private static final int _1_X = 20;
	private static final int _2_X = 80;
	private static final int _3_X = 190;
	private static final int _4_X = 300;
	private static final int _5_X = 360;
	private static final int _6_X = 420;
    private static final int _7_X = 480;


	private static final int _1_Y = 20;
	private static final int _2_Y = 60;
	private static final int _3_Y = 100;
	
	private static final int _CLOSE_X = 210;
	
	private static final int _CLOSE_Y = 200;

	private static final int CLOSE_WIDTH = 100;
	private static final int CLOSE_HEIGHT = BUTTON_HEIGHT;
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 300;

    private GetData covMarketIndex;
    private GetData fastCovSunshine;
    private GetData covCompany;
	
	private JDialog dialog;

	boolean isCpPause = false;

	public InitTaskDialog(JFrame parent) {
		df = new DateFormatter();
		DateFormat format = new SimpleDateFormat("YYYY-MM-DD");
		df.setFormat(format );
		dialog = this;
		Point location = new Point();
		location.setLocation((parent.getLocation().getX() + (parent.getWidth() - WIDTH) / 2),
				(parent.getLocationOnScreen().getY() + (parent.getHeight() - HEIGHT) / 2));
		this.setLocation(location);
		setSize(WIDTH, HEIGHT);
		setLayout(null);
		setResizable(false);
		setTitle("数据初始化");

		adCp();
		adGs();
		adHq();
		
		JButton closeBt = new JButton("关闭");
		closeBt.setFont(SysFont.Infolab);
		closeBt.setBounds(_CLOSE_X, _CLOSE_Y, CLOSE_WIDTH, CLOSE_HEIGHT);
		closeBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		
		this.add(closeBt);
		
		this.setModal(true);

        covMarketIndex = new CovMarketIndex();
        fastCovSunshine = new FastCovSunshine("000300.SH");
        covCompany = new CovCompany();

	}
	
	private void adCp(){
		JLabel label = new JLabel("产品:");
		label.setFont(SysFont.Infolab);
		label.setBounds(_1_X, _1_Y, LABLE_WIDTH, LABLE_HEIGHT);

		final JXDatePicker startDateText = new JXDatePicker();
		startDateText.setFont(SysFont.Infolab);
		startDateText.setBounds(_2_X, _1_Y, TEXT_WIDTH, TEXT_HEIGHT);
		startDateText.setFormats("yyyy-MM-dd");
		startDateText.setDate(DateUtils.addDay(DateUtils.getToday(),-10));

        final JXDatePicker endDateText = new JXDatePicker();
		endDateText.setFont(SysFont.Infolab);
		endDateText.setBounds(_3_X, _1_Y, TEXT_WIDTH, TEXT_HEIGHT);
		endDateText.setFormats("yyyy-MM-dd");
		endDateText.setDate(DateUtils.getToday());

        final JLabel statusLabel = new JLabel();
        statusLabel.setFont(SysFont.Infolab);
        statusLabel.setBounds(_4_X, _1_Y, TEXT_WIDTH, TEXT_HEIGHT);


		final JButton startBt = new JButton("启动");
        final JButton stopBt = new JButton("停止");
        final JButton pauseBt = new JButton("暂停");

		startBt.setFont(SysFont.Infolab);
		startBt.setBounds(_5_X, _1_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        startBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBt.setEnabled(false);
                pauseBt.setEnabled(true);
                stopBt.setEnabled(true);
				System.out.println(DateUtils.format(startDateText.getDate())+","+DateUtils.format(endDateText.getDate()));
                fastCovSunshine.init(startDateText.getDate(),endDateText.getDate());
				printCpView(statusLabel);
            }
        });


		stopBt.setFont(SysFont.Infolab);
		stopBt.setBounds(_6_X, _1_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		stopBt.setEnabled(false);
        stopBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopBt.setEnabled(false);
                startBt.setEnabled(true);
                pauseBt.setEnabled(false);
                fastCovSunshine.stop();
            }
        });


		pauseBt.setFont(SysFont.Infolab);
		pauseBt.setBounds(_7_X, _1_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		pauseBt.setEnabled(false);

        pauseBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBt.setEnabled(false);
                stopBt.setEnabled(false);
				isCpPause = !isCpPause;
				pauseBt.setText(isCpPause? "继续":"暂定");
                fastCovSunshine.pauseOrRestart();
				printCpView(statusLabel);
            }
		});
		
		this.add(label);
		this.add(startDateText);
		this.add(endDateText);
        this.add(statusLabel);

		this.add(startBt);
		this.add(stopBt);
		this.add(pauseBt);
	}

	void printCpView(final JLabel statusLabel){
		new Thread(){

			@Override
			public void run() {

				super.run();
				System.out.println(fastCovSunshine.isRun() + "|--|" + fastCovSunshine.getIndex() + "|--|" + fastCovSunshine.getSumCount());
				while(fastCovSunshine.isRun()){
					if(fastCovSunshine.getIndex() > 0 && fastCovSunshine.getSumCount() > 0){
						int process = fastCovSunshine.getIndex() * 10000/fastCovSunshine.getSumCount();
						statusLabel.setText(process/100f+"%");
						if(fastCovSunshine.getIndex()>=fastCovSunshine.getSumCount()){
							fastCovSunshine.stop();
							statusLabel.setText("执行完成");
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}.start();
	}
	
	private void adGs(){
		JLabel label = new JLabel("公司:");
		label.setFont(SysFont.Infolab);
		label.setBounds(_1_X, _2_Y, LABLE_WIDTH, LABLE_HEIGHT);

		JXDatePicker startDateText = new JXDatePicker();
		startDateText.setFont(SysFont.Infolab);
		startDateText.setBounds(_2_X, _2_Y, TEXT_WIDTH, TEXT_HEIGHT);
		startDateText.setFormats("yyyy-MM-dd");
		startDateText.setDate(DateUtils.addDay(DateUtils.getToday(),-365));

		JXDatePicker endDateText = new JXDatePicker();
		endDateText.setFont(SysFont.Infolab);
		endDateText.setBounds(_3_X, _2_Y, TEXT_WIDTH, TEXT_HEIGHT);
		endDateText.setFormats("yyyy-MM-dd");
		endDateText.setDate(DateUtils.getToday());


		JButton startBt = new JButton("启动");
		startBt.setFont(SysFont.Infolab);
		startBt.setBounds(_5_X, _2_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

		JButton stopBt = new JButton("停止");
		stopBt.setFont(SysFont.Infolab);
		stopBt.setBounds(_6_X, _2_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		stopBt.setEnabled(false);
		
		JButton pauseBt = new JButton("暂停");
		pauseBt.setFont(SysFont.Infolab);
		pauseBt.setBounds(_7_X, _2_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		pauseBt.setEnabled(false);
		
		this.add(label);
		this.add(startDateText);
		this.add(endDateText);
		this.add(startBt);
		this.add(stopBt);
		this.add(pauseBt);
	}

	private void adHq(){
		JLabel label = new JLabel("行情:");
		label.setFont(SysFont.Infolab);
		label.setBounds(_1_X, _3_Y, LABLE_WIDTH, LABLE_HEIGHT);

		JXDatePicker startDateText = new JXDatePicker();
		startDateText.setFont(SysFont.Infolab);
		startDateText.setBounds(_2_X, _3_Y, TEXT_WIDTH, TEXT_HEIGHT);
		startDateText.setFormats("yyyy-MM-dd");
		startDateText.setDate(DateUtils.addDay(DateUtils.getToday(),-365));


		JXDatePicker endDateText = new JXDatePicker();
		endDateText.setFont(SysFont.Infolab);
		endDateText.setBounds(_3_X, _3_Y, TEXT_WIDTH, TEXT_HEIGHT);
		endDateText.setFormats("yyyy-MM-dd");
		endDateText.setDate(DateUtils.getToday());

		JButton startBt = new JButton("启动");
		startBt.setFont(SysFont.Infolab);
		startBt.setBounds(_5_X, _3_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

		JButton stopBt = new JButton("停止");
		stopBt.setFont(SysFont.Infolab);
		stopBt.setBounds(_6_X, _3_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		stopBt.setEnabled(false);
		
		JButton pauseBt = new JButton("暂停");
		pauseBt.setFont(SysFont.Infolab);
		pauseBt.setBounds(_7_X, _3_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		pauseBt.setEnabled(false);
		
		this.add(label);
		this.add(startDateText);
		this.add(endDateText);
		this.add(startBt);
		this.add(stopBt);
		this.add(pauseBt);
	}

}
