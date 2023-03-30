package com.example.task.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeavyGroupDto {
    private long id;

    private final String name;

    private final String description;

    private final List<HeavyParticipantDto> participants;

    public HeavyGroupDto(@JsonProperty("id") long id,
                         @JsonProperty("name") String name,
                         @JsonProperty("description") String description,
                         @JsonProperty("participants") List<HeavyParticipantDto> participants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.participants = participants;
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

    public List<HeavyParticipantDto> getParticipants() {
        return participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HeavyGroupDto that = (HeavyGroupDto) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, participants);
    }
}
