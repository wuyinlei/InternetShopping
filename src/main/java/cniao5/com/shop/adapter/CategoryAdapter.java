package cniao5.com.shop.adapter;

import android.content.Context;

import java.util.List;

import cniao5.com.shop.R;
import cniao5.com.shop.bean.Category;

public class CategoryAdapter extends SimpleAdapter<Category> {


    public CategoryAdapter(Context context, List<Category> datas) {
        super(context, R.layout.template_single_text, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, Category item) {


        viewHoder.getTextView(R.id.textView).setText(item.getName());

    }
}
