package com.SIDevelopers.noticiasdehonduras;

import java.util.Locale;






import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	SharedPreferences sp;
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Set up the action bar.
		final ActionBar actionBar = (ActionBar) getSupportActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		
		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					
					.setTabListener(this));
		}
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	

	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}
	
	@Override
	public void onTabReselected(Tab arg0,
			android.support.v4.app.FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onTabSelected(ActionBar.Tab tab,
			android.support.v4.app.FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0,
			android.support.v4.app.FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.acerca:
//	        	
	        	AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
	        	popupBuilder.setTitle(R.string.app_name);
	        	
	        	
	        	TextView titulo = new TextView(MainActivity.this);
	        	titulo.setText("\nversión "+getResources().getString(R.string.version)+"\n©2014 San Isidro Developers\nDerechos Reservados\n");
	        	titulo.setGravity(Gravity.CENTER);
	        	titulo.setTextSize(18);
	        	int currentapiVersion = android.os.Build.VERSION.SDK_INT;
	        	if (currentapiVersion >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH){
	        		titulo.setTextColor(Color.BLACK);
	        		popupBuilder.setIcon(R.drawable.about);
	        	} else{
	        		titulo.setTextColor(Color.WHITE);
	        		popupBuilder.setIcon(R.drawable.about_light);
	        	}
	        	
	        	
	        	popupBuilder.setView(titulo)
	        	.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id){
                       
                   }
               });
	        	popupBuilder.show();
	            return true;
	        case R.id.calificar:
	        	launchMarket();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void launchMarket() {
	    Uri uri = Uri.parse("market://details?id=" + getPackageName());
	    Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
	    try {
	        startActivity(myAppLinkToMarket);
	    } catch (ActivityNotFoundException e) {
	        Toast.makeText(this, " No se pudo encontrar la aplicación.", Toast.LENGTH_SHORT).show();
	    }
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//AdBuddiz.cacheAds(MainActivity.this); // this = current Activity
		//AdBuddiz.showAd(MainActivity.this);
		exit();
	}

	public void exit(){
		 
		
		AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
    	popupBuilder.setTitle(R.string.app_name);
    	popupBuilder.setIcon(R.drawable.ic_launcher);
    	
    	TextView titulo = new TextView(MainActivity.this);
    	titulo.setText("\n¿Desea salir de la aplicación?\n");
    	titulo.setGravity(Gravity.CENTER);
    	titulo.setTextSize(18);
    	int currentapiVersion = android.os.Build.VERSION.SDK_INT;
    	if (currentapiVersion >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    		titulo.setTextColor(Color.BLACK);
    		
    	} else{
    		titulo.setTextColor(Color.WHITE);
    		
    	}
    	popupBuilder.setView(titulo)
    	.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
              finish(); 
           }
       })
       .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
               
            }
        });
    	popupBuilder.show();
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = null;
			int tipo=0;
			Bundle args = new Bundle();
			switch (position) {
	        case 0:
	        	 tipo = 0;
	        	fragment = new FragmentGeneral();
				
                args.putInt("tipo", tipo);
                fragment.setArguments(args);
                break;
	        case 1:
	        	tipo = 1;
	        	fragment = new FragmentGeneral();
				args.putInt("tipo", tipo);
                fragment.setArguments(args);
	        	break;
	        case 2:
	        	tipo = 2;
	        	fragment = new FragmentGeneral();
				args.putInt("tipo", tipo);
                fragment.setArguments(args);
	        	break;
	        default:
	        	break;
			}
			
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class FragmentGeneral extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private String[] data;
		private int[] icon;
		MenuListAdapter mMenuAdapter;
		Bundle args;
		private int tipo;
		public static final String ARG_SECTION_NUMBER = "section_number";

		public FragmentGeneral() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_generales,
					container, false);
			args = getArguments();
			tipo = args.getInt("tipo");
			if(tipo==0){
				data = getResources().getStringArray(R.array.generales);
				icon = new int[] { R.drawable.d_laprensa, R.drawable.d_tiempo,
	                 R.drawable.d_elheraldo,R.drawable.d_latribuna,R.drawable.d_lagaceta,
	                 R.drawable.d_ellibertador,R.drawable.d_ceibeno,R.drawable.d_progreseno,
	                 R.drawable.d_hondudiario,R.drawable.d_proceso_digital,R.drawable.d_lanoticia,
	                 R.drawable.d_hrn,R.drawable.d_canal6};
			}else if(tipo==1){
				data = getResources().getStringArray(R.array.deportes);
				icon = new int[] { R.drawable.d_diez, R.drawable.d_golazo,R.drawable.d_mas,
						R.drawable.d_campeones,R.drawable.d_catrachosport};
			}else if(tipo==2){
				data = getResources().getStringArray(R.array.sociales);
				icon = new int[] { R.drawable.d_estilo,R.drawable.d_eva,R.drawable.d_hondurastips,R.drawable.d_chicos};
			}
			
			ListView generalList = (ListView) rootView
					.findViewById(R.id.lstGenerales);
			mMenuAdapter = new MenuListAdapter(getActivity(), data,
	                 icon);
			generalList.setAdapter(mMenuAdapter);
			generalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
						long id) {
					Intent i = new Intent(getActivity(), WebViewActivity.class);
					i.putExtra("pos",position);
					i.putExtra("tipo",tipo);
					startActivity(i);	
				}
			});
			return rootView;
		}
	}

	

}
