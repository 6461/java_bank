package com.mma.bank;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

public class FileHandler {
    private static FileHandler instance = null;
    private static Context context = null;

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }

        return instance;
    }

    public static void setContext(Context context) {
        FileHandler.context = context;
    }

    private FileHandler() {}

    public Object readFile(Type type, String filename) {
        Object obj = null;

        try {
            Gson gson = new Gson();
            InputStream is = context.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            String json = "";

            while ((line = br.readLine()) != null) {
                json = json + line;
            }

            is.close();
            obj = gson.fromJson(json, type);
        } catch (IOException e) {
            Log.e("IOException", e.toString());
        }

        return obj;
    }

    public void writeFile(Type type, Object obj, String filename) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting().serializeNulls();
            Gson gson = builder.create();
            String json = gson.toJson(obj, type);
            // Open stream for writing.
            OutputStream os = context.openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            // Write JSON data to file.
            osw.write(json);
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", e.toString());
        }
    }
}
