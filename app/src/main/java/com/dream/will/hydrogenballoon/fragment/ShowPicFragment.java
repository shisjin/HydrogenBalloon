package com.dream.will.hydrogenballoon.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.content.IntentConstant;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 */

public class ShowPicFragment extends Fragment implements View.OnClickListener{

    private String pic_url;
    private String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        pic_url = arguments.getString(IntentConstant.KEY_SHOW_PIC_URL);
        title = arguments.getString(IntentConstant.KEY_SHOW_PIC_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_show_pic,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView show_image = (ImageView) view.findViewById(R.id.show_image);
        show_image.setOnClickListener(this);
        TextView tit = (TextView) view.findViewById(R.id.title);
        tit.setText(title);
        Glide.with(getActivity()).load(pic_url).asBitmap().dontAnimate().into(show_image);
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(show_image);
    }


    Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Toast.makeText(getActivity(), "heh", Toast.LENGTH_SHORT).show();
            }
        };
private long  lastTime = 0;
    @Override
    public void onClick(View v) {
//        long currenttime = SystemClock.currentThreadTimeMillis();
//        if (currenttime - lastTime<500){
//            Toast.makeText(getActivity(), "move", Toast.LENGTH_SHORT).show();
//            handler.removeMessages(1);
//        }else {
//            lastTime = currenttime;
//            Message message = Message.obtain();
//            message.what = 1;
//            handler.sendMessageDelayed(message,500);
//        }
    }
}
