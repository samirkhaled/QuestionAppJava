package com.example.questionsjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.questionsjava.databinding.FrontBinding;

public class Front extends AppCompatActivity implements View.OnClickListener {
  FrontBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.front);
        binding= DataBindingUtil.setContentView(this,R.layout.front);


        binding.frontBtnQuestions.setOnClickListener(this);
        binding.frontBtnRate.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

       switch (view.getId())
       {
         case  R.id.front_btn_questions:
             startActivity(new Intent(this,Second.class));

                break;


       case R.id.front_btn_rate:

         try {
             Uri uri=Uri.parse("market://details?idcom.example.questionsjava");
             Intent market=new Intent(Intent.ACTION_VIEW);
             market.setData(uri);
             startActivity(market);
         }catch (Exception ex)
         {
             Uri uri=Uri.parse("https://play.google.com/store/apps/details?idcom.example.questionsjava");
             Intent market=new Intent(Intent.ACTION_VIEW);
             market.setData(uri);
             startActivity(market);

         }

           break;
           default:

       }

    }
}