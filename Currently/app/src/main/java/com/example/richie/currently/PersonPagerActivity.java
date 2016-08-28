package com.example.richie.currently;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by richardmacias on 8/2/16.
 */
public class PersonPagerActivity extends FragmentActivity {

    private static final String EXTRA_PERSON_ID =
            "com.example.richie.criminalintent.person_id";
    private ViewPager mViewPager;
    private List<Person> mPersons;

    public static Intent newIntent(Context packageContext, UUID personId){
        Intent intent = new Intent(packageContext, PersonPagerActivity.class);
        intent.putExtra(EXTRA_PERSON_ID, personId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_pager);
        UUID personId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_PERSON_ID);

        mViewPager =(ViewPager)findViewById(R.id.activity_person_view_pager);
        mPersons = PersonLab.get(this).getPersons();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Person person = mPersons.get(position);
                return PersonFragment.newInstance(person.getId());
            }

            @Override
            public int getCount() {
                return 0;
            }
        });
    }
}
