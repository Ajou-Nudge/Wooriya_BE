package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.ProposalPostRequestDto;
import com.nudge.wooriya.data.dto.ProposalPostResponseDto;
import com.nudge.wooriya.data.entity.ProposalPost;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface BoardService {

    ProposalPostResponseDto getProposalPost(Long id) throws Exception;

    List<ProposalPostResponseDto> getAllProposalPost() throws Exception;

    Long saveProposalPost(ProposalPostRequestDto proposalPostRequestDto) throws Exception;

    Long deleteProposalPost(Long id) throws Exception;

    Long updateProposalPost(ProposalPostRequestDto proposalPostRequestDto, Long id) throws Exception;
}