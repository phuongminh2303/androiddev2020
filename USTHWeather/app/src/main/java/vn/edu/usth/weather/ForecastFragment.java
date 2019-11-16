package vn.edu.usth.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

public class ForecastFragment extends Fragment {
    public ForecastFragment() {

    }

    private static String [] dayName = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private static int [] iconArray = {R.drawable.clouds, R.drawable.party_cloudy, R.drawable.storm, R.drawable.sun, R.drawable.wind};
    private static String [] weatherStatus = {"Cloudy", "Party Cloudy", "Thunderstorms", "Sunny", "Windy"};
    private Random rand = new Random();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* OLD METHOD: copy and paste LinearLayout multiple times in fragment_forecast.xml */
//        return inflater.inflate(R.layout.fragment_forecast, container, false);

        /* FANCY METHOD: use a loop and 2 files: fragment_forecast2_xml and weather_row.xml*/
        ScrollView view = (ScrollView) inflater.inflate(R.layout.fragment_forecast2, container, false);
        LinearLayout weatherContainer = view.findViewById(R.id.weather_container);

        for(int i = 0; i < 14; i++){
            // setup weather row layout
            LinearLayout row = (LinearLayout) inflater.inflate(R.layout.weather_row, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(30, 0, 0, 10);

            // randomly set information to weather row
            ((TextView) row.findViewById(R.id.day_row_date)).setText(dayName[i%7]);
            int index = rand.nextInt((iconArray.length- 1) - 0 + 1) + 0;
            ((ImageView) row.findViewById(R.id.day_row_icon)).setImageResource(iconArray[index]);
            ((TextView) row.findViewById(R.id.day_row_status)).setText(weatherStatus[index]);
            int temperature = rand.nextInt((30-20) +1 ) + 20;
            ((TextView) row.findViewById(R.id.day_row_temp)).setText(temperature +"C-" + (temperature+3) + "C");
            weatherContainer.addView(row, layoutParams);
        }
        return view;
    }
}
