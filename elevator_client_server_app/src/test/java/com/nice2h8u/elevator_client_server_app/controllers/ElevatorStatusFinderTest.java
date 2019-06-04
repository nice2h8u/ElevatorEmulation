package com.nice2h8u.elevator_client_server_app.controllers;

import com.nice2h8u.elevator_client_server_app.DTO.CommandDTO;
import com.nice2h8u.elevator_client_server_app.Entity.Elevator;
import com.nice2h8u.elevator_client_server_app.Entity.Floor;
import com.nice2h8u.elevator_client_server_app.Entity.States.Closing;
import com.nice2h8u.elevator_client_server_app.Entity.States.Moving;
import com.nice2h8u.elevator_client_server_app.Entity.States.Opening;
import com.nice2h8u.elevator_client_server_app.Entity.States.Staing;
import com.nice2h8u.elevator_client_server_app.services.ElevatorStatusFinder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ElevatorStatusFinderTest {




    @Test
    public void getMovingFrom4() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Fifth.toString()));
        Thread.sleep(2010);
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Seventh.toString()));

        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();
        Thread.sleep(1465);
        assertEquals (new Moving(Floor.Fifth,Floor.Sixth),elevatorStatusFinder.getElevatorCurrentState(new Date()));
    }

    @Test
    public void statusWhenOpening() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();

        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Third.toString()));
        Thread.sleep(1100);
       Date bl = new Date();
        System.out.println(bl);
        assertEquals(new Opening(Floor.Third), elevatorStatusFinder.getElevatorCurrentState(bl));
        Thread.sleep(70);
        Date f = new Date();
        System.out.println(f);
        assertEquals(new Opening(Floor.Third), elevatorStatusFinder.getElevatorCurrentState(bl));
    }

        @Test
    public void find() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Second.toString()));
        Thread.sleep(1500);
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Third.toString()));
        Thread.sleep(3000);
        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();
        assertEquals(new Staing(Floor.Third), elevatorStatusFinder.getElevatorCurrentState(new Date()));
    }

    @Test
    public void checkThatFuncDontDelCommandWhenItTimeIsNotCome() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Third.toString()));
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Sixth.toString()));
        Thread.sleep(5300);
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Third.toString()));

        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();
        assertEquals(new Moving(Floor.Sixth,Floor.Fifth), elevatorStatusFinder.getElevatorCurrentState(new Date()));
    }


    @Test
    public void justStatusWithNoCommands() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();
        assertEquals(new Staing(Floor.First), elevatorStatusFinder.getElevatorCurrentState(new Date()));
    }


    @Test
    public void twoStatusRequest() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.First.toString()));
        Thread.sleep(2000);
         assertEquals(new Staing(Floor.First), elevatorStatusFinder.getElevatorCurrentState(new Date()));
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Seventh.toString()));
        Thread.sleep(2600);
        assertEquals(new Moving(Floor.Sixth,Floor.Seventh), elevatorStatusFinder.getElevatorCurrentState(new Date()));

    }

    @Test
    public void toStatusRequest() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.First.toString()));
        Thread.sleep(2000);
        //assertEquals(new Staing(Floor.First), elevatorStatusFinder.getElevatorCurrentState(new Date()));
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Seventh.toString()));
        Thread.sleep(990);
        assertEquals(new Moving(Floor.Second,Floor.Third), elevatorStatusFinder.getElevatorCurrentState(new Date()));

    }
    @Test
    public void toCheckError() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Fourth.toString()));
        Thread.sleep(10);
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.First.toString()));
        Thread.sleep(30);
        assertEquals(new Moving(Floor.First,Floor.Second), elevatorStatusFinder.getElevatorCurrentState(new Date()));

        Thread.sleep(50);
        assertEquals(new Moving(Floor.First,Floor.Second), elevatorStatusFinder.getElevatorCurrentState(new Date()));

    }
    @Test
    public void toMoreError() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.First.toString()));
        Thread.sleep(10);
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Fourth.toString()));
        Thread.sleep(30);
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Fifth.toString()));
         elevatorStatusFinder.getElevatorCurrentState(new Date());

        Thread.sleep(200);
        assertEquals(new Opening(Floor.First), elevatorStatusFinder.getElevatorCurrentState(new Date()));
        Thread.sleep(3100);
        assertEquals(new Closing(Floor.Fourth), elevatorStatusFinder.getElevatorCurrentState(new Date()));
    }
@Test
    public void findMoreAndMore() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();

        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.First.toString()));
        Thread.sleep(10);
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Fourth.toString()));
        Thread.sleep(30);
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Fifth.toString()));
        elevatorStatusFinder.getElevatorCurrentState(new Date());

        Thread.sleep(200);
        assertEquals(new Opening(Floor.First), elevatorStatusFinder.getElevatorCurrentState(new Date()));
        Thread.sleep(2100);
        assertEquals(new Moving(Floor.Third,Floor.Fourth), elevatorStatusFinder.getElevatorCurrentState(new Date()));
    }

@Test
    public void goToFloorAndThenTwoStatus() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();

        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Second.toString()));
        Thread.sleep(600);
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.First.toString()));
        assertEquals(new Opening(Floor.Second), elevatorStatusFinder.getElevatorCurrentState(new Date()));
        Thread.sleep(500);
        assertEquals(new Closing(Floor.Second), elevatorStatusFinder.getElevatorCurrentState(new Date()));
        assertEquals(new Closing(Floor.Second), elevatorStatusFinder.getElevatorCurrentState(new Date()));
        Thread.sleep(2000);
        assertEquals(new Staing(Floor.First), elevatorStatusFinder.getElevatorCurrentState(new Date()));
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.First.toString()));
        Thread.sleep(20);
        assertEquals(new Opening(Floor.First), elevatorStatusFinder.getElevatorCurrentState(new Date()));

    }


    @Test
    public void goToFloorAndThenGoToFirstAfterStaing() throws InterruptedException {
        Elevator.getInstance().setCommandsOfElevator(new ArrayList<>());
        ElevatorStatusFinder elevatorStatusFinder = new ElevatorStatusFinder();

        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Second.toString()));
        Thread.sleep(600);

        assertEquals(new Opening(Floor.Second), elevatorStatusFinder.getElevatorCurrentState(new Date()));
        Thread.sleep(500);
        assertEquals(new Closing(Floor.Second), elevatorStatusFinder.getElevatorCurrentState(new Date()));
        Thread.sleep(500);
        assertEquals(new Staing(Floor.Second), elevatorStatusFinder.getElevatorCurrentState(new Date()));
        Elevator.getInstance().addCommandDto(new CommandDTO(Floor.Third.toString()));
        Thread.sleep(2000);
        assertEquals(new Staing(Floor.Third), elevatorStatusFinder.getElevatorCurrentState(new Date()));

    }
}