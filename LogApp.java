import java.io.*;
import java.util.*;

public class LogApp {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Authenticate the user with PIN
        if (!authenticatePin()) {
            return;
        }

        // Load existing log entries
        Map<Integer, Map<String, String>> log = loadLog();
        int entryCounter = log.keySet().stream().max(Integer::compare).orElse(0) + 1;

        // Project Raziel

        // Display a random greeting
        System.out.println(getGreeting());

        // Main menu loop
        while (true) {
            System.out.println("\nHow can I help you?");
            System.out.println("1. Add Log Entry");
            System.out.println("2. Update Log Entry");
            System.out.println("3. Delete Log Entry");
            System.out.println("4. Find Log Entry");
            System.out.println("5. List All Log Entries");
            System.out.println("6. Reset PIN");
            System.out.println("7. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEntry(log, entryCounter);
                    entryCounter++;
                    break;
                case "2":
                    updateEntry(log);
                    break;
                case "3":
                    deleteEntry(log);
                    break;
                case "4":
                    findEntry(log);
                    break;
                case "5":
                    listEntries(log);
                    break;
                case "6":
                    resetPin();
                    break;
                case "7":
                    System.out.println("Exiting the program.");
                    saveLog(log);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Generate a random greeting
    private static String getGreeting() {
        String[] greetings = {
                "Hello! Welcome to your personal log.",
                "Hi there! Ready to jot down some thoughts?",
                "Hey! Let's get started with your logging journey.",
                "Hello! What's on your mind today?"
        };
        return greetings[new Random().nextInt(greetings.length)];
    }

    // Load log entries from log.txt
    private static Map<Integer, Map<String, String>> loadLog() {
        Map<Integer, Map<String, String>> log = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4);
                int entryNumber = Integer.parseInt(parts[0]);
                Map<String, String> entry = new HashMap<>();
                entry.put("date", parts[1]);
                entry.put("time", parts[2]);
                entry.put("content", parts[3]);
                log.put(entryNumber, entry);
            }
        } catch (IOException e) {
            // If the file doesn't exist, return an empty log
        }
        return log;
    }

    // Save log entries to log.txt
    private static void saveLog(Map<Integer, Map<String, String>> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
            for (Map.Entry<Integer, Map<String, String>> entry : log.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue().get("date") + "," + entry.getValue().get("time")
                        + "," + entry.getValue().get("content") + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save PIN to pin.txt
    private static void savePin(String pin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pin.txt"))) {
            writer.write(pin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load PIN from pin.txt
    private static String loadPin() {
        try (BufferedReader reader = new BufferedReader(new FileReader("pin.txt"))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    // Set up a new PIN
    private static void setPin() {
        while (true) {
            System.out.print("Set up a 4-digit PIN: ");
            String pin = scanner.nextLine();
            if (pin.matches("\\d{4}")) {
                savePin(pin);
                System.out.println("PIN set up successfully.");
                break;
            } else {
                System.out.println("Please enter a valid 4-digit PIN consisting of numbers only.");
            }
        }
    }

    // Reset the PIN
    private static void resetPin() {
        System.out.println("Resetting PIN...");
        setPin();
    }

    // Authenticate the user with PIN
    private static boolean authenticatePin() {
        String pin = loadPin();
        if (pin == null) {
            System.out.println("No PIN set up. Please set up a PIN.");
            setPin();
            return authenticatePin();
        } else {
            int attempts = 3;
            while (attempts > 0) {
                System.out.print("Enter your 4-digit PIN: ");
                String enteredPin = scanner.nextLine();
                if (enteredPin.equals(pin)) {
                    System.out.println("PIN authenticated.");
                    return true;
                } else {
                    attempts--;
                    System.out.println("Incorrect PIN. " + attempts + " attempts remaining.");
                }
            }
            System.out.println("Too many incorrect attempts. Exiting.");
            return false;
        }
    }

    // Add a new log entry
    private static void addEntry(Map<Integer, Map<String, String>> log, int entryNumber) {
        System.out.print("Enter date (MM-DD-YYYY): ");
        String date = scanner.nextLine();
        System.out.print("Enter time (HH:MM AM/PM): ");
        String time = scanner.nextLine();
        System.out.print("Enter log content: ");
        String content = scanner.nextLine();
        log.put(entryNumber, Map.of("date", date, "time", time, "content", content));
        System.out.println("Log entry #" + entryNumber + " for '" + date + " " + time + "' added successfully.");
        saveLog(log);
    }

    // Update an existing log entry
    private static void updateEntry(Map<Integer, Map<String, String>> log) {
        System.out.print("Enter entry number to update: ");
        int entryNumber = Integer.parseInt(scanner.nextLine());
        if (log.containsKey(entryNumber)) {
            System.out.print("Enter new date (MM-DD-YYYY): ");
            String date = scanner.nextLine();
            System.out.print("Enter new time (HH:MM AM/PM): ");
            String time = scanner.nextLine();
            System.out.print("Enter new log content: ");
            String content = scanner.nextLine();
            log.put(entryNumber, Map.of("date", date, "time", time, "content", content));
            System.out.println("Log entry #" + entryNumber + " for '" + date + " " + time + "' updated successfully.");
            saveLog(log);
        } else {
            System.out.println("Entry with number '" + entryNumber + "' not found.");
        }
    }

    // Delete an existing log entry
    private static void deleteEntry(Map<Integer, Map<String, String>> log) {
        System.out.print("Enter entry number to delete: ");
        int entryNumber = Integer.parseInt(scanner.nextLine());
        if (log.containsKey(entryNumber)) {
            log.remove(entryNumber);
            System.out.println("Log entry #" + entryNumber + " deleted successfully.");
            saveLog(log);
        } else {
            System.out.println("Entry with number '" + entryNumber + "' not found.");
        }
    }

    // Find and display an existing log entry
    private static void findEntry(Map<Integer, Map<String, String>> log) {
        System.out.print("Enter entry number to find: ");
        int entryNumber = Integer.parseInt(scanner.nextLine());
        if (log.containsKey(entryNumber)) {
            Map<String, String> entry = log.get(entryNumber);
            System.out.println("#" + entryNumber + " - Date: " + entry.get("date") + " Time: " + entry.get("time")
                    + " - Word Count: " + entry.get("content").split("\\s+").length);
            System.out.println(entry.get("content"));
            System.out.print("Confirm this entry? (yes/no): ");
            String confirm = scanner.nextLine().toLowerCase();
            if (confirm.equals("yes") || confirm.equals("y")) {
                return;
            }
        } else {
            System.out.println("Entry with number '" + entryNumber + "' not found.");
        }
    }

    // List all log entries
    private static void listEntries(Map<Integer, Map<String, String>> log) {
        if (log.isEmpty()) {
            System.out.println("No log entries found.");
        } else {
            System.out.println("Log Entries:");
            for (Map.Entry<Integer, Map<String, String>> entry : log.entrySet()) {
                Map<String, String> logEntry = entry.getValue();
                int wordCount = logEntry.get("content").split("\\s+").length;
                System.out.println("#" + entry.getKey() + " - Date: " + logEntry.get("date") + " Time: "
                        + logEntry.get("time") + " - Word Count: " + wordCount);
            }
        }
    }
}
