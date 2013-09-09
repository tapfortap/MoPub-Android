package com.tapfortap.mopub;

import android.content.Context;
import android.util.Log;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.MoPubErrorCode;
import com.tapfortap.Interstitial;
import com.tapfortap.Interstitial.InterstitialListener;
import com.tapfortap.TapForTap;

import java.util.Map;

public class MoPubCustomInterstitial extends CustomEventInterstitial implements InterstitialListener {
    private Interstitial interstitial;
    private CustomEventInterstitialListener customEventInterstitialListener;

    @Override
    public void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        this.customEventInterstitialListener= customEventInterstitialListener;
        if (interstitial == null) {
//            String apiKey = serverExtras.get("tft_api_key");
            String apiKey = "YOUR_API_KEY";
//            if (apiKey != null) {
            TapForTap.initialize(context, apiKey);
//            }
            interstitial = Interstitial.create(context, this);
        }
    }

    @Override
    public void showInterstitial() {
        interstitial.show();
    }

    @Override
    public void onInvalidate() {
        interstitial = null;
    }

    @Override
    public void interstitialOnReceive(Interstitial interstitial) {
        customEventInterstitialListener.onInterstitialLoaded();
    }

    @Override
    public void interstitialOnFail(Interstitial interstitial, String message, Throwable throwable) {
        Log.e("TapForTap", "Failed load : " + message);
        customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.UNSPECIFIED);
    }

    @Override
    public void interstitialOnTap(Interstitial interstitial) {
        customEventInterstitialListener.onInterstitialClicked();
    }

    @Override
    public void interstitialOnShow(Interstitial interstitial) {
        customEventInterstitialListener.onInterstitialShown();
    }

    @Override
    public void interstitialOnDismiss(Interstitial interstitial) {
        customEventInterstitialListener.onInterstitialDismissed();
    }
}
