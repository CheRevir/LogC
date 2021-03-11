package com.cere.logc.impl;

import androidx.annotation.NonNull;

import com.cere.logc.PrintFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by CheRevir on 2021/3/11
 */
public class JsonPrintFormat implements PrintFormat {
    private int indentSpaces = 4;

    public JsonPrintFormat() {
    }

    /**
     * @param indentSpaces {@link #indentSpaces} Json缩进
     */
    public JsonPrintFormat(int indentSpaces) {
        this.indentSpaces = indentSpaces;
    }

    @NonNull
    @Override
    public String format(@NonNull Object msg) {
        String value = msg.toString();
        try {
            if (value.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(value);
                return " \n" + jsonObject.toString(indentSpaces);
            } else if (value.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(value);
                return " \n" + jsonArray.toString(indentSpaces);
            } else {
                return value;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
