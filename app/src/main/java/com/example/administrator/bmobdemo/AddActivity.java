package com.example.administrator.bmobdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class AddActivity extends Activity{
    private EditText et_name;
    private EditText et_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        et_name= (EditText) findViewById(R.id.et_name);
        et_address= (EditText) findViewById(R.id.et_address);
    }
    public void click2(View v){
        String name=et_name.getText().toString().trim();
        String address=et_address.getText().toString().trim();
        if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(address)){
            Toast.makeText(getApplicationContext(),"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
        }else{
            Person person=new Person();
            person.setName(name);person.setAddress(address);
            //第一：默认初始化
            Bmob.initialize(getApplicationContext(), "c2cf3280eabb60e2866b348dbaddff5e");
            person.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if(e==null){
                    Intent intent1=new Intent();
                    intent1.putExtra("ReturnCode",s);
                    // setResult()这个方法很重要，是专门用于向上一活动返回数据的。
                    // setResult() 接收两个参数，第一个参数用于向上一个活动返回处理结果，一般只使用RESULLT_OK,RESULT_CANCELED
                    setResult(RESULT_OK,intent1);
                    finish();
                    }
                }
            });
        }
    }
}
