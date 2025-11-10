package com.example.electionpollapplication.adapters.hooks;

import android.widget.Button;

import com.example.electionpollapplication.data.entities.Problem;

public interface OnCheckChangeCheckBox {
    void setOnChangeListener(Button buttonView, Boolean isChecked, Problem problem);
}
