import java.util.Scanner;

// Song Class
class Song {
    private String title;
    private String artist;
    private String album;
    private double duration;

    public Song(String title, String artist, String album, double duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public double getDuration() {
        return duration;
    }

    public void play() {
        System.out.println("Playing: " + title + " - " + artist);
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Artist: " + artist);
        System.out.println("Album: " + album);
        System.out.println("Duration: " + duration + " minutes");
    }
}

// Playlist Class
class Playlist {
    private Song[] songs;
    private int count;

    public Playlist(int capacity) {
        songs = new Song[capacity];
        count = 0;
    }

    // Add a song to the playlist
    public void addSong(Song song) {
        if (count < songs.length) {
            songs[count] = song;
            count++;
            System.out.println("Song added to the playlist!");
        } else {
            System.out.println("Playlist is full. Cannot add more songs.");
        }
    }

    // Remove a song by title
    public void removeSong(String title) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (songs[i].getTitle().equalsIgnoreCase(title)) {
                // Shift remaining songs to fill the gap
                for (int j = i; j < count - 1; j++) {
                    songs[j] = songs[j + 1];
                }
                songs[count - 1] = null;
                count--;
                found = true;
                System.out.println("Song removed from the playlist!");
                break;
            }
        }
        if (!found) {
            System.out.println("Song not found in the playlist.");
        }
    }

    // List all songs
    public void listSongs() {
        if (count == 0) {
            System.out.println("Playlist is empty.");
        } else {
            System.out.println("Playlist:");
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + songs[i].getTitle() + " - " + songs[i].getArtist());
            }
        }
    }

    // Play a song by title
    public void playSong(String title) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (songs[i].getTitle().equalsIgnoreCase(title)) {
                songs[i].play();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Song not found in the playlist.");
        }
    }
}


public class upload {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist(100); // Capacity of 100 songs

        boolean running = true;

        while (running) {
            System.out.println("\nMusic Playlist Manager");
            System.out.println("""
                    1. Add Song
                    2. Remove Song
                    3. List Songs
                    4. Play Song
                    5. Exit""");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter song title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter artist name: ");
                    String artist = sc.nextLine();
                    System.out.print("Enter album name: ");
                    String album = sc.nextLine();
                    System.out.print("Enter song duration (in minutes): ");
                    double duration = sc.nextDouble();
                    sc.nextLine(); // Consume newline

                    Song song = new Song(title, artist, album, duration);
                    playlist.addSong(song);
                }
                case 2 -> {
                    System.out.print("Enter title of the song to remove: ");
                    String title = sc.nextLine();
                    playlist.removeSong(title);
                }
                case 3 -> {
                    playlist.listSongs();
                }
                case 4 -> {
                    System.out.print("Enter title of the song to play: ");
                    String title = sc.nextLine();
                    playlist.playSong(title);
                }
                case 5 ->{
                    System.out.println("Exiting Music Playlist Manager...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}
