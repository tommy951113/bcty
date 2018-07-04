package com.example.tommy.bcty;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MobSDK.init(this);
//        sendCode(this);

        setTitle("运动预约");

        final TextView textDate = (TextView) findViewById(R.id.date);
        Button button = (Button)findViewById(R.id.btn_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SportListActivity.class);
                startActivity(intent);
            }
        });
        Calendar currentCalendar;
        int year, month, day;
        currentCalendar = Calendar.getInstance();
        year = currentCalendar.get(Calendar.YEAR);
        month = currentCalendar.get(Calendar.MONTH);
        day = currentCalendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                textDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, year, month, day);
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");
                } else {

                }
            }
        });
        page.show(context);
    }
}
