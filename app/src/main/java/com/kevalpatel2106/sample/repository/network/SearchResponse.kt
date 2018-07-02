package com.kevalpatel2106.sample.repository.network

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import com.kevalpatel2106.sample.bin.UserInfo

@Generated("com.robohorse.robopojogenerator")
data class SearchResponse(

        @field:SerializedName("total_count")
        val totalCount: Int,

        @field:SerializedName("incomplete_results")
        val incompleteResults: Boolean,

        @field:SerializedName("items")
        val items: List<UserItem>
)

data class UserItem(

        @field:SerializedName("gists_url")
        val gistsUrl: String? = null,

        @field:SerializedName("repos_url")
        val reposUrl: String? = null,

        @field:SerializedName("following_url")
        val followingUrl: String? = null,

        @field:SerializedName("starred_url")
        val starredUrl: String? = null,

        @field:SerializedName("login")
        val login: String,

        @field:SerializedName("followers_url")
        val followersUrl: String? = null,

        @field:SerializedName("type")
        val type: String? = null,

        @field:SerializedName("url")
        val url: String? = null,

        @field:SerializedName("subscriptions_url")
        val subscriptionsUrl: String? = null,

        @field:SerializedName("score")
        val score: Double? = null,

        @field:SerializedName("received_events_url")
        val receivedEventsUrl: String? = null,

        @field:SerializedName("avatar_url")
        val avatarUrl: String,

        @field:SerializedName("events_url")
        val eventsUrl: String? = null,

        @field:SerializedName("html_url")
        val htmlUrl: String? = null,

        @field:SerializedName("site_admin")
        val siteAdmin: Boolean,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("gravatar_id")
        val gravatarId: String? = null,

        @field:SerializedName("node_id")
        val nodeId: String? = null,

        @field:SerializedName("organizations_url")
        val organizationsUrl: String? = null
) {

    internal fun toUser() = UserInfo(
            username = login,
            avatarUrl = avatarUrl
    )
}