package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ProfileDto profile(String email) throws Exception;
}