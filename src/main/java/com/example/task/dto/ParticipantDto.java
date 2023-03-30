package com.example.task.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParticipantDto {
    private long id;

    private final String name;

    private final String wish;

    public ParticipantDto(@JsonProperty("id") long id,
                          @JsonProperty("name") String name,
                          @JsonProperty("wish") String wish) {
        this.id = id;
        this.name = name;
        this.wish = wish;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWish() {
        return wish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParticipantDto that = (ParticipantDto) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(wish, that.wish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, wish);
    }
}
