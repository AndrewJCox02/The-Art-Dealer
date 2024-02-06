public class Main {
    public static void main(String[] args) {
        Deck newDeck = new Deck();
        for (int i = 0; i < 4; i++) {
            System.out.println(newDeck.draw().toString());
        }


    }
}