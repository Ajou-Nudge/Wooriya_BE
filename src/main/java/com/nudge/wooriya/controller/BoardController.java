package com.nudge.wooriya.controller;

import com.nudge.wooriya.data.dto.CompanyPostDto;
import com.nudge.wooriya.data.dto.GroupPostDto;
import com.nudge.wooriya.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/companypost/post")
    public ResponseEntity<Long> postCompanyPost(@RequestBody CompanyPostDto companyPostDto) throws Exception {
        Long postId = boardService.saveCompanyPost(companyPostDto);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @DeleteMapping("/companypost/delete/{id}")
    public void deleteCompanyPost(@PathVariable Long id) {
        boardService.deleteCompanyPost(id);
    }

    @PostMapping("/companypost/update/{id}")
    public ResponseEntity<Long> updateCompanyPost(@RequestBody CompanyPostDto companyPostDto, @PathVariable Long id) throws Exception {
        Long postId = boardService.updateCompanyPost(companyPostDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @GetMapping("/companypost")
    public ResponseEntity<List<CompanyPostDto>> getAllCompanyPost() throws UnsupportedEncodingException {
        List<CompanyPostDto> companyPostResponseDtos = boardService.getAllCompanyPost();
        return ResponseEntity.status(HttpStatus.OK).body(companyPostResponseDtos);
    }

    @GetMapping("/companypost/{id}")
    public ResponseEntity<CompanyPostDto> getCompanyPost(@PathVariable Long id) {
        CompanyPostDto companyPostDto = boardService.getCompanyPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(companyPostDto);
    }

    @PostMapping("/grouppost/post")
    public ResponseEntity<Long> postGroupPost(GroupPostDto groupPostDto) throws SQLException, NoSuchAlgorithmException {
        Long postId = boardService.saveGroupPost(groupPostDto);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @DeleteMapping("/grouppost/delete/{id}")
    public void deleteGroupPost(@PathVariable Long id) {
        boardService.deleteGroupPost(id);
    }

    @PostMapping("/grouppost/update/{id}")
    public ResponseEntity<Long> updateGroupPost(@RequestBody GroupPostDto groupPostDto, @PathVariable Long id) throws Exception {
        Long postId = boardService.updateGroupPost(groupPostDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @GetMapping("/grouppost")
    public ResponseEntity<List<GroupPostDto>> getGroupPost() {
        List<GroupPostDto> groupPostDtos = boardService.getAllGroupPost();
        return ResponseEntity.status(HttpStatus.OK).body(groupPostDtos);
    }

    @GetMapping("/grouppost/{id}")
    public ResponseEntity<GroupPostDto> getGroupPost(@PathVariable Long id) {
        GroupPostDto groupPostDto = boardService.getGroupPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(groupPostDto);
    }
}