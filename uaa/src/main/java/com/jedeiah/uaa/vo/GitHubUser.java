package com.jedeiah.uaa.vo;

import lombok.Data;

import java.util.Date;

@Data
public class GitHubUser {
    // 用户登录名
    private String login;
    // 用户ID
    private long id;
    // 节点ID
    private String nodeId;
    // 用户头像URL
    private String avatarUrl;
    // Gravatar ID
    private String gravatarId;
    // 用户API URL
    private String url;
    // 用户HTML URL
    private String htmlUrl;
    // 用户关注者URL
    private String followersUrl;
    // 用户关注URL
    private String followingUrl;
    // 用户Gists URL
    private String gistsUrl;
    // 用户收藏的仓库URL
    private String starredUrl;
    // 用户订阅URL
    private String subscriptionsUrl;
    // 用户组织URL
    private String organizationsUrl;
    // 用户仓库URL
    private String reposUrl;
    // 用户事件URL
    private String eventsUrl;
    // 用户接收事件URL
    private String receivedEventsUrl;
    // 用户类型
    private String type;
    // 是否是站点管理员
    private boolean siteAdmin;
    // 用户姓名
    private String name;
    // 用户公司
    private String company;
    // 用户博客
    private String blog;
    // 用户位置
    private String location;
    // 用户邮箱
    private String email;
    // 是否可雇佣
    private Boolean hireable;
    // 用户简介
    private String bio;
    // 用户Twitter用户名
    private String twitterUsername;
    // 用户公开仓库数量
    private int publicRepos;
    // 用户公开Gists数量
    private int publicGists;
    // 用户关注者数量
    private int followers;
    // 用户关注数量
    private int following;
    // 用户创建时间
    private Date createdAt;
    // 用户更新时间
    private Date updatedAt;


    @Override
    public String toString() {
        return "GitHubUser{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", nodeId='" + nodeId + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gravatarId='" + gravatarId + '\'' +
                ", url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", followersUrl='" + followersUrl + '\'' +
                ", followingUrl='" + followingUrl + '\'' +
                ", gistsUrl='" + gistsUrl + '\'' +
                ", starredUrl='" + starredUrl + '\'' +
                ", subscriptionsUrl='" + subscriptionsUrl + '\'' +
                ", organizationsUrl='" + organizationsUrl + '\'' +
                ", reposUrl='" + reposUrl + '\'' +
                ", eventsUrl='" + eventsUrl + '\'' +
                ", receivedEventsUrl='" + receivedEventsUrl + '\'' +
                ", type='" + type + '\'' +
                ", siteAdmin=" + siteAdmin +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", blog='" + blog + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", hireable=" + hireable +
                ", bio='" + bio + '\'' +
                ", twitterUsername='" + twitterUsername + '\'' +
                ", publicRepos=" + publicRepos +
                ", publicGists=" + publicGists +
                ", followers=" + followers +
                ", following=" + following +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}