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
 * Comment.java
 * vk-android-sdk
 *
 * Created by Babichev Vitaly on 19.01.14.
 * Copyright (c) 2014 VK. All rights reserved.
 */
package org.flycraft.vkontakteapi.model;

import org.json.JSONObject;

/**
 * Comment object describes a comment.
 */
@SuppressWarnings("unused")
public class VKApiComment extends VKApiModel implements Identifiable
{

	/**
	 * Comment ID, positive number
	 */
	public int id;

	/**
	 * Comment author ID.
	 */
	public int from_id;

	/**
	 * Date when the comment was added as unixtime.
	 */
	public long date;

	/**
	 * Text of the comment
	 */
	public String text;

	/**
	 * ID of the user or community to whom the reply is addressed (if the comment is a reply to another comment).
	 */
	public int reply_to_user;

	/**
	 * ID of the comment the reply to which is represented by the current comment (if the comment is a reply to another comment).
	 */
	public int reply_to_comment;

	/**
	 * Number of likes on the comment.
	 */
	public int likes;

	/**
	 * Information whether the current user liked the comment.
	 */
	public boolean user_likes;

	/**
	 * Whether the current user can like on the comment.
	 */
	public boolean can_like;

	/**
	 * Information about attachments in the comments (photos, links, etc.;)
	 */
	public VKAttachments attachments = new VKAttachments();

	public VKApiComment(JSONObject from)
	{
		parse(from);
	}

	/**
	 * Fills a Comment instance from JSONObject.
	 */
	public VKApiComment parse(JSONObject from)
	{
		id = from.optInt("id");
		from_id = from.optInt("from_id");
		date = from.optLong("date");
		text = from.optString("text");
		reply_to_user = from.optInt("reply_to_user");
		reply_to_comment = from.optInt("reply_to_comment");
		attachments.fill(from.optJSONArray("attachments"));
		JSONObject likes = from.optJSONObject("likes");
		this.likes = ParseUtils.parseInt(likes, "count");
		this.user_likes = ParseUtils.parseBoolean(likes, "user_likes");
		this.can_like = ParseUtils.parseBoolean(likes, "can_like");
		return this;
	}


	/**
	 * Creates empty Comment instance.
	 */
	public VKApiComment()
	{

	}

	@Override
	public int getId()
	{
		return id;
	}
}
