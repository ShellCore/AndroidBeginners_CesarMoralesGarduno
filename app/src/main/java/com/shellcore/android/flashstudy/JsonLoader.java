package com.shellcore.android.flashstudy;

import android.content.ContentValues;
import android.content.Context;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Cesar on 21/06/2017.
 */

public class JsonLoader {

    public static String loadJsonFromAsset(Context context, String filePath) {
        String json;

        try {
            InputStream inputStream = context.getAssets().open(filePath);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }
}
