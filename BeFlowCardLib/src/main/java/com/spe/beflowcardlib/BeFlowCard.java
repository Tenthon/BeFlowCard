package com.spe.beflowcardlib;

import android.content.Context;
import android.content.Intent;

public class BeFlowCard {
    public static void beFlowcard(Context context){
        Intent i = new Intent(context, BeFlowCardActivity.class);
        context.startActivity(i);
    }
}
