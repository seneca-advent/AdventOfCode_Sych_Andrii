package Task02;

public class Cubes {
    private final int greenCubes;
    private final int blueCubes;
    private final int redCubes;
    Cubes(int greenCubes, int blueCubes, int redCubes) {
        this.blueCubes = blueCubes;
        this.greenCubes = greenCubes;
        this.redCubes = redCubes;
    }

    public int getGreenCubes() {
        return greenCubes;
    }
    public int getRedCubes() {
        return redCubes;
    }
    public int getBlueCubes() {
        return blueCubes;
    }
}
