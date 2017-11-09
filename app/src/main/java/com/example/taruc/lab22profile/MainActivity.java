package com.example.taruc.lab22profile;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_UPDATE_REQUEST = 5;
    public static final String PROFILE_NAME = "com.example.taruc.lab22profile.name";
    public static final String PROFILE_EMAIL = "com.example.taruc.lab22profile.email";
    private TextView textViewName, textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking UI
        textViewName = (TextView)findViewById(R.id.textViewName);
        textViewEmail = (TextView)findViewById((R.id.textViewEmail));
    }

    public void updateProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(intent,PROFILE_UPDATE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //request code = the unique request code that identify an intent call
        //resultCode = result of intent call; either OK or Cancel
        //data = the actual data returned by an intent call
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PROFILE_UPDATE_REQUEST && resultCode == RESULT_OK) {
            String name, email;
            name = data.getStringExtra(PROFILE_NAME);
            email = data.getStringExtra(PROFILE_EMAIL);
            textViewName.setText(getString(R.string.name) + ":" + name);
            textViewEmail.setText((getString(R.string.email) + " : " + email));
        }
    }

    public void showMain(View v){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        startActivity(intent);
    }

    public void showDial(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + "0123456789"));
        startActivity(intent);
    }

    public void showSendTo(View v){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + "wensheng8892@gmail.com"));


        //verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent,0);
        boolean isIntentSafe = activities.size()>0;

        //Start activity if its safe
        if(isIntentSafe){
            startActivity(intent);
        }
    }

    public void visitBAIT2073(View v){
        String uri = "http://bait2073.000webhostapp.com/welcome.html";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    protected void onStart(){
        super.onStart();
        Log.d("Main Activity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main Activity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main Activity","onPause");
    }
}
