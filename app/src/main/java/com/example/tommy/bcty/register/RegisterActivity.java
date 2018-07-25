package com.example.tommy.bcty.register;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tommy.bcty.R;
import com.example.tommy.bcty.sportlist.SportListActivity;

import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.contact)
    EditText contact;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.period)
    TextView period;
    @BindView(R.id.sportType)
    AppCompatSpinner sportType;
    @BindView(R.id.stadium)
    AppCompatSpinner stadium;
    @BindView(R.id.num)
    EditText num;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.btn_jump)
    Button btnJump;


    public static final String root = "http://192.168.0.125";
    public static final String url = root + "/bcty/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //初始化短信验证码登录
//        MobSDK.init(this);
//        sendCode(this);

        setTitle("运动预约");

        final OkHttpClient client = new OkHttpClient();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("username", username.getText().toString())
                                .addFormDataPart("contact", contact.getText().toString())
                                .addFormDataPart("date", date.getText().toString())
                                .addFormDataPart("period", period.getText().toString())
                                .addFormDataPart("sportType", sportType.getSelectedItem().toString())
                                .addFormDataPart("stadium", stadium.getSelectedItem().toString())
                                .addFormDataPart("num", num.getText().toString())
                                .addFormDataPart("btnSubmit", btnSubmit.getText().toString())
                                .build();
                        Request request = new Request.Builder()
                                .url(url)
                                .post(requestBody)
                                .build();

                        Response response = null;
                        try {
                            response = client.newCall(request).execute();
                            final Response finalResponse = response;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, finalResponse.message().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,SportListActivity.class);
                startActivity(intent);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            final String result = WebUtil.get("select.php");
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();

            }
        });
        Calendar currentCalendar;
        int year, month, day;
        currentCalendar = Calendar.getInstance();
        year = currentCalendar.get(Calendar.YEAR);
        month = currentCalendar.get(Calendar.MONTH);
        day = currentCalendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                date.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, year, month, day);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                period.setText(hour + ":"+minute);
            }
        },0,0,true);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });
    }

//    public void sendCode(Context context) {
//        RegisterPage page = new RegisterPage();
//        page.setTempCode(null);
//        page.setRegisterCallback(new EventHandler() {
//            @Override
//            public void afterEvent(int event, int result, Object data) {
//                if (result == SMSSDK.RESULT_COMPLETE) {
//                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
//                    String country = (String) phoneMap.get("country");
//                    String phone = (String) phoneMap.get("phone");
//                } else {
//
//                }
//            }
//        });
//        page.show(context);
//    }
}
