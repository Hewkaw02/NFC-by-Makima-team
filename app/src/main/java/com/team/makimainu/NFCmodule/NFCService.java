package com.team.makimainu.NFCmodule;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

import java.util.Arrays;

public class NFCService extends HostApduService {

    private static final String SELECT_APDU_HEADER = "00A40400";
    private static final byte[] SELECT_OK_SW = HexStringToByteArray("9000");
    private static final byte[] UNKNOWN_CMD_SW = HexStringToByteArray("0000");

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {

        //Handle the APDU here
        //...

        if (Arrays.equals(HexStringToByteArray(SELECT_APDU_HEADER), Arrays.copyOfRange(commandApdu, 0, 4))) {
            return SELECT_OK_SW;
        } else {
            return UNKNOWN_CMD_SW;
        }

//        String command = Utils.bytesToHex(commandApdu);
//        if (command.equalsIgnoreCase("00A4040007A0000002471001")) {
//            return Utils.hexStringToByteArray("9000");
//        } else {
//            return Utils.hexStringToByteArray("6F00");
//        }

    }

    @Override
    public void onDeactivated(int reason) {

    }

    public static byte[] HexStringToByteArray(String s) throws IllegalArgumentException {
        int len = s.length();
        if (len % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}