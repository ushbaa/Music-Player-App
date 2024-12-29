import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

	private static ArrayList<Album> albums = new ArrayList<>();

	public static void main(String[] args) {

		Album album = new Album("Album1", "AC/BC");

		album.addSong("TNT", 4.5);
		album.addSong("Highway to Hell", 3.5);
		album.addSong("Thunder Struck", 5.0);

		albums.add(album);
		
		album = new Album("Album2", "Eminem");

		album.addSong("Rap", 4.5);
		album.addSong("Not Afraid", 3.5);
		album.addSong("Lose yourself", 5.0);

		albums.add(album);

		LinkedList<Song> playlist_1 = new LinkedList<>();
		albums.get(0).addToPlaylist("TNT", playlist_1);
		albums.get(0).addToPlaylist("Highway to Hell", playlist_1);
		albums.get(1).addToPlaylist("Rap", playlist_1);
		albums.get(1).addToPlaylist("Lose yourself", playlist_1);

		play(playlist_1);

	}

	private static void play(LinkedList<Song> playList) {
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		boolean forward = true;

		ListIterator<Song> listIterator = playList.listIterator();

		if (playList.size() == 0) {

			System.out.println("This playlist have no songs");
		} else {

			System.out.println("Now Playing" + listIterator.next().toString());
			printMenu();
		}
		while (!quit) {
			int action = sc.nextInt();
			sc.nextLine();

			switch (action) {
			case 0:
				System.out.println("Playlist complete");
				quit = true;
				break;
			case 1:   //next song
				
				if (!forward) {
					if (listIterator.hasNext()) {

					}
					forward = true;
				}
				if (listIterator.hasNext()) {
					System.out.println("Now Playing" + listIterator.next().toString());
				} else {
					System.out.println("No song available, reached to the end of the list");
					forward = false;
				}
				break;
			case 2:   //previous song
				
				if (forward) {
					if (listIterator.hasPrevious()) {
						listIterator.previous();
					}
					forward = false;

				}
				if (listIterator.hasPrevious()) {
					System.out.println("Now Playing" + listIterator.previous().toString());
				} else {
					System.out.println("We are the first song");
					forward = false;
				}
				break;

			case 3:   //current song
				if (forward) {

					if (listIterator.hasPrevious()) {
						System.out.println("Now Playing" + listIterator.previous().toString());
						forward = false;
					} else {
						System.out.println("We are at the start of the list");
					}
				} else {
					if (listIterator.hasNext()) {
						System.out.println("Now Playing" + listIterator.next().toString());
						forward = true;
					} else {
						System.out.println("We have reached to the end of list");
					}
				}
				break;

			case 4: //list of all songs
				
				printList(playList);
				break;

			case 5:  //all available songs
				printMenu();   
				
			case 6:    //delete current song
				
				if (playList.size() > 0) {
					listIterator.remove();
					if (listIterator.hasNext()) {
						System.out.println("Now Playing" + listIterator.next().toString());
					} else {
						if (listIterator.hasPrevious())
							System.out.println("Now Playing" + listIterator.previous().toString());
						forward = true;
					}
				}
			}
		}
	}

	private static void printMenu() {
		System.out.println("Available options\n Press:");
		
		System.out.println("0 - to quit\n"
		        + "1 - to play next song\n" 
				+ "2 - to play previous song\n"
				+ "3 - to replay the current song\n" 
		        + "4 - list of all songs\n"
				+ "5 - to print all available options\n" 
		        + "6 - Delete current song");

	}

	private static void printList(LinkedList<Song> playList) {

		Iterator<Song> iterator = playList.iterator();
		System.out.println("------------------------");

		while (iterator.hasNext()) {
			System.out.println(iterator.next());

		}
		System.out.println("-------------------------");
	}
}
