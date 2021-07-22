package tw.com.watsonfan.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userid,userpassword;
    private TextView result;
    private Button sendbtn,cancelbtn;
    private CheckBox remme;

    private Button btn1,btn2,btn3;

    private TextView touchme;
    private LinearLayout layout1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

    }



    private void findViews(){ //自定的私有化方法  findViews()

        userid = findViewById(R.id.userid);
        userpassword = findViewById(R.id.userpwd);
        result = findViewById(R.id.result);
        sendbtn = findViewById(R.id.sendbtn);
        cancelbtn = findViewById(R.id.cancelbtn);
        remme = findViewById(R.id.remme);  //核取方塊

        remme.setChecked(false);  //設定一開始時，選項不勾選


        sendbtn.setOnClickListener(sendListener);  //註冊 sendbtn 按鈕做事件監聽

        cancelbtn.setOnClickListener(v -> {
            userid.setText("");
            userpassword.setText("");
        });

        remme.setOnClickListener(v -> {
            if (remme.isChecked()){  //已選
                result.setText("已勾選");
            }else{
                result.setText("");
            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(btnLis);
        btn2.setOnClickListener(btnLis);
        btn3.setOnClickListener(btnLis);


        touchme = findViewById(R.id.touchme);
        layout1 = findViewById(R.id.layout1);

        touchme.setOnTouchListener(new MyTouch());  //使用物件方式處理控制

        layout1.setOnTouchListener(new MyTouch());

        userpassword.setOnFocusChangeListener(new MyFocus());

    }

    private View.OnClickListener sendListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String uid = userid.getText().toString();
            String upwd = userpassword.getText().toString();
            result.setText(uid + "-" + upwd);

        }
    }; //匿名類別


    private View.OnClickListener btnLis = v -> {

        switch (v.getId()){
            case R.id.btn1:
                result.setText("第一個按鈕被點擊了！");
                break;
            case R.id.btn2:
                result.setText("第二個按鈕被點擊了！");
                break;
            case R.id.btn3:
                result.setText("第三個按鈕被點擊了！");
                break;
        }
    };

    //物件的方式建立
    private class MyTouch implements View.OnTouchListener{

        // down  , move  , up
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();

            if (action == MotionEvent.ACTION_DOWN){
                result.setText("Down");
            }else if (action == MotionEvent.ACTION_MOVE){
                //result.setText("Move");

                int pointCount = event.getPointerCount();
                StringBuffer sb = new StringBuffer();
                for (int i = 0 ; i < pointCount ; i++){
                    sb.append("點："+event.getPointerId(i) + ":" + (int)event.getX(i) + "-" + (int)event.getY(i));
                    if (i < pointCount -1)
                        sb.append("\n");
                }
                result.setText(sb.toString());



            }else if (action == MotionEvent.ACTION_UP){
                result.setText("Up");
            }
            return true;
        }
    }

    private class MyFocus implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            result.setText(hasFocus ? "觀注":"已離開");  //三元運算子
        }
    }


}