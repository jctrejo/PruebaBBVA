package com.upax.bbvaprueba1.di

import android.content.Context
import com.upax.bbvaprueba1.data.local.Preferences
import com.upax.bbvaprueba1.data.local.PreferencesImpl

object DataProvider {
    fun providesPreferences(context: Context): Preferences = PreferencesImpl.getInstance(context, "")
}
