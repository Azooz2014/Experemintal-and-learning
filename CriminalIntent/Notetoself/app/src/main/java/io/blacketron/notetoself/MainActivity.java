package io.blacketron.notetoself;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.audiofx.AudioEffect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private NoteAdapter mNoteAdapter;
    private SharedPreferences mPrefs;
    private int mAnimOption;
    private boolean mSound;
    private SoundPool mSp;
    private int mIdBeep = -1;
    private Animation mAnimFlash;
    private Animation mAnimFadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteAdapter = new NoteAdapter();

        ListView listNote = (ListView) findViewById(R.id.listView);

        listNote.setAdapter(mNoteAdapter);

        listNote.setLongClickable(true);

        listNote.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Ask noteAdapter to delete entry.
                mNoteAdapter.deleteNotes(i);

                //stop any running animation that's still running even after deleting the note.
                view.clearAnimation();

                return true;
            }
        });
        listNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                //Playing the sound fx.
                if(mSound){

                    mSp.play(mIdBeep,1,1,0,0,1);
                }

                Note tempNote = mNoteAdapter.getItem(i);

                DialogShowNote dialog = new DialogShowNote();

                dialog.sendNoteSelected(tempNote);

                dialog.show(getSupportFragmentManager(), "");
            }
        });

        //Instantiate soundPool based on android version of the host's device.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
            mSp = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(5).build();
        }else{

            mSp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        try{

            //Creating the required objects for loading sound fx into memory.
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor descriptor;

            //load fx to memory , ready for use.
            descriptor = assetManager.openFd("beep.ogg");
            mIdBeep = mSp.load(descriptor,0);
        }catch (IOException e){

            Log.e("Error", "failed to download sound files because: " + e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            DialogNewNote dialog = new DialogNewNote();
            dialog.show(getSupportFragmentManager(), "");
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewNote(Note n) {

        mNoteAdapter.addNote(n);
    }

    //Inner Class
    public class NoteAdapter extends BaseAdapter {

        private JSONSerializer mSerializer;

        List<Note> noteList = new ArrayList<>();

        @Override
        public int getCount() {
            return noteList.size();
        }

        @Override
        public Note getItem(int i) {
            return noteList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            /*Checking wither the List view is empty
            * if it's empty, create one.
            */
            if (view == null) {

                /*Create LayoutInflater object called inflater*/
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                /*Instantiate view using inflater.inflate.*/
                view = inflater.inflate(R.layout.list_item, viewGroup, false);
            }// End of if.

            /*
            * The NullPointerException problem was because that the app didn't know where the ids' where, in which view?
            *  so the solution was to explicitly tell the it that the View object from getView parameter
            *  is the targeted view by using " view.findViewById();" after the layout is inflated
            *  and saved into our View object.*/
            ImageView ivImportant = view.findViewById(R.id.imageViewImportant);
            ImageView ivTodo = view.findViewById(R.id.imageViewTodo);
            ImageView ivIdea = view.findViewById(R.id.imageViewIdea);

            TextView txtTitle = view.findViewById(R.id.txtTitle_lv);
            TextView txtDescription = view.findViewById(R.id.txtDescription_lv);

            Note tempNote = noteList.get(i);

            //To animate or to not animate
            if(tempNote.isImportant() && mAnimOption != SettingsActivity.mNone){

                view.setAnimation(mAnimFlash);
            }else{

                view.setAnimation(mAnimFadeIn);
            }
            if (!tempNote.isImportant()) {

                ivImportant.setVisibility(View.GONE);
            }

            if (!tempNote.isTodo()) {

                ivTodo.setVisibility(View.GONE);
            }

            if (!tempNote.isIdea()) {

                ivIdea.setVisibility(View.GONE);
            }

            txtTitle.setText(tempNote.getTitle());
            txtDescription.setText(tempNote.getDescription());

            return view;
        }

        public void addNote(Note n) {

            noteList.add(n);
            notifyDataSetChanged();
        }

        public NoteAdapter() {

            mSerializer = new JSONSerializer("NoteToSelf.json", MainActivity.this.getApplicationContext());

            try {

                noteList = mSerializer.load();
            } catch (Exception e) {

                Log.e("Error loading notes", "" + e);
            }
        }

        public void saveNotes() {

            try {

                mSerializer.save(noteList);
            } catch (Exception e) {

                Log.e("Error saving notes", "" + e);
            }
        }

        public void deleteNotes(int n){

            noteList.remove(n);
            notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPrefs = getSharedPreferences("Note to self", MODE_PRIVATE);
        mSound = mPrefs.getBoolean("sound", true);
        mAnimOption = mPrefs.getInt("Anim option", SettingsActivity.mFast);

        mAnimFlash = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flash);
        mAnimFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        //set the rate of flash based on the settings.
        if(mAnimOption == SettingsActivity.mFast){

            mAnimFlash.setDuration(100);
            Log.i("Anim =","" + mAnimOption);
        }else if(mAnimOption == SettingsActivity.mSlow){

            mAnimFlash.setDuration(1000);
            Log.i("Anim =","" + mAnimOption);
        }
        mNoteAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mNoteAdapter.saveNotes();
    }
}