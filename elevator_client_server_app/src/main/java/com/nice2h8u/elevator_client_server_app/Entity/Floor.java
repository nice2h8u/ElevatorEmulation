package com.nice2h8u.elevator_client_server_app.Entity;

public enum Floor {
    First("Первый"),Second("Второй"),Third("Третий"),Fourth("Четвертый"),Fifth("Пятый"),Sixth("Шестой"),Seventh("Седьмой");

    private String nameOfFloor;


    Floor(String nameOfFloor) {
        this.nameOfFloor = nameOfFloor;

    }

    @Override
    public String toString() {
        return  "Вызвать лифт на "+nameOfFloor+" этаж";
    }

    public String num(){
        return nameOfFloor;
    }
}