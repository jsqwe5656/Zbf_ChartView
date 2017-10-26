package com.example.administrator.zbf_chartview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.example.administrator.zbf_chartview.io.ChartDatasBean;
import com.example.administrator.zbf_chartview.io.IOHttpCallBack;
import com.example.administrator.zbf_chartview.io.MyOkHttp;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 绘制折线图的碎片
 */
public class BlankFragment extends Fragment
{
    private LineChart[] mCharts = new LineChart[5];
    private final String url = "http://192.168.1.189:8081/packet/minute/day?date=2017-10-11&userId=4";

    public BlankFragment() {
        // Required empty public constructor
    }

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            for (LineChart linechart : mCharts)
            {
                linechart.setAutoScaleMinMaxEnabled(true);
                linechart.notifyDataSetChanged();
                linechart.invalidate();
            }

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        viewInit(view);
        dataInit();
        mCharts[0].clear();
        return view;
    }

    private void viewInit(View view) {
        mCharts[0] = (LineChart) view.findViewById(R.id.line1);
        mCharts[1] = (LineChart) view.findViewById(R.id.line2);
        mCharts[2] = (LineChart) view.findViewById(R.id.line3);
        mCharts[3] = (LineChart) view.findViewById(R.id.line4);
        mCharts[4] = (LineChart) view.findViewById(R.id.line5);

        //让每个图标都实现一个监听并实现缩放联动
        mCharts[0].setOnChartGestureListener(new CoupleChartGestureListener(mCharts[0], new Chart[]{mCharts[1], mCharts[2], mCharts[3], mCharts[4]}));
        mCharts[1].setOnChartGestureListener(new CoupleChartGestureListener(mCharts[1], new Chart[]{mCharts[0], mCharts[2], mCharts[3], mCharts[4]}));
        mCharts[2].setOnChartGestureListener(new CoupleChartGestureListener(mCharts[2], new Chart[]{mCharts[1], mCharts[0], mCharts[3], mCharts[4]}));
        mCharts[3].setOnChartGestureListener(new CoupleChartGestureListener(mCharts[3], new Chart[]{mCharts[1], mCharts[2], mCharts[0], mCharts[4]}));
        mCharts[4].setOnChartGestureListener(new CoupleChartGestureListener(mCharts[3], new Chart[]{mCharts[1], mCharts[2], mCharts[0], mCharts[3]}));
        //监听折线图值被选中事件
        mCharts[0].setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                mCharts[1].highlightValues(new Highlight[]{h});
                mCharts[2].highlightValues(new Highlight[]{h});
                mCharts[4].highlightValues(new Highlight[]{h});
                mCharts[3].highlightValues(new Highlight[]{h});
            }

            @Override
            public void onNothingSelected() {
                mCharts[0].highlightValue(null);
            }
        });

        mCharts[1].setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                mCharts[0].highlightValues(new Highlight[]{h});
                mCharts[2].highlightValues(new Highlight[]{h});
                mCharts[3].highlightValues(new Highlight[]{h});
                mCharts[4].highlightValues(new Highlight[]{h});
            }

            @Override
            public void onNothingSelected() {
                mCharts[1].highlightValue(null);
            }
        });

        mCharts[2].setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                mCharts[0].highlightValues(new Highlight[]{h});
                mCharts[1].highlightValues(new Highlight[]{h});
                mCharts[4].highlightValues(new Highlight[]{h});
                mCharts[3].highlightValues(new Highlight[]{h});
            }

            @Override
            public void onNothingSelected() {
                mCharts[2].highlightValue(null);
            }
        });

        mCharts[3].setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                mCharts[0].highlightValues(new Highlight[]{h});
                mCharts[1].highlightValues(new Highlight[]{h});
                mCharts[2].highlightValues(new Highlight[]{h});
                mCharts[4].highlightValues(new Highlight[]{h});
            }

            @Override
            public void onNothingSelected() {
                mCharts[3].highlightValue(null);
            }
        });

        mCharts[4].setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                mCharts[0].highlightValues(new Highlight[]{h});
                mCharts[1].highlightValues(new Highlight[]{h});
                mCharts[2].highlightValues(new Highlight[]{h});
                mCharts[3].highlightValues(new Highlight[]{h});
            }

            @Override
            public void onNothingSelected() {
                mCharts[4].highlightValue(null);
            }
        });

        for (int i = 0; i < mCharts.length; i++)
        {
            if (i == 4)
                chartInit(mCharts[i], Color.WHITE, true);
            else
                chartInit(mCharts[i], Color.WHITE, false);
        }

    }

    private List<ChartDatasBean> list = new ArrayList<>();          //折线图整体数据

    private List<String> dateList = new ArrayList<>();              //保存时间数据用作X轴
    private List<Entry> heart = new ArrayList<>();                  //心跳数据
    private List<Entry> resp = new ArrayList<>();                   //呼吸率数据
    private List<Entry> spo2 = new ArrayList<>();                   //血氧数据
    private List<Entry> plus = new ArrayList<>();                   //脉搏数据
    private List<Entry> temperature = new ArrayList<>();            //体温数据

    //折线图背景色值
    private int[] mBGColors = new int[]{
            Color.parseColor("#c01202"),
            Color.parseColor("#dbb102"),
            Color.parseColor("#11ae5a"),
            Color.parseColor("#0d489d"),
            Color.parseColor("#512c8f"),
    };
    //折现颜色
    private int[] mLineColor = new int[]{
            Color.parseColor("#fe0230"),
            Color.parseColor("#3923ad"),
            Color.parseColor("#ffa800"),
            Color.parseColor("#0ceae6"),
            Color.parseColor("#ec0cfd")
    };


    /**
     * 数据初始化
     */
    private void dataInit() {
        MyOkHttp myOkHttp = new MyOkHttp();
        //先清空数据 防止数据重复
        list.clear();
        //TODO 暂时屏蔽真实数据
/*        myOkHttp.getJson(url, new IOHttpCallBack()
        {
            @Override
            public void getIOHttpCallBack(String result, int code) {
                if (code == 0)
                {
                    list = JSON.parseArray(result, ChartDatasBean.class);
                    chartDataInit();
                }
            }
        });*/
        for (int i = 0; i < 600; i++)
        {
            ChartDatasBean bean = new ChartDatasBean();
            bean.setDate("2107-10-11 00:00");
            int value = (int) (Math.random() * i);
            bean.setSpo2(value);
            bean.setHeartRate(value);
            bean.setRespRate(value);
            bean.setPlusRate(value);
            bean.setTemperature(value);
            list.add(bean);
        }
        chartDataInit();
    }

    /**
     * 初始化具体折线图数值
     */
    private void chartDataInit() {
        for (int i = 0; i < list.size(); i++)
        {
            ChartDatasBean bean = list.get(i);
            //截取时间 HH:MM
//            String date = (bean.getDate()).substring(11);
            dateList.add(bean.getDate());
            spo2.add(new Entry(i, bean.getSpo2()));
            heart.add(new Entry(i, bean.getHeartRate()));
            resp.add(new Entry(i, bean.getRespRate()));
            plus.add(new Entry(i, bean.getPlusRate()));
            temperature.add(new Entry(i, bean.getTemperature()));
        }
        setChartDatas();
    }

    /**
     * 添加数据到折线图
     */
    private void setChartDatas() {
        mCharts[0].setDrawMarkers(true);
        LineData spo2Data = lineDataInit(spo2, mLineColor[0], "血氧");
        LineData heartData = lineDataInit(heart, mLineColor[1], "心率");
        LineData respData = lineDataInit(resp, mLineColor[2], "呼吸率");
        LineData plusData = lineDataInit(plus, mLineColor[3], "脉率");
        LineData tempData = lineDataInit(temperature, mLineColor[4], "体温");

        mCharts[0].setData(spo2Data);
        mCharts[1].setData(heartData);
        mCharts[2].setData(respData);
        mCharts[3].setData(plusData);
        mCharts[4].setData(tempData);

        handler.sendEmptyMessageDelayed(0, 300);
    }

    /**
     * 折线图初始化
     */
    private void chartInit(LineChart lineChart, int bgcolor, boolean show) {

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        //设置背景色
        lineChart.setBackgroundColor(Color.parseColor("#ffffff"));
        //禁止Y轴缩放
        lineChart.setScaleYEnabled(false);
        //可拖动
        lineChart.setDragEnabled(true);
        //如果设置为true，图表在触摸后继续滚动
        lineChart.setDragDecelerationEnabled(false);
        //设置折线图背景色
        lineChart.setBackgroundColor(bgcolor);
        //禁止双击缩放
        lineChart.setDoubleTapToZoomEnabled(false);
        Description des = new Description();
        des.setText("");
        lineChart.setDescription(des);

        xInit(lineChart, show);
        yInit(lineChart);
    }

    /**
     * 初始化X轴
     */
    private void xInit(LineChart lineChart, boolean show) {
        //设置X轴的值
        XAxis xAxis = lineChart.getXAxis();
        //显示X轴
        xAxis.setEnabled(true);
        xAxis.setLabelCount(10);
        //设置X轴位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置最小值
//        xAxis.setAxisMinimum(0f);
        //设置最小间隔
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter()
        {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dateList.get((int) value % dateList.size());
            }
        });

        //绘制此轴标签
        xAxis.setDrawLabels(show);
        xAxis.setDrawGridLines(false);
    }

    /**
     * 初始化Y轴
     */
    private void yInit(LineChart lineChart) {
        YAxis yAxis = lineChart.getAxisLeft();
        //显示Y轴
        yAxis.setEnabled(true);
        //设置Y轴上线偏移数值
        yAxis.setSpaceTop(20);
        yAxis.setSpaceBottom(20);
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //设置显示数据条数
        yAxis.setLabelCount(3);
        //设置最小值
//        yAxis.setAxisMinimum(0);
        yAxis.setGridColor(Color.parseColor("#cbcbcb"));
        //绘制此轴标签
        yAxis.setDrawLabels(true);

        YAxis right = lineChart.getAxisRight();
        right.setDrawLabels(false);
        right.setDrawGridLines(false);
        right.setDrawAxisLine(true);
    }

    /**
     * 折现数据样式初始化
     */
    private LineData lineDataInit(List<Entry> data, int color, String labeName) {
        LineDataSet set = new LineDataSet(data, labeName);
        //线粗
        set.setLineWidth(1.0f);
        //线的颜色
        set.setColor(color);
        //是否画圆
        set.setDrawCircles(false);
        //高亮值的颜色
        set.setHighLightColor(Color.parseColor("#7ccbd8"));
        //不绘制水平的高亮线
        set.setDrawHorizontalHighlightIndicator(false);
        //高亮线的宽度
        set.setHighlightLineWidth(0.8f);
        //是否显示数值
        set.setDrawValues(false);

        return new LineData(set);
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

}
