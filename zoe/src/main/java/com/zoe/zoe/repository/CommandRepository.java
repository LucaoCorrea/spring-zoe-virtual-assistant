package com.zoe.zoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zoe.zoe.model.CommandModel;
import java.util.List;

public interface CommandRepository extends JpaRepository<CommandModel, Long> {
    List<CommandModel> findByUserId(Long userId);
}