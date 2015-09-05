package org.flycraft.vkontakteapi.model;

import org.json.JSONException;
import org.json.JSONObject;

public class VkAudioArray extends VKList<VKApiAudio> {
    @Override
    public VKApiModel parse(JSONObject response) throws JSONException {
        fill(response, VKApiAudio.class);
        return this;
    }
}
