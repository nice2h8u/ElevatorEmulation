package com.nice2h8u.elevator_client_server_app.Entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class Command {

    private Floor goToFloor;
    private Date timeOfCommand;

    @Builder
    public Command(Floor goToFloor, Date timeOfCommand) {
        this.goToFloor = goToFloor;
        this.timeOfCommand = timeOfCommand;
    }
}
