package com.tapfortap.mopub;

import android.content.Context;
import com.mopub.mobileads.CustomEventBanner;
import com.mopub.mobileads.MoPubErrorCode;
import com.tapfortap.Banner;
import com.tapfortap.TapForTap;

import java.util.Map;

public class MoPubCustomBanner extends CustomEventBanner implements Banner.BannerListener {
    private Banner banner;
    private CustomEventBannerListener customEventBannerListener;

    @Override
    public void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        this.customEventBannerListener = customEventBannerListener;
        if (banner == null) {
//            String apiKey = serverExtras.get("tft_api_key");
            String apiKey = "YOUR_API_KEY";
//            if (apiKey != null) {
                TapForTap.initialize(context, apiKey);
//            }
            banner = Banner.create(context, this);
            banner.enableForceLoad();
            banner.disableAutoRollover();
        }
        banner.startShowingAds();
    }

    @Override
    public void onInvalidate() {
        if (banner != null) {
            banner.setListener(null);
            banner = null;
        }
    }

    @Override
    public void bannerOnReceive(Banner banner) {
        if (customEventBannerListener != null) {
            customEventBannerListener.onBannerLoaded(banner);
        }
    }

    @Override
    public void bannerOnFail(Banner banner, String s, Throwable throwable) {
        if (customEventBannerListener != null) {
            customEventBannerListener.onBannerFailed(MoPubErrorCode.UNSPECIFIED);
        }
    }

    @Override
    public void bannerOnTap(Banner banner) {
        if (customEventBannerListener != null) {
            customEventBannerListener.onBannerClicked();
        }
    }
}
