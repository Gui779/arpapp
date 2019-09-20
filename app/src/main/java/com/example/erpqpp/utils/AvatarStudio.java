package com.example.erpqpp.utils;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.example.erpqpp.R;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


public class AvatarStudio extends DialogFragment implements View.OnClickListener {

    public static final  String  EXTRA_NEEDCROP                                  = "needcrop";
    public static final  String  EXTRA_TEXT_CAMERA                               = "text_camera";
    public static final  String  EXTRA_TEXT_GALLERY                              = "text_gallery";
    public static final  String  EXTRA_TEXT_CANCEL                               = "text_cancel";
    public static final  String  BAOMING                                         = "baoming";
    public static final  boolean DEFAULT_NEEDCROP                                = false;
    private static final int     CAMAER_REQUEST_STORAGE_WRITE_ACCESS_PERMISSION  = 110;
    private static final int     GALLERY_REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 111;
    private boolean mNeedCrop;
    private boolean mDimEnabled;
    private String  cameraText;
    private String  galleryText;
    private String  cancelText;
    private String baoming;
    private GalleryConfig galleryConfig;

    private List<String> path = new ArrayList<>();
    private IHandlerCallBack iHandlerCallBack;
    public static final String TAG = "哈哈";
    private final int PERMISSIONS_REQUEST_READ_CONTACTS = 8;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNeedCrop = getArguments().getBoolean(EXTRA_NEEDCROP, DEFAULT_NEEDCROP);
        cameraText = getArguments().getString(EXTRA_TEXT_CAMERA, "相机");
        galleryText = getArguments().getString(EXTRA_TEXT_GALLERY, "图库");
        cancelText = getArguments().getString(EXTRA_TEXT_CANCEL,"取消");
        baoming = getArguments().getString(BAOMING,"");
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(),
                mDimEnabled ? R.style.BottomDialogDim : R.style.BottomDialog1);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(lp);
        }
        initView(dialog);
        return dialog;
    }

    private void initView(Dialog dialog) {
        TextView camera =  dialog.findViewById(R.id.camera);
        camera.setText(cameraText);
        camera.setOnClickListener(this);
        TextView gallery =  dialog.findViewById(R.id.gallery);
        gallery.setText(galleryText);

        gallery.setOnClickListener(this);
        TextView cancel =  dialog.findViewById(R.id.cancel);
        cancel.setText(cancelText);
        cancel.setOnClickListener(this);
        initGallery();

        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider(baoming)   // provider(必填)
                .pathList(path)                         // 记录已选的图片
                .multiSelect(false)                      // 是否多选   默认：false
                .multiSelect(false, 9)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
                .crop(mNeedCrop)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .crop(mNeedCrop, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.camera) {

            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        "需要您提供写/读存储权限",
                        CAMAER_REQUEST_STORAGE_WRITE_ACCESS_PERMISSION);
            } else {
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).openCamera(mActivity);
                dismiss();

            }

        } else if (id == R.id.gallery) {

            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        "需要您提供写/读存储权限",
                        CAMAER_REQUEST_STORAGE_WRITE_ACCESS_PERMISSION);
            } else {
                galleryConfig.getBuilder().isOpenCamera(false).build();
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(mActivity);
                dismiss();
            }

        } else if (id == R.id.cancel) {
            dismiss();
        }
    }



    private CallBack mCallBack;

    public interface CallBack {
        void callback(String uri);
    }
    private static FragmentActivity mActivity;
    public static class Builder {
        AvatarStudio mAvatarStudio;


        public Builder(FragmentActivity activity) {
            mActivity = activity;
            mAvatarStudio = new AvatarStudio();
        }

        public Builder needCrop(boolean crop) {
            mAvatarStudio.mNeedCrop = crop;
            return this;
        }




        public Builder setText(String camera, String gallery, String cancel,String baoming) {
            mAvatarStudio.cameraText = camera;
            mAvatarStudio.galleryText = gallery;
            mAvatarStudio.cancelText = cancel;
            mAvatarStudio.baoming = baoming;
            return this;
        }

        public AvatarStudio show(CallBack callBack) {
            mAvatarStudio.mCallBack = callBack;
            Bundle bundle = new Bundle();
            bundle.putBoolean(EXTRA_NEEDCROP, mAvatarStudio.mNeedCrop);
            bundle.putString(EXTRA_TEXT_CAMERA, mAvatarStudio.cameraText);
            bundle.putString(EXTRA_TEXT_GALLERY, mAvatarStudio.galleryText);
            bundle.putString(EXTRA_TEXT_CANCEL, mAvatarStudio.cancelText);
            bundle.putString(BAOMING, mAvatarStudio.baoming);
            mAvatarStudio.setArguments(bundle);
            FragmentManager fm = mActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragment = fm.findFragmentByTag("avatarStudio");
            if (fragment != null) {
                ft.remove(fragment);
            }
            ft.addToBackStack(null);
            mAvatarStudio.show(ft, "avatarStudio");
            return mAvatarStudio;
        }

    }


    private void initGallery() {
        iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "onStart: 开启");
            }

            @Override
            public void onSuccess(List<String> photoList) {
                Log.i(TAG, "onSuccess: 返回数据");
                path.clear();
                for (String s : photoList) {
                    Log.i(TAG, s);
                    path.add(s);
                    compressWithLs(s);

                }

            }

            @Override
            public void onCancel() {
                Log.i(TAG, "onCancel: 取消");
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: 结束");
            }

            @Override
            public void onError() {
                Log.i(TAG, "onError: 出错");
            }
        };

    }




    /**
     * 鲁班压缩
     * */
    private void compressWithLs(final String photos) {
        Luban.with(mActivity)
                .load(photos)    // 传入需要压缩的图片列表
                .ignoreBy(100)   // 忽略不压缩图片的大小
                .setTargetDir(getPath()) // 设置压缩后文件存储的路径
                .setCompressListener(new OnCompressListener() {   // 设置回调
                    @Override
                    public void onStart() {
                        //TODO 压缩前开始调用 ，可以在方法内启动
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        //把压缩后的图片传给后台
                        if (mCallBack!=null){
                            mCallBack.callback(file.getAbsolutePath());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO  压缩过程中出现问题时调用
                    }
                }).launch();
    }


    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (shouldShowRequestPermissionRationale(permission)) {
            new AlertDialog.Builder(getContext())
                    .setTitle("权限拒绝")
                    .setMessage(rationale)
                    .setPositiveButton("好", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton("拒绝", null)
                    .show();
        } else {
            requestPermissions(new String[]{permission}, requestCode);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {

        if (requestCode == CAMAER_REQUEST_STORAGE_WRITE_ACCESS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).openCamera(mActivity);
                dismiss();
            }
        } else if (requestCode == GALLERY_REQUEST_STORAGE_WRITE_ACCESS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                galleryConfig.getBuilder().isOpenCamera(false).build();
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(mActivity);
                dismiss();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
