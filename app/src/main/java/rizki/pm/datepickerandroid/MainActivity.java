package rizki.pm.datepickerandroid;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Calendar myCalender;
    DatePickerDialog.OnDateSetListener date;
    EditText txt_tgl, txt_jam;
    Button btn_getDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_tgl = (EditText)findViewById(R.id.txt_tgl);
        txt_jam = (EditText)findViewById(R.id.txt_jam);

        btn_getDateTime = (Button)findViewById(R.id.btn_get_datetime);
        myCalender = Calendar.getInstance();


        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalender.set(Calendar.YEAR, year);
                myCalender.set(Calendar.MONTH, monthOfYear);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        txt_tgl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalender
                        .get(Calendar.YEAR), myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        txt_jam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_jam.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        btn_getDateTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        "Tanggal : " + txt_tgl.getText().toString() + "\n" +
                                "Jam : " + txt_jam.getText().toString()
                        , Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txt_tgl.setText(sdf.format(myCalender.getTime()));
    }
    
}
