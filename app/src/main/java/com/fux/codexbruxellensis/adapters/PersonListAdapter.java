package com.fux.codexbruxellensis.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fux.codexbruxellensis.model.Person;
import com.fux.codexbruxellensis.services.PersonFinder;
import com.fux.codexbruxellensis.services.SamplePersonFinder;
import com.fux.codexbruxellensis.views.PersonItemView;
import com.fux.codexbruxellensis.views.PersonItemView_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

@EBean
public class PersonListAdapter extends BaseAdapter {

    private List<Person> persons;

    @Bean(SamplePersonFinder.class)
    PersonFinder personFinder;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        persons = personFinder.findAll();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PersonItemView personItemView;
        personItemView = convertView == null ? PersonItemView_.build(context) : (PersonItemView) convertView;
        personItemView.bind(getItem(position));
        return personItemView;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Person getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
