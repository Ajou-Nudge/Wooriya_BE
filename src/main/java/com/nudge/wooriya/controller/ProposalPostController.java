package com.nudge.wooriya.controller;

import com.nudge.wooriya.data.dto.ProposalPostRequestDto;
import com.nudge.wooriya.data.dto.ProposalPostResponseDto;
import com.nudge.wooriya.data.dto.ProposalRequestDto;
import com.nudge.wooriya.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="ProposalPost", description = "제휴글/제휴 제안 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/proposal-post")
public class ProposalPostController {
    private final BoardService boardService;

    @Operation(summary = "postProposalPost", description = "[Token O] 제휴글 게시")
    @PostMapping("/post")
    public ResponseEntity<Long> postProposalPost(@RequestBody ProposalPostRequestDto proposalPostRequestDto) throws Exception {
        Long postId = boardService.saveProposalPost(proposalPostRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @Operation(summary = "deleteCompanyPost", description = "[Token O] 제휴글 삭제")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteCompanyPost(@PathVariable Long id) throws Exception {
        Long postId = boardService.deleteProposalPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @Operation(summary = "updateCompanyPost", description = "[Token O] 제휴글 수정")
    @PostMapping("/update/{id}")
    public ResponseEntity<Long> updateCompanyPost(@RequestBody ProposalPostRequestDto proposalPostRequestDto, @PathVariable Long id) throws Exception {
        Long postId = boardService.updateProposalPost(proposalPostRequestDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(postId);
    }

    @Operation(summary = "getAllCompanyPost", description = "[Token X] 제휴글 전체 보기")
    @GetMapping("/")
    public ResponseEntity<List<ProposalPostResponseDto>> getAllCompanyPost() throws Exception {
        List<ProposalPostResponseDto> proposalPostResponseDtos = boardService.getAllProposalPost();
        return ResponseEntity.status(HttpStatus.OK).body(proposalPostResponseDtos);
    }

    @Operation(summary = "getCompanyPost", description = "[Token X] 제휴글 상세 보기")
    @GetMapping("/{id}")
    public ResponseEntity<ProposalPostResponseDto> getCompanyPost(@PathVariable Long id) throws Exception {
        ProposalPostResponseDto proposalPostResponseDto = boardService.getProposalPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(proposalPostResponseDto);
    }

    @Operation(summary = "sendProposal", description = "[Token O] 제휴 제안 보내기")
    @PostMapping("/send/{id}")
    public ResponseEntity<Boolean> sendProposal(@PathVariable Long id, @RequestBody ProposalRequestDto proposalRequestDto) throws Exception {
        Boolean result = boardService.sendProposal(id, proposalRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}