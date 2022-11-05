package com.example.swahiliapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.swahiliapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TranslateSentence extends AppCompatActivity {

    @BindView(R.id.question)
    TextView tvQuestion;

    @BindView(R.id.check_button)
    Button checkButton;

    @BindView(R.id.user_answer)
    EditText etUserAnswer;

    @BindView(R.id.task_progress_bar)
    ProgressBar progressBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_sentence);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        checkButton.setEnabled(false);

    }
}