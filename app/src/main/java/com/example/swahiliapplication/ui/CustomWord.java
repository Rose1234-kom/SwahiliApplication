package com.example.swahiliapplication.ui;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.example.swahiliapplication.R;
import com.nex3z.flowlayout.FlowLayout;


public class CustomWord extends AppCompatTextView{
    private static final String TAG = "CustomWord";

    private String word;
    ViewGroup.LayoutParams params;


    public CustomWord(Context context,String word) {
        super(context);
        this.word=word;

        setText(word);

        ViewGroup.LayoutParams LayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        setTextColor(getResources().getColor(R.color.black));

        setLayoutParams(LayoutParams);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setTextSize(20);

        setBackground(ContextCompat.getDrawable(getContext(),R.drawable.custom_word_border));
    }

    public void goToViewGroup(CustomLayout customLayout,FlowLayout sentenceLayout){
        ViewParent parent = getParent();

        if(parent instanceof CustomLayout) {
            customLayout.removeViewCustomLayout(this);
            sentenceLayout.addView(this);
        }else {
            sentenceLayout.removeView(this);
            customLayout.addView(this);
        }
    }


}
