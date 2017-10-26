package com.example.administrator.zbf_chartview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.NumberPicker;
import android.widget.TextView;


public class BlankFragment2 extends Fragment
{
    private TextView tv;

    private NumberPicker picker1, picker2, picker3;


    private OnFragmentInteractionListener mListener;

    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        tv = view.findViewById(R.id.fg2_tv);
        startFlick(tv);
        picker1 = view.findViewById(R.id.picker1);
        picker1.setMaxValue(0);
        picker1.setMaxValue(100);
        picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * 开启View闪烁效果
     */
    private void startFlick(View view) {
        if (null == view)
        {
            return;
        }
        Animation alphaAnimation = new AlphaAnimation(1, 0f);
        alphaAnimation.setDuration(600);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(alphaAnimation);
    }

    /**
     * 停止闪烁
     */
    private void stopFlick(View view) {
        if (null == view)
        {
            return;
        }
        view.clearAnimation();
    }
}
