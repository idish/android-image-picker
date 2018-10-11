package com.esafirm.imagepicker.model;

public enum MediaType {
    IMAGE(0), VIDEO(1);

    public int value;

    MediaType(int value) {
        this.value = value;
    }

    public static MediaType fromInteger(int x) {
        switch (x) {
            case 0:
                return IMAGE;
            case 1:
                return VIDEO;
            default:
                return null;
        }
    }
}
