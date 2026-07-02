
package com.rzcl.screen.hook;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Looper;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class EntryHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {

        XposedHelpers.findAndHookMethod(
                Activity.class,
                "onResume",
                new XC_MethodHook() {

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {

                        Activity activity =
                                (Activity) param.thisObject;

                        new Handler(
                                Looper.getMainLooper()
                        ).postDelayed(() -> {

                            try {

                                activity.setRequestedOrientation(
                                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                                );

                            } catch (Throwable ignored) {
                            }

                        }, 160);
                    }
                }
        );
    }
}
