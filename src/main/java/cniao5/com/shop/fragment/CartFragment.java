package cniao5.com.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.List;

import cniao5.com.shop.activity.base.CreateOrderActivity;
import cniao5.com.shop.R;
import cniao5.com.shop.adapter.CartAdapter;
import cniao5.com.shop.adapter.decoration.DividerItemDecoration;
import cniao5.com.shop.bean.ShoppingCart;
import cniao5.com.shop.http.OkHttpHelper;
import cniao5.com.shop.utils.CartProvider;
import cniao5.com.shop.widget.CNiaoToolBar;


public class CartFragment extends BaseFragment implements View.OnClickListener{


    public static final int ACTION_EDIT=1;
    public static final int ACTION_CAMPLATE=2;
    private static final String TAG = "CartFragment";


    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;

    @ViewInject(R.id.checkbox_all)
    private CheckBox mCheckBox;

    @ViewInject(R.id.txt_total)
    private TextView mTextTotal;

    @ViewInject(R.id.btn_order)
    private Button mBtnOrder;

    @ViewInject(R.id.btn_del)
    private Button mBtnDel;

    @ViewInject(R.id.toolbar)
    protected CNiaoToolBar mToolbar;


    private CartAdapter mAdapter;
    private CartProvider cartProvider;


    private OkHttpHelper httpHelper = OkHttpHelper.getInstance();


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart,container,false);
    }


    @Override
    public void init() {

        cartProvider = new CartProvider(getActivity());

        changeToolbar();
        showData();
    }


    @OnClick(R.id.btn_del)
    public void delCart(View view){

        mAdapter.delCart();
    }

    @OnClick(R.id.btn_order)
    public void toOrder(View view){

        Intent intent = new Intent(getActivity(), CreateOrderActivity.class);

        startActivity(intent,true);
    }




    private void showData(){


        List<ShoppingCart> carts = cartProvider.getAll();

        mAdapter = new CartAdapter(getActivity(),carts,mCheckBox,mTextTotal);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));


    }



    public void refData(){

        mAdapter.clear();
        List<ShoppingCart> carts = cartProvider.getAll();
        mAdapter.addData(carts);
        mAdapter.showTotalPrice();



    }




    public void changeToolbar(){

        mToolbar.hideSearchView();
        mToolbar.showTitleView();
        mToolbar.setTitle(R.string.cart);
        mToolbar.getRightButton().setVisibility(View.VISIBLE);
        mToolbar.setRightButtonText("编辑");

        mToolbar.getRightButton().setOnClickListener(this);

        mToolbar.getRightButton().setTag(ACTION_EDIT);


    }


    private void showDelControl(){
        mToolbar.getRightButton().setText("完成");
        mTextTotal.setVisibility(View.GONE);
        mBtnOrder.setVisibility(View.GONE);
        mBtnDel.setVisibility(View.VISIBLE);
        mToolbar.getRightButton().setTag(ACTION_CAMPLATE);

        mAdapter.checkAll_None(false);
        mCheckBox.setChecked(false);

    }

    private void  hideDelControl(){

        mTextTotal.setVisibility(View.VISIBLE);
        mBtnOrder.setVisibility(View.VISIBLE);


        mBtnDel.setVisibility(View.GONE);
        mToolbar.setRightButtonText("编辑");
        mToolbar.getRightButton().setTag(ACTION_EDIT);

        mAdapter.checkAll_None(true);
        mAdapter.showTotalPrice();

        mCheckBox.setChecked(true);
    }


    @Override
    public void onClick(View v) {



        int action = (int) v.getTag();
        if(ACTION_EDIT == action){

            showDelControl();
        }
        else if(ACTION_CAMPLATE == action){

            hideDelControl();
        }


    }
}
