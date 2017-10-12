package com.example.administrator.zbf_chartview;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, BlankFragment2.OnFragmentInteractionListener
{
    private LinearLayout ln_replace;

    private Button btn_replace;
    private Button btn_replace2;

    private BlankFragment f1;
    private BlankFragment2 f2;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewInit();
        fragmentInit();
    }

    /**
     * 视图初始化
     */
    private void viewInit() {
        ln_replace = (LinearLayout) findViewById(R.id.ln_replace);
        btn_replace = (Button) findViewById(R.id.btn_replace);
        btn_replace.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                f1Init();
            }
        });
        btn_replace2 = (Button) findViewById(R.id.btn_replace2);
        btn_replace2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                f2Init();
            }
        });
    }

    /**
     * ftagment初始化
     */
    private void fragmentInit() {
        fragmentManager = getSupportFragmentManager();

        f1Init();
    }

    /**
     * 初始化第一个碎片
     */
    private void f1Init() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (f1 == null)
        {
            f1 = new BlankFragment();
            fragmentTransaction.add(R.id.ln_replace, f1);
        }
        hideFragment(fragmentTransaction);
        fragmentTransaction.show(f1);
//        fragmentTransaction.replace(R.id.ln_replace, f1);
        fragmentTransaction.commit();
    }

    /**
     * 初始化第二个碎片
     */
    private void f2Init() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (f2 == null)
        {
            f2 = new BlankFragment2();
            fragmentTransaction.add(R.id.ln_replace, f2);
        }
        hideFragment(fragmentTransaction);
        fragmentTransaction.show(f2);
        fragmentTransaction.commit();
    }

    /**
     * 隐藏碎片
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (f1 != null)
        {
            fragmentTransaction.hide(f1);
        }
        if (f2 != null)
        {
            fragmentTransaction.hide(f2);
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
