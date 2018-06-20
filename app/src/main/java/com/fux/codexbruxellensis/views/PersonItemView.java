package com.fux.codexbruxellensis.views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fux.codexbruxellensis.R;
import com.fux.codexbruxellensis.model.Person;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.person_item)
public class PersonItemView extends LinearLayout {

    @ViewById
    TextView firstNameView;
    @ViewById
    TextView lastNameView;

    public PersonItemView(Context context) {
        super(context);
    }

    public void bind(Person person) {
        firstNameView.setText(person.getFirstName());
        lastNameView.setText(person.getLastName());
    }
}
