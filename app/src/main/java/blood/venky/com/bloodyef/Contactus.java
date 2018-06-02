package blood.venky.com.bloodyef;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Contactus extends AppCompatActivity {
    private Button fb,mail,insta,call,about,signout;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    public void onBackPressed() {
      Intent i=new Intent(Contactus.this,MainActivity.class);
      startActivity(i);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        fb=(Button)findViewById(R.id.fb);
        insta=(Button)findViewById(R.id.insta);
        about=(Button)findViewById(R.id.about);
        mail=(Button)findViewById(R.id.gmail);
        call=(Button)findViewById(R.id.call);
        signout=(Button)findViewById(R.id.signout);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "yefindia@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Blood Donation");
                intent.putExtra(Intent.EXTRA_TEXT, "Urgent required Blood Type");
                startActivity(intent);
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.DONUT)
            @RequiresApi(api = Build.VERSION_CODES.DONUT)
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/yefindia");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/yefindia")));
                }
            }
        });
fb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
        startActivity(launchFacebookApplication);

    }
});

call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String phno="tel:9015901668";

        Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse(phno));
        startActivity(i);
	}
});
about.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse("https://yefindia.in/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
	}
});
signout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        auth.signOut();
		finish();
        Intent intent = new Intent(Contactus.this,Login.class);
        startActivity(intent);
		
	}
});

    }
}
