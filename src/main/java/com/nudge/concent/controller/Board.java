package com.nudge.concent.controller;

import com.nudge.concent.data.dto.CompanyPostDto;
import com.nudge.concent.data.dto.GroupPostDto;
import com.nudge.concent.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Board {
    private final BoardService boardService;

    @Autowired
    public Board(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/companypost/post")
    public ResponseEntity<Long> postCompanyPost(@RequestBody CompanyPostDto companyPostDto) {
        Long postId = boardService.saveCompanyPost(companyPostDto);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @GetMapping("/companypost")
    public ResponseEntity<List<CompanyPostDto>> getCompanyPost() {
        List<CompanyPostDto> companyPostDtos = boardService.getAllCompanyPost();
        return ResponseEntity.status(HttpStatus.OK).body(companyPostDtos);
    }

    @PostMapping("/grouppost/post")
    public ResponseEntity<Long> postGroupPost(@RequestBody GroupPostDto groupPostDto) {
        Long postId = boardService.saveGroupPost(groupPostDto);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @GetMapping("/grouppost")
    public ResponseEntity<List<GroupPostDto>> getGroupPost() {
        List<GroupPostDto> groupPostDtos = boardService.getAllGroupPost();
        return ResponseEntity.status(HttpStatus.OK).body(groupPostDtos);
    }
}
