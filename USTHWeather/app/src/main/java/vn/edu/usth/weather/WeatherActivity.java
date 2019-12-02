package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WeatherActivity extends AppCompatActivity {
    MediaPlayer music;
    Toolbar my_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i("Info", "onCreate() method here!!!");

        // Create a new Fragment to be placed in the activity
//        ForecastFragment firstFragment = new ForecastFragment();

        // Add the fragment to the 'container' FrameLayout
//        getSupportFragmentManager().beginTransaction().add(R.id.container, firstFragment).commit();
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // Copy music file to sdcard0
        copyFileToExternalStorage(R.raw.skygarden, "skygarden.mp3");

        // Play music in the app
        music = MediaPlayer.create(WeatherActivity.this, R.raw.skygarden);
        music.start();
    }

    // labwork 12
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.refresh: Toast.makeText(getApplicationContext(), "Refresh successfully!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    // labwork 11
    private void copyFileToExternalStorage(int resourceId, String resourceName){
        String pathSDCard = Environment.getExternalStorageDirectory()
                + "/Android/data/vn.edu.usth.weather/" + resourceName;
        try{
            InputStream in = getResources().openRawResource(resourceId);
            FileOutputStream out = null;
            out = new FileOutputStream(pathSDCard);
            byte[] buff = new byte[1024];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Info", "onStart() method here!!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Info", "onResume() method here!!!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Info", "onPause() method here!!!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Info", "onStop() method here!!!");
        // Stop background music when close the program
        music.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Info", "onDestroy() method here!!!");
    }
}
