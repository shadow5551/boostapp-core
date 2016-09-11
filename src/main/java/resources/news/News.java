package resources.news;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 10.9.16.
 */
@Entity
@Table(name = "boostapp.news")
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    /*@Column(name = "data")
    private Date data;*/

    @Column(name = "content")
    private Date content;

    public int getIdnews() {
        return id;
    }

    public void setIdnews(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }*/

    public Date getContent() {
        return content;
    }

    public void setContent(Date content) {
        this.content = content;
    }
}
