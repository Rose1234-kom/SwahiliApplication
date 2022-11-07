package com.example.swahiliapplication.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
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
    @BindView(R.id.imageview)
    ImageView imageView;
    TextView textView;
    TextToSpeech textToSpeech;
    private Random random;
    private int pos;
    private FixedDataSource dataSource;

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

    @BindView(
            R.id.continue_button
    )
    AppCompatButton continueButton;


    @BindView(R.id.main_layout)
    ConstraintLayout mainLayout;

    @BindView(R.id.check_button)
    Button checkButton;

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
        dataSource = new FixedDataSource();
        imageView = findViewById(R.id.imageview);
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(questionText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });


        ButterKnife.bind(this);

        dataSource.setupCorrectWords();
        dataSource.setupWordsToConfuse();
        dataSource.setupSentencesToTranslate();

        setupSentenceAndWords();
        showWordsOnLine();
        // initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }

    private void showWordsOnLine() {
        //Click
        wordOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordOne.getVisibility() == View.VISIBLE) {
                    wordOne.setVisibility(View.INVISIBLE);
                    wordRandOne.setVisibility(View.VISIBLE);
                    wordRandOneText.setText(wordOneText.getText().toString());
                }
            }
        });
        wordRandOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandOne.getVisibility() == View.VISIBLE && wordOne.getVisibility()==View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandOne.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandOneText.getText().toString());
                }else if (wordRandOne.getVisibility() == View.VISIBLE && wordTwo.getVisibility()==View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandOne.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandOneText.getText().toString());
                }else if (wordRandOne.getVisibility() == View.VISIBLE && wordThree.getVisibility()==View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandOne.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandOneText.getText().toString());
                }else if (wordRandOne.getVisibility() == View.VISIBLE && wordFour.getVisibility()==View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandOne.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandOneText.getText().toString());
                }

            }
        });
        wordTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordTwo.getVisibility() == View.VISIBLE) {
                    wordTwo.setVisibility(View.INVISIBLE);
                    wordRandTwo.setVisibility(View.VISIBLE);
                    wordRandTwoText.setText(wordTwoText.getText().toString());
                }
            }
        });
        wordRandTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandTwo.getVisibility() == View.VISIBLE && wordOne.getVisibility()==View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandTwoText.getText().toString());
                }else if (wordRandTwo.getVisibility() == View.VISIBLE&& wordTwo.getVisibility()==View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandTwoText.getText().toString());
                }else if (wordRandTwo.getVisibility() == View.VISIBLE && wordThree.getVisibility()==View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandTwoText.getText().toString());
                }else if (wordRandTwo.getVisibility() == View.VISIBLE && wordFour.getVisibility()==View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandTwo.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandTwoText.getText().toString());
                }
            }
        });
        wordThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordThree.getVisibility() == View.VISIBLE) {
                    wordThree.setVisibility(View.INVISIBLE);
                    wordRandThree.setVisibility(View.VISIBLE);
                    wordRandThreeText.setText(wordThreeText.getText().toString());
                }
            }
        });
        wordRandThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandThree.getVisibility() == View.VISIBLE && wordOne.getVisibility()==View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandThreeText.getText().toString());
                }else if (wordRandThree.getVisibility() == View.VISIBLE&& wordTwo.getVisibility()==View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandThreeText.getText().toString());
                }else if (wordRandThree.getVisibility() == View.VISIBLE && wordThree.getVisibility()==View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandThreeText.getText().toString());
                }else if (wordRandThree.getVisibility() == View.VISIBLE && wordFour.getVisibility()==View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandThree.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandThreeText.getText().toString());
                }
            }
        });
        wordRandFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandFour.getVisibility() == View.VISIBLE && wordOne.getVisibility()==View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandFourText.getText().toString());
                }else if (wordRandFour.getVisibility() == View.VISIBLE && wordTwo.getVisibility()==View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandFourText.getText().toString());
                }else if (wordRandFour.getVisibility() == View.VISIBLE && wordThree.getVisibility()==View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandFourText.getText().toString());
                }else if (wordRandFour.getVisibility() == View.VISIBLE && wordFour.getVisibility()==View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandFour.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandFourText.getText().toString());
                }
            }
        });
        wordRandFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandFive.getVisibility() == View.VISIBLE && wordOne.getVisibility()==View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandFiveText.getText().toString());
                }else if (wordRandFive.getVisibility() == View.VISIBLE && wordTwo.getVisibility()==View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandFiveText.getText().toString());
                }else if (wordRandFive.getVisibility() == View.VISIBLE && wordThree.getVisibility()==View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandFiveText.getText().toString());
                }else if (wordRandFive.getVisibility() == View.VISIBLE && wordFour.getVisibility()==View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandFive.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandFiveText.getText().toString());
                }
            }
        });
        wordRandSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordRandSix.getVisibility() == View.VISIBLE && wordOne.getVisibility()==View.INVISIBLE) {
                    wordOne.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.INVISIBLE);
                    wordOneText.setText(wordRandSixText.getText().toString());
                }else if (wordRandSix.getVisibility() == View.VISIBLE && wordTwo.getVisibility()==View.INVISIBLE) {
                    wordTwo.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.INVISIBLE);
                    wordTwoText.setText(wordRandSixText.getText().toString());
                }else if (wordRandSix.getVisibility() == View.VISIBLE && wordThree.getVisibility()==View.INVISIBLE) {
                    wordThree.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.INVISIBLE);
                    wordThreeText.setText(wordRandSixText.getText().toString());
                }else if (wordRandSix.getVisibility() == View.VISIBLE && wordFour.getVisibility()==View.INVISIBLE) {
                    wordFour.setVisibility(View.VISIBLE);
                    wordRandSix.setVisibility(View.INVISIBLE);
                    wordFourText.setText(wordRandSixText.getText().toString());
                }
            }
        });
    }

    private void showDialogCorrect(String correct) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(WordTaskActivity.this);
        if (correct.equals("Correct")) {
            dialog.setMessage("Your Answer Is Correct");
        } else {
            dialog.setMessage("Your Answer Is Incorrect");
        }
        dialog.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                setupSentenceAndWords();
            }
        });
        AlertDialog alert = dialog.create();
        alert.show();
    }


    private void checkAnswer() {
        //Concatenate words from cards and match with what's stored in the arraylist
        if (wordOneText.getText().toString().concat(" ").concat(wordTwoText.getText().toString()).equals(dataSource.getSentenceTranslatedList().get(pos))) {
            showDialogCorrect("Correct");
        } else if (wordOneText.getText().toString().concat(" ").concat(wordTwoText.getText().toString()).concat(" ").concat(wordThreeText.getText().toString()).equals(dataSource.getSentenceTranslatedList().get(pos))) {
            showDialogCorrect("Correct");
        } else if (wordOneText.getText().toString().concat(" ").concat(wordTwoText.getText().toString()).concat(" ").concat(wordThreeText.getText().toString()).concat(" ").concat(wordFourText.getText().toString()).equals(dataSource.getSentenceTranslatedList().get(pos))) {
            showDialogCorrect("Correct");
        } else {
            showDialogCorrect("Incorrect");
        }

    }

    private void setupSentenceAndWords() {
        int index = new Random().nextInt(4);
        pos = index;
        if (!questionText.getText().toString().isEmpty()) {
            questionText.setText("");
            wordOneText.setText("");
            wordRandOneText.setText("");
            wordTwoText.setText("");
            wordRandTwoText.setText("");
            wordThreeText.setText("");
            wordRandThreeText.setText("");
            wordFourText.setText("");
            wordRandFourText.setText("");
            wordRandFiveText.setText("");
            wordRandSixText.setText("");
        }
        questionText.setText(dataSource.getSentenceToTranslateList().get(index));

        //Setup words such that they get randomised in the container
        if (dataSource.getWordSetCorrect().get(index).length == 2) {
            wordRandFiveText.setText(dataSource.getWordSetCorrect().get(index)[0]);
            wordRandSixText.setText(dataSource.getWordSetCorrect().get(index)[1]);
        } else if (dataSource.getWordSetCorrect().get(index).length == 3) {
            wordRandOneText.setText(dataSource.getWordSetCorrect().get(index)[0]);
            wordRandThreeText.setText(dataSource.getWordSetCorrect().get(index)[2]);
            wordRandFourText.setText(dataSource.getWordSetCorrect().get(index)[1]);
        } else {
            wordRandOneText.setText(dataSource.getWordSetCorrect().get(index)[0]);
            wordRandFiveText.setText(dataSource.getWordSetCorrect().get(index)[2]);
            wordRandThreeText.setText(dataSource.getWordSetCorrect().get(index)[1]);
            wordRandFourText.setText(dataSource.getWordSetCorrect().get(index)[3]);
        }
        if (dataSource.getWordSetConfuse().get(index).length == 4) {
            wordRandThreeText.setText(dataSource.getWordSetConfuse().get(index)[0]);
            wordRandFourText.setText(dataSource.getWordSetConfuse().get(index)[3]);
            wordRandOneText.setText(dataSource.getWordSetConfuse().get(index)[2]);
            wordRandTwoText.setText(dataSource.getWordSetConfuse().get(index)[1]);

        } else if (dataSource.getWordSetConfuse().get(index).length == 3) {
            wordRandTwoText.setText(dataSource.getWordSetConfuse().get(index)[2]);
            wordRandFiveText.setText(dataSource.getWordSetConfuse().get(index)[0]);
            wordRandSixText.setText(dataSource.getWordSetConfuse().get(index)[1]);

        } else {
            wordRandTwoText.setText(dataSource.getWordSetConfuse().get(index)[0]);
            wordRandThreeText.setText(dataSource.getWordSetConfuse().get(index)[1]);
        }
    }
}