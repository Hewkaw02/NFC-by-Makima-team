package com.team.makimainu;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NFC_UseActivity extends AppCompatActivity {

    private boolean nfcEnabled = false;
    private Button nfcToggleButton;
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_use);

        nfcToggleButton = (Button) findViewById(R.id.nfc_toggle_button);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            // Device does not support NFC
            Toast.makeText(this, "This device does not support NFC.", Toast.LENGTH_SHORT).show();
            finish();
        } else if (!nfcAdapter.isEnabled()) {
            // NFC is disabled
            Toast.makeText(this, "Please enable NFC.", Toast.LENGTH_SHORT).show();
        } else {
            // NFC is enabled
            // continue with your NFC functionality
        }

        if (nfcEnabled) {
            nfcToggleButton.setText("Disable NFC");
        } else {
            nfcToggleButton.setText("Enable NFC");
        }

        nfcToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nfcEnabled) {
                    nfcAdapter.disableForegroundDispatch(NFC_UseActivity.this);
                    nfcToggleButton.setText("Enable NFC");
                    nfcEnabled = false;
                } else {
                    nfcAdapter.enableForegroundDispatch(NFC_UseActivity.this, null, null, null);
                    nfcToggleButton.setText("Disable NFC");
                    nfcEnabled = true;
                }
            }
        });

    }
}