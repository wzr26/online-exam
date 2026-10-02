package com.wzr26.onlineexam.model;

public class Exam {

    private Long id;
    private String title;
    private Integer duration;

    public Exam() {
    }

    public Exam(
            Long id,
            String title,
            Integer duration
    ) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}

