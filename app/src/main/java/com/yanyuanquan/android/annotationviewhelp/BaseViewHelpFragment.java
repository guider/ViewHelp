package com.yanyuanquan.android.annotationviewhelp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by apple on 16/8/2.
 */

public abstract class BaseViewHelpFragment extends Fragment {

    protected View errorView;
    protected View emptyView;
    protected View loadingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup c, @Nullable Bundle savedInstanceState) {
        ViewHelp anno = this.getClass().getAnnotation(ViewHelp.class);
        int layoutId = getLayoutId();
        if (layoutId == 0) {
            throw new IllegalArgumentException("  Fragment " + this.getClass().getSimpleName() + "   布局未获得 ");
        }
        View root = LayoutInflater.from(getActivity()).inflate(layoutId, null);
        initSetting(root);

        if (anno == null || !anno.needMultView()) {
            return root;
        }

        FrameLayout container = new FrameLayout(getActivity());
        if (container == null) {
            throw new IllegalArgumentException(" acitiviy  " + this.getClass().getSimpleName() + "缺少  container名称的FrameLayout ");
        }
        //noinspection ResourceType
        errorView = LayoutInflater.from(getActivity()).inflate((anno.errorView() == 0) ? getErroView() : anno.errorView(), null);
        //noinspection ResourceType
        emptyView = LayoutInflater.from(getActivity()).inflate((anno.emptyView() == 0) ? getEmptyView() : anno.emptyView(), null);
        //noinspection ResourceType
        loadingView = LayoutInflater.from(getActivity()).inflate((anno.loadingView() == 0) ? getLoadingView() : anno.loadingView(), null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        container.addView(root, params);
        container.addView(errorView, params);
        container.addView(emptyView, params);
        container.addView(loadingView, params);
        return container;

    }

    protected abstract int getLayoutId();

    protected abstract void initSetting(View root);


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

    public int getErroView() {
        return R.layout.layout_error;
    }

    public int getEmptyView() {
        return R.layout.layout_empty;
    }
}
