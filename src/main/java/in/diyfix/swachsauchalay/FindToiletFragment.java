package in.diyfix.swachsauchalay;

import android.os.Bundle;  
import android.support.v4.app.ListFragment;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class FindToiletFragment extends ListFragment implements OnItemClickListener {
    String[] friends = new String[] {
        "rekha",
        "aditi",
        "yuv"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, friends);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView adapter, View view, int position, long id) {
        Toast.makeText(getActivity().getBaseContext(), "Item: " + friends[position], Toast.LENGTH_LONG).show();
    }
}
