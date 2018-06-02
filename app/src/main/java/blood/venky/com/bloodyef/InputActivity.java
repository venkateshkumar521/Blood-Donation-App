package blood.venky.com.bloodyef;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InputActivity extends AppCompatActivity {
    Button view;
    DatabaseReference myRef;
    List<Userdetails> list;
    RecyclerView recyclerview;
    @Override
    public void onBackPressed() {
        Toast.makeText(InputActivity.this, "Back Button is being Pressed!", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        //view = (Button) findViewById(R.id.view);
        recyclerview = (RecyclerView) findViewById(R.id.rview);
        myRef = FirebaseDatabase.getInstance().getReference();
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Userdetails userdetails = dataSnapshot1.getValue(Userdetails.class);
                            Userdetails listdata = new Userdetails();
                            assert userdetails != null;
                            String Desc = userdetails.getDesc();
                            String Hosp = userdetails.getHosp();
                            String Address = userdetails.getAddress();
                            String Mobile=userdetails.getMobile();
                            String Blood=userdetails.getBlood();
                            listdata.setDesc(Desc);
                            listdata.setHosp(Hosp);
                            listdata.setAddress(Address);
                            listdata.setMobile(Mobile);
                            listdata.setBlood(Blood);
                            list.add(listdata);
                            // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                        }
                        RecyclerviewAdapter recycler = new RecyclerviewAdapter(list);
                        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(InputActivity.this);
                        recyclerview.setLayoutManager(layoutmanager);
                        recyclerview.setItemAnimator( new DefaultItemAnimator());
                        recyclerview.setAdapter(recycler);
                    }


                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        // Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        }

