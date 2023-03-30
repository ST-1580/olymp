package com.example.task.entity;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private String wish;

    @ManyToOne()
    private Group group;

    @OneToOne()
    private Participant recipient;

    public Participant() {
    }

    public Participant(String name, String wish, Group group, Participant recipient) {
        this.name = name;
        this.wish = wish;
        this.group = group;
        this.recipient = recipient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Participant getRecipient() {
        return recipient;
    }

    public void setRecipient(Participant recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(wish, that.wish) && group.getId() == that.group.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, wish, group.getId());
    }
}
