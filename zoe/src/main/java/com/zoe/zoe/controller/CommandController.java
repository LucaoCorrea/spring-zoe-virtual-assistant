package com.zoe.zoe.controller;

import com.zoe.zoe.model.CommandModel;
import com.zoe.zoe.model.UserModel;
import com.zoe.zoe.services.CommandService;
import com.zoe.zoe.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commands")
public class CommandController {

    private final CommandService commandService;
    private final UserService userService;

    public CommandController(CommandService commandService, UserService userService) {
        this.commandService = commandService;
        this.userService = userService;
    }

    @PostMapping
    public CommandModel registerCommand(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String command = body.get("command");
        String response = body.get("response");
        String dateTimeStr = body.get("dateTime");

        
        LocalDateTime dateTime = dateTimeStr != null
                ? LocalDateTime.parse(dateTimeStr)
                : LocalDateTime.now();

        
        UserModel user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        
        CommandModel commandModel = new CommandModel();
        commandModel.setCommand(command);
        commandModel.setResponse(response);
        commandModel.setDateTime(dateTime);
        commandModel.setUser(user);

        return commandService.save(commandModel);
    }

    @GetMapping("/{userId}")
    public List<CommandModel> listByUser(@PathVariable Long userId) {
        return commandService.findByUserId(userId);
    }
}
