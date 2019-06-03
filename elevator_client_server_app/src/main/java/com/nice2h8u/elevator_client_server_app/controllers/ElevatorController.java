package com.nice2h8u.elevator_client_server_app.controllers;


import com.nice2h8u.elevator_client_server_app.DTO.CommandDTO;
import com.nice2h8u.elevator_client_server_app.Entity.Elevator;
import com.nice2h8u.elevator_client_server_app.Entity.Floor;
import com.nice2h8u.elevator_client_server_app.services.ElevatorStatusFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class ElevatorController {

    private final ElevatorStatusFinder finder;

    public ElevatorController() {
        this.finder = new ElevatorStatusFinder();
    }

    @GetMapping("/")

    public String newIngredient(Model model) {


        List<CommandDTO> commands = new ArrayList<>();
        commands.add(new CommandDTO("Просмотреть состояние лифта"));
        for (Floor floor : Floor.values())
            commands.add(new CommandDTO(floor.toString()));




        model.addAttribute("commands", commands);
        model.addAttribute("commandDTO", new CommandDTO());
        model.addAttribute("status",Elevator.getInstance().getCurrentState());
        return "index";
    }

    @PostMapping("/")

    public String getCommand(@ModelAttribute CommandDTO commandToPost) {



        if (!commandToPost.getState().equals("Просмотреть состояние лифта"))
            Elevator.getInstance().addCommandDto(commandToPost);
        else
           Elevator.getInstance().setCurrentState(finder.getElevatorCurrentState(new Date()).toString());

        return "redirect:/";
    }



}
