package com.example.fujindong.scottlibrary;

import java.util.List;

/**
 * Created by fujindong on 2017/1/1.
 */

public interface PermissionListener {
    void onGranted();

    /**
     *
     * @param deniedPermissions 未授权的权限
     */
    void onDenied(List<String> deniedPermissions);
}
