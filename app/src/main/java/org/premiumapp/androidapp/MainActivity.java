package org.premiumapp.androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.worklight.wlclient.api.WLClient;
import com.worklight.wlclient.api.WLFailResponse;
import com.worklight.wlclient.api.WLResourceRequest;
import com.worklight.wlclient.api.WLResponse;
import com.worklight.wlclient.api.WLResponseListener;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    WLClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = WLClient.createInstance(this);

        client.connect(new WLResponseListener() {
            @Override
            public void onSuccess(WLResponse wlResponse) {

                Log.d("mfp", "Successssssssssssssssssssssssss.....................");

                callAdapter();
            }

            @Override
            public void onFailure(WLFailResponse wlFailResponse) {

                Log.d("mfp", "Faillllllllllllllllllllllllllllllll........................");
            }
        });
    }

    private void callAdapter() {
        try {
            URI adapterPath = new URI("adapters/javaAdapter/users");

            WLResourceRequest request = new WLResourceRequest(adapterPath, WLResourceRequest.GET);

            request.send(new WLResponseListener() {
                @Override
                public void onSuccess(WLResponse wlResponse) {
                    Log.d("mfp", "Invocation status: " + wlResponse.getResponseText());
                }

                @Override
                public void onFailure(WLFailResponse wlFailResponse) {
                    Log.d("mfp", "Invocation failed: " + wlFailResponse.getResponseText());
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
