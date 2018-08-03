package Data;

import Points.Point;

import java.util.*;

public class PointData {
    private static final int PARTITION_SIZE = 16;
    Point[] points;
    int[] weight;
    HashMap<Point, Integer> partition;
    List<List<Point>> partitions;
    int[] numberOfPartitions;

    PointData(Point[] points, int[] weight, int[] dimensions) {
        this.points = points;
        this.weight = weight;
        numberOfPartitions = new int[dimensions.length];
        int totalPartitions = 1;
        for (int i = 0; i < dimensions.length; i++) {
            numberOfPartitions[i] = (dimensions[i] - 1) / PARTITION_SIZE + 1;
            totalPartitions *= numberOfPartitions[i];
        }
        partitions = new ArrayList<>();
        for (int i = 0; i < totalPartitions; i++) {
            partitions.add(new ArrayList<>());
        }
        partition = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            partition.put(points[i],points[i].getPartitionNumber(numberOfPartitions, PARTITION_SIZE));
            partitions.get(partition.get(points[i])).add(points[i]);
        }
    }

    Set<Point> getLocalPoints(int partition, int buffer) {
        int[] dimension = new int[numberOfPartitions.length];
        int[] min = new int[numberOfPartitions.length];
        int[] max = new int[numberOfPartitions.length];
        int numberOfReturnedPartions = 1;
        for (int i = 0; i < numberOfPartitions.length; i++) {
            dimension[i] = partition % numberOfPartitions[i];
            partition /= numberOfPartitions[i];
            min[i] = dimension[i] > buffer ? dimension[i] - buffer : 0;
            max[i] = dimension[i] + buffer < numberOfPartitions[i] ? dimension[i] + buffer + 1 : numberOfPartitions[i];
            numberOfReturnedPartions *= max[i] - min[i];
        }
        Set<Point> localPoints = new HashSet<>();
        for (int i = 0; i < numberOfReturnedPartions; i++) {
            int partitionIndex = i;
            int partitionNumber = 0;
            for (int j = 0; j < dimension.length; j++) {
                if (j > 0) {
                    partitionNumber *= numberOfPartitions[j-1];
                }
                partitionNumber += min[j];
                partitionNumber += partitionIndex % (max[j] - min[j]);
                partitionIndex /= (max[j] - min[j]);
            }
            localPoints.addAll(partitions.get(partitionNumber));
        }
        return localPoints;
    }
}
