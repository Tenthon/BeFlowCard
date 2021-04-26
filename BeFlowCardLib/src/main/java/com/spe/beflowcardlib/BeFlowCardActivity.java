package com.spe.beflowcardlib;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class BeFlowCardActivity extends AppCompatActivity {
    private EditText edtCardNumber, edtCardName, edtMonth, edtYears, edtCVV, edtTxtCardNumber, edtTxtName, edtTxtExpires;
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
    private ImageView imageCard;
    private String valueCardNumber = null;
    private String valueCardName = null;
    private String valueMonth = null;
    private String valueYears = null;
    private String valueCVV = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_flow_card);
        initView();
    }

    private void initView() {
        edtCardNumber = findViewById(R.id.edtCardNumber);
        edtCardName = findViewById(R.id.edtCardName);
        //edtMonth = findViewById(R.id.edtMonth);
        spinnerMonth = findViewById(R.id.spinnerMont);
        //edtYears = findViewById(R.id.edtYears);
        spinnerYears = findViewById(R.id.spinnerYears);
        edtCVV = findViewById(R.id.edtCvv);
        textCard = findViewById(R.id.edtNoCard);
        textName = findViewById(R.id.edtTxtNama);
        textExpires = findViewById(R.id.edtTxtExpires);
        relDepan = findViewById(R.id.relCardDepan);
        relBelakang = findViewById(R.id.relCardBelakang);
        textVCCBelakang = findViewById(R.id.textVCC);
        btnSubmit = findViewById(R.id.btnSubmit);
        imageCard = findViewById(R.id.imgCard);


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
                //valueCardNumber = s.toString();
                int length = source.length();

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(source);
                System.out.println("nilain " + length + "value" + valueCardNumber);
                //untuk validasi jenis kartu
                if (length >= 4) {
                    imageCard.setVisibility(VISIBLE);
                    if(source.equals("8888")){
                        imageCard.setImageResource(R.drawable.creditcard);
                        Toast.makeText(getApplicationContext(), "Credit Card", Toast.LENGTH_SHORT).show();
                    } else if(source.equals("5555")) {
                        imageCard.setImageResource(R.drawable.debit);
                        Toast.makeText(getApplicationContext(), "Debit Card", Toast.LENGTH_SHORT).show();
                    } else if(source.equals("1111")){
                        imageCard.setImageResource(R.drawable.visa);
                        Toast.makeText(getApplicationContext(), "Visa Card", Toast.LENGTH_SHORT).show();
                    } else if(source.equals("1234")){
                        imageCard.setImageResource(R.drawable.ic_mastercard);
                        Toast.makeText(getApplicationContext(), "Master Card", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    imageCard.setVisibility(INVISIBLE);
                }


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
                spinnerMonth.setEnabled(true);
                //valueCardName = s.toString();
                if (s.length() == 0) {
                    textName.setText("#### #### #### ####");
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
//                valueCVV = textVCCBelakang.getText().toString();
//                System.out.println("value oncek" + valueCVV);

            }

            @Override
            public void afterTextChanged(Editable s) {
                String source = s.toString();
                valueCVV = source;
                System.out.println("data source" + source);

                if ( source.length() >= 5){
                    showFront();
                }
            }
        };
        edtCVV.addTextChangedListener(textWatcherVCC);


        //animated untuk card
//
        edtCVV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Animation bottomUp = AnimationUtils.loadAnimation(getApplicationContext(),
//                        R.anim.bottom_up);
//                relBelakang.startAnimation(bottomUp);
//                relBelakang.setVisibility(VISIBLE);
//
//
//                Animation bottomDown = AnimationUtils.loadAnimation(getApplicationContext(),
//                        R.anim.bottom_down);
//                relDepan.startAnimation(bottomDown);
//                relDepan.setVisibility(GONE);
                showBack();
                textExpires.setBackgroundResource(R.drawable.background_treansparant);
                return false;
            }
        });


        edtCVV.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO ) {
                    // handle next button
                    showFront();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    return true;
                }
                return false;
            }
        });

        edtCardNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Animation bottomUp = AnimationUtils.loadAnimation(getApplicationContext(),
//                        R.anim.bottom_up);
//                relDepan.startAnimation(bottomUp);
//                relDepan.setVisibility(VISIBLE);
//
//                Animation bottomDown = AnimationUtils.loadAnimation(getApplicationContext(),
//                        R.anim.bottom_down);
//                relBelakang.startAnimation(bottomDown);
//                relBelakang.setVisibility(GONE);
                showFront();
                textCard.setBackgroundResource(R.drawable.background_animasi);
                textName.setBackgroundResource(R.drawable.background_treansparant);
                textExpires.setBackgroundResource(R.drawable.background_treansparant);
                return false;
            }
        });


        edtCardName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Animation bottomUp = AnimationUtils.loadAnimation(getApplicationContext(),
//                        R.anim.bottom_up);
//                relDepan.startAnimation(bottomUp);
//                relDepan.setVisibility(VISIBLE);
//
//                Animation bottomDown = AnimationUtils.loadAnimation(getApplicationContext(),
//                        R.anim.bottom_down);
//                relBelakang.startAnimation(bottomDown);
//                relBelakang.setVisibility(GONE);
                showFront();
                textCard.setBackgroundResource(R.drawable.background_treansparant);
                textName.setBackgroundResource(R.drawable.background_animasi);
                textExpires.setBackgroundResource(R.drawable.background_treansparant);
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
                    spinnerMonth.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });

        spinnerMonth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(spinnerMonth.getWindowToken(),0);
                showFront();
                textCard.setBackgroundResource(R.drawable.background_treansparant);
                textName.setBackgroundResource(R.drawable.background_treansparant);
                textExpires.setBackgroundResource(R.drawable.background_animasi);
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
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(spinnerYears.getWindowToken(),0);
                showFront();
                textCard.setBackgroundResource(R.drawable.background_treansparant);
                textName.setBackgroundResource(R.drawable.background_treansparant);
                textExpires.setBackgroundResource(R.drawable.background_animasi);
                return false;
            }
        });
    }

    private void buttonSubmit(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueCardNumber = edtCardNumber.getText().toString();
                valueCardName = edtCardName.getText().toString();
                valueCVV = edtCVV.getText().toString();
                if(valueCardNumber.equals("") || valueCardName.equals("") || valueMonth == null || valueYears == null
                        || valueCVV.equals("")){
                    Toast.makeText(getApplicationContext(),"Informasi harus lengkap" , Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("Card Number" + valueCardNumber);
                    System.out.println("Card Name" + valueCardName);
                    System.out.println("Card Month" + valueMonth);
                    System.out.println("Card Years"+ valueYears);
                    System.out.println("Card CVV" + valueCVV);
                    Toast.makeText(getApplicationContext(),"Submit berhasil !!!" , Toast.LENGTH_SHORT).show();
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