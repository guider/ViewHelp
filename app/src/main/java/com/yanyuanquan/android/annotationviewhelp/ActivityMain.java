package com.yanyuanquan.android.annotationviewhelp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by apple on 16/8/2.
 */
@ViewHelp(needMultView = false)
public class ActivityMain extends BaseViewHelpActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }


    @OnClick({R.id.error, R.id.empty, R.id.loading})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.error:
                showErrorView();
                break;
            case R.id.empty:
                showEmptyView();
                break;
            case R.id.loading:
                showLoadingView();
                break;
        }
    }


}
