package com.dream.will.hydrogenballoon.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.Destinations;
import com.dream.will.hydrogenballoon.utils.DisplayUtil;

import java.util.List;


/**
 * 经典路线地图显示
 * Created by Karlo on 2016/12/22.
 */

public class MyMapView extends FrameLayout implements BaiduMap.OnMapClickListener, View.OnClickListener {
    private TextureMapView mapView;
    private Context context;
    private BaiduMap map;
    private BitmapDescriptor bitmap;
    private TextView infoTitle;
    private TextView infoContent;
    private OnMyMapClickListener listener;
    private int planId;

    public MyMapView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MyMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mapView = new TextureMapView(context);
        mapView.showZoomControls(false);
        mapView.showScaleControl(false);
        map = mapView.getMap();
        map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        map.setOnMapClickListener(this);
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setAllGesturesEnabled(false);
        addView(mapView, params);
        bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_map_green_digit);
        View infoView = LayoutInflater.from(context).inflate(R.layout.plan_map_infoview, null, false);
        infoTitle = ((TextView) infoView.findViewById(R.id.infoView_title));
        infoContent = ((TextView) infoView.findViewById(R.id.infoView_content));
        LayoutParams infoViewParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        infoViewParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        infoViewParams.width = DisplayUtil.getScreenWidth(context) / 2;
        infoViewParams.setMargins(0, 0, 0, 20);
        infoView.setOnClickListener(this);
        addView(infoView, infoViewParams);
    }

    public void setData(@NonNull Destinations.DataBean.SectionsBean.ModelsBean bean) {
        planId = bean.getId();
        /*清除覆盖物*/
        map.clear();
        Log.i("google.karlo", "Map----setData: " + bean.getTitle());
        LatLng center = null;
        /*旅行计划计数*/
        int count = 0;
        List<Destinations.DataBean.SectionsBean.ModelsBean.DaysBean> days = bean.getDays();
        for (Destinations.DataBean.SectionsBean.ModelsBean.DaysBean daysBean : days) {
            List<Destinations.DataBean.SectionsBean.ModelsBean.DaysBean.PointsBean> points = daysBean.getPoints();
            for (Destinations.DataBean.SectionsBean.ModelsBean.DaysBean.PointsBean pointsBean : points) {
                count++;
                Destinations.DataBean.SectionsBean.ModelsBean.DaysBean.PointsBean.PoiBean poi = pointsBean.getPoi();
                LatLng latLng = new LatLng(poi.getYouji_lat(), poi.getYouji_lng());
                OverlayOptions options = new MarkerOptions()
                        .position(latLng)
                        .icon(bitmap);
                map.addOverlay(options);
                if (count == 1) {
                    center = latLng;
                }
            }
        }
        if (null != center) {
            setInfoTitle(bean.getTitle());
            setInfoContent(String.valueOf(bean.getDays_count() + "天，" + count + "条旅游计划"));
            setCenter(center);
        }
    }

    public void setInfoTitle(String title) {
        if (null != infoTitle) {
            infoTitle.setText(title);
        }
    }

    public void setInfoContent(String content) {
        if (null != infoContent) {
            infoContent.setText(content);
        }
    }

    public void setCenter(LatLng center) {
        //地图状态，构建者模式
        MapStatus mapStatus = new MapStatus.Builder()
                //设置目标点
                .target(center)
                //支持缩放模式3-18
                .zoom(13)
                //构建
                .build();
        //构建新状态,工厂模式
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(mapStatus);
        //更新状态BaiduMap.setMapStatus()
        map.setMapStatus(msu);
    }

    public void onMapResume() {
        if (null != mapView) {
            mapView.onResume();
        }
    }

    public void onMapDestroy() {
        if (null != mapView) {
            mapView.onDestroy();
        }
    }

    public void onMapPause() {
        if (null != mapView) {
            mapView.onPause();
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        request();
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    @Override
    public void onClick(View v) {
        request();
    }

    private void request() {
        if (null != listener) {
            listener.OnMapClick(planId);
        }
    }

    public void setListener(OnMyMapClickListener listener) {
        this.listener = listener;
    }

    public interface OnMyMapClickListener {
        void OnMapClick(int planId);
    }
}
