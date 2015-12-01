package in.diyfix.swachsauchalay;

import android.os.Bundle;  
import android.support.v4.app.Fragment;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;

public class FindToiletFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_layout, container, false);
    }
}
