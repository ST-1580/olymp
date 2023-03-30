package com.example.task.controller;

import com.example.task.dto.InputParticipantDto;
import com.example.task.dto.ParticipantDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public interface ParticipantController {
    @ApiOperation(value = "Add a participant", notes = "Name must be not null")
    @PostMapping("/group/{id}/participant")
    long createParticipant(@PathVariable("id") long groupId, InputParticipantDto participantDto);

    @ApiOperation(value = "Delete participant from group", notes = "Group and participant must exist")
    @DeleteMapping("/group/{groupId}/participant/{participantId}")
    void deleteParticipant(@PathVariable("groupId") long groupId,
                           @PathVariable("participantId") long participantId);

    @ApiOperation(value = "Get participant recipient from group", notes = "Group anf participant must exist")
    @GetMapping("/group/{groupId}/participant/{participantId}/recipient")
    ParticipantDto getParticipantRecipient(@PathVariable("groupId") long groupId,
                                           @PathVariable("participantId") long participantId);
}
