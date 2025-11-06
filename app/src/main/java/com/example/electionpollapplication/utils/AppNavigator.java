package com.example.electionpollapplication.utils;

import android.content.Context;
import android.content.Intent;

public class AppNavigator {
    public static void goTo(Context context, Class<?> destination){
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }
}
