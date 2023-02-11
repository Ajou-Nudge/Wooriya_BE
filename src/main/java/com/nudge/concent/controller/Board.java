package com.nudge.concent.controller;

import com.nudge.concent.data.dto.CompanyPostDto;
import com.nudge.concent.data.dto.GroupPostDto;
import com.nudge.concent.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class Board {
    private final BoardService boardService;

    @Autowired
    public Board(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value="/companypost/post", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> postCompanyPost(MultipartHttpServletRequest req) throws Exception {
        Long postId = boardService.saveCompanyPost(req);
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

    @RequestMapping(value="/imageupload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getImage(MultipartHttpServletRequest req) throws SQLException, NoSuchAlgorithmException {
        String urlImage = boardService.saveImage(req);
        return ResponseEntity.status(HttpStatus.OK).body(urlImage);
    }

    @PostMapping("/grouppost/post")
    public ResponseEntity<Long> postGroupPost(MultipartHttpServletRequest req) throws SQLException, NoSuchAlgorithmException {
        Long postId = boardService.saveGroupPost(req);
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