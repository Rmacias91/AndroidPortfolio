package com.example.richie.currently;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Richie on 7/13/16.
 */
public class PeopleListFragment extends Fragment {

    private RecyclerView mPersonRecyclerView;
    private PersonAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_people_list,container,false);

        mPersonRecyclerView = (RecyclerView) v.findViewById(R.id.person_recycler_view);
        mPersonRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }

    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Person> persons = personLab.getPersons();

        if(mAdapter==null) {
            mAdapter = new PersonAdapter(persons);
            mPersonRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class PersonHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{
        private Person mPerson;
        private TextView mNameTextView;
        private TextView mAvailabilityTextView;
        private TextView mPositionTextView;

        public PersonHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView)
                    itemView.findViewById(R.id.list_item_name);
            mAvailabilityTextView = (TextView)
                    itemView.findViewById(R.id.list_item_availability);
            mPositionTextView = (TextView)
                    itemView.findViewById(R.id.list_item_position);

        }

        public void bindCrime(Person person){
            mPerson = person;
            mNameTextView.setText(mPerson.getName());
            mAvailabilityTextView.setText(mPerson.getAvailability());
            mPositionTextView.setText(mPerson.getPosition());
        }
        @Override
        public void onClick(View v){
            //Should start PageViewer Activity
        }

    }


    private class PersonAdapter extends RecyclerView.Adapter<PersonHolder>{
        private List<Person> mPersons;
        public PersonAdapter(List<Person> persons){mPersons = persons;}

        @Override
        public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_person,parent,false);
            return new PersonHolder(view);
        }

        @Override
        public void onBindViewHolder(PersonHolder holder, int position) {
            Person person = mPersons.get(position);
            holder.bindCrime(person);
        }

        @Override
        public int getItemCount(){
            return mPersons.size();
        }
    }

}
