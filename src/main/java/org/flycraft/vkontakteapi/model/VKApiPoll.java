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
 * PollAttachment.java
 * vk-android-sdk
 *
 * Created by Babichev Vitaly on 19.01.14.
 * Copyright (c) 2014 VK. All rights reserved.
 */
package org.flycraft.vkontakteapi.model;



import org.json.JSONException;
import org.json.JSONObject;
import static org.flycraft.vkontakteapi.model.VKAttachments.*;

/**
 * Describes poll on the wall on board.
 */
@SuppressWarnings("unused")
public class VKApiPoll extends VKAttachments.VKApiAttachment {

    /**
     * Poll ID to get information about it using polls.getById method;
     */
    public int id;

    /**
     * ID of the user or community that owns this poll.
     */
    public int owner_id;

    /**
     * Date (in Unix time) the poll was created.
     */
    public long created;

    /**
     * Question in the poll.
     */
    public String question;

    /**
     * The total number of users answered.
     */
    public int votes;

    /**
     * Response ID of the current user(if the current user has not yet posted in this poll, it contains 0)
     */
    public int answer_id;

    /**
     * Array of answers for this question.
     */
    public VKList<Answer> answers;

	public VKApiPoll(JSONObject from) throws JSONException
	{
		parse(from);
	}
    /**
     * Fills a Poll instance from JSONObject.
     */
    public VKApiPoll parse(JSONObject source) {
        id = source.optInt("id");
        owner_id = source.optInt("owner_id");
        created = source.optLong("created");
        question = source.optString("question");
        votes = source.optInt("votes");
        answer_id = source.optInt("answer_id");
        answers = new VKList<Answer>(source.optJSONArray("answers"), Answer.class);
        return this;
    }

    /**
     * Creates empty Country instance.
     */
    public VKApiPoll() {

    }

    @Override
    public CharSequence toAttachmentString() {
        return null;
    }

    @Override
    public String getType() {
        return TYPE_POLL;
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * Represents answer for the poll
     */
    public final static class Answer extends VKApiModel implements Identifiable {

        /**
         * ID of the answer for the question
         */
        public int id;

        /**
         * Text of the answer
         */
        public String text;

        /**
         * Number of users that voted for this answer
         */
        public int votes;

        /**
         * Rate of this answer in percent
         */
        public double rate;

        public Answer parse(JSONObject source) {
            id = source.optInt("id");
            text = source.optString("text");
            votes = source.optInt("votes");
            rate = source.optDouble("rate");
            return this;
        }

        @Override
        public int getId() {
            return id;
        }
    }
}
