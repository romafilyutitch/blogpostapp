package org.romafill.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private long id;
    private String title;
    private String text;
    private LocalDateTime createdDateTime;
    private long likes;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && likes == post.likes &&  Objects.equals(title, post.title) && Objects.equals(text, post.text) && Objects.equals(createdDateTime, post.createdDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, createdDateTime, likes);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", createDate=" + createdDateTime +
                ", likes=" + likes +
                '}';
    }
}
