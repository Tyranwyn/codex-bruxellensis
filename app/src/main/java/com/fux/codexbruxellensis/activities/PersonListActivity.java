package com.fux.codexbruxellensis.activities;

import android.app.Activity;
import android.widget.ListView;
import android.widget.Toast;

import com.fux.codexbruxellensis.R;
import com.fux.codexbruxellensis.adapters.PersonListAdapter;
import com.fux.codexbruxellensis.model.Person;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import static android.widget.Toast.makeText;

@EActivity(R.layout.person_list)
public class PersonListActivity extends Activity {

    @ViewById
    ListView personList;
    @Bean
    PersonListAdapter adapter;

    @AfterViews
    void bindAdapter() {
        personList.setAdapter(adapter);
    }

    @ItemClick
    void personListItemClicked(Person person) {
        makeText(this, person.getFirstName() + " " + person.getLastName(), Toast.LENGTH_SHORT).show();
    }

}
