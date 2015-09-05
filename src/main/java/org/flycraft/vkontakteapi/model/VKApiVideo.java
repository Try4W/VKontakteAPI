//
//  Copyright (c) 2014 VK.com
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy of
//  this software and associated documentation files (the "Software"), to deal in
//  the Software without restriction, including without limitation the rights to
//  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
//  the Software, and to permit persons to whom the Software is furnished to do so,
//  subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be included in all
//  copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
//  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
//  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
//  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
//  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

/**
 * Video.java
 * VK Dev
 *
 * Created by Babichev Vitaly on 29.09.13.
 * Copyright (c) 2013 VK. All rights reserved.
 */
package org.flycraft.vkontakteapi.model;


import org.flycraft.vkontakteapi.util.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

import static org.flycraft.vkontakteapi.model.ParseUtils.parseBoolean;
import static org.flycraft.vkontakteapi.model.VKAttachments.*;

/**
 * A video object describes an video file.
 */
@SuppressWarnings("unused")
public class VKApiVideo extends VKAttachments.VKApiAttachment implements Identifiable {

    /**
     * Video ID.
     */
    public int id;

    /**
     * Video owner ID.
     */
    public int owner_id;

    /**
     * Video album ID.
     */
    public int album_id;

    /**
     * Video title.
     */
    public String title;

    /**
     * Text describing video.
     */
    public String description;

    /**
     * Duration of the video in seconds.
     */
    public int duration;

    /**
     * String with video+vid key.
     */
    public String link;

    /**
     * Date when the video was added, as unixtime.
     */
    public long date;

    /**
     * Number of views of the video.
     */
    public int views;

    /**
     * URL of the page with a player that can be used to play a video in the browser.
     * Flash and HTML5 video players are supported; the player is always zoomed to fit
     * the window size.
     */
    public String player;

    /**
     * URL of the video cover image with the size of 130x98px.
     */
    public String photo_130;

    /**
     * URL of the video cover image with the size of 320x240px.
     */
    public String photo_320;

    /**
     * URL of the video cover image with the size of 640x480px (if available).
     */
    public String photo_640;

    /**
     * Array of all photos.
     */
    public VKPhotoSizes photo = new VKPhotoSizes();

    /**
     * An access key using for get information about hidden objects.
     */
    public String access_key;

    /**
     * Number of comments on the video.
     */
    public int comments;

    /**
     * Whether the current user can comment on the video
     */
    public boolean can_comment;

    /**
     * Whether the current user can repost this video
     */
    public boolean can_repost;

    /**
     * Information whether the current user liked the video.
     */
    public boolean user_likes;

    /**
     * Information whether the the video should be repeated.
     */
    public boolean repeat;

    /**
     * Number of likes on the video.
     */
    public int likes;

    /**
     * Privacy to view of this video.
     */
    public int privacy_view;

    /**
     * Privacy to comment of this video.
     */
    public int privacy_comment;

    /**
     * URL of video with height of 240 pixels. Returns only if you use direct auth.
     */
    public String mp4_240;

    /**
     * URL of video with height of 360 pixels. Returns only if you use direct auth.
     */
    public String mp4_360;

    /**
     * URL of video with height of 480 pixels. Returns only if you use direct auth.
     */
    public String mp4_480;

    /**
     * URL of video with height of 720 pixels. Returns only if you use direct auth.
     */
    public String mp4_720;

    /**
     * URL of the external video link.
     */
    public String external;

	public VKApiVideo(JSONObject from) throws JSONException
	{
		parse(from);
	}
    /**
     * Fills a Video instance from JSONObject.
     */
    public VKApiVideo parse(JSONObject from) {
        id = from.optInt("id");
        owner_id = from.optInt("owner_id");
        title = from.optString("title");
        description = from.optString("description");
        duration = from.optInt("duration");
        link = from.optString("link");
        date = from.optLong("date");
        views = from.optInt("views");
        comments = from.optInt("comments");
        player = from.optString("player");
        access_key = from.optString("access_key");
        album_id = from.optInt("album_id");

        JSONObject likes = from.optJSONObject("likes");
        if(likes != null) {
            this.likes = likes.optInt("count");
            user_likes = parseBoolean(likes, "user_likes");
        }
        can_comment = parseBoolean(from, "can_comment");
        can_repost = parseBoolean(from, "can_repost");
        repeat = parseBoolean(from, "repeat");

        privacy_view = VKPrivacy.parsePrivacy(from.optJSONObject("privacy_view"));
        privacy_comment = VKPrivacy.parsePrivacy(from.optJSONObject("privacy_comment"));

        JSONObject files = from.optJSONObject("files");
        if(files != null) {
            mp4_240 = files.optString("mp4_240");
            mp4_360 = files.optString("mp4_360");
            mp4_480 = files.optString("mp4_480");
            mp4_720 = files.optString("mp4_720");
            external = files.optString("external");
        }

        photo_130 = from.optString("photo_130");
        if(!TextUtils.isEmpty(photo_130)) {
            photo.add(VKApiPhotoSize.create(photo_130, 130));
        }

        photo_320 = from.optString("photo_320");
        if(!TextUtils.isEmpty(photo_320)) {
            photo.add(VKApiPhotoSize.create(photo_320, 320));
        }

        photo_640 = from.optString("photo_640");
        if(!TextUtils.isEmpty(photo_640)) {
            photo.add(VKApiPhotoSize.create(photo_640, 640));
        }
        return this;
    }

    /**
     * Creates empty Video instance.
     */
    public VKApiVideo() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public CharSequence toAttachmentString() {
        StringBuilder result = new StringBuilder(TYPE_VIDEO).append(owner_id).append('_').append(id);
        if(!TextUtils.isEmpty(access_key)) {
            result.append('_');
            result.append(access_key);
        }
        return result;
    }

    @Override
    public String getType() {
        return TYPE_VIDEO;
    }

    @Override
    public String toString() {
        return title;
    }

}
