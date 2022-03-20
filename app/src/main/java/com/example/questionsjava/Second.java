package com.example.questionsjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.questionsjava.databinding.SecondBinding;

import java.lang.reflect.Array;
import java.util.Locale;

public class Second extends AppCompatActivity implements View.OnClickListener {

    SecondBinding binding;
    String []questions;
    String []answers;
    int counter=0;
    TextToSpeech speech;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second);
        binding= DataBindingUtil.setContentView(this,R.layout.second);
        questions=getResources().getStringArray(R.array.questions);
        answers=getResources().getStringArray(R.array.answers);
        binding.secondBtnAnswer.setOnClickListener(this);
        binding.secondBtnBack.setOnClickListener(this);
        binding.secondBtnNext.setOnClickListener(this);
        binding.secondBtnVoice.setOnClickListener(this);
        //this for top textViews
        binding.secondTvQuestion.setText(questions[counter]);
        binding.secondTvYy.setText(String.valueOf(answers.length));
        binding.secondTvXx.setText(String.valueOf(counter+1)+"/");
        //speech
        speech= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
           result=speech.setLanguage(Locale.US)  ;
            }
        });


        //longClick
        binding.secondBtnAnswer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(result==TextToSpeech.LANG_NOT_SUPPORTED||result==TextToSpeech.LANG_MISSING_DATA)
                {

                }else
                {
                    speech.speak(binding.secondTvAnswer.getText(),TextToSpeech.QUEUE_FLUSH,null,null);
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.second_btn_answer:

                binding.secondTvAnswer.setText(answers[counter]);

                break;
            case R.id.second_btn_back:
              try {
                  counter--;
                  binding.secondTvQuestion.setText(questions[counter]);
                  binding.secondTvXx.setText(String.valueOf(counter+1)+"/");
                  binding.secondTvAnswer.setText(R.string.second_answer_text);
                  speech.stop();
              }catch (Exception ex)
              {
                  counter=questions.length-1;
                  binding.secondTvQuestion.setText(questions[counter]);
                  binding.secondTvXx.setText(String.valueOf(counter+1)+"/");
                  binding.secondTvAnswer.setText(R.string.second_answer_text);
                  speech.stop();
              }
                break;
            case R.id.second_btn_next:
              try {
                  counter++;
                  binding.secondTvQuestion.setText(questions[counter]);
                  binding.secondTvXx.setText(String.valueOf(counter+1)+"/");
                  binding.secondTvAnswer.setText(R.string.second_answer_text);
                  speech.stop();
              }
              catch (Exception ex)
              {
                  counter=0;
                  binding.secondTvQuestion.setText(questions[counter]);
                  binding.secondTvXx.setText(String.valueOf(counter+1)+"/");
                  binding.secondTvAnswer.setText(R.string.second_answer_text);
                  speech.stop();
              }

                break;
            case R.id.second_btn_voice:
                if(result==TextToSpeech.LANG_NOT_SUPPORTED||result==TextToSpeech.LANG_MISSING_DATA)
                {
                    Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
                }else
                {
                    speech.speak(binding.secondTvQuestion.getText(),TextToSpeech.QUEUE_FLUSH,null,null);
                }

                break;
            default:

        }
    }
}