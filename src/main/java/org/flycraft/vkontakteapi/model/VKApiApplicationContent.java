/**
 * AppInfo.java
 * vk-android-sdk
 * <p/>
 * Created by Babichev Vitaly on 19.01.14.
 * Copyright (c) 2014 VK. All rights reserved.
 */
package org.flycraft.vkontakteapi.model;

import org.flycraft.vkontakteapi.util.TextUtils;
import org.json.JSONObject;

import static org.flycraft.vkontakteapi.model.VKAttachments.*;

/**
 * Describes information about application in the post.
 */
@SuppressWarnings("unused")
public class VKApiApplicationContent extends VKApiAttachment {

    /**
     * ID of the application that posted on the wall;
     */
    public int id;

    /**
     * Application name
     */
    public String name;

    /**
     * Image URL for preview with maximum width in 130px
     */
    public String photo_130;

    /**
     * Image URL for preview with maximum width in 130px
     */
    public String photo_604;

    /**
     * Image URL for preview;
     */
    public VKPhotoSizes photo = new VKPhotoSizes();

    public VKApiApplicationContent(JSONObject source) {
        parse(source);
    }

    /**
     * Fills an ApplicationContent instance from JSONObject.
     */
    public VKApiApplicationContent parse(JSONObject source) {
        id = source.optInt("id");
        name = source.optString("name");
        photo_130 = source.optString("photo_130");
        if (!TextUtils.isEmpty(photo_130)) {
            photo.add(VKApiPhotoSize.create(photo_130, 130));
        }
        photo_604 = source.optString("photo_604");
        if (!TextUtils.isEmpty(photo_604)) {
            photo.add(VKApiPhotoSize.create(photo_604, 604));
        }
        return this;
    }

    /**
     * Creates empty ApplicationContent instance.
     */
    public VKApiApplicationContent() {

    }

    @Override
    public CharSequence toAttachmentString() {
        throw new UnsupportedOperationException("Attaching app info is not supported by VK.com API");
    }

    @Override
    public String getType() {
        return TYPE_APP;
    }

    @Override
    public int getId() {
        return id;
    }
}