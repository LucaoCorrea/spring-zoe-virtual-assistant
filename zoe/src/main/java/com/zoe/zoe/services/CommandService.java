package com.zoe.zoe.services;

import com.zoe.zoe.model.CommandModel;
import com.zoe.zoe.repository.CommandRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommandService {

    private final CommandRepository commandRepository;
    public CommandService(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public List<CommandModel> findByUserId(Long userId) {
        return commandRepository.findByUserId(userId);
    }

    public CommandModel save(CommandModel command) {
        return commandRepository.save(command);
    }
}
