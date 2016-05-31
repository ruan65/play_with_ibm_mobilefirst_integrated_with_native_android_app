package org.premiumapp.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.worklight.wlclient.api.WLClient;
import com.worklight.wlclient.api.WLFailResponse;
import com.worklight.wlclient.api.WLResourceRequest;
import com.worklight.wlclient.api.WLResponse;
import com.worklight.wlclient.api.WLResponseListener;

import org.premiumapp.androidapp.data.JsonStoreAdapter;

import java.net.URI;
import java.net.URISyntaxException;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private WLClient client;
    private JsonStoreAdapter storeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = WLClient.createInstance(this);

        client.connect(new WLResponseListener() {
            @Override
            public void onSuccess(WLResponse wlResponse) {

                Log.d("mfp", "Successssssssssssssssssssssssss.....................");

            }

            @Override
            public void onFailure(WLFailResponse wlFailResponse) {

                Log.d("mfp", "Faillllllllllllllllllllllllllllllll........................");
            }
        });

        storeAdapter = ThisApp.get(this).getAdapter();
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

    public void callAdapter(View view) {
        callAdapter();
    }

    public void writeCollection(View view) {
        Timber.d("writeCollection..................");

        startActivity(new Intent(this, WriteActivity.class));
    }

    public void readCollection(View view) {
        storeAdapter.printData();
    }
}
