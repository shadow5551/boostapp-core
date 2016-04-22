package resources.payments;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "boostapp.payments")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "projectId")
    private Integer projectId;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "amountInCents")
    private int amountInCents;

    @Column(name = "createdOn")
    private Date createdOn;

    public Payment() {}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setAmountInCents(int amount) {
        this.amountInCents = amount;
    }

    public int getAmountInCents() {
        return this.amountInCents;
    }
}