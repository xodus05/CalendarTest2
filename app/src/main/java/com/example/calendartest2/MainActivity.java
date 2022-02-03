package com.example.calendartest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker; // datePicker - 날짜를 선택하는 달력
    TextView viewDatePick; // viewDatePick - 선택한 날짜를 보여주는 textView
    EditText edtDiary; // edtDiary - 선택한 날짜의 일기를 쓰거나 기존에 저장된 일기가 있다면 보여주고 수정하는 영역
    Button btnSave; //btnSave - 선택한 날짜의 일기 저장 및 수정(덮어쓰기) 버튼

    String fileName; // fileName - 돌고 도는 선택된 날짜의 파일 이름

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("내장메모리에 일기를 저장하고 읽을 수 있는 간단한 일기장 앱");

        //뷰에 있는 위젯들 리턴 받아두기
       datePicker = (DatePicker) findViewById(R.id.datePicker);
       viewDatePick = (TextView) findViewById(R.id.viewDatePick);
       edtDiary = (EditText) findViewById(R.id.edtDiary);
       btnSave = (Button) findViewById(R.id.btnSave);
    }
}