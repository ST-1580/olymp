package com.example.task.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupDto {
    private final long id;

    private final String name;

    private final String description;

    public GroupDto(@JsonProperty("id") long id,
                    @JsonProperty("name") String name,
                    @JsonProperty("description") String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
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
        GroupDto groupDto = (GroupDto) o;
        return id == groupDto.id && Objects.equals(name, groupDto.name) && Objects.equals(description,
                groupDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
