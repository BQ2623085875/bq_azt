package com.azhyun.massxj.fragment.azt;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.azhyun.massxj.R;
import com.azhyun.massxj.view.LazyLoadFragment;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 商品图文详情
 */
public class GraphicDetailsFragment extends LazyLoadFragment {
    @BindView(R.id.tv)
    TextView tv;


    private static final String ITEM_ID = "bill_id";

    public GraphicDetailsFragment() {
        // Required empty public constructor
    }


    public static GraphicDetailsFragment newInstance(String itemId) {
        GraphicDetailsFragment fragment = new GraphicDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ITEM_ID, itemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_graphic_details;
    }

    @Override
    protected void lazyLoad() {

        tv.setText(ITEM_ID);
    }

}
