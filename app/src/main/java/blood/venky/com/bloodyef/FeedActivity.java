package blood.venky.com.bloodyef;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;
//class
public class FeedActivity extends AppCompatActivity  {

    TextView eDesc,eHosp, eAddress,eMobile,eBlood;
    Button save, view,Back;
    DatabaseReference myRef;
    List<Userdetails> list;
    RecyclerView recyclerview;
    String[] Blood = { "A+","A-","B+","B-","AB+","AB-","A1+","A1-","A1B+","A1B-","A2+","A2-","A2B+","A2B-","0+","0-","Bombay Blood Group","INRA","Any","Other"};
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FeedActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        eDesc = (EditText) findViewById(R.id.eDesc);
        eAddress = (EditText) findViewById(R.id.eAddress);
        eMobile=(EditText) findViewById(R.id.eMobile);
        eHosp=(EditText)findViewById(R.id.eHosp);
        eBlood=(EditText)findViewById(R.id.eBlood);
        save = findViewById(R.id.save);
        Back=findViewById(R.id.Back);

        Back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent it1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it1);
            }

        });
        recyclerview = findViewById(R.id.rview);
        myRef = FirebaseDatabase.getInstance().getReference();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });
    }

    private void post() {
        String Hosp = eHosp.getText().toString();
        String Desc = eDesc.getText().toString();
        String Address = eAddress.getText().toString();
        String Mobile=eMobile.getText().toString();
        String Blood=eBlood.getText().toString();
        String key = myRef.push().getKey();
        if(eMobile.length()!=10)
        {
            Toast.makeText(getApplicationContext(),"Please enter valid Mobile Number",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isEmpty(Blood) && !TextUtils.isEmpty(Mobile) && !TextUtils.isEmpty(Desc)&& !TextUtils.isEmpty(Hosp)&& !TextUtils.isEmpty(Address)) {
            String id = myRef.push().getKey();
            Userdetails userdetails=new Userdetails();
            userdetails.setHosp(Hosp);
            userdetails.setDesc(Desc);
            userdetails.setAddress(Address);
            userdetails.setBlood(Blood);
            userdetails.setMobile(Mobile);
            myRef.child(id).setValue(userdetails);
            eHosp.setText("");
            eAddress.setText("");
            eDesc.setText("");
            eMobile.setText("");
            eBlood.setText("");
            Toast.makeText(this, "Post Submitted Succesfully", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(FeedActivity.this,MainActivity.class);
            startActivity(intent);
            finish();


    }
    else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter correct Details", Toast.LENGTH_LONG).show();
        }
    }


}





