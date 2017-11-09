/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

import nu.smashit.utils.Tools;

/**
 *
 * @author jodus
 */
class BrickRow {

    public final Brick[] row;
    private final int numberOfNormalBricks;
    private final int numberOfPowerups;
    private final int numberOfPowerdowns;
    private final int numberOfEmptyPlaces;
    private final int y;

    public BrickRow(int numberOfNormalBricks, int numberOfPowerups, int numberOfPowerdowns, int numberOfEmptyPlaces, int y) {
        this.numberOfNormalBricks = numberOfNormalBricks;
        this.numberOfPowerups = numberOfPowerups;
        this.numberOfPowerdowns = numberOfPowerdowns;
        this.numberOfEmptyPlaces = numberOfEmptyPlaces;
        this.y = y;

        row = new Brick[getNumberOfTotalPlaces()];
        fillRow();
    }

    public Brick getBrick(int colIndex) {
        return row[colIndex];
    }

    public int getNumberOfTotalPlaces() {
        return getNumberOfEmptyPlaces() + getNumberOfTotalBricks();
    }

    public int getNumberOfTotalBricks() {
        return numberOfNormalBricks + numberOfPowerups + numberOfPowerdowns;
    }

    public int getNumberOfNormalBricks() {
        return numberOfNormalBricks;
    }

    public int getNumberOfPowerups() {
        return numberOfPowerups;
    }

    public int getNumberOfPowerdowns() {
        return numberOfPowerdowns;
    }

    public int getNumberOfEmptyPlaces() {
        return numberOfEmptyPlaces;
    }

    private void fillRow() {
        fillType(numberOfNormalBricks, BrickType.BrickSort.NORMAL);
        fillType(numberOfPowerups, BrickType.BrickSort.POWERUP);
        fillType(numberOfPowerdowns, BrickType.BrickSort.POWERDOWN);
    }

    private void fillType(int number, BrickType.BrickSort sort) {
        int block_width = (int) (GameCanvas.WIDTH / getNumberOfTotalPlaces());
        int place;

        for (int bricknr = 0; bricknr < number; bricknr++) {
            do {
                place = Tools.getRandomBetween(0, getNumberOfTotalPlaces() - 1);
            } while (row[place] != null);

            int x = place * block_width;
            row[place] = new Brick(x, y, block_width, Brick.HEIGHT, getRandomBrickType(sort));
        }
    }

    private BrickType getRandomBrickType(BrickType.BrickSort sort) {
        //TODO uit db halen
        return new BrickType("test", sort);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Brick row1 : row) {
            if (row1 == null) {
                sb.append("*.............*");
            } else {
                sb.append(row1.toString());
            }
        }
        return sb.toString();
    }
}
