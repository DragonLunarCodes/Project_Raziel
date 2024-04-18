# Project_Raziel
# Personal Log Application

## Overview

This is a simple Java-based application for maintaining a personal log. Users can create, update, delete, find, and list log entries. The application also includes PIN authentication to ensure privacy and security of the logs.

## Features

- **Add Log Entry**: Create a new log entry with date, time, and content.
- **Update Log Entry**: Modify an existing log entry.
- **Delete Log Entry**: Remove a log entry.
- **Find Log Entry**: Search for a specific log entry.
- **List All Log Entries**: Display all log entries with their details.
- **PIN Authentication**: Protect the logs with a 4-digit PIN.

## Prerequisites

- Java Development Kit (JDK) installed
- Text editor or IDE (e.g., Visual Studio Code, IntelliJ IDEA)
- Terminal or command prompt

## Getting Started

1. **Clone the Repository**
    ```bash
    git clone https://github.com/DragonLunarCodes/Project_Raziel.git
    ```
    or download the ZIP file and extract it.

2. **Navigate to the Project Directory**
    ```bash
    cd log-app
    ```

3. **Compile and Run the Application**
    ```bash
    javac LogApp.java
    java LogApp
    ```

## Usage

### Main Menu

When you run the application, you'll see a main menu with the following options:

1. **Add Log Entry**: Add a new log entry.
2. **Update Log Entry**: Update an existing log entry.
3. **Delete Log Entry**: Delete a log entry.
4. **Find Log Entry**: Search for a specific log entry.
5. **List All Log Entries**: Display all log entries.
6. **Reset PIN**: Change the 4-digit PIN.
7. **Exit**: Close the application.

### PIN Authentication

- When you first run the application, you'll be prompted to set up a 4-digit PIN.
- For subsequent runs, you'll need to enter this PIN to access the logs.

### Adding a Log Entry

- Enter the date in the format `MM-DD-YYYY`.
- Enter the time in the format `HH:MM AM/PM`.
- Enter the log content.

### Updating a Log Entry

- Enter the entry number of the log you want to update.
- Follow the prompts to enter the new date, time, and content.

### Deleting a Log Entry

- Enter the entry number of the log you want to delete.

### Finding a Log Entry

- Enter the entry number of the log you want to find.
- The application will display the log entry details and ask for confirmation.

### Listing All Log Entries

- The application will display all log entries with their details, including word count.

