package cniao5.com.shop.http;

import android.content.Context;
import android.content.Intent;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import cniao5.com.shop.application.MyApplication;
import cniao5.com.shop.activity.base.LoginActivity;
import cniao5.com.shop.R;
import cniao5.com.shop.utils.ToastUtils;


/**
 * Created by <a href="http://www.cniao5.com">菜鸟窝</a>
 * 一个专业的Android开发在线教育平台
 */
public abstract class SimpleCallback<T> extends BaseCallback<T> {

    protected Context mContext;

    public SimpleCallback(Context context){

        mContext = context;

    }

    @Override
    public void onBeforeRequest(Request request) {

    }

    @Override
    public void onFailure(Request request, Exception e) {

    }

    @Override
    public void onResponse(Response response) {

    }

    @Override
    public void onTokenError(Response response, int code) {
        ToastUtils.show(mContext, mContext.getString(R.string.token_error));

        Intent intent = new Intent();
        intent.setClass(mContext, LoginActivity.class);
        mContext.startActivity(intent);

        MyApplication.getInstance().clearUser();

    }


}
