package com.example.demo;

public class Films {
    public int id;
    public String title;
    public String timeStart;
    public String timeEnd;

    public Films(int id, String title, String timeStart, String timeEnd) {
        this.setId(id);
        this.setTitle(title);
        this.setTimeStart(timeStart);
        this.setTimeEnd(timeEnd);
    }

    @Override
    public String toString() {
        return String.format(String.valueOf(this.getId()), this.getTitle(), this.getTimeStart(), this.getTimeEnd());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

}

