//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.carlos.common;

import android.app.MultiApplication;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;

import com.carlos.common.ui.delegate.MyAppRequestListener;
import com.carlos.common.ui.delegate.MyTaskDescDelegate;
import com.carlos.common.utils.SPTools;
import com.carlos.libcommon.StringFog;
import com.kook.librelease.R.layout;
import com.lody.virtual.client.core.AppCallback;
import com.lody.virtual.client.core.SettingConfig;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.VLog;

public class App extends MultiApplication {
    private static App gApp;
    AppComponentDelegate mAppComponentDelegate;
    public final SettingConfig mConfig = new SettingConfig() {
        public SettingConfig.FakeWifiStatus fakeWifiStatus = new SettingConfig.FakeWifiStatus();

        public String getMainPackageName() {
            return StringFog.decrypt(com.kook.librelease.StringFog.decrypt("Li4ADXojLDdhNFE1IykYD2UjOAZqATg7KQhSVg=="));
        }

        public String getExtPackageName() {
            return StringFog.decrypt(com.kook.librelease.StringFog.decrypt("Li4ADXojLDdhNFE1IykYD2UjOAZqATg7KQQcJ2cVFlo="));
        }

        public boolean isEnableIORedirect() {
            return true;
        }

        public Intent onHandleLauncherIntent(Intent originIntent) {
            Intent intent = new Intent();
            ComponentName component = new ComponentName(this.getMainPackageName(), StringFog.decrypt(com.kook.librelease.StringFog.decrypt("LAgfCGsgNANgNAY/Iy4pDmUVQCZqEQYeLl8cGH0KNC5nHlkcLyxbJW8VAiJlHiwg")));
            intent.setComponent(component);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return intent;
        }

        public boolean isUseRealDataDir(String packageName) {
            return false;
        }

        public boolean isOutsidePackage(String packageName) {
            return false;
        }

        public boolean isAllowCreateShortcut() {
            return false;
        }

        public boolean isHostIntent(Intent intent) {
            return intent.getData() != null && StringFog.decrypt(com.kook.librelease.StringFog.decrypt("Iwg+KmUzNAY=")).equals(intent.getData().getScheme());
        }

        public boolean isUseRealApkPath(String packageName) {
            return packageName.equals(StringFog.decrypt(com.kook.librelease.StringFog.decrypt("Li4ADXogLCtiAQY1KjkYP28gTVo=")));
        }

        public boolean isEnableVirtualSdcardAndroidData() {
            return BuildCompat.isQ();
        }

        public boolean resumeInstrumentationInMakeApplication(String packageName) {
            return packageName.equals(StringFog.decrypt(com.kook.librelease.StringFog.decrypt("Li4ADXogLANONCA2KBguDWwjASZvASAqPC4+MWIKQSB8NF0iIz4AVg=="))) ? true : super.resumeInstrumentationInMakeApplication(packageName);
        }

        public boolean isUnProtectAction(String action) {
            return action.startsWith(StringFog.decrypt(com.kook.librelease.StringFog.decrypt("ITw+H2MgFiV9Dgo5LwgqLn0zGgNvHAZF"))) ? true : super.isUnProtectAction(action);
        }

        public SettingConfig.FakeWifiStatus getFakeWifiStatus(String packageName, int userId) {
            String SSID_KEY = StringFog.decrypt(com.kook.librelease.StringFog.decrypt("Ki02CWgIGiFiAQZF")) + packageName + StringFog.decrypt(com.kook.librelease.StringFog.decrypt("Jy5SVg==")) + userId;
            String MAC_KEY = StringFog.decrypt(com.kook.librelease.StringFog.decrypt("Iwg+OWYzQStnAVRF")) + packageName + StringFog.decrypt(com.kook.librelease.StringFog.decrypt("Jy5SVg==")) + userId;
            String ssid = SPTools.getString(VirtualCore.get().getContext(), SSID_KEY);
            String mac = SPTools.getString(VirtualCore.get().getContext(), MAC_KEY);
            if (!TextUtils.isEmpty(ssid) && !TextUtils.isEmpty(mac)) {
                this.fakeWifiStatus.setSSID(ssid);
                this.fakeWifiStatus.setDefaultMac(mac);
                return this.fakeWifiStatus;
            } else {
                return null;
            }
        }
    };
    private BroadcastReceiver mGmsInitializeReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            View view = LayoutInflater.from(context).inflate(layout.content_gms_init_layout, (ViewGroup)null);
            Toast toast = new Toast(context);
            toast.setGravity(81, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);

            try {
                toast.show();
            } catch (Throwable var6) {
                Throwable e = var6;
                e.printStackTrace();
            }

        }
    };

    public App() {
    }

    public static App getApp() {
        return gApp;
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        gApp = this;
        VLog.OPEN_LOG = true;

        try {
            VLog.e("Harrisonsens","attachBaseContext------");
            VirtualCore.get().startup(this, base, this.mConfig);
        } catch (Throwable var3) {
            Throwable e = var3;
            e.printStackTrace();
        }

    }

    public void en(byte en) {
        boolean abc = en == 1;
    }

    public AppCallback getAppCallback() {
        return this.mAppComponentDelegate;
    }

    @RequiresApi(
        api = 21
    )
    public void onCreate() {
        super.onCreate();
        this.lazyInjectInit();
        if (this.mAppComponentDelegate == null) {
            this.mAppComponentDelegate = new AppComponentDelegate(this);
        }

        final VirtualCore virtualCore = VirtualCore.get();
        virtualCore.setAppCallback(this.mAppComponentDelegate);
        virtualCore.initialize(new VirtualCore.VirtualInitializer() {
            public void onMainProcess() {
                AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
                App.this.mAppComponentDelegate.setMainProcess(App.gApp, true);
                App.this.registerReceiver(App.this.mGmsInitializeReceiver, new IntentFilter(StringFog.decrypt(com.kook.librelease.StringFog.decrypt("LggcPG8jGi9iV1kzKj42PW8aASZoATA/IxgAKk42JBJ9JVlNIRY2XWILJEx9HFEKLBhSVg=="))));
            }

            @RequiresApi(
                api = 17
            )
            public void onVirtualProcess() {
                App.this.mAppComponentDelegate.setMainProcess((Context)null, false);
                virtualCore.setTaskDescriptionDelegate(new MyTaskDescDelegate());
                virtualCore.setTaskDescriptionDelegate(new MyTaskDescDelegate());
                virtualCore.setAppRequestListener(new MyAppRequestListener(App.this));
            }

            public void onServerProcess() {
            }
        });
    }

    private void lazyInjectInit() {
    }
}
