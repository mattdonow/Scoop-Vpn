/*
 * Copyright (c) 2012-2018 Arne Schwabe
 * Distributed under the GNU GPL v2 with additional terms. For full terms see the file doc/LICENSE.txt
 */

package de.blinkt.openvpn.activities;

/*
 * Copyright (c) 2012-2018 Arne Schwabe
 * Distributed under the GNU GPL v2 with additional terms. For full terms see the file doc/LICENSE.txt
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import de.blinkt.openvpn.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.matt.scoopVPN.CountrySelectFragment;
import com.matt.scoopVPN.MainScreenFragment;
import com.matt.scoopVPN.ServerSelectFragment;

import de.blinkt.openvpn.R;

public class ScoopMain extends AppCompatActivity
        implements CountrySelectFragment.OnCountrySelectListener, ServerSelectFragment.OnServerSelectListener
{
    public static final String EXTRA_MESSAGE="com.example.matt,myfirstapp.MESSAGE";
    private DrawerLayout myDrawer;
    private SharedPreferences ui_prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ui_prefs = this.getSharedPreferences("UI_PREF", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);


        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //CountrySelectFragment countrySelectFragment = new CountrySelectFragment();
        //fragmentTransaction.add(R.id.content_frame, countrySelectFragment);
        //fragmentTransaction.commit();
        MainScreenFragment mainScreenFragment = new MainScreenFragment();
        fragmentTransaction.add(R.id.content_frame, mainScreenFragment);
        fragmentTransaction.commit();

        //     FragmentManager fragmentManager = getSupportFragmentManager();
        //     FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //    PlusOneFragment plusOneFragment = new PlusOneFragment();
        //   fragmentTransaction.add(R.id.content_frame, plusOneFragment);
        //   fragmentTransaction.commit();







        Log.i("MSDMSD", "Created1:");
        myDrawer = findViewById(R.id.drawer_layout);
        NavigationView navView =findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        Log.i("MENU_TEST" , item.getTitle().toString());

                        myDrawer.closeDrawers();
                        Log.i("NAV DRAWER", "ITEM ID IS: " +  item.getItemId() );
                        switch (item.getItemId()) {
                            case R.id.MAIN:
                                Log.i("MSDMSD", "CASE MAIN");
                                Log.i("MSDMSD1", "NOT YET IMPLEMENTED");
                                myDrawer.closeDrawer(GravityCompat.START);
                                MainScreenFragment mainScreenFragment = new MainScreenFragment();
                                setFragment(mainScreenFragment);

                                break;

                            case R.id.select_country:
                                Log.i("MSDMSD", "CASE select_country");
                                Log.i("MSDMSD1", "NOT YET IMPLEMENTED");
                                myDrawer.closeDrawer(GravityCompat.START);
                                CountrySelectFragment countrySelectFragment = new CountrySelectFragment();
                               setFragment(countrySelectFragment);
                                break;

                            case R.id.select_server:
                                Log.i("MSDMSD", "CASE select_server");
                                Log.i("MSDMSD1", "NOT YET IMPLEMENTED");
                                myDrawer.closeDrawer(GravityCompat.START);
                                ServerSelectFragment serverSelectFragment = ServerSelectFragment.newInstance();
                                setFragment(serverSelectFragment);
                                break;

                            case R.id.select_options:
                                Log.i("MSDMSD", "CASE select_options");
                                Log.i("MSDMSD1", "NOT YET IMPLEMENTED");
                                myDrawer.closeDrawer(GravityCompat.START);
                                break;

                        }
                        return false;
                    }


                }
        );
        Log.i("MSDMSD", "Done navItemSelect listener");
    }
    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager= getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Log.i("MSDMSD", "on omOptonsItemSelected");
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.i("MSDMSD", "CASE Home");
                myDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCountrySelect() {
        String country;
        country=ui_prefs.getString("selectedCountry", "Not applicable");
        ServerSelectFragment serverSelectFragment = ServerSelectFragment.newInstance();
        setFragment(serverSelectFragment);
        Log.i("MainActivity","Selected Country : " + country );
    }

    @Override
    public void onServerSelect() {
        String country;
        country=ui_prefs.getString("selectedServer", "Not applicable");
        Log.i("MainActivity","Selected Server : " + country );
    }
//    public void sendMessage(View view) {
//        Log.i("MSDMSD"," Button press sendMsd");
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message=editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE,message);
//        startActivity(intent);
//        // Do something later to button
//
//    }
//
//    public void onCountrySelect(View view) {
//        Intent intent = new Intent(this, SelectCountry.class );
//        Log.i("MSDMSD"," STARTING JSONTEST INTENT");
//        startActivity(intent);
//    }
//    public void onServerSelect(View view) {
//        Intent intent = new Intent(this, selectServer.class );
//        Log.i("MSDMSD"," STARTING JsonServers");
//        startActivity(intent);
//    }

}
