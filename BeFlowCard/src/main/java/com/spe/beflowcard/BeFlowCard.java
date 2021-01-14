package com.spe.beflowcard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class BeFlowCard {
    public static void beFlowcard(Context context){
        Intent i = new Intent(context, BeFlowCardActivity.class);
        context.startActivity(i);
    }
}
