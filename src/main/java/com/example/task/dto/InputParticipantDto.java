package com.example.task.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;

public class InputParticipantDto {
    private final String name;

    private final String wish;

    public InputParticipantDto(@Nonnull @JsonProperty("name") String name,
                               @JsonProperty("wish") String wish) {
        this.name = name;
        this.wish = wish;
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
        InputParticipantDto that = (InputParticipantDto) o;
        return Objects.equals(name, that.name) && Objects.equals(wish, that.wish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wish);
    }
}
