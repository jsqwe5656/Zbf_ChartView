package com.example.administrator.zbf_chartview.io;

/**
 * 折线图数据实体
 * Created by hs-301 on 2017/10/12.
 */
public class ChartDatasBean
{

    /**
     * date : 2017-10-11 16:23
     * userId : 4
     * heartRate : 9
     * respRate : 0
     * plusRate : 0
     * temperature : 0
     * spo2 : 0
     * gesture : 未知
     * status : false
     * id : 59dddd90f5fb03103c3472ba
     */

    private String date;
    private String userId;
    private int heartRate;              //心率   范围15-300
    private int respRate;               //呼吸率 范围6-70
    private int plusRate;               //脉搏   范围15-300
    private int temperature;            //体温   范围20-50
    private int spo2;                   //血氧   范围0-100
    private String gesture;
    private boolean status;
    private String id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getRespRate() {
        return respRate;
    }

    public void setRespRate(int respRate) {
        this.respRate = respRate;
    }

    public int getPlusRate() {
        return plusRate;
    }

    public void setPlusRate(int plusRate) {
        this.plusRate = plusRate;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getSpo2() {
        return spo2;
    }

    public void setSpo2(int spo2) {
        this.spo2 = spo2;
    }

    public String getGesture() {
        return gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ChartDatasBean{" +
                "date='" + date + '\'' +
                ", userId='" + userId + '\'' +
                ", heartRate=" + heartRate +
                ", respRate=" + respRate +
                ", plusRate=" + plusRate +
                ", temperature=" + temperature +
                ", spo2=" + spo2 +
                ", gesture='" + gesture + '\'' +
                ", status=" + status +
                ", id='" + id + '\'' +
                '}';
    }
}
