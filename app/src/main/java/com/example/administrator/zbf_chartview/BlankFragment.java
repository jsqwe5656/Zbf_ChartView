package com.example.administrator.zbf_chartview;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.alibaba.fastjson.JSON;
import com.example.administrator.zbf_chartview.io.ChartDatasBean;
import com.example.administrator.zbf_chartview.io.IOHttpCallBack;
import com.example.administrator.zbf_chartview.io.IOResult;
import com.example.administrator.zbf_chartview.io.MyOkHttp;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 绘制折线图的碎片
 */
public class BlankFragment extends Fragment implements OnChartGestureListener, OnChartValueSelectedListener
{
    private LineChart lineChart;
    private final String url = "http://192.168.1.189:8081/packet/minute/day?date=2017-10-11&userId=4";

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        chartInit(view);
        dataInit();
        return view;
    }

    private List<ChartDatasBean> list;          //折线图整体数据

    private List<String> dateList;              //保存时间数据用作X轴

    private List<Entry> spo2;                   //血氧数据

    private List<Entry> heart;                  //心跳数据

    private List<Entry> resp;                   //呼吸率数据

    private List<Entry> plus;                   //脉搏数据

    private List<Entry> temperature;            //体温数据


    /**
     * 数据初始化
     */
    private void dataInit() {
        MyOkHttp myOkHttp = new MyOkHttp();
        myOkHttp.getJson(url, new IOHttpCallBack()
        {
            @Override
            public void getIOHttpCallBack(String result, int code) {
                if (code == 0)
                {
                    list = JSON.parseArray(result, ChartDatasBean.class);
                    chartDataInit();
                }
            }
        });
    }

    /**
     * 初始化具体折线图数值
     */
    private void chartDataInit() {
        dateList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        {
            ChartDatasBean bean = list.get(i);
            //截取时间 HH:MM
            String date = (bean.getDate()).substring(11);
            dateList.add(date);
            spo2.add(new Entry(i, bean.getSpo2()));
            heart.add(new Entry(i, bean.getHeartRate()));
            resp.add(new Entry(i, bean.getRespRate()));
            plus.add(new Entry(i, bean.getPlusRate()));
            temperature.add(new Entry(i, bean.getTemperature()));
        }
        Log.e("zbf", dateList.toString());
        //初始化X轴
        xInit();
        //初始化Y轴
        yInit();
        setChartDatas();
    }

    /**
     * 初始化Y轴
     * TODO
     */
    private void yInit() {

    }

    /**
     * 添加数据到折线图
     */
    private void setChartDatas() {
        LineDataSet dataSet = new LineDataSet(spo2, "血氧");


    }

    /**
     * 折线图初始化
     */
    private void chartInit(View view) {
        lineChart = (LineChart) view.findViewById(R.id.line1);

        lineChart.setOnChartGestureListener(this);
        lineChart.setOnChartValueSelectedListener(this);
        //设置背景色
        lineChart.setBackgroundColor(Color.parseColor("#ffffff"));
        //禁止Y轴缩放
        lineChart.setScaleYEnabled(false);
        //可拖动
        lineChart.setDragEnabled(true);


    }

    /**
     * 初始化X轴
     */
    private void xInit() {
        //设置X轴的值
        XAxis xAxis = lineChart.getXAxis();
        //设置X轴位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //最小显示值
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter()
        {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dateList.get((int) value % dateList.size());
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("zbf", "onResume");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("zbf", "BlankFragment onDetach");
    }

    /**
     * 根据时间得到
     *
     * @return
     */
    public static String getMinute(String date) {
        return null;
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
