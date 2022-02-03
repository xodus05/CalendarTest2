package com.example.calendartest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.util.Calendar;

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

       //오늘 날짜를 받게해주는 Calender 친구들
       Calendar c = Calendar.getInstance();
       int cYear = c.get(Calendar.YEAR);
       int cMonth = c.get(Calendar.MONTH);
       int cDay = c.get(Calendar.DAY_OF_MONTH);

       //첫 시작 시에는 오늘 날짜 일기 읽어주기
       checkdDay(cYear, cMonth, cDay);

       // datePick 기능 만들기
       // datePicker.init(연도, 달, 일)
       datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
           @Override
           public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
               //이미 선택한 날짜에 일기가 있는지 없는지 체그해야할 시간이다.
               checkedDay(year, monthOfYear, dayOfMonth);
           }
       });

       //저장/수정 버튼 누르면 실행되는 리스너
       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //fileName을 넣고 저장 시키는 메소드를 호출
               saveDiary(fileName);
           }
       });
    }

    //일기 파일 읽기
    private void checkdDay(int year, int monthOfyear, int dayOfMonth) {

       //받은 날짜로 날짜 보여주는
        viewDatePick.setText(year + " - " + monthOfyear + " - " + dayOfMonth);

       // 파일 이름을 만들어준다.
       fileName = year +"" + monthOfyear + "" + dayOfMonth + ".txt";

        FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            String str = new String(fileData, "EUC-KR");
            Toast.makeText(getApplicationContext(), "일기 써둔 날", Toast.LENGTH_SHORT).show();
            edtDiary.setText(str);
            btnSave.setText("수정하기");
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "일기 없는 날", Toast.LENGTH_SHORT).show();
            edtDiary.setText("");
            btnSave.setText("새 일기 저장");
            e.printStackTrace();
        }
    }

    //일기 저장하는 메소드
}