package vn.edu.usth.weather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Random;


public class WeatherAndForecastFragment extends Fragment {

    // Store instance variables
    private String title;
    private int page;
    private TextView tempText;
    private ImageView weatherImage;
    private RequestQueue requestQueue;

    public WeatherAndForecastFragment() {
        // Required empty public constructor
    }

    // newInstance constructor for creating fragment with arguments
    protected static WeatherAndForecastFragment newInstance(int page, String title) {
        WeatherAndForecastFragment weatherAndForecastFragment = new WeatherAndForecastFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        weatherAndForecastFragment.setArguments(args);
        return weatherAndForecastFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("page", 0);
            title = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);
        RelativeLayout weather_container = (RelativeLayout) view.findViewById(R.id.weather_container);
        ((TextView) weather_container.findViewById(R.id.tab_location)).setText(title);

        tempText = (TextView) weather_container.findViewById(R.id.current_temp);
        weatherImage = (ImageView) weather_container.findViewById(R.id.current_weather);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        switch (title){
            case "Hanoi": jsonParse("21.0278,105.8342");
            case "Paris": jsonParse("48.8566,2.3522");
            case "Toulouse": jsonParse("43.6047,1.4442");
        }
        return view;
    }

    private void jsonParse(String latlong){
        String url = getResources().getString(R.string.api_key) + latlong;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("currently");
                    // get value from json
                    String weatherCondition = jsonObject.getString("summary");
                    String temperature = jsonObject.getString("temperature") + "F";
                    Log.i("USTHWeather", "summary" + weatherCondition + temperature);

                    // display updated weather
                    tempText.setText(temperature + "\n" + weatherCondition);
                    if (weatherCondition.contains("Cloud") || weatherCondition.contains("Clear")){
                        weatherImage.setImageResource(R.drawable.clouds);
                    } else if (weatherCondition.contains("Sun")){
                        weatherImage.setImageResource(R.drawable.sun);
                    } else if (weatherCondition.contains("Wind")) {
                        weatherImage.setImageResource(R.drawable.wind);
                    } else {
                        weatherImage.setImageResource(R.drawable.storm);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}
