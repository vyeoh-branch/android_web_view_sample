

package com.example.anshul.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import org.json.JSONObject;
import android.app.Application;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Branch.getAutoInstance(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Branch init
        Branch.getInstance().initSession(new Branch.BranchReferralInitListener() {
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    Log.i("Recieving BRANCH SDK", referringParams.toString());
                    // Branch.getInstance().setIdentity("vanessa_user_id");
                    // Retrieve deeplink keys from 'referringParams' and evaluate the values to determine where to route the user
                    // Check '+clicked_branch_link' before deciding whether to use your Branch routing logic

                    Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                    startActivity(intent);
                } else {
                    Log.i("ERROR BRANCH SDK", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }

}
