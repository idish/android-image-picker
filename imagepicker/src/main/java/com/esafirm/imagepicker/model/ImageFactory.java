package com.esafirm.imagepicker.model;

import android.net.Uri;

import com.esafirm.imagepicker.helper.ImagePickerUtils;

import java.util.ArrayList;
import java.util.List;

public class ImageFactory {

    public static List<Image> singleListFromPath(String path, Uri imageUri) {
        List<Image> images = new ArrayList<>();
        images.add(new Image(0, ImagePickerUtils.getNameFromFilePath(path), path, imageUri));
        return images;
    }
}
