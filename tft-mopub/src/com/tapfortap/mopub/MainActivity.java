package com.tapfortap.mopub;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;

public class MainActivity extends Activity implements MoPubView.BannerAdListener, MoPubInterstitial.InterstitialAdListener {
    private MoPubView moPubView;
    private MoPubInterstitial interstitial;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        moPubView = (MoPubView)findViewById(R.id.mopub_view);
        moPubView.setAdUnitId("0931c5722f6b4485ba7b3c2506bf06ba");
        moPubView.loadAd();
        moPubView.setBannerAdListener(this);

        interstitial = new MoPubInterstitial(this, "8ee286cbf23e4cb4be0b30c2bcdb3dc8");
        interstitial.setInterstitialAdListener(this);
        interstitial.load();

        Button showInterstitial = (Button)findViewById(R.id.show_interstitial_button);
        showInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interstitial.load();
            }
        });
    }

    protected void onDestroy() {
        moPubView.destroy();
        super.onDestroy();
    }

    @Override
    public void onBannerLoaded(MoPubView banner) {
    }

    @Override
    public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
    }

    @Override
    public void onBannerClicked(MoPubView banner) {
    }

    @Override
    public void onBannerExpanded(MoPubView banner) {
    }

    @Override
    public void onBannerCollapsed(MoPubView banner) {
    }

    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        interstitial.show();
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {
    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {
    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
    }
}
