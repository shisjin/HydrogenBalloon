package com.dream.will.hydrogenballoon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.ui.LognActivity;
import com.dream.will.hydrogenballoon.ui.MsgActivity;
import com.dream.will.hydrogenballoon.ui.SettingActivity;
import com.dream.will.hydrogenballoon.ui.YiJiangActivity;

/**
 */

public class WoDeFragment extends Fragment {

    private ImageView iv_wode_head;
    private TextView tv_wode_logn;
    private ImageView iv_wode_xiaoxi;
    private ImageView iv_wode_fengxiang;
    private LinearLayout layout_shoucang_wode;
    private LinearLayout layout_dingdan_wode;
    private LinearLayout layout_qita_yijian;
    private LinearLayout layout_qita_setting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.wode_fragment, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListenter();
    }

    private void setListenter() {

        iv_wode_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LognActivity.class));
            }
        });

        tv_wode_logn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LognActivity.class));
            }
        });

        iv_wode_xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MsgActivity.class));
            }
        });

        iv_wode_fengxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_layout, null);
                PopupWindow popupWindow = new PopupWindow(view1,
                        getActivity().getResources().getDisplayMetrics().widthPixels / 2,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.pupupwindow_shape));

                // 显示出来
                popupWindow.showAsDropDown(iv_wode_fengxiang);
            }
        });

        layout_shoucang_wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LognActivity.class));
            }
        });

        layout_dingdan_wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LognActivity.class));
            }
        });
        layout_qita_yijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), YiJiangActivity.class));
            }
        });

        layout_qita_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SettingActivity.class));

            }
        });
    }

    private void initView(View inflate) {
        iv_wode_head = (ImageView) inflate.findViewById(R.id.iv_wode_head);
        tv_wode_logn = (TextView) inflate.findViewById(R.id.tv_wode_logn);
        iv_wode_xiaoxi = (ImageView) inflate.findViewById(R.id.iv_wode_xiaoxi);
        iv_wode_fengxiang = (ImageView) inflate.findViewById(R.id.iv_wode_fengxiang);
        layout_shoucang_wode = (LinearLayout) inflate.findViewById(R.id.layout_shoucang_wode);
        layout_dingdan_wode = (LinearLayout) inflate.findViewById(R.id.layout_dingdan_wode);
        layout_qita_yijian = (LinearLayout) inflate.findViewById(R.id.layout_qita_wode);
        layout_qita_setting = (LinearLayout) inflate.findViewById(R.id.layout_qita_setting);
    }
}
