import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Animal {
    String species;
    List<String> commands;
    String type;

    Animal(String species, String type) {
        this.species = species;
        this.type = type;
        this.commands = new ArrayList<>();
    }

    void addCommand(String command) {
        this.commands.add(command);
    }

    @Override
    public String toString() {
        return species + " знает команды: " + String.join(", ", commands);
    }
}

class Counter {
    int count = 0;
    boolean open = true;

    void add() {
        if (!open) throw new IllegalStateException("Счетчик не открыт.");
        count++;
    }

    public void close() {
        open = false;
    }
}

public class AnimalRegistry {
    List<Animal> animals = new ArrayList<>();
    Counter counter = new Counter();

    void addAnimal(String species, String type) {
        if (species == null || type == null)
            throw new IllegalArgumentException("Все поля должны быть заполнены.");

        animals.add(new Animal(species, type));
        counter.add();
        counter.close();
    }

    void trainAnimal(String species, String command) {
        for (Animal animal : animals) {
            if (animal.species.equals(species)) {
                animal.addCommand(command);
                System.out.println(species + " выучил " + command + ".");
                return;
            }
        }
        System.out.println("Животное вида " + species + " не найдено.");
    }

    void listAnimals() {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            AnimalRegistry registry = new AnimalRegistry();

            while (true) {
                System.out.println("1. Добавить новое животное");
                System.out.println("2. Обучить животное");
                System.out.println("3. Показать всех животных");
                System.out.println("4. Выход");
                System.out.print("Выберите действие: ");

                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        System.out.print("Введите вид: ");
                        String species = scanner.nextLine();
                        System.out.print("Введите тип (Домашний/Вьючный): ");
                        String type = scanner.nextLine();
                        registry.addAnimal(species, type);
                        break;
                    case "2":
                        System.out.print("Введите вид: ");
                        species = scanner.nextLine();
                        System.out.print("Введите команду: ");
                        String command = scanner.nextLine();
                        registry.trainAnimal(species, command);
                        break;
                    case "3":
                        registry.listAnimals();
                        break;
                    case "4":
                        return;
                    default:
                        System.out.println("Неверный выбор");
                }
            }
        }
    }
}