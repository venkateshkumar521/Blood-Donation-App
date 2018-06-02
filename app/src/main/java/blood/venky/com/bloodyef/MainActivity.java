package blood.venky.com.bloodyef;

import android.app.AlertDialog;
import android.app.admin.DeviceAdminInfo;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import  com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DeviceAdminInfo context;
    Intent intent,sharingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(MainActivity.this,Contactus.class);
                startActivity(intent);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            intent=new Intent(MainActivity.this,Settings.class);
            startActivity(intent);
        }
        if(id == R.id.action_search){

        }
        if(id == R.id.Myprofile){
            intent=new Intent(MainActivity.this,Myprofile.class);
            startActivity(intent);
        }


        if(id==R.id.sign_out)
        {
            auth.signOut();
            finish();
             intent = new Intent(this,Login.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Userdetails) {
            intent=new Intent(MainActivity.this,InputActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_postrequest) {
                intent=new Intent(MainActivity.this,FeedActivity.class);
                startActivity(intent);

        }
        else if (id == R.id.nav_MyProfile) {
           intent=new Intent(MainActivity.this,Myprofile.class);
            startActivity(intent);

        }

        else if (id == R.id.nav_Settings) {
       intent=new Intent(MainActivity.this,Settings.class);
        startActivity(intent);
        }
        else if (id == R.id.nav_share) {
            sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://yefindia.in/");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Visit this Website......");
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

        }
        else if (id == R.id.nav_send) {
            intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "yefindia@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Blood Donation");
            intent.putExtra(Intent.EXTRA_TEXT, "Urgent required Blood Type");
            startActivity(intent);


        }
        else if(id==R.id.nav_About)
        {
            Uri uri = Uri.parse("https://yefindia.in/");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
