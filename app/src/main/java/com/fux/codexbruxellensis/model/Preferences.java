package com.fux.codexbruxellensis.model;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

import java.util.Set;

@SharedPref
public interface Preferences {
    Set<String> favorites();
}
