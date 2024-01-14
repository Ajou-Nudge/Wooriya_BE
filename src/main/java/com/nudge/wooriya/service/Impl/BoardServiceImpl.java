package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.SecurityUtil;
import com.nudge.wooriya.data.dto.ProposalPostRequestDto;
import com.nudge.wooriya.data.dto.ProposalPostResponseDto;
import com.nudge.wooriya.data.entity.ProposalPost;
import com.nudge.wooriya.data.repository.PostImageMetaRepository;
import com.nudge.wooriya.data.repository.ProposalPostRepository;
import com.nudge.wooriya.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final MetadataServiceImpl metadataService;
    private final PostImageMetaRepository postImageMetaRepository;
    private final ProposalPostRepository proposalPostRepository;

    @Autowired
    public BoardServiceImpl(MetadataServiceImpl metadataService, PostImageMetaRepository postImageMetaRepository, ProposalPostRepository proposalPostRepository) {
        this.proposalPostRepository = proposalPostRepository;
        this.metadataService = metadataService;
        this.postImageMetaRepository = postImageMetaRepository;
    }

    @Override
    public ProposalPostResponseDto getProposalPost(Long id) throws Exception {
        ProposalPost proposalPost = proposalPostRepository.findById(id).orElseThrow(() -> new Exception("post not found"));
        ProposalPostResponseDto proposalPostResponseDto = new ProposalPostResponseDto();
        proposalPostResponseDto.setTitle(proposalPost.getTitle());
        proposalPostResponseDto.setAuthor(proposalPost.getAuthor());
        proposalPostResponseDto.setAffiliateKinds(proposalPost.getAffiliateKinds());
        proposalPostResponseDto.setAffiliateForm(proposalPost.getAffiliateForm());
        proposalPostResponseDto.setAffiliatePeriod(proposalPost.getAffiliatePeriod());
        proposalPostResponseDto.setRecruitmentPeriod(proposalPost.getRecruitmentPeriod());
        proposalPostResponseDto.setPrLinks(proposalPost.getPrLinks());
        proposalPostResponseDto.setPhotos(proposalPost.getPhotos());
        proposalPostResponseDto.setDetail(proposalPost.getDetail());

        return proposalPostResponseDto;
    }

    @Override
    public List<ProposalPostResponseDto> getAllProposalPost() {
        List<ProposalPost> proposalPosts = proposalPostRepository.findAll();
        List<ProposalPostResponseDto> proposalPostResponseDtos = new ArrayList<>();
        for (ProposalPost proposalPost : proposalPosts) {
            ProposalPostResponseDto proposalPostResponseDto = new ProposalPostResponseDto();
            proposalPostResponseDto.setTitle(proposalPost.getTitle());
            proposalPostResponseDto.setAuthor(proposalPost.getAuthor());
            proposalPostResponseDto.setAffiliateKinds(proposalPost.getAffiliateKinds());
            proposalPostResponseDto.setAffiliateForm(proposalPost.getAffiliateForm());
            proposalPostResponseDto.setAffiliatePeriod(proposalPost.getAffiliatePeriod());
            proposalPostResponseDto.setRecruitmentPeriod(proposalPost.getRecruitmentPeriod());
            proposalPostResponseDto.setPrLinks(proposalPost.getPrLinks());
            proposalPostResponseDto.setPhotos(proposalPost.getPhotos());
            proposalPostResponseDto.setDetail(proposalPost.getDetail());

            proposalPostResponseDtos.add(proposalPostResponseDto);
        }
        return proposalPostResponseDtos;
    }

    @Override
    public Long saveProposalPost(ProposalPostRequestDto proposalPostRequestDto) throws Exception {
        System.out.println(SecurityUtil.getCurrentMemberId().getEmail());
        ProposalPost proposalPost = new ProposalPost();
        proposalPost.setTitle(proposalPostRequestDto.getTitle());
        proposalPost.setAuthor(SecurityUtil.getCurrentMemberId().getEmail());
        proposalPost.setAffiliateKinds(proposalPostRequestDto.getAffiliateKinds());
        proposalPost.setAffiliateForm(proposalPostRequestDto.getAffiliateForm());
        proposalPost.setAffiliatePeriod(proposalPostRequestDto.getAffiliatePeriod());
        proposalPost.setRecruitmentPeriod(proposalPostRequestDto.getRecruitmentPeriod());
        proposalPost.setPrLinks(proposalPostRequestDto.getPrLinks());
        proposalPost.setPhotos(proposalPostRequestDto.getPhotos());
        proposalPost.setDetail(proposalPostRequestDto.getDetail());
        proposalPost.setCreatedAt(LocalDateTime.now());
        proposalPost.setUpdatedAt(LocalDateTime.now());
        Long id = proposalPostRepository.save(proposalPost).getId();

        return id;
    }

    public Long deleteProposalPost(Long id) throws Exception {
        ProposalPost proposalPost = proposalPostRepository.findById(id).orElseThrow(() -> new Exception("post not found"));
        if(!SecurityUtil.getCurrentMemberId().getEmail().equals(proposalPost.getAuthor())) {
            return -1L;
        }
        proposalPostRepository.deleteById(id);
        return id;
    }

    @Override
    public Long updateProposalPost(ProposalPostRequestDto proposalPostRequestDto, Long id) throws Exception {
        ProposalPost proposalPost = proposalPostRepository.findById(id).orElseThrow(() -> new Exception("post not found"));

        if(!SecurityUtil.getCurrentMemberId().getEmail().equals(proposalPost.getAuthor())) {
            return -1L;
        }

        proposalPost.setTitle(proposalPostRequestDto.getTitle());
        proposalPost.setAffiliateKinds(proposalPostRequestDto.getAffiliateKinds());
        proposalPost.setAffiliateForm(proposalPostRequestDto.getAffiliateForm());
        proposalPost.setAffiliatePeriod(proposalPostRequestDto.getAffiliatePeriod());
        proposalPost.setRecruitmentPeriod(proposalPostRequestDto.getRecruitmentPeriod());
        proposalPost.setPrLinks(proposalPostRequestDto.getPrLinks());
        proposalPost.setPhotos(proposalPostRequestDto.getPhotos());
        proposalPost.setDetail(proposalPostRequestDto.getDetail());
        Long postId = proposalPostRepository.save(proposalPost).getId();

        return postId;
    }

}