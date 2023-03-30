package com.example.task.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeavyParticipantDto {
    private long id;

    private final String name;

    private final String wish;

    private final ParticipantDto recipient;

    public HeavyParticipantDto(@JsonProperty("id") long id,
                               @JsonProperty("name") String name,
                               @JsonProperty("wish") String wish,
                               @JsonProperty("recipient") ParticipantDto recipient) {
        this.id = id;
        this.name = name;
        this.wish = wish;
        this.recipient = recipient;
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

    public ParticipantDto getRecipient() {
        return recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HeavyParticipantDto that = (HeavyParticipantDto) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(wish, that.wish) && Objects.equals(recipient, that.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, wish, recipient);
    }
}
