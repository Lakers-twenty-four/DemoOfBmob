package com.example.administrator.bmobdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

import static com.example.administrator.bmobdemo.R.id.tv;

public class MainActivity extends AppCompatActivity {
    private List<Person>mlist=new ArrayList<Person>();
    private MyAdapter myAdapter=new MyAdapter(mlist);
    private RecyclerView rv;
    private TextView tv;
    private String ReturnCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();
        initView();

    }

    private void addData() {
        Person one=new Person();
        one.setName("增杭");
        one.setAddress("潮州市高级中学");
        mlist.add(one);
    }

    private void initView() {
        tv= (TextView) findViewById(R.id.tv);
        rv= (RecyclerView) findViewById(R.id.rv);
        rv.setAdapter(myAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        //第一：默认初始化
        Bmob.initialize(getApplicationContext(), "c2cf3280eabb60e2866b348dbaddff5e");

    }

    public void click1(View v){
        Intent intent=new Intent(MainActivity.this,AddActivity.class);
        //返回数据给上一个活动 第二个参数为请求码
        startActivityForResult(intent,1);



        }


        //由于我们是使用startActivityForResult()方法来启动addActivity的，在addActivity被销毁之后会
        //回调上一个活动的onActivvityResult()方法，因此我们需要在MainActivity中重写这个方法来得到返回的数据
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    ReturnCode = data.getStringExtra("ReturnCode");
                    tv.setText(ReturnCode);


                    BmobQuery<Person>query=new BmobQuery<Person>();
                    query.getObject(ReturnCode, new QueryListener<Person>() {
                        ;
                        @Override
                        public void done(Person person, BmobException e) {
                            if(e==null){
                                mlist.add(person);
                                Toast.makeText(MainActivity.this,"可以显示的逻辑",Toast.LENGTH_SHORT).show();
                                rv.setAdapter(myAdapter);
                            }else{
                                Toast.makeText(MainActivity.this,"无法显示的逻辑",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
        }
    }
}

