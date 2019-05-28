package Enums;

public enum Floor {
    First("Первый"),Second("Второй"),Third("Третий"),Fourth("Четвертый"),Fifth("Пятый"),Sixth("Шестой"),Seventh("Седьмой");

    private String nameOfFloor;


    Floor(String nameOfFloor) {
        this.nameOfFloor = nameOfFloor;

    }

    @Override
    public String toString() {
        return  nameOfFloor;
    }
}
