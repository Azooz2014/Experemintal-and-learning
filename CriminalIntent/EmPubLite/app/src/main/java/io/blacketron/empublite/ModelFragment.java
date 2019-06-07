package io.blacketron.empublite;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Process;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicReference;

public class ModelFragment extends Fragment {

    final private AtomicReference<BookContents> contents = new AtomicReference<>();
    final private AtomicReference<SharedPreferences> prefs = new AtomicReference<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context host) {
        super.onAttach(host);

        if (contents.get() == null){

            new LoadThread(host).start();
        }
    }

    public BookContents getBook(){

        return contents.get();
    }

    public SharedPreferences getPrefs(){

        return prefs.get();
    }

    private class LoadThread extends Thread {

        private final Context context;
        LoadThread (Context context){
            super();

            this.context = context.getApplicationContext();
        }

        @Override
        public void run() {

            prefs.set(PreferenceManager.getDefaultSharedPreferences(context));

            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

            Gson gson = new Gson();

            try{

                InputStream is = context.getAssets().open("book/contents.json");

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                contents.set(gson.fromJson(reader, BookContents.class));

                EventBus.getDefault().post(new BookLoadedEvent(getBook()));

            } catch (IOException e) {
                Log.e(getClass().getSimpleName(), "Exception parsing JSON", e);
            }
        }
    }
}
