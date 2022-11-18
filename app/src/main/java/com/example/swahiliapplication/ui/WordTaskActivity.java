package com.example.swahiliapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.swahiliapplication.ConstantValues;
import com.example.swahiliapplication.FixedDataSource;
import com.example.swahiliapplication.Models.CurrProgress;
import com.example.swahiliapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordTaskActivity extends AppCompatActivity {

    private static final String TAG = "WordTaskActivity";
    public RelativeLayout answerContainer;
    @BindView(R.id.imageview)
    ImageView imageView;
    TextView textView;
    TextToSpeech textToSpeech;
    private Random random;
    private int pos, cont;
    private FixedDataSource dataSource;
    private ArrayList<String> answeredCorrect = new ArrayList<>();
    private ArrayList<String> answeredWrong = new ArrayList<>();
    private HashSet<String> hashSet = new HashSet<>();


    @BindView(R.id.word_1_card)
    MaterialCardView wordOne;
    @BindView(R.id.word_random_1_card)
    MaterialCardView wordRandOne;
    @BindView(R.id.word_2_card)
    MaterialCardView wordTwo;
    @BindView(R.id.word_random_2_card)
    MaterialCardView wordRandTwo;
    @BindView(R.id.word_3_card)
    MaterialCardView wordThree;
    @BindView(R.id.word_random_3_card)
    MaterialCardView wordRandThree;
    @BindView(R.id.word_4_card)
    MaterialCardView wordFour;
    @BindView(R.id.word_random_4_card)
    MaterialCardView wordRandFour;
    @BindView(R.id.word_random_5_card)
    MaterialCardView wordRandFive;
    @BindView(R.id.word_random_6_card)
    MaterialCardView wordRandSix;
    @BindView(R.id.word_1)
    TextView wordOneText;
    @BindView(R.id.word_random_1)
    TextView wordRandOneText;
    @BindView(R.id.word_2)
    TextView wordTwoText;
    @BindView(R.id.word_random_2)
    TextView wordRandTwoText;
    @BindView(R.id.word_3)
    TextView wordThreeText;
    @BindView(R.id.word_random_3)
    TextView wordRandThreeText;
    @BindView(R.id.word_4)
    TextView wordFourText;
    @BindView(R.id.word_random_4)
    TextView wordRandFourText;
    @BindView(R.id.word_random_5)
    TextView wordRandFiveText;
    @BindView(R.id.word_random_6)
    TextView wordRandSixText;
    @BindView(R.id.question)
    TextView questionText;


    @BindView(R.id.main_layout)
    ConstraintLayout mainLayout;

    @BindView(R.id.check_button)
    Button checkButton;

    TextView textView1;

    ProgressBar progressBar;


    ArrayList<String> words = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();

    int progressBarValue;

    String action;
    Context context = WordTaskActivity.this;
    int prog, last;

    Intent intent;
    ArrayList<String> answeredContentListCorrect = new ArrayList<>();
    ArrayList<String> answeredContentListTranslatedCorrect = new ArrayList<>();
    ArrayList<String> answeredContentListWrong = new ArrayList<>();
    ArrayList<String> answeredContentListTranslatedWrong = new ArrayList<>();
    ArrayList<String[]> answeredContentListWordsCorrect = new ArrayList<>();
    ArrayList<String[]> answeredContentListWordsCorrectWrong = new ArrayList<>();
    ArrayList<String[]> answeredContentListWordsConfuse = new ArrayList<>();
    ArrayList<String[]> answeredContentListWordsConfuseWrong = new ArrayList<>();
    ImageView taskClose;
    private int move = 0;
    private int inc = 0;
    private ConstantValues constantValues = new ConstantValues();
    private String levelId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_task);
        progressBar = findViewById(R.id.task_progress_bar);
        questionText = findViewById(R.id.question);
        wordOne = findViewById(R.id.word_1_card);
        wordRandOne = findViewById(R.id.word_random_1_card);
        wordTwo = findViewById(R.id.word_2_card);
        wordRandTwo = findViewById(R.id.word_random_2_card);
        wordThree = findViewById(R.id.word_3_card);
        wordRandThree = findViewById(R.id.word_random_3_card);
        wordFour = findViewById(R.id.word_4_card);
        wordRandFour = findViewById(R.id.word_random_4_card);
        wordRandFive = findViewById(R.id.word_random_5_card);
        wordRandSix = findViewById(R.id.word_random_6_card);
        wordOneText = findViewById(R.id.word_1);
        wordRandOneText = findViewById(R.id.word_random_1);
        wordTwoText = findViewById(R.id.word_2);
        wordRandTwoText = findViewById(R.id.word_random_2);
        wordThreeText = findViewById(R.id.word_3);
        wordRandThreeText = findViewById(R.id.word_random_3);
        wordFourText = findViewById(R.id.word_4);
        wordRandFourText = findViewById(R.id.word_random_4);
        wordRandFiveText = findViewById(R.id.word_random_5);
        wordRandSixText = findViewById(R.id.word_random_6);
        dataSource = new FixedDataSource();
        imageView = findViewById(R.id.imageview);
        taskClose = findViewById(R.id.close_task);

        taskClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSaveAnsweredQuestion();
            }
        });
        intent = getIntent();
        if (intent.hasExtra("levelID") && intent.getStringExtra("levelID") != null) {
            levelId = intent.getStringExtra("levelID");
        }
        Log.i("ID", "" + levelId);
        /*
        DocumentReference reference=constantValues.getFirebaseFirestore().collection("Users").document(ConstantValues.getFirebaseAuth().getCurrentUser().getUid()).collection("CurrentProgress").document(levelId);
        CurrProgress currProgress=new CurrProgress();
        currProgress.setUserId(ConstantValues.getFirebaseAuth().getCurrentUser().getUid());
        reference.set(currProgress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }
            }
        });*/
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                // if no errors found
                if (i != TextToSpeech.ERROR) {
                    // to choose language of speech
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        setupContentInPage();
        resumeProgress(levelId);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(questionText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        ButterKnife.bind(this);

        showWordsOnLine();
        // initData();
    }

    public void setupContentInPage() {
        if (intent.hasExtra("Action")) {
            action = intent.getStringExtra("Action");
            if (intent.getStringExtra("Action").equals("Introduction")) {
                dataSource.setupSentencesToTranslate();
                setupSentenceAndWords(dataSource.getSentenceToTranslateList(), dataSource.getSentencesAnsweredCorrectList(), dataSource.getSentencesAnsweredWrongList(), dataSource.getWordSetCorrect(), dataSource.getWordSetConfuse());
            } else if (intent.getStringExtra("Action").equals("Greetings")) {
                dataSource.setupGreetingsToTranslate();
//                setupSentenceAndWords(dataSource.getGreetingsToTranslateList(), dataSource.getGreetingsTranslatedList(), dataSource.getGreetingsWordCorrectList(), dataSource.getGreetingsWordConfuseList());
            } else if (intent.getStringExtra("Action").equals("Numbers")) {
                dataSource.setupNumbersToTranslate();
//                setupSentenceAndWords(dataSource.getNumbersToTranslateList(), dataSource.getNumbersTranslatedList(), dataSource.getNumbersWordCorrectList(), dataSource.getNumbersWordConfuseList());
            } else if (intent.getStringExtra("Action").equals("Colours")) {
//                dataSource.setupColoursToTranslate();
            } else if (intent.getStringExtra("Action").equals("Family")) {
                dataSource.setupFamilyContent();
//                setupSentenceAndWords(dataSource.getFamilyMembersInfoToTranslateList(), dataSource.getFamilyMembersInfoTranslatedList(), dataSource.getFamilyMembersInfoWordCorrectList(), dataSource.getFamilyMembersInfoWordConfuseList());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(action);
            }
        });
    }

    private void showWordsOnLine() {
        //Click
        wordOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordOne.getVisibility() == View.VISIBLE && wordRandOne.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.INVISIBLE);
                    wordRandOne.setVisibility(View.VISIBLE);
                    wordRandOneText.setText(wordOneText.getText().toString());
                } else if (wordOne.getVisibility() == View.VISIBLE && wordRandTwo.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.INVISIBLE);
                    wordRandTwo.setVisibility(View.VISIBLE);
                    wordRandTwoText.setText(wordOneText.getText().toString());
                } else if (wordOne.getVisibility() == View.VISIBLE && wordRandThree.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.INVISIBLE);
                    wordRandThree.setVisibility(View.VISIBLE);
                    wordRandThreeText.setText(wordOneText.getText().toString());
                } else if (wordOne.getVisibility() == View.VISIBLE && wordRandFour.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.INVISIBLE);
                    wordRandFour.setVisibility(View.VISIBLE);
                    wordRandFourText.setText(wordOneText.getText().toString());
                } else if (wordOne.getVisibility() == View.VISIBLE && wordRandFive.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.INVISIBLE);
                    wordRandFive.setVisibility(View.VISIBLE);
                    wordRandFiveText.setText(wordOneText.getText().toString());
                } else if (wordOne.getVisibility() == View.VISIBLE && wordRandSix.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.INVISIBLE);
                    wordRandSix.setVisibility(View.VISIBLE);
                    wordRandSixText.setText(wordOneText.getText().toString());
                }
            }
        });
        wordRandOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandOne.getVisibility() == View.VISIBLE && wordOne.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandOne.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandOneText.getText().toString());
                } else if (wordRandOne.getVisibility() == View.VISIBLE && wordTwo.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandOne.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandOneText.getText().toString());
                } else if (wordRandOne.getVisibility() == View.VISIBLE && wordThree.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandOne.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandOneText.getText().toString());
                } else if (wordRandOne.getVisibility() == View.VISIBLE && wordFour.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandOne.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandOneText.getText().toString());
                }

            }
        });
        wordTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordTwo.getVisibility() == View.VISIBLE && wordRandOne.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.INVISIBLE);
                    wordRandOne.setVisibility(View.VISIBLE);
                    wordRandOneText.setText(wordTwoText.getText().toString());
                } else if (wordTwo.getVisibility() == View.VISIBLE && wordRandTwo.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.INVISIBLE);
                    wordRandTwo.setVisibility(View.VISIBLE);
                    wordRandTwoText.setText(wordTwoText.getText().toString());
                } else if (wordTwo.getVisibility() == View.VISIBLE && wordRandThree.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.INVISIBLE);
                    wordRandThree.setVisibility(View.VISIBLE);
                    wordRandThreeText.setText(wordTwoText.getText().toString());
                } else if (wordTwo.getVisibility() == View.VISIBLE && wordRandFour.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.INVISIBLE);
                    wordRandFour.setVisibility(View.VISIBLE);
                    wordRandFourText.setText(wordTwoText.getText().toString());
                } else if (wordTwo.getVisibility() == View.VISIBLE && wordRandFive.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.INVISIBLE);
                    wordRandFive.setVisibility(View.VISIBLE);
                    wordRandFiveText.setText(wordTwoText.getText().toString());
                } else if (wordTwo.getVisibility() == View.VISIBLE && wordRandSix.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.INVISIBLE);
                    wordRandSix.setVisibility(View.VISIBLE);
                    wordRandSixText.setText(wordTwoText.getText().toString());
                }
            }
        });
        wordRandTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandTwo.getVisibility() == View.VISIBLE && wordOne.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandTwoText.getText().toString());
                } else if (wordRandTwo.getVisibility() == View.VISIBLE && wordTwo.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandTwoText.getText().toString());
                } else if (wordRandTwo.getVisibility() == View.VISIBLE && wordThree.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandTwoText.getText().toString());
                } else if (wordRandTwo.getVisibility() == View.VISIBLE && wordFour.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandTwoText.getText().toString());
                }
            }
        });
        wordThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordThree.getVisibility() == View.VISIBLE && wordRandOne.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.INVISIBLE);
                    wordRandOne.setVisibility(View.VISIBLE);
                    wordRandOneText.setText(wordThreeText.getText().toString());
                } else if (wordThree.getVisibility() == View.VISIBLE && wordRandTwo.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.INVISIBLE);
                    wordRandTwo.setVisibility(View.VISIBLE);
                    wordRandTwoText.setText(wordThreeText.getText().toString());
                } else if (wordThree.getVisibility() == View.VISIBLE && wordRandThree.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.INVISIBLE);
                    wordRandThree.setVisibility(View.VISIBLE);
                    wordRandThreeText.setText(wordThreeText.getText().toString());
                } else if (wordThree.getVisibility() == View.VISIBLE && wordRandFour.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.INVISIBLE);
                    wordRandFour.setVisibility(View.VISIBLE);
                    wordRandFourText.setText(wordThreeText.getText().toString());
                } else if (wordThree.getVisibility() == View.VISIBLE && wordRandFive.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.INVISIBLE);
                    wordRandFive.setVisibility(View.VISIBLE);
                    wordRandFiveText.setText(wordThreeText.getText().toString());
                } else if (wordThree.getVisibility() == View.VISIBLE && wordRandSix.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.INVISIBLE);
                    wordRandSix.setVisibility(View.VISIBLE);
                    wordRandSixText.setText(wordThreeText.getText().toString());
                }
            }
        });
        wordFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordFour.getVisibility() == View.VISIBLE && wordRandOne.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.INVISIBLE);
                    wordRandOne.setVisibility(View.VISIBLE);
                    wordRandOneText.setText(wordFourText.getText().toString());
                } else if (wordFour.getVisibility() == View.VISIBLE && wordRandTwo.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.INVISIBLE);
                    wordRandTwo.setVisibility(View.VISIBLE);
                    wordRandTwoText.setText(wordFourText.getText().toString());
                } else if (wordFour.getVisibility() == View.VISIBLE && wordRandThree.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.INVISIBLE);
                    wordRandThree.setVisibility(View.VISIBLE);
                    wordRandThreeText.setText(wordFourText.getText().toString());
                } else if (wordFour.getVisibility() == View.VISIBLE && wordRandFour.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.INVISIBLE);
                    wordRandFour.setVisibility(View.VISIBLE);
                    wordRandFourText.setText(wordFourText.getText().toString());
                } else if (wordFour.getVisibility() == View.VISIBLE && wordRandFive.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.INVISIBLE);
                    wordRandFive.setVisibility(View.VISIBLE);
                    wordRandFiveText.setText(wordFourText.getText().toString());
                } else if (wordFour.getVisibility() == View.VISIBLE && wordRandSix.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.INVISIBLE);
                    wordRandSix.setVisibility(View.VISIBLE);
                    wordRandSixText.setText(wordFourText.getText().toString());
                }
            }
        });
        wordRandThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandThree.getVisibility() == View.VISIBLE && wordOne.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandThreeText.getText().toString());
                } else if (wordRandThree.getVisibility() == View.VISIBLE && wordTwo.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandThreeText.getText().toString());
                } else if (wordRandThree.getVisibility() == View.VISIBLE && wordThree.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandThreeText.getText().toString());
                } else if (wordRandThree.getVisibility() == View.VISIBLE && wordFour.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandThreeText.getText().toString());
                }
            }
        });
        wordRandFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandFour.getVisibility() == View.VISIBLE && wordOne.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandFourText.getText().toString());
                } else if (wordRandFour.getVisibility() == View.VISIBLE && wordTwo.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandFourText.getText().toString());
                } else if (wordRandFour.getVisibility() == View.VISIBLE && wordThree.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandFourText.getText().toString());
                } else if (wordRandFour.getVisibility() == View.VISIBLE && wordFour.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandFourText.getText().toString());
                }
            }
        });
        wordRandFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandFive.getVisibility() == View.VISIBLE && wordOne.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandFiveText.getText().toString());
                } else if (wordRandFive.getVisibility() == View.VISIBLE && wordTwo.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandFiveText.getText().toString());
                } else if (wordRandFive.getVisibility() == View.VISIBLE && wordThree.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandFiveText.getText().toString());
                } else if (wordRandFive.getVisibility() == View.VISIBLE && wordFour.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandFiveText.getText().toString());
                }
            }
        });
        wordRandSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandSix.getVisibility() == View.VISIBLE && wordOne.getVisibility() == View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandSixText.getText().toString());
                } else if (wordRandSix.getVisibility() == View.VISIBLE && wordTwo.getVisibility() == View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandSixText.getText().toString());
                } else if (wordRandSix.getVisibility() == View.VISIBLE && wordThree.getVisibility() == View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandSixText.getText().toString());
                } else if (wordRandSix.getVisibility() == View.VISIBLE && wordFour.getVisibility() == View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandSixText.getText().toString());
                }
            }
        });
    }


    private void checkAnswer(String action) {
        ArrayList<TextView> wordsFormed = new ArrayList<>();
        String result;
        if (wordTwoText.getText().toString().isEmpty() && wordThreeText.getText().toString().isEmpty() && wordFourText.getText().toString().isEmpty()) {
            result = wordOneText.getText().toString();
            if (action.equals("Introduction")) {
                if (result.equals(dataSource.getSentenceTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            } else if (action.equals("Greetings")) {
                if (result.equals(dataSource.getGreetingsTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            } else if (action.equals("Numbers")) {

            } else if (action.equals("Family")) {
                if (result.equals(dataSource.getFamilyMembersInfoTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            } else if (action.equals("Time")) {
                if (result.equals(dataSource.getTimeTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            }
        } else if (wordThreeText.getText().toString().isEmpty() && wordFourText.getText().toString().isEmpty()) {
            result = wordOneText.getText().toString().concat(" ").concat(wordTwoText.getText().toString());
            if (action.equals("Introduction")) {
                if (result.equals(dataSource.getSentenceTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            } else if (action.equals("Greetings")) {
                if (result.equals(dataSource.getGreetingsTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            } else if (action.equals("Numbers")) {

            }
        } else if (wordFourText.getText().toString().isEmpty()) {
            result = wordOneText.getText().toString().concat(" ").concat(wordTwoText.getText().toString()).concat(" ").concat(wordThreeText.getText().toString());
            if (action.equals("Introduction")) {
                if (result.equals(dataSource.getSentenceTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            } else if (action.equals("Greetings")) {
                if (result.equals(dataSource.getGreetingsTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            } else if (action.equals("Numbers")) {

            } else if (action.equals("Time")) {
                if (result.equals(dataSource.getTimeTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            }
        } else {
            result = wordOneText.getText().toString().concat(" ").concat(wordTwoText.getText().toString()).concat(" ").concat(wordThreeText.getText().toString()).concat(" ").concat(wordFourText.getText().toString());
            if (action.equals("Introduction")) {
                if (result.equals(dataSource.getSentenceTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            } else if (action.equals("Greetings")) {
                if (result.equals(dataSource.getGreetingsTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            } else if (action.equals("Numbers")) {

            } else if (action.equals("Time")) {
                if (result.equals(dataSource.getTimeTranslatedList().get(pos))) {
                    showDialogCorrect();
                } else {
                    showDialogForWrong();
                }
            }
        }
        //Concatenate words from cards and match with what's stored in the arraylist
    }

    private void resumeProgress(String levelId) {
        if(levelId!=null) {
            DocumentReference documentReference = constantValues.getFirebaseFirestore().collection("Users").document(ConstantValues.getFirebaseAuth().getCurrentUser().getUid()).collection("CurrentProgress").document(levelId);
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot snapshot = task.getResult();
                        if (snapshot.exists()) {
                            Map<String, ArrayList<Integer>> ansQuest = (Map<String, ArrayList<Integer>>) snapshot.get("answeredQuestions");
                            if (ansQuest!=null && ansQuest.entrySet().size() != 0) {
                                if (ansQuest.get("0") != null) {
                                    last = ansQuest.get("0").size();
                                }
                            }
                            Log.i("Fire", "" + last);

                            if (action.equals("Introduction")) {
                                if (last > 0 && last < dataSource.getSentenceToTranslateList().size()) {
                                    setupSentenceAndWords(last, dataSource.getSentenceToTranslateList(), dataSource.getSentencesAnsweredCorrectList(), dataSource.getSentencesAnsweredWrongList(), dataSource.getWordSetCorrect(), dataSource.getWordSetConfuse());
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    private void showDialogCorrect() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(WordTaskActivity.this);
        dialog.setMessage("Your Answer Is Correct");
        dialog.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alert = dialog.create();
        alert.show();
        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                progressBar.setProgress(prog++);
                if (action.equals("Introduction")) {
                    if(cont>0){
                        cont+=1;
                        if (cont >= dataSource.getSentenceToTranslateList().size()) {
                            showDialogScorePerfect();
                        } else {
                            dataSource.getSentencesAnsweredCorrectList().set(cont, true);
                            setupSentenceAndWords(cont, dataSource.getSentenceToTranslateList(), dataSource.getSentencesAnsweredCorrectList(), dataSource.getSentencesAnsweredWrongList(), dataSource.getWordSetCorrect(), dataSource.getWordSetConfuse());
                        }
                    }
                    else {
                        inc += 1;
                        if (inc >= dataSource.getSentenceToTranslateList().size()) {
                            showDialogScorePerfect();
                        } else {
                            answeredCorrect.add(dataSource.getSentenceToTranslateList().get(pos));
                            dataSource.getSentencesAnsweredCorrectList().set(pos, true);
                            setupSentenceAndWords(dataSource.getSentenceToTranslateList(), dataSource.getSentencesAnsweredCorrectList(), dataSource.getSentencesAnsweredWrongList(), dataSource.getWordSetCorrect(), dataSource.getWordSetConfuse());
                        }
                    }
                } else if (action.equals("Greetings")) {
                    /*dataSource.getGreetingsToTranslateList().remove(pos);
                    dataSource.getGreetingsWordCorrectList().remove(pos);
                    dataSource.getGreetingsWordConfuseList().remove(pos);*/
//                    setupSentenceAndWords(dataSource.getGreetingsToTranslateList(), dataSource.getGreetingsTranslatedList(), dataSource.getGreetingsWordCorrectList(), dataSource.getGreetingsWordConfuseList());
                } else if (action.equals("Numbers")) {
                    /*dataSource.getNumbersToTranslateList().remove(pos);
                    dataSource.getNumbersWordCorrectList().remove(pos);
                    dataSource.getNumbersWordConfuseList().remove(pos);*/
//                    setupSentenceAndWords(dataSource.getNumbersToTranslateList(), dataSource.getNumbersTranslatedList(), dataSource.getNumbersWordCorrectList(), dataSource.getNumbersWordConfuseList());
                } else if (action.equals("Family")) {
                    /*dataSource.getNumbersToTranslateList().remove(pos);
                    dataSource.getNumbersWordCorrectList().remove(pos);
                    dataSource.getNumbersWordConfuseList().remove(pos);*/
//                    setupSentenceAndWords(dataSource.getFamilyMembersInfoToTranslateList(), dataSource.getFamilyMembersInfoTranslatedList(), dataSource.getFamilyMembersInfoWordCorrectList(), dataSource.getFamilyMembersInfoWordConfuseList());
                }
            }
        });
    }

    private void showDialogForWrong() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(WordTaskActivity.this);
        dialog.setMessage("Your Answer Is Incorrect");
        answeredContentListWrong.add(dataSource.getSentenceToTranslateList().get(pos));
        answeredContentListTranslatedWrong.add(dataSource.getSentenceTranslatedList().get(pos));
        answeredContentListWordsCorrectWrong.add(dataSource.getWordSetCorrect().get(pos));
        answeredContentListWordsConfuseWrong.add(dataSource.getWordSetConfuse().get(pos));
        dialog.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                progressBar.setProgress(prog++);
                if (action.equals("Introduction")) {
                    setupSentenceAndWords(dataSource.getSentenceToTranslateList(), dataSource.getSentencesAnsweredCorrectList(), dataSource.getSentencesAnsweredWrongList(), dataSource.getWordSetCorrect(), dataSource.getWordSetConfuse());
                } else if (action.equals("Greetings")) {
//                    setupSentenceAndWords(dataSource.getGreetingsToTranslateList(), dataSource.getGreetingsTranslatedList(), dataSource.getGreetingsWordCorrectList(), dataSource.getGreetingsWordConfuseList());
                } else if (action.equals("Numbers")) {
//                    setupSentenceAndWords(dataSource.getNumbersToTranslateList(), dataSource.getNumbersTranslatedList(), dataSource.getNumbersWordCorrectList(), dataSource.getNumbersWordConfuseList());
                } else if (action.equals("Family")) {
//                    setupSentenceAndWords(dataSource.getFamilyMembersInfoToTranslateList(), dataSource.getFamilyMembersInfoTranslatedList(), dataSource.getFamilyMembersInfoWordCorrectList(), dataSource.getFamilyMembersInfoWordConfuseList());
                } else if (action.equals("Time")) {
//                    setupSentenceAndWords(dataSource.getTimeToTranslateList(), dataSource.getTimeTranslatedList(), dataSource.getFamilyMembersInfoWordCorrectList(), dataSource.getFamilyMembersInfoWordConfuseList());
                }
            }
        });
        AlertDialog alert = dialog.create();
        alert.show();
    }


    private void setupSentenceAndWords(ArrayList<String> elementsTranslate, ArrayList<Boolean> elementsAnsweredCorrect, ArrayList<Boolean> elementsAnsweredWrong, ArrayList<String[]> elementsWordCorrect, ArrayList<String[]> elementsWordConfuse) {
        progressBar.setMax(elementsTranslate.size());
        prog = progressBar.getProgress() + 1;
        ArrayList<TextView> randomText = new ArrayList<>();
        ArrayList<TextView> otherRandom = new ArrayList<>();
        randomText.add(wordRandOneText);
        randomText.add(wordRandTwoText);
        randomText.add(wordRandThreeText);
        randomText.add(wordRandFourText);
        randomText.add(wordRandFiveText);
        randomText.add(wordRandSixText);
        for (TextView t : randomText) {
            if (!t.getText().toString().isEmpty()) {
                t.setText("");
            }
        }
        for (TextView t : otherRandom) {
            if (!t.getText().toString().isEmpty()) {
                t.setText("");
            }
        }
        int index;
        if (last > 0 && last < elementsTranslate.size()) {
            index = last + 1;
            Log.i("Test", "" + last);
            {
                pos = index;
                questionText.setText(elementsTranslate.get(index));
                wordOne.setVisibility(View.INVISIBLE);
                wordTwo.setVisibility(View.INVISIBLE);
                wordThree.setVisibility(View.INVISIBLE);
                wordFour.setVisibility(View.INVISIBLE);
                wordRandOne.setVisibility(View.VISIBLE);
                wordRandTwo.setVisibility(View.VISIBLE);
                wordRandThree.setVisibility(View.VISIBLE);
                wordRandFour.setVisibility(View.VISIBLE);
                wordRandFive.setVisibility(View.VISIBLE);
                wordRandSix.setVisibility(View.VISIBLE);
                for (TextView t : randomText) {
                    if (!t.getText().toString().isEmpty()) {
                        t.setText("");
                    }
                }
                Collections.shuffle(randomText);
                for (int i = 0; i < elementsWordCorrect.get(index).length; i++) {
                    randomText.get(i).setText(elementsWordCorrect.get(index)[i]);
                }
                for (TextView t : randomText) {
                    if (t.getText().toString().isEmpty()) {
                        otherRandom.add(t);
                    }
                }
                Collections.shuffle(otherRandom);
                if (otherRandom.size() > 0) {
                    for (TextView s : otherRandom) {
                        if (s.getText().toString().isEmpty()) {

                        } else {
                            s.setText("");
                        }
                    }
                }
                //Setup words such that they get randomised in the container
                if (otherRandom.size() >= elementsWordConfuse.get(index).length) {
                    for (int i = 0; i < elementsWordConfuse.get(index).length; i++) {
                        otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                    }
                } else if (otherRandom.size() <= elementsWordConfuse.get(index).length) {
                    for (int i = 0; i < otherRandom.size(); i++) {
                        otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                    }
                }
            }
        }
        else if(cont>0 && cont<=elementsTranslate.size()){
            index=cont;
            if(elementsAnsweredCorrect.get(index)){
                {
                    pos = index;
                    questionText.setText(elementsTranslate.get(index));
                    wordOne.setVisibility(View.INVISIBLE);
                    wordTwo.setVisibility(View.INVISIBLE);
                    wordThree.setVisibility(View.INVISIBLE);
                    wordFour.setVisibility(View.INVISIBLE);
                    wordRandOne.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.VISIBLE);
                    for (TextView t : randomText) {
                        if (!t.getText().toString().isEmpty()) {
                            t.setText("");
                        }
                    }
                    Collections.shuffle(randomText);
                    for (int i = 0; i < elementsWordCorrect.get(index).length; i++) {
                        randomText.get(i).setText(elementsWordCorrect.get(index)[i]);
                    }
                    for (TextView t : randomText) {
                        if (t.getText().toString().isEmpty()) {
                            otherRandom.add(t);
                        }
                    }
                    Collections.shuffle(otherRandom);
                    if (otherRandom.size() > 0) {
                        for (TextView s : otherRandom) {
                            if (s.getText().toString().isEmpty()) {

                            } else {
                                s.setText("");
                            }
                        }
                    }
                    //Setup words such that they get randomised in the container
                    if (otherRandom.size() >= elementsWordConfuse.get(index).length) {
                        for (int i = 0; i < elementsWordConfuse.get(index).length; i++) {
                            otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                        }
                    } else if (otherRandom.size() <= elementsWordConfuse.get(index).length) {
                        for (int i = 0; i < otherRandom.size(); i++) {
                            otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                        }
                    }
                }
            }else{
                {
                    pos = index;
                    questionText.setText(elementsTranslate.get(index));
                    wordOne.setVisibility(View.INVISIBLE);
                    wordTwo.setVisibility(View.INVISIBLE);
                    wordThree.setVisibility(View.INVISIBLE);
                    wordFour.setVisibility(View.INVISIBLE);
                    wordRandOne.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.VISIBLE);
                    for (TextView t : randomText) {
                        if (!t.getText().toString().isEmpty()) {
                            t.setText("");
                        }
                    }
                    Collections.shuffle(randomText);
                    for (int i = 0; i < elementsWordCorrect.get(index).length; i++) {
                        randomText.get(i).setText(elementsWordCorrect.get(index)[i]);
                    }
                    for (TextView t : randomText) {
                        if (t.getText().toString().isEmpty()) {
                            otherRandom.add(t);
                        }
                    }
                    Collections.shuffle(otherRandom);
                    if (otherRandom.size() > 0) {
                        for (TextView s : otherRandom) {
                            if (s.getText().toString().isEmpty()) {

                            } else {
                                s.setText("");
                            }
                        }
                    }
                    //Setup words such that they get randomised in the container
                    if (otherRandom.size() >= elementsWordConfuse.get(index).length) {
                        for (int i = 0; i < elementsWordConfuse.get(index).length; i++) {
                            otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                        }
                    } else if (otherRandom.size() <= elementsWordConfuse.get(index).length) {
                        for (int i = 0; i < otherRandom.size(); i++) {
                            otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                        }
                    }
                }
            }
        }
        else {
            index = inc;
            if (inc <= elementsTranslate.size() && elementsAnsweredCorrect.get(index)) {
                {
                    pos = index;
                    questionText.setText(elementsTranslate.get(index));
                    wordOne.setVisibility(View.INVISIBLE);
                    wordTwo.setVisibility(View.INVISIBLE);
                    wordThree.setVisibility(View.INVISIBLE);
                    wordFour.setVisibility(View.INVISIBLE);
                    wordRandOne.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.VISIBLE);
                    for (TextView t : randomText) {
                        if (!t.getText().toString().isEmpty()) {
                            t.setText("");
                        }
                    }
                    Collections.shuffle(randomText);
                    for (int i = 0; i < elementsWordCorrect.get(index).length; i++) {
                        randomText.get(i).setText(elementsWordCorrect.get(index)[i]);
                    }
                    for (TextView t : randomText) {
                        if (t.getText().toString().isEmpty()) {
                            otherRandom.add(t);
                        }
                    }
                    Collections.shuffle(otherRandom);
                    if (otherRandom.size() > 0) {
                        for (TextView s : otherRandom) {
                            if (s.getText().toString().isEmpty()) {

                            } else {
                                s.setText("");
                            }
                        }
                    }
                    //Setup words such that they get randomised in the container
                    if (otherRandom.size() >= elementsWordConfuse.get(index).length) {
                        for (int i = 0; i < elementsWordConfuse.get(index).length; i++) {
                            otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                        }
                    } else if (otherRandom.size() <= elementsWordConfuse.get(index).length) {
                        for (int i = 0; i < otherRandom.size(); i++) {
                            otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                        }
                    }
                }
            }
            else
            {
                    pos = index;
                    questionText.setText(elementsTranslate.get(index));
                    wordOne.setVisibility(View.INVISIBLE);
                    wordTwo.setVisibility(View.INVISIBLE);
                    wordThree.setVisibility(View.INVISIBLE);
                    wordFour.setVisibility(View.INVISIBLE);
                    wordRandOne.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.VISIBLE);
                    for (TextView t : randomText) {
                        if (!t.getText().toString().isEmpty()) {
                            t.setText("");
                        }
                    }
                    Collections.shuffle(randomText);
                    for (int i = 0; i < elementsWordCorrect.get(index).length; i++) {
                        randomText.get(i).setText(elementsWordCorrect.get(index)[i]);
                    }
                    for (TextView t : randomText) {
                        if (t.getText().toString().isEmpty()) {
                            otherRandom.add(t);
                        }
                    }
                    Collections.shuffle(otherRandom);
                    if (otherRandom.size() > 0) {
                        for (TextView s : otherRandom) {
                            if (s.getText().toString().isEmpty()) {

                            } else {
                                s.setText("");
                            }
                        }
                    }
                    //Setup words such that they get randomised in the container
                    if (otherRandom.size() >= elementsWordConfuse.get(index).length) {
                        for (int i = 0; i < elementsWordConfuse.get(index).length; i++) {
                            otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                        }
                    } else if (otherRandom.size() <= elementsWordConfuse.get(index).length) {
                        for (int i = 0; i < otherRandom.size(); i++) {
                            otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                        }
                    }
                }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (elementsAnsweredCorrect.stream().allMatch(b -> b)) {
                showDialogScorePerfect();
            }
        }
    }

    private void setupSentenceAndWords(int last, ArrayList<String> elementsTranslate, ArrayList<Boolean> elementsAnsweredCorrect, ArrayList<Boolean> elementsAnsweredWrong, ArrayList<String[]> elementsWordCorrect, ArrayList<String[]> elementsWordConfuse) {
        progressBar.setMax(elementsTranslate.size());
        prog = progressBar.getProgress() + 1;
        ArrayList<TextView> randomText = new ArrayList<>();
        ArrayList<TextView> otherRandom = new ArrayList<>();
        randomText.add(wordRandOneText);
        randomText.add(wordRandTwoText);
        randomText.add(wordRandThreeText);
        randomText.add(wordRandFourText);
        randomText.add(wordRandFiveText);
        randomText.add(wordRandSixText);
        for (TextView t : randomText) {
            if (!t.getText().toString().isEmpty()) {
                t.setText("");
            }
        }
        for (TextView t : otherRandom) {
            if (!t.getText().toString().isEmpty()) {
                t.setText("");
            }
        }
        int index;
        if (last > 0 && last < elementsTranslate.size()) {
            index = last;
            cont=last;
            Log.i("Test", "" + last);
            progressBar.setProgress(last);
            {
                pos = index;
                questionText.setText(elementsTranslate.get(index));
                wordOne.setVisibility(View.INVISIBLE);
                wordTwo.setVisibility(View.INVISIBLE);
                wordThree.setVisibility(View.INVISIBLE);
                wordFour.setVisibility(View.INVISIBLE);
                wordRandOne.setVisibility(View.VISIBLE);
                wordRandTwo.setVisibility(View.VISIBLE);
                wordRandThree.setVisibility(View.VISIBLE);
                wordRandFour.setVisibility(View.VISIBLE);
                wordRandFive.setVisibility(View.VISIBLE);
                wordRandSix.setVisibility(View.VISIBLE);
                for (TextView t : randomText) {
                    if (!t.getText().toString().isEmpty()) {
                        t.setText("");
                    }
                }
                Collections.shuffle(randomText);
                for (int i = 0; i < elementsWordCorrect.get(index).length; i++) {
                    randomText.get(i).setText(elementsWordCorrect.get(index)[i]);
                }
                for (TextView t : randomText) {
                    if (t.getText().toString().isEmpty()) {
                        otherRandom.add(t);
                    }
                }
                Collections.shuffle(otherRandom);
                if (otherRandom.size() > 0) {
                    for (TextView s : otherRandom) {
                        if (s.getText().toString().isEmpty()) {

                        } else {
                            s.setText("");
                        }
                    }
                }
                //Setup words such that they get randomised in the container
                if (otherRandom.size() >= elementsWordConfuse.get(index).length) {
                    for (int i = 0; i < elementsWordConfuse.get(index).length; i++) {
                        otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                    }
                } else if (otherRandom.size() <= elementsWordConfuse.get(index).length) {
                    for (int i = 0; i < otherRandom.size(); i++) {
                        otherRandom.get(i).setText(elementsWordConfuse.get(index)[i]);
                    }
                }
            }
        }
        if (last>=elementsTranslate.size()) {
            showDialogScorePerfect();
        }
    }

    private void showDialogScorePerfect() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(WordTaskActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View custDialog = LayoutInflater.from(WordTaskActivity.this).inflate(R.layout.perfect_score_layout, viewGroup, false);
        dialog.setView(custDialog);
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        MaterialButton continueLevel = alertDialog.findViewById(R.id.continue_level);
        continueLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                startActivity(new Intent(WordTaskActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

    private void checkSaveAnsweredQuestion(){
        if (action.equals("Introduction")) {
            AlertDialog.Builder al=new AlertDialog.Builder(WordTaskActivity.this);
            al.setMessage("Do you wish to quit and save progress?");
            al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i1) {
                    Map<String, ArrayList<Integer>> questionsAnswered = new HashMap<>();
                    ArrayList<String> levelContent = new ArrayList<>();
                    ArrayList<Integer> ans = new ArrayList<>();
                        for (int i = 0; i < dataSource.getSentencesAnsweredCorrectList().size(); i++) {
                            if (dataSource.getSentencesAnsweredCorrectList().get(i)) {
                                ans.add(i);
                            }
                        }
                        levelContent.add(0, "Introduction");
                        questionsAnswered.put("0", ans);
                        if (levelId != null) {
                            DocumentReference progRef = constantValues.getFirebaseFirestore().collection("Users").document(ConstantValues.getFirebaseAuth().getCurrentUser().getUid()).collection("CurrentProgress").document(levelId);
                            if(ans.size()==0){

                            }else {
                                progRef.update("answeredQuestions", (questionsAnswered), "levelContent", FieldValue.arrayUnion("Introduction"), "level", "Beginner").addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                        }
                                    }
                                });
                            }
                        }
                    startActivity(new Intent(WordTaskActivity.this, HomeActivity.class));
                    finish();
                }
            });
            al.setNeutralButton("Quit only", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(WordTaskActivity.this, HomeActivity.class));
                    finish();
                }
            });
            al.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog dialog=al.create();
            dialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        checkSaveAnsweredQuestion();
    }

    //Store the failed question
    private void storeFailedQuestion() {

    }
}