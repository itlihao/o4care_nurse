package com.o4care.nurse.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class FileUtil {
    public final String APK_SAVE_PATH = Environment.getExternalStorageDirectory() + "/Android/data/laixi/installpackage/";

    File pathFile = new File( APK_SAVE_PATH );
    static FileUtil instance;
    Context mContext;

    public FileUtil(Context mContext) {
        this.mContext = mContext;
    }

    public static FileUtil getInstance(Context mContext) {
        if (instance == null) {
            instance = new FileUtil(mContext);
        }
        return instance;
    }

    List<String> fileList;

    public List<String> scanFile() {
        return scanFile(pathFile);
    }

    public List<String> scanFile(File file) {
        fileList = new ArrayList<String>();
        if (file.exists()) {
            File[] filepath = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    if (file.isFile()) {
                        fileList.add(file.getName());
                    }
                    return true;
                }
            });

        } else {
        }
        return fileList;
    }

    public interface UpdateUIListener {
        void updateUI(int percent);
    }

    UpdateUIListener updateUIListener;

    public void setUpdateUIListener(UpdateUIListener updateUIListener) {
        this.updateUIListener = updateUIListener;
    }

    public boolean writeResponseBodyToDisk(ResponseBody body, String fileName) {
        return writeResponseBodyToDisk(body, pathFile, fileName);
    }

    public boolean writeApkResponseBodyToDisk(ResponseBody body, String fileName) {
        return writeResponseBodyToDisk(body, new File(APK_SAVE_PATH), fileName);
    }

    public boolean writeResponseBodyToDisk(ResponseBody body, File path, String fileName) {
        try {
            if (!path.exists()) {
                path.mkdirs();
            }
            File futureStudioIconFile = new File(path, fileName);
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("wyg", "file download: " + fileSizeDownloaded + " of " + fileSize + "%" + (int) (fileSizeDownloaded * 100 / fileSize) + (updateUIListener != null));
                    // 进度

                    if (updateUIListener != null) {
                        updateUIListener.updateUI((int) (fileSizeDownloaded * 100 / fileSize));
                    }
                }

                outputStream.flush();
                outputStream.close();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除文件夹以及目录下的文件
     *
     * @param filePath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String filePath) {
        boolean flag = false;
        // 如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        File[] files = dirFile.listFiles();
        if (files == null || files.length == 0) {
            return false;
        }
        // 遍历删除文件夹下的所有文件(包括子目录)
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                // 删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } else {
                // 删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        // 删除当前空目录
        return dirFile.delete();
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param filePath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public boolean deleteFolder(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                // 为文件时调用删除文件方法
                return deleteFile(filePath);
            } else {
                // 为目录时调用删除目录方法
                return deleteDirectory(filePath);
            }
        }
    }

    public boolean deleteApkFolder() {
        return deleteFolder(APK_SAVE_PATH);
    }

    public static File getSaveFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        return file;
    }
}
