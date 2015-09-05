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
 * VKAttachments.java
 * vk-android-sdk
 *
 * Created by Babichev Vitaly on 01.02.14.
 * Copyright (c) 2014 VK. All rights reserved.
 */
package org.flycraft.vkontakteapi.model;



import org.flycraft.vkontakteapi.util.VKStringJoiner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A list of attachments in {@link VKApiComment}, {@link VKApiPost}, {@link VKApiMessage}
 */
public class VKAttachments extends VKList<VKAttachments.VKApiAttachment> {

    /**
     * Attachment is a photo.
     * @see org.flycraft.vkontakteapi.model.VKApiPhoto
     */
    public static final String TYPE_PHOTO = "photo";

    /**
     * Attachment is a video.
     * @see org.flycraft.vkontakteapi.model.VKApiVideo
     */
    public static final String TYPE_VIDEO = "video";

    /**
     * Attachment is an audio.
     * @see org.flycraft.vkontakteapi.model.VKApiAudio
     */
    public static final String TYPE_AUDIO = "audio";

    /**
     * Attachment is a document.
     * @see org.flycraft.vkontakteapi.model.VKApiDocument
     */
    public static final String TYPE_DOC = "doc";

    /**
     * Attachment is a wall post.
     * @see org.flycraft.vkontakteapi.model.VKApiPost
     */
    public static final String TYPE_POST = "wall";

    /**
     * Attachment is a posted photo.
     * @see org.flycraft.vkontakteapi.model.VKApiPostedPhoto
     */
    public static final String TYPE_POSTED_PHOTO = "posted_photo";

    /**
     * Attachment is a link
     * @see org.flycraft.vkontakteapi.model.VKApiLink
     */
    public static final String TYPE_LINK = "link";

    /**
     * Attachment is a note
     * @see org.flycraft.vkontakteapi.model.VKApiNote
     */
    public static final String TYPE_NOTE = "note";

    /**
     * Attachment is an application content
     * @see org.flycraft.vkontakteapi.model.VKApiApplicationContent
     */
    public static final String TYPE_APP = "app";

    /**
     * Attachment is a poll
     * @see org.flycraft.vkontakteapi.model.VKApiPoll
     */
    public static final String TYPE_POLL = "poll";

    /**
     * Attachment is a WikiPage
     * @see org.flycraft.vkontakteapi.model.VKApiWikiPage
     */
    public static final String TYPE_WIKI_PAGE = "page";

    /**
     * Attachment is a PhotoAlbum
     * @see org.flycraft.vkontakteapi.model.VKApiPhotoAlbum
     */
    public static final String TYPE_ALBUM = "album";


    public VKAttachments() {
        super();
    }

    public VKAttachments(VKApiAttachment... data) {
        super(Arrays.asList(data));
    }

    public VKAttachments(List<? extends VKApiAttachment> data) {
        super(data);
    }

    public VKAttachments(JSONObject from) {
        super();
        fill(from);
    }

    public VKAttachments(JSONArray from) {
        super();
        fill(from);
    }

    public void fill(JSONObject from) {
        super.fill(from, parser);
    }

    public void fill(JSONArray from) {
        super.fill(from, parser);
    }

    public String toAttachmentsString() {
        ArrayList<CharSequence> attachments = new ArrayList<CharSequence>();
        for (VKApiAttachment attach : this) {
            attachments.add(attach.toAttachmentString());
        }
        return VKStringJoiner.join(attachments, ",");
    }
    /**
     * Parser that's used for parsing photo sizes.
     */
    private final Parser<VKApiAttachment> parser = new Parser<VKApiAttachment>() {
        @Override
        public VKApiAttachment parseObject(JSONObject attachment) throws Exception {
            String type = attachment.optString("type");
            if(TYPE_PHOTO.equals(type)) {
                return new VKApiPhoto().parse(attachment.getJSONObject(TYPE_PHOTO));
            } else if(TYPE_VIDEO.equals(type)) {
                return new VKApiVideo().parse(attachment.getJSONObject(TYPE_VIDEO));
            } else if(TYPE_AUDIO.equals(type)) {
                return new VKApiAudio().parse(attachment.getJSONObject(TYPE_AUDIO));
            } else if(TYPE_DOC.equals(type)) {
                return new VKApiDocument().parse(attachment.getJSONObject(TYPE_DOC));
            } else if(TYPE_POST.equals(type)) {
                return new VKApiPost().parse(attachment.getJSONObject(TYPE_POST));
            } else if(TYPE_POSTED_PHOTO.equals(type)) {
                return new VKApiPostedPhoto().parse(attachment.getJSONObject(TYPE_POSTED_PHOTO));
            } else if(TYPE_LINK.equals(type)) {
                return new VKApiLink().parse(attachment.getJSONObject(TYPE_LINK));
            } else if(TYPE_NOTE.equals(type)) {
                return new VKApiNote().parse(attachment.getJSONObject(TYPE_NOTE));
            } else if(TYPE_APP.equals(type)) {
                return new VKApiApplicationContent().parse(attachment.getJSONObject(TYPE_APP));
            } else if(TYPE_POLL.equals(type)) {
                return new VKApiPoll().parse(attachment.getJSONObject(TYPE_POLL));
            } else if(TYPE_WIKI_PAGE.equals(type)) {
                return new VKApiWikiPage().parse(attachment.getJSONObject(TYPE_WIKI_PAGE));
            } else if(TYPE_ALBUM.equals(type)) {
                return new VKApiPhotoAlbum().parse(attachment.getJSONObject(TYPE_ALBUM));
            }
            return null;
        }
    };

    /**
     * An abstract class for all attachments
     */
    @SuppressWarnings("unused")
    public abstract static class VKApiAttachment extends VKApiModel implements Identifiable {

        /**
         * Convert attachment to special string to attach it to the post, message or comment.
         */
        public abstract CharSequence toAttachmentString();

        /**
         * @return type of this attachment
         */
        public abstract String getType();
    }
}
