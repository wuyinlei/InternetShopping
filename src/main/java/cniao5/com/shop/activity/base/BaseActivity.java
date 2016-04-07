package cniao5.com.shop.activity.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import cniao5.com.shop.application.MyApplication;
import cniao5.com.shop.activity.LoginActivity;
import cniao5.com.shop.bean.User;

public class BaseActivity extends AppCompatActivity {


    protected static final String TAG = BaseActivity.class.getSimpleName();

    public void startActivity(Intent intent,boolean isNeedLogin){


        if(isNeedLogin){

            User user = MyApplication.getInstance().getUser();
            if(user !=null){
                super.startActivity(intent);
            }
            else{

                MyApplication.getInstance().putIntent(intent);
                Intent loginIntent = new Intent(this
                        , LoginActivity.class);
                super.startActivity(intent);

            }

        }
        else{
            super.startActivity(intent);
        }

    }
}
