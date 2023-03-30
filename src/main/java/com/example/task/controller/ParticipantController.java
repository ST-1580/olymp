package com.example.task.controller;

import com.example.task.dto.InputParticipantDto;
import com.example.task.dto.ParticipantDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public interface ParticipantController {
    @PostMapping("/group/{id}/participant")
    long createParticipant(@PathVariable("id") long groupId, InputParticipantDto participantDto);

    @DeleteMapping("/group/{groupId}/participant/{participantId}")
    void deleteParticipant(@PathVariable("groupId") long groupId,
                           @PathVariable("participantId") long participantId);

    @GetMapping("/group/{groupId}/participant/{participantId}/recipient")
    ParticipantDto getParticipantRecipient(@PathVariable("groupId") long groupId,
                                           @PathVariable("participantId") long participantId);
}
