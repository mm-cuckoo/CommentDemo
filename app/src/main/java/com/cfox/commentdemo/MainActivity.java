package com.cfox.commentdemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private EditText mCommentContentEt;
    private Button mBtnSubmitComment,mBtnOpen;

    private LinearLayout mCommentLayout;
    private RelativeLayout mRelativeWrap;

    private int maxSize = 0;
    private int minSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {

        mCommentLayout = (LinearLayout) findViewById(R.id.comm_bottom_ll);
        mRelativeWrap = (RelativeLayout) findViewById(R.id.comm_wrap_ll);

        mCommentContentEt = (EditText) findViewById(R.id.comment_content);
        mCommentContentEt.setOnClickListener(this);

        mBtnSubmitComment = (Button) findViewById(R.id.btn_submit_commemt);
        mBtnSubmitComment.setOnClickListener(this);

        mBtnOpen = (Button) findViewById(R.id.btn_open_comm);
        mBtnOpen.setOnClickListener(this);

        mRelativeWrap.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                mRelativeWrap.getWindowVisibleDisplayFrame(rect);

                if (maxSize > rect.height()) {
                    minSize = rect.height();
                } else {
                    maxSize = rect.height();
                }

                if (maxSize == rect.height()) {//收起
                    mCommentLayout.setVisibility(View.GONE);
                    mBtnOpen.setVisibility(View.VISIBLE);
                }

                if (minSize == rect.height()) {//弹起
                    mBtnOpen.setVisibility(View.GONE);
                    mCommentLayout.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                hideInputMethod(v);
                mCommentContentEt.setText("");
                mCommentContentEt.clearFocus();

            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (/*event.getX() > left && event.getX() < right
                    &&*/ event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit_commemt:
                Toast.makeText(this,"提交评论",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_open_comm:
                mCommentContentEt.setFocusable(true);
                mCommentContentEt.setFocusableInTouchMode(true);
                mCommentContentEt.requestFocus();
                mCommentContentEt.findFocus();
                showInputMethod(mCommentContentEt);
                break;
        }
    }
}
