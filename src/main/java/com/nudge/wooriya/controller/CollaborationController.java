package com.nudge.wooriya.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="User", description = "카페 정보 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe")
public class CollaborationController {

}
