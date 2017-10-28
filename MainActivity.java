package com.keep.otpsender;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    ArrayList<Temp> al;
    TextView t1;
    String phoneno,msg;
    Random rand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Asycn obj=new Asycn();
        obj.execute();

//        al=obj.toWhomOtp();
      /* for(int i=0;i<al.size();i++)
        {
            phoneno=al.get(i).phoneno.toString();
            msg="Your Otp Is\n";
            al.get(i).otp=rand.nextInt(89999)+100001;
            msg=msg+Integer.toString(al.get(i).otp);
            al.get(i).flag=1;
            sendSMSMessage();
            al.add(i,al.get(i));
        }
        new Async().execute(al);*/
    }
    protected void sendMessage()
    {
        rand=new Random();
        Log.i("MyMistakes",Integer.toString(al.size()));
        for(int i=0;i<al.size();i++)
        {
            phoneno=al.get(i).phoneno.toString();
            msg="Your Otp Is\n";
            al.get(i).otp=rand.nextInt(89999)+10001;
            msg=msg+Integer.toString(al.get(i).otp);
            al.get(i).flag=1;
            sendSMSMessage();
            al.set(i,al.get(i));
        }
        new Async().execute(al);
    }
    protected void sendSMSMessage() {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneno, null, msg, null, null);
          // Toast.makeText(this, "SMS sent.",
            //     Toast.LENGTH_LONG).show();
    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneno, null, msg, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }*/
}
