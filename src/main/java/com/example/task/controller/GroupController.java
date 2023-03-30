package com.example.task.controller;

import java.util.List;

import com.example.task.dto.GroupDto;
import com.example.task.dto.HeavyGroupDto;
import com.example.task.dto.HeavyParticipantDto;
import com.example.task.dto.InputGroupDto;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Create a group", notes = "Name must be not null")
    @PostMapping("/group")
    long createGroup(InputGroupDto groupDto);
    @ApiOperation(value = "Get a list of groups", notes = "Group participants info dosent available")
    @GetMapping("/groups")
    List<GroupDto> getGroups();

    @ApiOperation(value = "Get info about group")
    @GetMapping("/group/{id}")
    HeavyGroupDto getGroup(@PathVariable("id") long groupId)
            ;
    @ApiOperation(value = "Update group", notes = "Name must be not null")
    @PutMapping("/group/{id}")
    void updateGroup(@PathVariable("id") long groupId, InputGroupDto groupDto);

    @ApiOperation(value = "Delete group")
    @DeleteMapping("/group/{id}")
    void deleteGroup(@PathVariable("id") long groupId);

    @ApiOperation(value = "Create a list of participants")
    @PostMapping("/group/{id}/toss")
    List<HeavyParticipantDto> getReadyParticipants(@PathVariable("id") long groupId);
}
