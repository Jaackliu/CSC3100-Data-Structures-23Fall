package codes;
import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;
import java.util.Comparator;

class Player implements Comparable<Player> {
    int floor;
    int HP;
    String direction;
    int order;

    public Player(int floor, int HP, String direction, int order) {
        this.floor = floor;
        this.HP = HP;
        this.direction = direction;
        this.order = order;
    }

    @Override
    public int compareTo(Player other) {
        return this.floor - other.floor;
    }
}

public class BattleGame {

    public static Stack<Player> Floors = new Stack<Player>();
    public static Stack<Player> fightWaitlist = new Stack<Player>();

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int numberOfPlayers = sc.nextInt();
        Player[] floorLevels = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            int floor = sc.nextInt();
            int HP = sc.nextInt();
            String direction = sc.next();
            Player player = new Player(floor, HP, direction, i);
            floorLevels[i] = player;
        }

        Arrays.sort(floorLevels);

        for (int i = 0; i < numberOfPlayers; i++) {
            Floors.push(floorLevels[i]);
        }

        while (! Floors.isEmpty()) {
            Player initialPlayer = Floors.pop();
            if (initialPlayer.direction.equals("D")) {
                fightWaitlist.push(initialPlayer);
            } else if (initialPlayer.direction.equals("U")) {
                if (! fightWaitlist.isEmpty() && fightWaitlist.peek().direction.equals("D")) {
                    Player winner = fight(initialPlayer, fightWaitlist.pop());
                    if (winner.HP != 0) {
                        Floors.push(winner);
                    }
                } else {
                    fightWaitlist.push(initialPlayer);
                }
            }
        }

        int size = fightWaitlist.size();
        Player[] finalFloors = new Player[size];
        for (int i = 0; i < size; i++) {
            finalFloors[i] = fightWaitlist.pop();
        }

        Arrays.sort(finalFloors, Comparator.comparingInt(Player -> Player.order));

        for (int i = 0; i < size; i++) {
            System.out.println(finalFloors[i].HP);
        }
    }

    public static Player fight(Player player_1, Player player_2) {
        if (player_1.HP > player_2.HP) {
            return new Player(player_1.floor, player_1.HP - 1, player_1.direction, player_1.order);
        } else if (player_1.HP < player_2.HP) {
            return new Player(player_2.floor, player_2.HP - 1, player_2.direction, player_2.order);
        } else {
            return new Player(player_1.floor, 0, player_1.direction, player_1.order);
        }
    }

}
