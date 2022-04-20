package com.example.myapplication;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,
        AdapterView.OnItemClickListener {
    private EditText etid,etpwd;
    private ListPopupWindow lpw;
    private String[] list;
    private String cinid,cinpwd,check;
    private ImageView login;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login= (ImageView) findViewById(R.id.imageView4);
        etid = (EditText) findViewById(R.id.editTextTextPersonName);
        etpwd = (EditText) findViewById(R.id.editTextTextPassword);
        checkBox=(CheckBox) findViewById(R.id.checkBox);
        etid.setOnTouchListener(this);

        list = new String[] { "21212", "12221", "11222", "11221" };
        lpw = new ListPopupWindow(this);
        lpw.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list));
        lpw.setAnchorView(etid);
        lpw.setModal(true);
        lpw.setOnItemClickListener(this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    login.setEnabled(true);
                    login.setImageResource(R.drawable.go);
                }
                else
                {
                    login.setEnabled(false);
                    login.setImageResource(R.drawable.ngo);
                }
            }
        });
        etid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cinid=etid.getText().toString();
                cinpwd=etpwd.getText().toString();
                check=checkBox.getText().toString();
                if(!TextUtils.isEmpty(cinid) && !TextUtils.isEmpty(cinpwd))
                {
                    login.setEnabled(true);
                    login.setImageResource(R.drawable.go);
                }
                else
                {
                    login.setEnabled(false);
                    login.setImageResource(R.drawable.ngo);
                }
            }
        });
        etpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cinid=etid.getText().toString();
                cinpwd=etpwd.getText().toString();
                if(!TextUtils.isEmpty(cinid) && !TextUtils.isEmpty(cinpwd) )
                {
                    login.setEnabled(true);
                    login.setImageResource(R.drawable.go);
                }
                else
                {
                    login.setEnabled(false);
                    login.setImageResource(R.drawable.ngo);
                }
            }
        });



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = list[position];
        etid.setText(item);
        lpw.dismiss();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getX() >= (v.getWidth() - ((EditText) v)
                    .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                lpw.show();
                return true;
            }
        }
        return false;
    }
}