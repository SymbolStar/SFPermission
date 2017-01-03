package com.example.fujindong.scottlibrary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file =  getExternalCacheDir(); // Android/data/com.example.fujindong.scottlibrary  的cache 文件夹
//        不好的就是一些杀毒软件垃圾清理会把它干掉。
        file.getPath();
        File file1 = getExternalFilesDir("");//传空代表files 文件根目录。 传具体的串就会新建一个文件夹
        file1.getPath();
    }

    public void permissionOnClick(View view){

//多权限申请
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CALL_PHONE);
        }
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            ActivityCompat.requestPermissions(this,permissionList.toArray(new String[permissionList.size()]),1);
        } else{
            doSomething();
        }

//单个权限申请
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
//        }else{
//            makeCall();
//        }

    }

    private void makeCall(){
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel://10010"));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    private void doSomething(){
        Toast.makeText(this, "所有权限都同意了", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PermissionActivity.class);
        startActivity(intent);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0){
                    for (int grantResult : grantResults){
                        if (grantResult != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "某个权限被拒绝了", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    doSomething();
                }



//              单个权限申请回调
//                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
//                    makeCall();
//                }else {
//                    Toast.makeText(this, "权限被拒绝了", Toast.LENGTH_SHORT).show();
//                }
                break;
            default:
                break;
        }
    }
}
