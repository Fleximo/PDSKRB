package com.fleximo.podskarbi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SpendActivity extends AppCompatActivity implements View.OnClickListener{

    private AutoCompleteTextView actv_SpendActivity_category = null;
    private Button btn_SpendActivity_Ok = null;
    private Button btn_SpendActivity_Cancel = null;
    private Button btn_SpendActivity_AddSpendCategory = null;
    private EditText et_SpendActivity_AddCategory = null;
    private LinearLayout ll_SpendActivity_AddSpendCategory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend);
        //Init
        actv_SpendActivity_category = (AutoCompleteTextView)findViewById(R.id.actv_SpendActivity_category);
        btn_SpendActivity_Ok = (Button)findViewById(R.id.btn_SpendActivity_Ok);
        btn_SpendActivity_Cancel = (Button)findViewById(R.id.btn_SpendActivity_Cancel);
        btn_SpendActivity_AddSpendCategory = (Button)findViewById(R.id.btn_SpendActivity_AddSpendCategory);
        ll_SpendActivity_AddSpendCategory = (LinearLayout)findViewById(R.id.ll_SpendActivity_AddSpendCategory);
        //Fill
        ArrayAdapter<String> categories_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.categories));
        actv_SpendActivity_category.setAdapter(categories_adapter);
        //Set listeners
        actv_SpendActivity_category.setOnClickListener(this);
        btn_SpendActivity_AddSpendCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.actv_SpendActivity_category: {
                actv_SpendActivity_category.showDropDown();
                break;
            }
           case R.id.btn_SpendActivity_Ok: {
                finish();
                break;
            }
            case R.id.btn_SpendActivity_Cancel: {
                finish();
                break;
            }
            case R.id.btn_SpendActivity_AddSpendCategory: {
                ll_SpendActivity_AddSpendCategory.setVisibility(View.VISIBLE);
                break;
            }
        }
    }
}
