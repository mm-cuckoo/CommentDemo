package com.cfox.commentdemo;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private EditText mCommentContentEt;
    private Button mBtnSubmitComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
    }

    private void initView() {
        mCommentContentEt = (EditText) findViewById(R.id.comment_content);
        mCommentContentEt.setOnFocusChangeListener(new CommentEt());
        mCommentContentEt.setFocusable(false);
        mCommentContentEt.setOnClickListener(this);

        mBtnSubmitComment = (Button) findViewById(R.id.btn_submit_commemt);
        mBtnSubmitComment.setOnClickListener(this);
    }

    private class CommentEt implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if(hasFocus){
                mBtnSubmitComment.setVisibility(View.VISIBLE);
                mCommentContentEt.setGravity(Gravity.LEFT);
                v.setClickable(false);
            }else {
                mCommentContentEt.setGravity(Gravity.CENTER);
                mBtnSubmitComment.setVisibility(View.GONE);
                v.setClickable(true);
            }

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                hideInputMethod(v);
                mCommentContentEt.setText("");
                mCommentContentEt.setFocusable(false);
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
            case R.id.comment_content:
                mCommentContentEt.setFocusable(true);
                mCommentContentEt.setFocusableInTouchMode(true);
                mCommentContentEt.requestFocus();
                mCommentContentEt.findFocus();
                showInputMethod(v);
                break;

            case R.id.btn_submit_commemt:
                Toast.makeText(this,"提交评论",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
