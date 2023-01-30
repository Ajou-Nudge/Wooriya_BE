package com.nudge.concent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nudge.concent.data.dto.CompanyPostDto;
import com.nudge.concent.data.dto.GroupPostDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FunctionTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    String groupName = "아주대학교";
    String companyName = "제공사";
    String title = "제목";
    String coType = "의료";
    int coSize = 100;

    CompanyPostDto companyPostDto = new CompanyPostDto(1L, title, companyName, coType, coSize);
    GroupPostDto groupPostDto = new GroupPostDto(1L, title, groupName, coType, coSize);

    @Test
    @DisplayName("[POST] Company Post")
    void postCompanyTest() throws Exception {
        String body = mapper.writeValueAsString(
                CompanyPostDto.builder().companyName(companyPostDto.getCompanyName()).title(companyPostDto.getTitle()).coType(companyPostDto.getCoType()).coSize(companyPostDto.getCoSize()).build()
        );

        mvc.perform(post("/companypost/post")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("[GET] Company Post")
    void getCompanyTest() throws Exception {
        List<CompanyPostDto> companyPostDtos = new ArrayList<>();
        companyPostDtos.add(companyPostDto);
        mvc.perform(get("/companypost")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(companyPostDtos.get(0).getTitle()))
                .andExpect(jsonPath("$[0].companyName").value(companyPostDtos.get(0).getCompanyName()))
                .andExpect(jsonPath("$[0].coType").value(companyPostDtos.get(0).getCoType()))
                .andExpect(jsonPath("$[0].coSize").value(companyPostDtos.get(0).getCoSize()));
    }

    @Test
    @DisplayName("[POST] Group Post")
    void postGroupTest() throws Exception {
        String body = mapper.writeValueAsString(
                GroupPostDto.builder().groupName(groupPostDto.getGroupName()).title(groupPostDto.getTitle()).coType(groupPostDto.getCoType()).coSize(groupPostDto.getCoSize()).build()
        );

        mvc.perform(post("/grouppost/post")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("[GET] Group Post")
    void getGroupTest() throws Exception {
        List<GroupPostDto> groupPostDtos = new ArrayList<>();
        groupPostDtos.add(groupPostDto);
        mvc.perform(get("/grouppost")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(groupPostDtos.get(0).getTitle()))
                .andExpect(jsonPath("$[0].groupName").value(groupPostDtos.get(0).getGroupName()))
                .andExpect(jsonPath("$[0].coType").value(groupPostDtos.get(0).getCoType()))
                .andExpect(jsonPath("$[0].coSize").value(groupPostDtos.get(0).getCoSize()));

    }
}
