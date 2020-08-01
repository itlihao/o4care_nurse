package com.o4care.nurse.utils;

import android.os.Environment;

public class Constants {
    
	public static final int RC_TASK_SIGNATURE = 23;
    public static final int RC_TASK_PHOTO = 25;
    public static final int RC_PICTURE_CROP = 56;
    public static final int RC_OPEN_CAMERA  = 76;

    public static String[] week = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日",};
    public static String[] careItem = new String[]{"医疗护理", "生活照料", "康复训练", "其他服务"};
    public static class File {

        public static final String APK_SAVE_PATH = Environment.getExternalStorageDirectory() + "/Android/data/laixi/installpackage/";
       
        // public static String RECORDER_SAVE_URI =
        // Environment.getExternalStorageDirectory() + "/kgmusic/download/";//
        // 测试
    }
}
