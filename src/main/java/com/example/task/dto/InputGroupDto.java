package com.example.task.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;

public class InputGroupDto {
    @Nonnull
    private final String name;

    private final String description;

    public InputGroupDto(@Nonnull @JsonProperty("name") String name,
                         @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InputGroupDto that = (InputGroupDto) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
