package com.example.administrator.zbf_chartview.io;

/**
 * 数据请求回调接口
 * Created by hs-301 on 2017/9/5.
 */
public interface IOHttpCallBack
{
    /**
     *
     * @param result    返回数据
     * @param code      成功\失败  0 1
     */
    void getIOHttpCallBack(String result, int code);
}
