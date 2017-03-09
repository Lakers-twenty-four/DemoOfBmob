package com.example.administrator.bmobdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7 0007.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Person>mlist;
    public MyAdapter(List<Person>mlist){
        this.mlist=mlist;
    }
    //onCreateViewHolder()用于创建ViewHolder的实例
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    //用于对RecyclerView子项进行赋值
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Person person=mlist.get(position);
        holder.tv_name.setText(person.getName());
        holder.tv_address.setText(person.getAddress());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    //【1】首先定义一个内部类ViewHolder，要继承自RecyclerView.ViewHolder.
    //【2】然后ViewHolder的构造函数中要传入一个View参数，这个参数通常就是RecyclerView子项的


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_address;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
            tv_address= (TextView) itemView.findViewById(R.id.tv_address);
        }
    }
}
