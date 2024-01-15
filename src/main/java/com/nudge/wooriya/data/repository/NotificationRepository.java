package com.nudge.wooriya.data.repository;

import com.nudge.wooriya.data.entity.Notification;
import com.nudge.wooriya.data.entity.ProposalPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByReceiver(String receiver);
}
