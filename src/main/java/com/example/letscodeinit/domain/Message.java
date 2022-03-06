package com.example.letscodeinit.domain;

import javax.persistence.*;

@Entity
public class Message {  //наша сущность
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // чтоб фреймворк сам разобрался с базой данной и делал
    private Integer id;

    //текст сообщения
    private String text;

    //теги
    private String tag;

    public String getAuthorName(){ // для main.mustache создали
        return author != null ? author.getUsername() : "<none>";
    }

    @ManyToOne(fetch = FetchType.EAGER)// одному пользователю - множество сообщений , и при получение сразу EAGER + author
    @JoinColumn(name = "user_id")
    private User author;



    public Message() {
    }

    public Message(String text, String tag,User user) {
        this.text = text;
        this.tag = tag;
        this.author = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
