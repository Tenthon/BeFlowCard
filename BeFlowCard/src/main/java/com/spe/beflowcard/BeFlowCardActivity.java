package com.spe.beflowcard;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class BeFlowCardActivity extends AppCompatActivity {
    private EditText edtCardNumber, edtCardName, edtCVV;
    private TextView textCard, textName, textExpires, textVCCBelakang;
    private RelativeLayout relDepan, relBelakang;
    Boolean isDelete;
    boolean isBackShowing = false;
    private Spinner spinnerMonth, spinnerYears;
    private String[] month = {"Month","01","02","03","04",
            "05","06","07","08","09","10","11","12"};
    private String[] years = {"Years","2015","2016","2017","2018",
            "2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
    private String monthSelect;
    private Button btnSubmit;
    private String valueMonth, valueYears, valueCardNumber, valueCardName, valueCVV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_flow_card);
        initView();
    }
    private void initView() {
        edtCardNumber = findViewById(R.id.edtCardNumber);
        edtCardName = findViewById(R.id.edtCardName);
        spinnerMonth = findViewById(R.id.spinnerMont);
        spinnerYears = findViewById(R.id.spinnerYears);
        edtCVV = findViewById(R.id.edtCvv);
        textCard = findViewById(R.id.edtNoCard);
        textName = findViewById(R.id.edtTxtNama);
        textExpires = findViewById(R.id.edtTxtExpires);
        relDepan = findViewById(R.id.relCardDepan);
        relBelakang = findViewById(R.id.relCardBelakang);
        textVCCBelakang = findViewById(R.id.textVCC);
        btnSubmit = findViewById(R.id.btnSubmit);


        //textWatcher untuk Card Number
        TextWatcher textWatcherCardNumber = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textCard.setText(s);
                if (s.length() == 0) {
                    textCard.setText("#### #### #### ####");
                }
                if (before == 0)
                    isDelete = false;
                else
                    isDelete = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                String source = s.toString();
                valueCardNumber = s.toString();
                int length = source.length();

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(source);

                if (length > 0 && length % 5 == 0) {
                    if (isDelete)
                        stringBuilder.deleteCharAt(length - 1);
                    else
                        stringBuilder.insert(length - 1, " ");

                    edtCardNumber.setText(stringBuilder);
                    edtCardNumber.setSelection(edtCardNumber.getText().length());

                }
            }
        };
        edtCardNumber.addTextChangedListener(textWatcherCardNumber);


        //textWatcher untuk Nama
        TextWatcher textWatcherCardName = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textName.setText(s);
                valueCardName = s.toString();
                if (s.length() == 0) {
                    textName.setText("Tenthon Aligen Audit");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edtCardName.addTextChangedListener(textWatcherCardName);



        //textWatcher untuk VCC
        TextWatcher textWatcherVCC = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textVCCBelakang.setText(s);
                valueCVV = textVCCBelakang.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edtCVV.addTextChangedListener(textWatcherVCC);


        //animated untuk card
//
        edtCVV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showBack();
                return false;
            }
        });

        edtCardNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showFront();
                return false;
            }
        });


        edtCardName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showFront();
                return false;
            }
        });


        mySpinner();
        buttonSubmit();

    }

    private void mySpinner(){

        //Spinner Month
        final ArrayAdapter<String> adapterMonth = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,month);

        //Memasukan Adapter pada Spinner
        spinnerMonth.setAdapter(adapterMonth);

        //Mengeset listener untuk mengetahui event/aksi saat item dipilih
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                if(i == 0){
                    textExpires.setText("MM/YY");
                    spinnerYears.setEnabled(false);
                    valueMonth = null;
                } else {
                    Toast.makeText(getApplicationContext(),"Bulan "+adapterMonth.getItem(i) , Toast.LENGTH_SHORT).show();
                    textExpires.setText(adapterMonth.getItem(i) + "/");
                    monthSelect = textExpires.getText().toString();
                    spinnerYears.setEnabled(true);
                    valueMonth = adapterMonth.getItem(i);
                }

            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });

        spinnerMonth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showFront();
                return false;
            }
        });

        //Spinner Years

        final ArrayAdapter<String> adapterYears = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,years);

        //Memasukan Adapter pada Spinner
        spinnerYears.setAdapter(adapterYears);

        //Mengeset listener untuk mengetahui event/aksi saat item dipilih
        spinnerYears.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                if(i == 0){
                    if(monthSelect != null)
                        textExpires.setText(monthSelect);
                    else
                        textExpires.setText("MM/YY");

                    valueYears = null;
                } else {
                    Toast.makeText(getApplicationContext(),"Tahun "+adapterYears.getItem(i), Toast.LENGTH_SHORT).show();
                    textExpires.setText(monthSelect + adapterYears.getItem(i));
                    valueYears = adapterYears.getItem(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });
        spinnerYears.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showFront();
                return false;
            }
        });
    }

    private void buttonSubmit(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valueCardNumber != null  && valueCardName != null && valueMonth != null && valueYears != null
                        && valueCVV != null){
                    System.out.println("Card Number" + valueCardNumber);
                    System.out.println("Card Name" + valueCardName);
                    System.out.println("Card Month" + valueMonth);
                    System.out.println("Card Years"+ valueYears);
                    System.out.println("Card CVV" + valueCVV);
                } else {
                    Toast.makeText(getApplicationContext(),"Informasi harus lengkap" , Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void showFront() {
        if (isBackShowing) {
            Animator cardFlipRightIn = AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.card_flip_right_in);
            cardFlipRightIn.setTarget(relDepan);
            cardFlipRightIn.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    relBelakang.setVisibility(GONE);
                    relDepan.setVisibility(VISIBLE);
                    isBackShowing = false;
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            cardFlipRightIn.start();
        }

    }

    private void showBack() {
        if (!isBackShowing) {
            Animator cardFlipLeftIn = AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.card_flip_left_in);
            cardFlipLeftIn.setTarget(relBelakang);
            cardFlipLeftIn.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    relDepan.setVisibility(GONE);
                    relBelakang.setVisibility(VISIBLE);
                    isBackShowing = true;
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            cardFlipLeftIn.start();
        }
    }
}