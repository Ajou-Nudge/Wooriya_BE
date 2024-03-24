package com.nudge.wooriya.application.port.in.Prorposal;

import com.nudge.wooriya.application.port.in.Prorposal.dto.*;

import java.util.List;

public interface BoardUsecase {

    ProposalPostResponseDto getProposalPost(Long id) throws Exception;

    List<ProposalPostResponseDto> getAllProposalPost() throws Exception;

    Long saveProposalPost(ProposalPostRequestDto proposalPostRequestDto) throws Exception;

    Long deleteProposalPost(Long id) throws Exception;

    Long updateProposalPost(ProposalPostRequestDto proposalPostRequestDto, Long id) throws Exception;

    Boolean sendProposal(Long postId, ProposalRequestDto proposalRequestDto) throws Exception;
}