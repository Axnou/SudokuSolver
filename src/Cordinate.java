public class Cordinate {
    
    private int y;
    private int x;

    public Cordinate(int givenY, int givenX) {
        this.y = givenY;
        this.x = givenX;
    }

    //palauttaa sudokutaulukon koordinaatin y (rivin)
    public int getY() {
        return y;
    }

    //palauttaa sudokutaulukon koordinaatin x (sarake)
    public int getX() {
        return x;
    }
}
