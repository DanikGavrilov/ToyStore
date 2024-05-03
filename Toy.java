import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public static void main(String[] args){

    class Toy{
        private int id;
        private String name;
        private double weight;

        public Toy(int id, String name, double weight){
            this.id = id;
            this.name = name;
            this.weight = weight;
        }

        public int getId(){
            return id;
        }

        public String getName(){
            return name;
        }

        public double getWeight(){
            return weight;
        }
    }

    String line = "1, Doll, 1.5";
    String[] parts = line.split(",");
    int id = Integer.parseInt(parts[0]);
    String name = parts[1].trim();
    double weight = Double.parseDouble(parts[2]);

    Toy[] toys = new Toy[3];
    toys[0] = new Toy(1, "Doll", 1.5);
    toys[1] = new Toy(2, "Car", 1.0);
    toys[2] = new Toy(3, "Bear", 2.0);

    PriorityQueue queue = new PriorityQueue(Comparator.comparingDouble(Toy::getWeight));
        for (Toy toy : toys){
            queue.add(toy);
        }

    PriorityQueue totalQueue = new PriorityQueue<>(Comparator.comparingDouble(Toy::getWeight));
    totalQueue.addAll(queue);

    try (PrintWriter writer = new PrintWriter(new FileWriter("toys.txt"))){
        for (int i = 0; i < 10; i++){
            Toy toy = (Toy) totalQueue.peek();
            writer.printf("%d. %s (%.2f)", (i + 1), toy.getName(), toy.getWeight());
        }
    } 
    catch (IOException ex){
        System.err.println("Error writng to file: " +ex.getMessage());
    }

}