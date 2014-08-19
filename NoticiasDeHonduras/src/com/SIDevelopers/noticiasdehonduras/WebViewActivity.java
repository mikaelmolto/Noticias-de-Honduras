package com.SIDevelopers.noticiasdehonduras;

import java.lang.reflect.InvocationTargetException;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends ActionBarActivity {

	private WebView webPage;
	private int pos;
	private int tipo;
	private String title;
	public AdView adView;
	private String[] mGenerales;
	private String[] mDeportes;
	private String[] mSociales;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_webview);
		
		final ActionBar actionBar = (ActionBar) getSupportActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));
		
		mGenerales=getResources().getStringArray(R.array.generales);
		mDeportes=getResources().getStringArray(R.array.deportes);
		mSociales=getResources().getStringArray(R.array.sociales);
		
		adView = (AdView) findViewById(R.id.adView);    
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		adView.setAdListener(new AdListener(){
			@Override
			public void onAdFailedToLoad (int errorCode){
				adView.setVisibility(View.GONE);
			}
		});
		
		final Activity activity = this;
		//getActionBar().setIcon(R.drawable.plan);
		webPage=(WebView) findViewById(R.id.webPage);
		pos=getIntent().getExtras().getInt("pos");
		tipo=getIntent().getExtras().getInt("tipo");
		cargar_nombres(pos,tipo);
		title=getSupportActionBar().getTitle().toString();
		webPage.getSettings().setBuiltInZoomControls(true);
		webPage.getSettings().setSupportZoom(true); 
		webPage.getSettings().setUseWideViewPort(true);
		webPage.getSettings().setLoadWithOverviewMode(true);
		webPage.getSettings().setJavaScriptEnabled(true);
		webPage.setWebChromeClient(new WebChromeClient() {
			   public void onProgressChanged(WebView view, int progress) {
			     // Activities and WebViews measure progress with different scales.
			     // The progress meter will automatically disappear when we reach 100%
				   
				   getSupportActionBar().setTitle("Cargando...");
				   activity.setProgress(progress * 100);
				   if(progress == 100){
				    	 getSupportActionBar().setTitle(title);
				    	 //Toast.makeText(CarrerasPlanActivity.this, title, Toast.LENGTH_SHORT).show();
					   }
			     
			   }
			 });
		webPage.setWebViewClient(new WebViewClient() {
		    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			     Toast.makeText(WebViewActivity.this,description, Toast.LENGTH_SHORT).show();
			     //webPage.loadUrl("file:///android_asset/error.html");
			   }
		    public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
		        viewx.loadUrl(urlx);
		        return false;
		    	}
			});
		cargar_web(pos,tipo);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	private void cargar_web(int pos2, int tipo2) {
		// TODO Auto-generated method stub
		if(tipo2==0){
			switch(pos2){
			case 0:
				webPage.loadUrl(getResources().getString(R.string.g_laprensa));
				break;
			case 1:
				webPage.loadUrl(getResources().getString(R.string.g_tiempo));
				break;
			case 2:
				webPage.loadUrl(getResources().getString(R.string.g_heraldo));
				break;
			case 3:
				webPage.loadUrl(getResources().getString(R.string.g_tribuna));
				break;
			case 4:
				webPage.loadUrl(getResources().getString(R.string.g_gaceta));
				break;
			case 5:
				webPage.loadUrl(getResources().getString(R.string.g_libertador));
				break;
			case 6:
				webPage.loadUrl(getResources().getString(R.string.g_ceibeno));
				break;
			case 7:
				webPage.loadUrl(getResources().getString(R.string.g_progreseno));
				break;
			case 8:
				webPage.loadUrl(getResources().getString(R.string.g_hondudiario));
				break;
			case 9:
				webPage.loadUrl(getResources().getString(R.string.g_procesodigital));
				break;
			case 10:
				webPage.loadUrl(getResources().getString(R.string.g_lanoticia));
				break;
			case 11:
				webPage.loadUrl(getResources().getString(R.string.g_hrn));
				break;
			case 12:
				webPage.loadUrl(getResources().getString(R.string.g_canal6));
				break;
			default:
				break;
			}
		}else if(tipo2==1){
			switch(pos2){
			case 0:
				webPage.loadUrl(getResources().getString(R.string.d_diez));
				break;
			case 1:
				webPage.loadUrl(getResources().getString(R.string.d_golazo));
				break;
			case 2:
				webPage.loadUrl(getResources().getString(R.string.d_mas));
				break;
			case 3:
				webPage.loadUrl(getResources().getString(R.string.d_campeones));
				break;
			case 4:
				webPage.loadUrl(getResources().getString(R.string.d_catrachosports));
				break;
			default:
				break;
			}
		}else if(tipo2==2){
			switch(pos2){
			case 0:
				webPage.loadUrl(getResources().getString(R.string.s_estilo));
				break;
			case 1:
				webPage.loadUrl(getResources().getString(R.string.s_eva));
				break;
			case 2:
				webPage.loadUrl(getResources().getString(R.string.s_ht));
				break;
			case 3:
				webPage.loadUrl(getResources().getString(R.string.s_chicos));
				break;
			default:
				break;
			}
		}
	}

	public void cargar_nombres(int position, int tipo){
		final ActionBar actionBar = (ActionBar) getSupportActionBar();
		String nombre="";
		switch(tipo){
		case 0:
			nombre=mGenerales[position];
			actionBar.setTitle(nombre);
			break;
		case 1:
			nombre=mDeportes[position];
			actionBar.setTitle(nombre);
			break;
		case 2:
			nombre=mSociales[position];
			actionBar.setTitle(nombre);
			break;
		default:
			nombre=" - ";
			break;
		}
		
	}
	
	@Override
	public void onBackPressed() {
		WebView webPage=(WebView) findViewById(R.id.webPage);
		if(webPage.canGoBack() == true){
			webPage.goBack();
			Log.i("goBack", "true");
		} else {
			Log.i("goBack", "false");
			super.onBackPressed();
		}
	}

	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_web, menu);
        return super.onCreateOptionsMenu(menu);
    }
	
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
         
        // Handle action buttons
        switch(item.getItemId()) {
        case android.R.id.home:
        	Intent upIntent = new Intent(this, MainActivity.class);
        	NavUtils.navigateUpTo(this, upIntent);
	        return(true);
        case R.id.refrescar:
        	String Url;
        	Url=webPage.getUrl();
        	webPage.loadUrl(Url);
            return true;
        case R.id.compartir:
        	Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, webPage.getUrl());
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Noticias de Honduras by San Isidro Developers");
            startActivity(Intent.createChooser(intent, "Compartir"));
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		try {
	        Class.forName("android.webkit.WebView")
	                .getMethod("onPause", (Class[]) null)
	                            .invoke(webPage, (Object[]) null);

	    } catch(ClassNotFoundException cnfe) {
	       
	    } catch(NoSuchMethodException nsme) {
	        
	    } catch(InvocationTargetException ite) {
	        
	    } catch (IllegalAccessException iae) {
	        
	    }
	}

			
			
}
