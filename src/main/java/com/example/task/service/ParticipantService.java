package com.example.task.service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.task.controller.ParticipantController;
import com.example.task.dto.InputParticipantDto;
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
public class ParticipantService implements ParticipantController {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ParticipantRepository participantRepository;


    @Override
    public long createParticipant(long groupId, InputParticipantDto participantDto) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id " + groupId + " does not exist");
        }
        Group group = optionalGroup.get();

        Participant participant = participantRepository.save(
                new Participant(
                        participantDto.getName(),
                        participantDto.getWish(),
                        group,
                        null
                ));

        group.addParticipant(participant);
        groupRepository.save(group);
        return participant.getId();
    }

    @Override
    public void deleteParticipant(long groupId, long participantId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id " + groupId + " does not exist");
        }
        Group group = optionalGroup.get();

        Map<Long, Participant> participantById = group.getParticipants().stream().collect(Collectors.toMap(
                Participant::getId,
                Function.identity()
        ));

        if (!participantById.containsKey(participantId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Participant with id " + participantId + " does not exist in group with id " + groupId);
        }

        for (Participant participant : participantById.values()) {
            if (participant.getRecipient() != null && participant.getRecipient().getId() == participantId) {
                participant.setRecipient(null);
                participantRepository.save(participant);
            }
        }

        Participant toDel = participantById.get(participantId);
        group.removeParticipant(toDel);
        groupRepository.save(group);
        participantRepository.delete(toDel);
    }

    @Override
    public ParticipantDto getParticipantRecipient(long groupId, long participantId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id " + groupId + " does not exist");
        }
        Group group = optionalGroup.get();

        Map<Long, Participant> participantById = group.getParticipants().stream().collect(Collectors.toMap(
                Participant::getId,
                Function.identity()
        ));

        if (!participantById.containsKey(participantId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Participant with id " + participantId + " does not exist in group with id " + groupId);
        }

        Participant participant = participantById.get(participantId);
        if (participant.getRecipient() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Participant with id " + participantId + " does not have recipient");
        }

        return convertToParticipant(participant.getRecipient());
    }

    private ParticipantDto convertToParticipant(Participant participant) {
        return participant == null ? null : new ParticipantDto(participant.getId(), participant.getName(), participant.getWish());
    }
}
