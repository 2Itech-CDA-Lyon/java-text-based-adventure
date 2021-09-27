package com.example;

import java.util.List;
import java.util.Scanner;

import com.example.entity.Room;
import com.example.repository.RoomRepository;

public class Editor {
    public static final int MENU_ROOM_LIST = 0;
    public static final int MENU_SINGLE_ROOM = 1;
    public static final int MENU_EDIT_ROOM = 2;

    public static void main( String[] args ) throws Exception
    {
        String userInput;
        Room currentRoom = new Room();
        Integer currentMenu = MENU_ROOM_LIST;
        RoomRepository repository = new RoomRepository();
        
        while (true) {
            List<Room> rooms = repository.findAll();
            // Affichage du menu
            // En fonction du menu dans lequel on se trouve actuellement
            System.out.println("");
            switch (currentMenu) {
                // Liste des lieux
                case MENU_ROOM_LIST:
                    System.out.println("0: Create new");
                    for (Room room : rooms) {
                        System.out.println(String.format("%d: %s", room.getId(), room.getName()));
                    }
                    break;

                // Menu d'un lieu particulier
                case MENU_SINGLE_ROOM:
                    System.out.println(currentRoom.getName());
                    System.out.println("1: Edit");
                    System.out.println("2: Delete");
                    break;

                // Modification d'un lieu
                case MENU_EDIT_ROOM:
                    System.out.println(String.format("1: [name] %s", currentRoom.getName()));
                    break;
            }

            // Attend une saisie utilisateur
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextLine();

            // Traite la saisie de l'utilisateur
            // En fonction du menu dans lequel on se trouve actuellement
            switch (currentMenu) {
                // Liste des lieux
                case MENU_ROOM_LIST:
                    if (userInput.equals("")) {
                        return;
                    }

                    try {
                        // Rècupère le lieu choisi par l'utilisateur
                        Integer roomId = Integer.parseInt(userInput);
                        // Si l'utilisateur a entré 0
                        if (roomId == 0) {
                            // Navigue dans le menu créer un lieu
                            currentRoom = new Room();
                            currentMenu = MENU_EDIT_ROOM;
                            break;
                        }

                        currentRoom = repository.findById(roomId);
                        // Si le lieu n'existe pas, affiche un message d'erreur
                        if (currentRoom == null) {
                            System.out.println("This room does not exist!");
                            break;
                        }
                        // Navigue dans le menu d'un lieu particulier
                        currentMenu = MENU_SINGLE_ROOM;
                    }
                    // Si l'utilisateur a rentré autre chose qu'un nombre
                    catch (NumberFormatException exception) {
                        System.out.println("You must enter a number!");
                    }
                    break;

                // Menu d'un lieu particulier
                case MENU_SINGLE_ROOM:
                    if (userInput.equals("")) {
                        currentMenu = MENU_ROOM_LIST;
                        break;
                    }
                    try {
                        // Rècupère le nombre entré par l'utilisateur
                        Integer option = Integer.parseInt(userInput);
                        // En fonction du nombre rentré par l'utilisateur
                        switch (option) {
                            // Si l'utlisateur a entré 1
                            case 1:
                                // Navigue vers le menu de modification d'un lieu
                                currentMenu = MENU_EDIT_ROOM;
                                break;

                            // Si l'utilisateur a entré 2
                            case 2:
                                // Supprime le lieu actuel
                                repository.delete(currentRoom);
                                // Navigue vers la liste des lieux
                                currentMenu = MENU_ROOM_LIST;
                                break;
                        }
                    }
                    // Si l'utilisateur a rentré autre chose qu'un nombre
                    catch (NumberFormatException exception) {
                        System.out.println("You must enter a number!");
                    }
                    break;

                // Modification d'un lieu
                case MENU_EDIT_ROOM:
                    if (userInput.equals("")) {
                        currentMenu = MENU_SINGLE_ROOM;
                        break;
                    }

                    try {
                        // Rècupère le nombre entré par l'utilisateur
                        Integer option = Integer.parseInt(userInput);
                        
                        switch (option) {
                            // Si l'utilisateur a entré 1
                            case 1:
                                // Change le nom du lieu
                                System.out.println("");
                                System.out.println("Enter new value:");
                                userInput = scanner.nextLine();
                                currentRoom.setName(userInput);
                                // Sauvegarde l'objet en base de données
                                repository.save(currentRoom);
                                break;
                        }
                    }
                    // Si l'utilisateur a rentré autre chose qu'un nombre
                    catch (NumberFormatException exception) {
                        System.out.println("You must enter a number!");
                    }
                    break;
            }
        }
    }
}
