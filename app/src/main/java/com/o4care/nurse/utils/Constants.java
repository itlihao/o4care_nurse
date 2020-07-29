package com.o4care.nurse.utils;

import android.os.Environment;

public class Constants {
    
	public static final int RC_TASK_SIGNATURE = 23;
    public static final int RC_TASK_PHOTO = 25;
    public static final int RC_PICTURE_CROP = 56;
    public static final int RC_OPEN_CAMERA  = 76;

    public static class File {

        public static final String APK_SAVE_PATH = Environment.getExternalStorageDirectory() + "/Android/data/laixi/installpackage/";
       
        // public static String RECORDER_SAVE_URI =
        // Environment.getExternalStorageDirectory() + "/kgmusic/download/";//
        // 测试
    }
}
