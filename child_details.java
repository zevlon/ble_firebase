package com.example.zevlon.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class child_details extends AppCompatActivity {

    private static final String TAG = "child_details";
    private TextView dob;//ui
    private DatePickerDialog.OnDateSetListener onDateSetListener;//ui
    private TextView age;//ui
    private FloatingActionButton btn;//ui
    private FloatingActionButton bth;//ui
    private TextView weight;
    private TextView name;
    private TextView mom_name;
    private TextView h;
    private Button submit;

    DatabaseReference databasechild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_details);

        //mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        age = (TextView) findViewById(R.id.age);

        dob = (TextView) findViewById(R.id.dob);

        //btn = (FloatingActionButton) findViewById(R.id.fta);

        bth = (FloatingActionButton) findViewById(R.id.bth);

        name=(TextView)findViewById(R.id.child_name);

        mom_name=(TextView)findViewById(R.id.mother_name);

        h=(TextView)findViewById((R.id.height));

        submit=(Button)findViewById(R.id.submit);

        databasechild= FirebaseDatabase.getInstance().getReference("child");

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(view.getContext(), datePickerListener, mYear, mMonth, mDay);
                dateDialog.getDatePicker().setMaxDate(new Date().getTime());
                dateDialog.show();
            }
        });

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(child_details.this);

                View mview = getLayoutInflater().inflate(R.layout.ftb, null);

                TextInputEditText name = (TextInputEditText) mview.findViewById(R.id.ang_name);
                TextInputEditText address = (TextInputEditText) mview.findViewById(R.id.address);
                TextInputEditText taluk = (TextInputEditText) mview.findViewById(R.id.taluk);
                TextInputEditText district = (TextInputEditText) mview.findViewById(R.id.district);
                TextInputEditText pin = (TextInputEditText) mview.findViewById(R.id.pin);
                Button submit = (Button) mview.findViewById(R.id.submit);


                mBuilder.setView(mview);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });*/

        bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent i=new Intent(child_details.this,DeviceScanActivity.class);
            startActivity(i);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_info();

                name.setText("");
                mom_name.setText("");
                dob.setText("");
                age.setText("");
                h.setText("");
                weight.setText("");

                Toast.makeText(child_details.this,"Data sent to firebase",Toast.LENGTH_SHORT).show();
            }
        });



        Intent i=getIntent();
        String text=i.getStringExtra(DeviceControlActivity.s);

        weight=(TextView)findViewById(R.id.weight);
        weight.setText(text);

    }

    private void add_info() {
        String child_name = name.getText().toString().trim();
        String mother_name = mom_name.getText().toString().trim();
        String date_of_birth = dob.getText().toString().trim();
        String age_c = age.getText().toString().trim();
        //String gender_c = .getText().toString().trim();
        String weight_c = weight.getText().toString().trim();
        String height_c = h.getText().toString().trim();

        String id=databasechild.push().getKey();
        Details details=new Details(child_name,mother_name,date_of_birth,age_c,weight_c,height_c);

        databasechild.child(id).setValue(details);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            String format = new SimpleDateFormat("dd MMM YYYY").format(c.getTime());
            dob.setText(format);
            age.setText(calculateAge(c.getTimeInMillis()));
        }
    };

    String calculateAge ( long date) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

       int month;

        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        month = (12 - dob.get(Calendar.MONTH) + today.get(Calendar.MONTH) )% 12;
        String abc=String.format("%1$s years %2$s months",age,month);
        Toast.makeText(child_details.this,abc,Toast.LENGTH_SHORT);

        return abc;
        }


}