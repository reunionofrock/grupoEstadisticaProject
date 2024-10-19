

import javax.swing.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dao extends JFrame {

    static double calculateMean(List<Double> data) {
        double sum = 0;
        for (double num : data) {
            sum += num;
        }
        return sum / data.size();
    }

    static double calculateMedian(List<Double> data) {
        data.sort(Double::compareTo);
        int size = data.size();
        if (size % 2 == 0) {
            return (data.get(size / 2 - 1) + data.get(size / 2)) / 2.0;
        } else {
            return data.get(size / 2);
        }
    }

    static double calculateMode(List<Double> data) {
        double mode = data.get(0);
        int maxCount = 0;
        for (int i = 0; i < data.size(); i++) {
            double value = data.get(i);
            int count = 1;
            for (int j = 0; j < data.size(); j++) {
                if (data.get(j) == value) {
                    count++;
                }
            }
            if (count > maxCount) {
                mode = value;
                maxCount = count;
            }
        }
        return mode;
    }

    static double calculateVariance(List<Double> data, double mean) {
        double sum = 0;
        for (double num : data) {
            sum += Math.pow(num - mean, 2);
        }
        return sum / data.size();
    }
    
     public double calculateGroupedMean(List<Double> data) {
        Map<Double, Integer> frequencyMap = getFrequencyMap(data);
        double sum = 0;
        int totalFrequency = 0;

        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            sum += entry.getKey() * entry.getValue();
            totalFrequency += entry.getValue();
        }

        return sum / totalFrequency;
    }

    public double calculateGroupedMedian(List<Double> data) {
        Map<Double, Integer> frequencyMap = getFrequencyMap(data);
        int totalFrequency = 0;

        for (int frequency : frequencyMap.values()) {
            totalFrequency += frequency;
        }

        int medianPosition = totalFrequency / 2;
        int cumulativeFrequency = 0;
        double median = 0;

        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            cumulativeFrequency += entry.getValue();
            if (cumulativeFrequency >= medianPosition) {
                median = entry.getKey();
                break;
            }
        }

        return median;
    }

    public double calculateGroupedMode(List<Double> data) {
        Map<Double, Integer> frequencyMap = getFrequencyMap(data);
        double mode = 0;
        int maxFrequency = 0;

        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mode = entry.getKey();
            }
        }

        return mode;
    }

    public double calculateGroupedVariance(List<Double> data, double mean) {
        Map<Double, Integer> frequencyMap = getFrequencyMap(data);
        double sum = 0;
        int totalFrequency = 0;

        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            sum += Math.pow(entry.getKey() - mean, 2) * entry.getValue();
            totalFrequency += entry.getValue();
        }

        return sum / totalFrequency;
    }

    private Map<Double, Integer> getFrequencyMap(List<Double> data) {
        Map<Double, Integer> frequencyMap = new HashMap<>();

        for (double value : data) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }

        return frequencyMap;
    }

}
