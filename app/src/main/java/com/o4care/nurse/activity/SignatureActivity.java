package com.o4care.nurse.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;

import com.o4care.nurse.R;
import com.o4care.nurse.utils.Constants;
import com.o4care.nurse.widget.signature.SignatureView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class SignatureActivity extends BaseActivity {
    @BindView(R.id.signature_pad)
    SignatureView mSignaturePad;

    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_signature;
    }

    @OnClick({R.id.btn_commit, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:{
                //保存签名图片
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                saveSignature(signatureBitmap);
                Intent sigIntent = new Intent();
                sigIntent.putExtra("filePath", filePath);
                setResult(Constants.RC_TASK_SIGNATURE, sigIntent);
                finish();
                break;
            }
            case R.id.btn_cancel:
                break;
            default:
                break;
        }
    }

    //保存签名
    public boolean saveSignature(Bitmap signature) {
        boolean result = false;
        try {
            //保存的文件名
            String name = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + "temp/image/sign_" + name + ".jpg";
            File photo = new File(filePath);
            saveBitmapToJPG(signature, photo);

            /*
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(photo);
            mediaScanIntent.setData(contentUri);
            ActivityCareSubmit.this.sendBroadcast(mediaScanIntent);
            */
            result = true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }
}
