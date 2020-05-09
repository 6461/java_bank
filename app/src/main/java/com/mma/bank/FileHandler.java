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

    /**
     * Read JSON data from file and convert it to object.
     * @param type Type of the object.
     * @param filename Filename.
     * @return File content.
     */
    public Object readFile(Type type, String filename) {
        Object obj = null;

        try {
            Gson gson = new Gson();
            // Open stream for reading.
            InputStream is = context.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            String json = "";
            // Read JSON data from file.
            while ((line = br.readLine()) != null) {
                json = json + line;
            }
            is.close();
            // Deserialize data. Convert JSON to object.
            obj = gson.fromJson(json, type);
        } catch (IOException e) {
            Log.e("IOException", e.toString());
        }

        return obj;
    }

    /**
     * Convert object to JSON data and write it to file.
     * @param type Type of the object.
     * @param obj Data to write.
     * @param filename Filename.
     */
    public void writeFile(Type type, Object obj, String filename) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting().serializeNulls();
            Gson gson = builder.create();
            // Serialize data. Convert object to JSON.
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
