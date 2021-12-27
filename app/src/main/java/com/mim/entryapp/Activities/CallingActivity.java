package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mim.entryapp.R;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;

import java.util.List;

public class CallingActivity extends AppCompatActivity {

    private static final String APP_KEY = "c9dad215-fa77-4c25-82d1-dc7a0999a70e";
    private static final String APP_SECRET = "YDTtNnpZBUWG52CSs/B0lg==";
    private static final String ENVIRONMENT = "clientapi.sinch.com";

    private com.sinch.android.rtc.calling.Call call;
    private TextView callState;
    private SinchClient sinchClient;

    private Button button;

    String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        Intent intent = getIntent();
        String number  = intent.getStringExtra("number");

        phone = getIntent().getStringExtra("phone");


        sinchClient = Sinch.getSinchClientBuilder()
                .context(this)
                .userId(phone)
                .applicationKey(APP_KEY)
                .applicationSecret(APP_SECRET)
                .environmentHost(ENVIRONMENT)
                .build();

        sinchClient.setSupportCalling(true);
        sinchClient.startListeningOnActiveConnection();
        sinchClient.start();

        sinchClient.getCallClient().addCallClientListener((CallClientListener) new SinchCallClientListener());

        button = (Button) findViewById(R.id.button);
        callState = (TextView) findViewById(R.id.callState);

        button.setOnClickListener((View.OnClickListener) view -> {
            if (call == null) {
                call = sinchClient.getCallClient().callUser(number);
                ((Call) call).addCallListener(new SinchCallListener());
                button.setText("Hang Up");
            } else {
                call.hangup();
            }
        });
    }

    private class SinchCallListener implements CallListener {

        @Override
        public void onCallProgressing(com.sinch.android.rtc.calling.Call call) {
            callState.setText("ringing");
        }

        @Override
        public void onCallEstablished(com.sinch.android.rtc.calling.Call call) {
            callState.setText("connected");
            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
        }

        @Override
        public void onCallEnded(com.sinch.android.rtc.calling.Call endedCall) {
            call = null;
            SinchError a = endedCall.getDetails().getError();
            button.setText("Call");
            callState.setText("");
            setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
        }

        @Override
        public void onShouldSendPushNotification(com.sinch.android.rtc.calling.Call call, List<PushPair> list) {

        }
    }


    private class SinchCallClientListener implements CallClientListener {
        @Override
        public void onIncomingCall(CallClient callClient, com.sinch.android.rtc.calling.Call incomingCall) {
            call = incomingCall;
            Toast.makeText(CallingActivity.this, "incoming call", Toast.LENGTH_SHORT).show();
            call.answer();
            call.addCallListener(new SinchCallListener());
            button.setText("Hang Up");
        }
    }

}