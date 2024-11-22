package tz.quests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
            1 // Number of test cases
            4 // Number of cities
            gdansk // City name
            2 // Number of neighbors
            2 1 // Neighbor index and cost
            3 3 // Neighbor index and cost
            bydgoszcz // City name
            3 // Number of neighbors
            1 1 // Neighbor index and cost
            3 1 // Neighbor index and cost
            4 4 // Neighbor index and cost
            torun // City name
            3 // Number of neighbors
            1 3 // Neighbor index and cost
            2 1 // Neighbor index and cost
            4 1 // Neighbor index and cost
            warszawa // City name
            2 // Number of neighbors
            2 4 // Neighbor index and cost
            3 1 // Neighbor index and cost
            2 // Number of paths to find
            gdansk warszawa // Source and destination
            bydgoszcz warszawa // Source and destination
 */

public class SecondQuest {
    public static void main(String[] args) {
        /*
         Instructions for the input data are written at the
         top and approximate numbers and names are placed,
         complete according to the instructions
         */
        String inputData = """
            1
            4
            gdansk
            2
            2 1
            3 3
            bydgoszcz
            3
            1 1
            3 1
            4 4
            torun
            3
            1 3
            2 1
            4 1
            warszawa
            2
            2 4
            3 1
            2
            gdansk warszawa
            bydgoszcz warszawa
            """;

        // Split input into lines for processing
        String[] lines = inputData.split("\\n");
        int currentLine = 0;

        // Read the number of test cases
        int t = Integer.parseInt(lines[currentLine++].trim());

        // Iterate through all test cases
        for (int test = 0; test < t; test++) {
            // Read the number of cities
            int n = Integer.parseInt(lines[currentLine++].trim());

            // Map to store city names and their indices
            Map<String, Integer> cityIndexMap = new HashMap<>();
            String[] cityNames = new String[n];

            // Matrix to represent graph adjacency
            int[][] adjMatrix = new int[n][n];

            // Initialize adjacency matrix with maximum values
            for (int i = 0; i < n; i++) {
                Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
                adjMatrix[i][i] = 0; // Cost to self is always 0
            }

            // Input city data
            for (int i = 0; i < n; i++) {
                String cityName = lines[currentLine++].trim();
                cityIndexMap.put(cityName, i);
                cityNames[i] = cityName;

                int p = Integer.parseInt(lines[currentLine++].trim());
                for (int j = 0; j < p; j++) {
                    String[] connection = lines[currentLine++].trim().split("\\s+");
                    int neighborIndex = Integer.parseInt(connection[0]) - 1;
                    int cost = Integer.parseInt(connection[1]);
                    adjMatrix[i][neighborIndex] = cost;
                }
            }

            // Floyd-Warshall algorithm to calculate shortest paths
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                dist[i] = Arrays.copyOf(adjMatrix[i], n);
            }

            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (dist[i][k] != Integer.MAX_VALUE &&
                                dist[k][j] != Integer.MAX_VALUE &&
                                dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }

            // Read the number of queries
            int r = Integer.parseInt(lines[currentLine++].trim());

            // Process each query
            for (int query = 0; query < r; query++) {
                String[] route = lines[currentLine++].trim().split("\\s+");
                String city1 = route[0];
                String city2 = route[1];

                int start = cityIndexMap.get(city1);
                int end = cityIndexMap.get(city2);

                // Print the result of the query
                int cost = dist[start][end];
                System.out.println(cost == Integer.MAX_VALUE ? "NO PATH" : cost);
            }

            // Skip empty line separating test cases
            if (currentLine < lines.length && lines[currentLine].trim().isEmpty()) {
                currentLine++;
            }
        }
    }
}
