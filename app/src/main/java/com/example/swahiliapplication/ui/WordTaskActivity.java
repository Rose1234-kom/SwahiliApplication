package com.example.swahiliapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swahiliapplication.FixedDataSource;
import com.example.swahiliapplication.QuestionModel;
import com.example.swahiliapplication.R;
import com.example.swahiliapplication.Repository;
import com.google.android.material.card.MaterialCardView;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordTaskActivity extends AppCompatActivity {

    private static final String TAG = "WordTaskActivity";
    public RelativeLayout answerContainer;
    ImageView imageView;
    TextView textView;
    TextToSpeech textToSpeech;
    private Random random;
    private FixedDataSource dataSource;

    @BindView(R.id.word_1_card)
    private MaterialCardView wordOne;
    @BindView(R.id.word_2_card)
    private MaterialCardView wordRandOne;

    private MaterialCardView wordTwo;

    private MaterialCardView wordRandTwo;

    private MaterialCardView wordThree;

    private MaterialCardView wordRandThree;

    private MaterialCardView wordFour;

    private MaterialCardView wordRandFour;

    private MaterialCardView wordRandFive;

    private MaterialCardView wordRandSix;

    private TextView wordOneText;
    private TextView wordRandOneText;
    private TextView wordTwoText;
    private TextView wordRandTwoText;
    private TextView wordThreeText;
    private TextView wordRandThreeText;
    private TextView wordFourText;
    private TextView wordRandFourText;
    private TextView wordRandFiveText;
    private TextView wordRandSixText;



    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;

    @BindView(R.id.check_button)
    Button checkButton;

    @BindView(R.id.question)
    TextView tvQuestion;
    TextView textView1;

    @BindView(R.id.task_progress_bar)
    ProgressBar progressBar;



    ArrayList<String> words = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();

    int progressBarValue;

    Context context = WordTaskActivity.this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_task);

        dataSource=new FixedDataSource();
        imageView = findViewById(R.id.imageview);
        textView = findViewById(R.id.question);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                // if no errors found
                if(i!= TextToSpeech.ERROR){
                    // to choose language of speech
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(textView.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });



        ButterKnife.bind(this);

        dataSource.setupCorrectWords();
        dataSource.setupWordsToConfuse();
        dataSource.setupSentencesToTranslate();

        // initData();
    }


    private void checkAnswer(){
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkButton.getText().equals("check")) {


                }else if (checkButton.getText().equals("continue")) {

                }
            }
        });
    }


}

