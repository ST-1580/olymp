package com.example.task.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.task.controller.GroupController;
import com.example.task.dto.GroupDto;
import com.example.task.dto.HeavyGroupDto;
import com.example.task.dto.HeavyParticipantDto;
import com.example.task.dto.InputGroupDto;
import com.example.task.dto.ParticipantDto;
import com.example.task.entity.Group;
import com.example.task.entity.Participant;
import com.example.task.repository.GroupRepository;
import com.example.task.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GroupService implements GroupController {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public long createGroup(InputGroupDto groupDto) {
        Group group = new Group(groupDto.getName(), groupDto.getDescription(), new HashSet<>());
        return groupRepository.save(group).getId();
    }

    @Override
    public List<GroupDto> getGroups() {
        List<Group> allGroups = groupRepository.findAll();
        return allGroups.stream().map(this::convertGroupToDto).collect(Collectors.toList());
    }

    @Override
    public HeavyGroupDto getGroup(long groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id " + groupId + " does not exist");
        }

        return convertGroupToHeavyDto(group.get());
    }

    @Override
    public void updateGroup(long groupId, InputGroupDto groupDto) {
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id " + groupId + " does not exist");
        }

        groupRepository.updateNameAndDescriptionById(groupId, groupDto.getName(), groupDto.getDescription());
    }

    @Override
    public void updateGroup(long groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id " + groupId + " does not exist");
        }

        groupRepository.deleteById(groupId);
    }

    @Override
    public List<HeavyParticipantDto> getReadyParticipants(long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id " + groupId + " does not exist");
        }
        Group group = optionalGroup.get();

        List<Participant> participants = group.getParticipants().stream().toList();
        if (participants.size() < 3) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Group participants count less than 3");
        }

        for (int i = 0; i < participants.size(); i++) {
            participants.get(i).setRecipient(participants.get((i + 1) % participants.size()));
            participantRepository.save(participants.get(i));
        }

        return participants.stream().map(this::convertToHeavyParticipant).collect(Collectors.toList());
    }

    private GroupDto convertGroupToDto(Group group) {
        return new GroupDto(group.getId(), group.getName(), group.getDescription());
    }

    private HeavyGroupDto convertGroupToHeavyDto(Group group) {
        Set<Participant> participants = group.getParticipants();
        Map<Long, Participant> participantById = participants
                .stream()
                .collect(Collectors.toMap(
                        Participant::getId,
                        Function.identity()
                ));

        List<HeavyParticipantDto> heavyParticipants = participants
                .stream()
                .map(this::convertToHeavyParticipant)
                .collect(Collectors.toList());

        return new HeavyGroupDto(
                group.getId(),
                group.getName(),
                group.getDescription(),
                heavyParticipants);
    }

    private HeavyParticipantDto convertToHeavyParticipant(Participant participant) {
        return new HeavyParticipantDto(
                participant.getId(),
                participant.getName(),
                participant.getWish(),
                convertToParticipant(participant.getRecipient())
        );
    }

    private ParticipantDto convertToParticipant(Participant participant) {
       return participant == null ? null : new ParticipantDto(participant.getId(), participant.getName(), participant.getWish());
    }
}
