package in.co.praveenkumar.mdroid.activity;

import in.co.praveenkumar.mdroid.fragment.CourseFragment;
import in.co.praveenkumar.mdroid.helper.AppNavigationDrawer;
import in.co.praveenkumar.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.viewpagerindicator.TabPageIndicator;

public class CourseActivity extends AppNavigationDrawer implements
		OnPageChangeListener {

	private ViewPager viewPager;
	private static final String[] TABS = { "All Courses", "Favourite" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);
		setUpDrawer();
		getSupportActionBar().setTitle("Moodle Home");
		getSupportActionBar().setIcon(R.drawable.ic_actionbar_icon);

		FragmentPagerAdapter mAdapter = new CourseTabsAdapter(
				getSupportFragmentManager());

		viewPager = (ViewPager) findViewById(R.id.course_pager);
		viewPager.setOffscreenPageLimit(3);
		viewPager.setAdapter(mAdapter);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(viewPager);
		indicator.setOnPageChangeListener(this);
	}

	class CourseTabsAdapter extends FragmentPagerAdapter {
		public CourseTabsAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			/*
			 * We use bundle to pass course listing type because, by using other
			 * methods we will lose the listing type information in the fragment
			 * on onResume (this calls empty constructor). For the same reason
			 * interface may not work. Bundles are passed again on onResume
			 */
			switch (position) {
			case 0:
				CourseFragment userCourses = new CourseFragment();

				// Set the listing type to only user courses in bundle.
				Bundle bundle = new Bundle();
				bundle.putInt("coursesType", CourseFragment.TYPE_USER_COURSES);
				userCourses.setArguments(bundle);

				return userCourses;
			case 1:
				CourseFragment favCourses = new CourseFragment();

				// Set the listing type to only user courses in bundle.
				Bundle bundle1 = new Bundle();
				bundle1.putInt("coursesType", CourseFragment.TYPE_FAV_COURSES);
				favCourses.setArguments(bundle1);

				return favCourses;
			}
			return null;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TABS[position];
		}

		@Override
		public int getCount() {
			return TABS.length;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int position) {
	}

}
