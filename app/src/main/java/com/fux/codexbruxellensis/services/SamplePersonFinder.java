package com.fux.codexbruxellensis.services;

import com.fux.codexbruxellensis.model.Person;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

@EBean
public class SamplePersonFinder implements PersonFinder {
    @Override
    public List<Person> findAll() {
        Person p1 = new Person("Sammi", "Fux");
        Person p2 = new Person("Jordi", "Van Pottelbergh");
        Person p3 = new Person("Mihail", "Baeck");
        Person p4 = new Person("Steve", "Charlier");
        Person p5 = new Person("Kevin", "Charlier");
        Person p6 = new Person("Jonatan", "Fux");

        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);
        personList.add(p6);

        return personList;
    }
}
