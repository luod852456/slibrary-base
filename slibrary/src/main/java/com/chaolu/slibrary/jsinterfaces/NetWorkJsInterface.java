package com.chaolu.slibrary.jsinterfaces;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import android.webkit.JavascriptInterface;

import com.chaolu.slibrary.event.ERefreshReload;
import com.chaolu.slibrary.manager.SEventManager;

public class NetWorkJsInterface extends BaseJsInterface {

    public NetWorkJsInterface(Activity activity) {
        super(activity);
    }

    @JavascriptInterface
    public void check_network() {
        openNetwork(getActivity());
    }

    @JavascriptInterface
    public void refresh_reload() {
        ERefreshReload event = new ERefreshReload();
        SEventManager.post(event);
    }

    /**
     * 打开WIFI设置界面
     */
    public void openNetwork(final Activity context) {
//        SDDialogConfirm dialogConfirm = new SDDialogConfirm(context);
//        dialogConfirm.setTextTitle("网络设置提示");
//        dialogConfirm.setTextContent("网络连接不可用,是否进行设置?");
//        dialogConfirm.setTextGravity(Gravity.CENTER);
//        dialogConfirm.setmListener(new SDDialogCustom.SDDialogCustomListener() {
//            @Override
//            public void onClickCancel(View v, SDDialogCustom dialog) {
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onClickConfirm(View v, SDDialogCustom dialog) {
//                context.startActivity(createNetWorkIntent());
//            }
//
//            @Override
//            public void onDismiss(SDDialogCustom dialog) {
//                dialog.dismiss();
//            }
//        });
//        dialogConfirm.show();
        context.startActivity(createNetWorkIntent());
    }

    /**
     * 网络配置
     */
    public static Intent createNetWorkIntent() {
        Intent intent = null;
        // 判断手机系统的版本 即API大于10 就是3.0或以上版本
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        return intent;
    }
}