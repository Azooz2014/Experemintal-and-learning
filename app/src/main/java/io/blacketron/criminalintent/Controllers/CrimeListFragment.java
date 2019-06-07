package io.blacketron.criminalintent.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.blacketron.criminalintent.Model.Crime;
import io.blacketron.criminalintent.Model.CrimeLab;
import io.blacketron.criminalintent.R;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mCrimeAdapter;
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private static final String SAVED_HINT_VISIBLE = "hint";
    CrimeLab mCrimeLab;
    int mCrimeCount;

//    private boolean mIsThereCrimeItem;
    private TextView mHintText;
    private boolean mIsSubtitleVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);

        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);

        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mHintText = view.findViewById(R.id.txt_hint);

        if(savedInstanceState != null){

            mIsSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
//            mIsThereCrimeItem = savedInstanceState.getBoolean(SAVED_HINT_VISIBLE);
        }

        updateUI();

        return view;
    }

    // calls updateUI() method to update RecyclerView after CrimeListActivity is back on top of the stack.
    @Override
    public void onResume() {
        super.onResume();

        updateUI();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mIsSubtitleVisible);
//        outState.putBoolean(SAVED_HINT_VISIBLE, mIsThereCrimeItem);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_crime_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);

        if(mIsSubtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        }else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.new_crime:

                Crime crime = new Crime();
                CrimeLab.get(getContext()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getContext(), crime.getId());
                startActivity(intent);
                return true;
            case R.id.show_subtitle:

                mIsSubtitleVisible = !mIsSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void updateSubtitle(){

        String subtitle = getResources().getQuantityString(R.plurals.subtitle_plural, mCrimeCount,mCrimeCount);

        if(!mIsSubtitleVisible){
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    public void updateHintVisibility(){

        if(mCrimeCount > 0){

        mHintText.setVisibility(View.GONE);

        }else{

            mHintText.setVisibility(View.VISIBLE);
        }

    }

    public void updateUI(){

        mCrimeLab = CrimeLab.get(getActivity());
        mCrimeCount = mCrimeLab.getCrimes().size();
        List<Crime> crimes = mCrimeLab.getCrimes();

        if (mCrimeAdapter == null) {
            mCrimeAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mCrimeAdapter);
        } else {

            //calls RecyclerView.Adapter notifyDataSetChanged()
            // method to update the list when it's data changed.
            mCrimeAdapter.setCrimes(crimes);
            mCrimeAdapter.notifyDataSetChanged();
        }
        updateSubtitle();
        updateHintVisibility();
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mCrimeSolvedImageView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_crime,parent,false));

            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            mCrimeSolvedImageView = itemView.findViewById(R.id.crime_solved_img);
        }

        public void bind(Crime crime){

            this.mCrime = crime;
            DateFormat dateFormat = new DateFormat();

            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(dateFormat.format("EEEE, MMM d, yyyy",mCrime.getDate()));
            mCrimeSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {

            /*Toast.makeText(getActivity(), mCrime.getTitle() + " is Clicked!",Toast.LENGTH_LONG)
                    .show();*/

            /*Intent intent = new Intent(getContext(), CrimeActivity.class);*/

            Intent intent = CrimePagerActivity.newIntent(getContext(), mCrime.getId());

            startActivity(intent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){

            this.mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CrimeHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder crimeHolder, int i) {

            Crime crime = mCrimes.get(i);

            crimeHolder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        public void setCrimes (List<Crime> crimes){
            mCrimes = crimes;
        }
    }
}
