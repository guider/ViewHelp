package com.yanyuanquan.android.annotationviewhelp;


import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewHelp {

    boolean needMultView() default true;

    int errorView() default 0;

    int emptyView() default 0;

    int loadingView() default 0;

    boolean showLoadingView() default true;

    boolean showErrorView() default true;

    boolean showEmptyView() default true;
}
