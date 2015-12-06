package in.diyfix.swachsauchalay;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Retrofit;
import retrofit.Response;
import retrofit.Callback;
import retrofit.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "SwachShauchalay";
    private static final String API_URL = "http://192.168.1.132:8000";

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
 
    public void getToilets() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        ToiletApiService.Server server = retrofit.create(ToiletApiService.Server.class);
        Call<GeoJson> call = server.getToilets();
        call.enqueue(new Callback<GeoJson>() {
            @Override
            public void onResponse(Response<GeoJson> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    for (GeoJson.Feature toilet: response.body().features) {
                        Log.d(TAG, "toilet:"+ toilet);
                    }
                } else {
                    Log.d(TAG, "error:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Throwable error) {
                Log.d(TAG, error.toString());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
 
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setCurrentItem(1);
 
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        getToilets();
    }
 
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RateToiletFragment(), "Rate");
        adapter.addFragment(new MapFragment(), "Map");
        adapter.addFragment(new FindToiletFragment(), "Find");
        viewPager.setAdapter(adapter);
    }
 
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
 
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
 
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
 
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
 
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
 
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
