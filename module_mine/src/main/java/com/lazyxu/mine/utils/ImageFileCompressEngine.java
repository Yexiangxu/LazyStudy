package com.lazyxu.mine.utils;

import android.content.Context;
import android.net.Uri;

import com.lazyxu.base.utils.luban.CompressionPredicate;
import com.lazyxu.base.utils.luban.Luban;
import com.lazyxu.base.utils.luban.OnNewCompressListener;
import com.lazyxu.base.utils.luban.OnRenameListener;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.engine.CompressFileEngine;
import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener;
import com.luck.picture.lib.utils.DateUtils;

import java.io.File;
import java.util.ArrayList;

public class ImageFileCompressEngine implements CompressFileEngine {

    @Override
    public void onStartCompress(Context context, ArrayList<Uri> source, OnKeyValueResultCallbackListener call) {
        Luban.with(context).load(source).ignoreBy(100).setRenameListener(new OnRenameListener() {
            @Override
            public String rename(String filePath) {
                int indexOf = filePath.lastIndexOf(".");
                String postfix = indexOf != -1 ? filePath.substring(indexOf) : ".jpg";
                return DateUtils.getCreateFileName("CMP_") + postfix;
            }
        }).filter(new CompressionPredicate() {
            @Override
            public boolean apply(String path) {
                if (PictureMimeType.isUrlHasImage(path) && !PictureMimeType.isHasHttp(path)) {
                    return true;
                }
                return !PictureMimeType.isUrlHasGif(path);
            }
        }).setCompressListener(new OnNewCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String source, File compressFile) {
                if (call != null) {
                    call.onCallback(source, compressFile.getAbsolutePath());
                }
            }

            @Override
            public void onError(String source, Throwable e) {
                if (call != null) {
                    call.onCallback(source, null);
                }
            }
        }).launch();
    }
}

