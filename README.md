#### 介绍
类似网易评论效果，点击事弹出键盘填写评论，点击其他地方收起。直接上图吧：

#### 效果图
![image](http://img.blog.csdn.net/20160723161832940?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

#### 布局使用注意
布局 comment_layout 要在 RelativeLayout 中,comment_layout 布局一定要被控件挤到最下面.

##### 如下示例布局
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >

    <ListView
        android:layout_above="@+id/comm_bottom_ll"
        android:layout_marginTop="10dp"
        android:id="@+id/comment_ll"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <include layout="@layout/comment_layout"/>
</RelativeLayout>
```