package com.example.task.controller;

import java.util.List;

import com.example.task.dto.GroupDto;
import com.example.task.dto.HeavyGroupDto;
import com.example.task.dto.HeavyParticipantDto;
import com.example.task.dto.InputGroupDto;
import com.example.task.dto.ParticipantDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public interface GroupController {
    @PostMapping("/group")
    long createGroup(InputGroupDto groupDto);

    @GetMapping("/groups")
    List<GroupDto> getGroups();

    @GetMapping("/group/{id}")
    HeavyGroupDto getGroup(@PathVariable("id") long groupId);

    @PutMapping("/group/{id}")
    void updateGroup(@PathVariable("id") long groupId, InputGroupDto groupDto);

    @DeleteMapping("/group/{id}")
    void updateGroup(@PathVariable("id") long groupId);

    @PostMapping("/group/{id}/toss")
    List<HeavyParticipantDto> getReadyParticipants(@PathVariable("id") long groupId);
}
