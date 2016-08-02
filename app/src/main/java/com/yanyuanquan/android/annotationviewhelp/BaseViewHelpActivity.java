package com.yanyuanquan.android.annotationviewhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public abstract class BaseViewHelpActivity extends AppCompatActivity {

    protected View errorView;
    protected View emptyView;
    protected View loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContainer());
        initView();

    }

    protected abstract void initView();

    public View getContainer() {
        ViewHelp anno = this.getClass().getAnnotation(ViewHelp.class);
        int layoutId = getLayoutId();

        if (layoutId == 0) {
            throw new IllegalArgumentException(" 布局未获得 ");
        }
        View root = LayoutInflater.from(this).inflate(layoutId, null);

        if (anno != null && anno.needMultView()) {
            FrameLayout container = (FrameLayout) root.findViewById(R.id.container);
            if (container == null) {
                throw new IllegalArgumentException(" acitiviy  " + this.getClass().getSimpleName() + "缺少  container名称的FrameLayout ");
            }
            //noinspection ResourceType
            errorView = LayoutInflater.from(this).inflate((anno.errorView() == 0) ? getErroView() : anno.errorView(), null);
            //noinspection ResourceType
            emptyView = LayoutInflater.from(this).inflate((anno.emptyView() == 0) ? getEmptyView() : anno.emptyView(), null);
            //noinspection ResourceType
            loadingView = LayoutInflater.from(this).inflate((anno.loadingView() == 0) ? getLoadingView() : anno.loadingView(), null);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            container.addView(errorView, params);
            container.addView(emptyView, params);
            container.addView(loadingView, params);
        }
        return root;

    }

    public void showErrorView() {
        if (errorView != null) {
            errorView.setVisibility(View.VISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }

    }

    public void showEmptyView() {
        if (errorView != null) {
            errorView.setVisibility(View.GONE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(View.VISIBLE);
        }
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }

    public void showLoadingView() {
        if (errorView != null) {
            errorView.setVisibility(View.GONE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }


    public int getLoadingView() {
        return R.layout.layout_loading;
    }

    public abstract int getLayoutId();

    public int getErroView() {
        return R.layout.layout_error;
    }

    public int getEmptyView() {
        return R.layout.layout_empty;
    }
}
