package com.o4care.nurse.fragment.map;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.o4care.nurse.R;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

import org.jetbrains.annotations.NotNull;

/**
 * @author xuexiang
 * @since 2019-10-30 00:19
 */
@Page(anim = CoreAnim.none)
public class MapFragment extends Fragment {
    private AMap aMap;
    private MapView mapView;
    private View mapLayout;

    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);

    private MarkerOptions markerOption;
    private Marker marker;

    /**
     * @return 返回为 null意为不需要导航栏
     *//*
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    *//**
     * 布局的资源id
     *
     * @return
     *//*
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map;
    }

    */

    /**
     * 初始化控件
     *//*
    @Override
    protected void initViews() {

        *//*mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(instanceState);// 此方法必须重写

        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }*//*
    }*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mapLayout == null) {
            Log.i("sys", "MF onCreateView() null");
            mapLayout = inflater.inflate(R.layout.fragment_map_location, container, false);
            mapView = (MapView) mapLayout.findViewById(R.id.map);
            Log.i("syslia", "mpLayout " + (mapLayout == null) + " mapView " + (mapView == null));
            mapView.onCreate(savedInstanceState);
            if (aMap == null) {
                aMap = mapView.getMap();
                setUpMap();
            }
        } else {

            if (mapLayout.getParent() != null) {
                ((ViewGroup) mapLayout.getParent()).removeView(mapLayout);
            }
        }
        return mapLayout;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        Log.i("sys", "mf onResume");
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onPause() {
        Log.i("sys", "mf onPause");
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("sys", "mf onSaveInstanceState");
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onDestroy() {
        Log.i("sys", "mf onDestroy");
        super.onDestroy();
        mapView.onDestroy();
    }


    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 设置默认定位按钮是否显示
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(12));

        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        aMap.addOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                // Log.e("AmapSuccess", location.toString());
                // latitude=30.189051#longitude=120.192904
                // latitude=30.227832#longitude=120.235527
                // latitude=30.188073#longitude=120.249947
            }
        });

        addMarkersToMap();

        setupLocationStyle();
    }

    /**
     * 设置自定义定位蓝点
     */
    private void setupLocationStyle() {
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();

        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        //设置定位频次方法
        myLocationStyle.interval(2000);

        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.drawable.gps_point));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(STROKE_COLOR);
        //自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(5);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(FILL_COLOR);
        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);
    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {

        // latitude=30.189051#longitude=120.192904
        // latitude=30.227832#longitude=120.235527
        // latitude=30.188073#longitude=120.249947
        LatLng latlng = new LatLng(30.188073, 120.249947);
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.img_4))
                .position(latlng)
                .draggable(true);
        aMap.addMarker(markerOption);

        latlng = new LatLng(30.227832, 120.235527);
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.img_4))
                .position(latlng)
                .draggable(true);
        aMap.addMarker(markerOption);
    }
}
