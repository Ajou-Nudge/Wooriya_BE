package com.nudge.wooriya.controller;

import com.nudge.wooriya.data.dto.ProposalPostRequestDto;
import com.nudge.wooriya.data.dto.ProposalPostResponseDto;
import com.nudge.wooriya.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/proposal-post/post")
    public ResponseEntity<Long> postCompanyPost(@RequestBody ProposalPostRequestDto proposalPostRequestDto) throws Exception {
        Long postId = boardService.saveProposalPost(proposalPostRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @DeleteMapping("/proposal-post/delete/{id}")
    public ResponseEntity<Long> deleteCompanyPost(@PathVariable Long id) throws Exception {
        Long postId = boardService.deleteProposalPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @PostMapping("/proposal-post/update/{id}")
    public ResponseEntity<Long> updateCompanyPost(@RequestBody ProposalPostRequestDto proposalPostRequestDto, @PathVariable Long id) throws Exception {
        Long postId = boardService.updateProposalPost(proposalPostRequestDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @GetMapping("/proposal-post")
    public ResponseEntity<List<ProposalPostResponseDto>> getAllCompanyPost() throws Exception {
        List<ProposalPostResponseDto> proposalPostResponseDtos = boardService.getAllProposalPost();
        return ResponseEntity.status(HttpStatus.OK).body(proposalPostResponseDtos);
    }

    @GetMapping("/proposal-post/{id}")
    public ResponseEntity<ProposalPostResponseDto> getCompanyPost(@PathVariable Long id) throws Exception {
        ProposalPostResponseDto proposalPostResponseDto = boardService.getProposalPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(proposalPostResponseDto);
    }
}