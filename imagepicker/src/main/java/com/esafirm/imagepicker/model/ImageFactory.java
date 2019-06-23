package com.esafirm.imagepicker.model;

import android.net.Uri;

import com.esafirm.imagepicker.helper.ImagePickerUtils;

import java.util.ArrayList;
import java.util.List;

public class ImageFactory {

    // This method is never called, so I could never care less about this shitty code
    public static List<Image> singleListFromPath(String path, Uri imageUri, MediaType mediaType) {
        List<Image> images = new ArrayList<>();
//        images.add(new Image(0, ImagePickerUtils.getNameFromFilePath(path), path, imageUri, mediaType, 0));
        return images;
    }
}
