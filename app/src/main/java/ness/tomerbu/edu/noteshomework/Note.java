package ness.tomerbu.edu.noteshomework;

/**
 * POJO - Plain Old Java Object
 */
public class Note {
    public static int counter;
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        counter++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
