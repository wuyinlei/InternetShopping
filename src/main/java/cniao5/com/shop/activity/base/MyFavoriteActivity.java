package cniao5.com.shop.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cniao5.com.shop.application.MyApplication;
import cniao5.com.shop.uri.Contants;
import cniao5.com.shop.R;
import cniao5.com.shop.adapter.BaseAdapter;
import cniao5.com.shop.adapter.FavoriteAdatper;
import cniao5.com.shop.adapter.decoration.CardViewtemDecortion;
import cniao5.com.shop.bean.Favorites;
import cniao5.com.shop.http.OkHttpHelper;
import cniao5.com.shop.http.SpotsCallBack;
import cniao5.com.shop.widget.CNiaoToolBar;

public class MyFavoriteActivity extends Activity {


    @ViewInject(R.id.toolbar)
    private CNiaoToolBar mToolbar;


    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerview;


    private FavoriteAdatper mAdapter;

    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);
        ViewUtils.inject(this);

        initToolBar();
        getFavorites();
    }



    private void initToolBar(){

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




    private void getFavorites(){

        Long userId = MyApplication.getInstance().getUser().getId();

        Map<String, Object> params = new HashMap<>();
        params.put("user_id",userId);


        okHttpHelper.get(Contants.API.FAVORITE_LIST, params, new SpotsCallBack<List<Favorites>>(this) {
            @Override
            public void onSuccess(Response response, List<Favorites> favorites) {
                showFavorites(favorites);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

                LogUtils.d("code:"+code);
            }
        });
    }

    private void showFavorites(List<Favorites> favorites) {


        mAdapter = new FavoriteAdatper(this,favorites);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.addItemDecoration(new CardViewtemDecortion());

        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }


}
