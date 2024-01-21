package sr.unasat.bp24.hibernate.services;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class ExtraFeature {

	public static final String GREEN_BOLD = "\033[1;32m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_GREEN = "\u001B[32m";

	public static void processingAnimation(String message) {
		System.out.println();
		System.out.print(message);
		for(int i=0; i<3; i++) {
			System.out.print(".");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("   Done!");
	}

	public static void thankYou() {
		System.out.println();
		String str="Thank You...!";
		for(int i=0; i<str.length(); i++) {
			System.out.print(GREEN_BOLD+str.charAt(i));
			if(str.charAt(i)!=' ') {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e){}
			}
		}
		System.out.println(ANSI_RESET);
		System.out.println();
	}

	public static LocalDate selectDate() {
		Scanner scanner = new Scanner(System.in);

		// Get the year
		System.out.print("Enter the year: ");
		int year = scanner.nextInt();

		// Get the month
		Month selectedMonth = selectMonth();

		// Get the day
		System.out.print("Enter the day: ");
		int day = scanner.nextInt();

		// Create a LocalDate with the selected year, month, and day
		return LocalDate.of(year, selectedMonth, day);
	}

	public static Month selectMonth() {
		Scanner scanner = new Scanner(System.in);

		int selectedMonthIndex = -1;

		while (selectedMonthIndex < 0 || selectedMonthIndex >= Month.values().length) {
			System.out.println("Select a month:");
			for (int i = 0; i < Month.values().length; i++) {
				System.out.println((i + 1) + ". " + Month.values()[i].getDisplayName(TextStyle.FULL, Locale.ENGLISH));
			}

			try {
				selectedMonthIndex = scanner.nextInt() - 1;
			} catch (Exception e) {
				// Handle non-integer input
				scanner.nextLine(); // Consume invalid input
			}

			if (selectedMonthIndex < 0 || selectedMonthIndex >= Month.values().length) {
				System.out.println("Invalid selection. Please try again.");
			}
		}

		return Month.values()[selectedMonthIndex];
	}

}
