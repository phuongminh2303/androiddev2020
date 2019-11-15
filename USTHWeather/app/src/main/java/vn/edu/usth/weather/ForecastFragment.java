package vn.edu.usth.weather;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ForecastFragment extends Fragment {
    public ForecastFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // textView "Thursday"
        TextView textView = new TextView(getActivity());
        textView.setText("Thursday");
        textView.setTextSize(20);
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);

        RelativeLayout.LayoutParams textviewparam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        // imageView
        ImageView icon = new ImageView(getActivity());
        icon.setImageResource(R.drawable.sunny);
        icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        RelativeLayout.LayoutParams imageviewparam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        View view = inflater.inflate(R.layout.fragment_forecast, container,  false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.parseColor("#cfcfcf"));
        linearLayout.addView(textView, textviewparam);
        linearLayout.addView(icon, imageviewparam);

        return view;
    }
}
