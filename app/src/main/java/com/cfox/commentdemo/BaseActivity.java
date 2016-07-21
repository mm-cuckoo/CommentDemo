package com.cfox.commentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : CommentDemo
 * <br/>PACKAGE_NAME : com.cfox.commentdemo
 * <br/>AUTHOR : Machao
 * <br/>DATA : 16-7-21
 * <br/>TIME : 下午8:48
 * <br/>MSG :
 * <br/>************************************************
 */
public class BaseActivity extends AppCompatActivity {
    private InputMethodManager mMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * 隐藏键盘
     */
    protected void hideInputMethod(View view){
        mMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 隐藏键盘
     */
    protected void showInputMethod(View view){
        mMethodManager.showSoftInput(view, 0);
    }
}
