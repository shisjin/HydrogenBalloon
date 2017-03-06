package com.dream.will.hydrogenballoon.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;



public abstract class AbsAdapter<T> extends BaseAdapter {
    //数据源
    List<T> data;
    //LayoutInflater
    LayoutInflater inflater;
    //布局资源
    int[] layoutId;

    //构造方法

    public AbsAdapter(List<T> data, Context context, int... layoutId) {
        this.data = data;
        this.layoutId = layoutId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return layoutId.length;
    }

    //抽象绑定数据的方法
    public abstract void bindData(int position,ViewHolder holder );

    // 当前布局布局的类型
    @Override
    public abstract int getItemViewType(int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        //得到当前数据布局类型
        int type = getItemViewType(position);
        if(convertView==null){
            convertView=inflater.inflate(layoutId[type],parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        //绑定数据
        bindData(position,holder);

        return convertView;
    }

    /*public static class ViewHolder{
        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        public View findViewById(int viewId){
            return view.findViewById(viewId);
        }
    }*/

    public static class ViewHolder{
        //保存的控件 是需要设置值的控件
        private View view;
        //通过SparsArray找到缓存布局  特点：二分查找  键只能是integer类型  占用内存小
        private SparseArray<View> mViews;
        public ViewHolder(View c){
            this.view = c;
            this.mViews = new SparseArray<View>();
        }

        //向子类提供一个方法，返回需要设置值的控件
        public  View findViewById(int viewId){
            //根据viewID找到对应控件
            View findView = mViews.get(viewId);
            if (findView == null){
                findView = view.findViewById(viewId);
                mViews.put(viewId,findView);
            }
            return findView;
        }
    }

}
