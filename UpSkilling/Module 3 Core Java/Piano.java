public class Piano implements Playable {
    public void play() {
        System.out.println("Playing Piano: Ding ding ding!");
    }
    public static void main(String[] args) {
        Guitar guitar = new Guitar();
        guitar.play();
        
        Piano piano = new Piano();
        piano.play();
    }
}
