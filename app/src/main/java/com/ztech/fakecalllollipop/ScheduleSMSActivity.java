package com.ztech.fakecalllollipop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class ScheduleSMSActivity extends AppCompatActivity implements SelectTimeFragment.IEventListener, SelectContactFragment.IEventListener {

    Calendar calendar = null;

    String contactImage = null;

    RadioGroup smsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_sms);

        smsType = (RadioGroup)findViewById(R.id.messageType);
    }

    @Override
    public void sendTime(Calendar calendar) {

        this.calendar = calendar;

    }

    @Override
    public void sendContactImage(String contactImage) {

        this.contactImage = contactImage;

    }

    public void onClickSchedule(View view) {

        EditText nameInput = (EditText) findViewById(R.id.nameInput);

        EditText numberInput = (EditText) findViewById(R.id.numberInput);

        EditText timeInput = (EditText) findViewById(R.id.scheduleTimePicker);

        String name = nameInput.getText().toString();

        String number = numberInput.getText().toString();

        String time = timeInput.getText().toString();

        if (number.equals("")) {

            Toast.makeText(this, "Number can't be empty!", Toast.LENGTH_SHORT).show();

            return;

        }

        if (time.equals("")) {

            Toast.makeText(this, "Call time can't be empty", Toast.LENGTH_SHORT).show();

            return;

        }

        if (name.equals("")) {

            name = getResources().getString(R.string.unknown);

        }

        RadioButton radioButton = (RadioButton)findViewById(smsType.getCheckedRadioButtonId());

        int radioButtonIndex = smsType.indexOfChild(radioButton);

        if (radioButtonIndex == 0) {

        } else if (radioButtonIndex == 1) {

        }

    }

}
