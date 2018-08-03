package Points;

import java.util.Arrays;

public class Point implements Comparable<Point> {
    private int[] coordinates;

    public Point(int... coordinates) {
        this.coordinates = coordinates;
    }

    Point diff(Point other) {
        assert coordinates.length == other.coordinates.length;
        int[] diff = new int[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            diff[i] = other.coordinates[i] - coordinates[i];
        }
        return new Point(diff);
    }

    public long distSquared(Point other) {
        assert coordinates.length == other.coordinates.length;
        int squared = 0;
        for (int i = 0; i < coordinates.length; i++) {
            int dist = other.coordinates[i] - coordinates[i];
            squared += dist * dist;
        }
        return squared;
    }

    public int getPartitionNumber(int[] numberOfPartitions, int partitionSize) {
        assert coordinates.length == numberOfPartitions.length;
        int partitionNumber = 0;
        for (int i = 0; i < coordinates.length; i++) {
            if (i != 0) {
                partitionNumber *= numberOfPartitions[i - 1];
            }
            partitionNumber += coordinates[i] / partitionSize;
        }
        return partitionNumber;
    }

    @Override
    public int compareTo(Point o) {
        return Arrays.compare(coordinates, o.coordinates);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Point && Arrays.equals(coordinates,((Point) o).coordinates);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }
}
