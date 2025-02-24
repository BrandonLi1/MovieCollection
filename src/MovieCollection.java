import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Scanner;
import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> collection = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);

    public MovieCollection() {
        Start();
    }

    public void loadMovieData() {
        ArrayList<String> adder = new ArrayList<>();
        try {
            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String Title = splitData[0];
                String Cast = splitData[1];
                String Director = splitData[2];
                String Overview = splitData[3];
                String Runtime = splitData[4];
                String Rating = splitData[5];
                Movie movie = new Movie(Title, Cast, Director, Overview, Runtime, Rating);
                collection.add(movie);
            }
            fileScanner.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void menu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public void searchTitles() {
        ArrayList<String> movieList = new ArrayList<>();
        System.out.print("Enter a title search term: ");
        String title = scan.nextLine();
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getTitle().contains(title)) {
                movieList.add(collection.get(i).getTitle());
            }
        }
        if (movieList.isEmpty()) {
            System.out.println("No movie titles match that search term!");
            return;
        }
        selectionSortWordList(movieList);
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println(i + 1 + ". " + movieList.get(i));
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int num = scan.nextInt()-1;
        scan.nextLine();
        String temp = movieList.get(num);
        System.out.println();
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getTitle().equals(temp)) {
                System.out.println("Title: " + collection.get(i).getTitle());
                System.out.println("Runtime: " + collection.get(i).getRuntime());
                System.out.println("Directed by: " + collection.get(i).getDirector());
                System.out.println("Cast: " + collection.get(i).getCast());
                System.out.println("Overview: " + collection.get(i).getOverview());
                System.out.println("User rating: " + collection.get(i).getRating());
                System.out.println();
                return;
            }
        }
    }

    public void searchCast() {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> movieList = new ArrayList<>();
        ArrayList<String> movies = new ArrayList<>();
        System.out.print("Enter a person to search for: ");
        String name = scan.nextLine();
        for (int i = 0; i < collection.size(); i++) {
            names.add(Arrays.toString(collection.get(i).getCast().split("\\|")));
        }
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).contains(name) && !movieList.contains(name)) {
                movieList.add(name);
            }
        }
        if (movieList.isEmpty()) {
            System.out.println("Nobody matches that search term!");
            return;
        }
        selectionSortWordList(movieList);
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println(i + 1 + ". " + movieList.get(i));
        }
        System.out.println("Which would you like to see all movies for?");
        System.out.print("Enter number: ");
        int num = scan.nextInt()-1;
        scan.nextLine();
        String temp = movieList.get(num);
        System.out.println();
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getCast().contains(temp)) {
                movies.add(collection.get(i).getTitle());
            }
        }
        selectionSortWordList(movies);
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(i + 1 + ". " + movieList.get(i));
        }
        System.out.println("Which movie would you like to learn more about? ");
        System.out.print("Enter number: ");
        num=scan.nextInt()-1;
        scan.nextLine();
        temp = movies.get(num);
        System.out.println();

        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getTitle().equals(temp)) {
                System.out.println("Title: " + collection.get(i).getTitle());
                System.out.println("Runtime: " + collection.get(i).getRuntime());
                System.out.println("Directed by: " + collection.get(i).getDirector());
                System.out.println("Cast: " + collection.get(i).getCast());
                System.out.println("Overview: " + collection.get(i).getOverview());
                System.out.println("User rating: " + collection.get(i).getRating());
                System.out.println();
                return;
            }
        }
    }

    private void selectionSortWordList(ArrayList<String> words) {
        int minIndex;
        for (int i = 0; i < words.size(); i++) {
            String min = words.get(i);
            for (int j = i; j < words.size(); j++) {
                if (words.get(j).compareTo(words.get(i)) < 0) {
                    min = words.get(j);
                    minIndex = j;
                    words.set(minIndex, words.get(i));
                    words.set(i, min);
                }
            }
        }

    }

    private void Start () {
        loadMovieData();
        menu();
    }
}
