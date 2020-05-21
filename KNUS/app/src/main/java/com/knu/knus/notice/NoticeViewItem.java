package com.knu.knus.notice;

public class NoticeViewItem {

    private String title;
    private String contents;
    private int like;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getLike() { return like; }

    public void setLike(int like) { this.like = like; }
}
