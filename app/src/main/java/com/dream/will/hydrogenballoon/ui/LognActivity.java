package com.dream.will.hydrogenballoon.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dream.will.hydrogenballoon.R;

public class LognActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText logn_name;
    private EditText logn_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logn);
        initView();

        toolbar.setNavigationIcon(R.drawable.md_nav_back);
        toolbar.setTitle("登录携程账号");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundResource(R.color.colorPrimary);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        logn_name = (EditText) findViewById(R.id.logn_name);
        logn_password = (EditText) findViewById(R.id.logn_password);

    }

    private void submit() {
        // validate
        String name = logn_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "携程注册邮箱或手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = logn_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
