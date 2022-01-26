package com.example.game.mode;

import com.example.entity.Direction;
import com.example.entity.Room;
import com.example.entity.RoomConnection;
import com.example.game.GameEditor;
import com.example.repository.RoomConnectionRepository;
import com.example.repository.RoomRepository;

public class RoomConnectionEditMode extends EditorMode
{
    protected Room startingRoom;
    protected Direction direction;

    public RoomConnectionEditMode(GameEditor editor, Room startingRoom, Direction direction)
    {
        super(editor);
        this.startingRoom = startingRoom;
        this.direction = direction;
    }

    @Override
    public void display()
    {
        RoomRepository repository = new RoomRepository();
        // Affiche le nom de la pièce et de la direction en cours de modification
        Room currentConnectedRoom = repository.findByStartingRoomAndDirection(startingRoom, direction);
        System.out.print(String.format("Editing connection from %s through %s", startingRoom.getName(), direction.getName()));
        if (currentConnectedRoom != null) {
            System.out.print(String.format(" (current: %s)", currentConnectedRoom.getName()));
        }
        System.out.println("");
        // Affiche la liste de toutes les pièces
        System.out.println("0: [Remove]");
        for (Room room : repository.findAll()) {
            System.out.println(String.format("%d: %s", room.getId(), room.getName()));
        }
    }

    @Override
    public void processInput(String userInput)
    {
        // Si la saisie de l'utilisateur est vide
        if (userInput.equals("")) {
            editor.setMode(new EditRoomMode(editor, startingRoom));
            return;
        }

        try {
            RoomConnectionRepository connectionRepository = new RoomConnectionRepository();
            // Convertit la saisie utilisateur en nombre
            int choice = Integer.parseInt(userInput);
            switch (choice) {
                // Si l'utilisateur souhaite supprimer la connexion
                case 0:

                // Si l'utilisateur souhaite modifier le lieu de destination
                default:
                    // Récupère le lieu de destination souhaitée
                    RoomRepository repository = new RoomRepository();
                    Room toRoom = repository.findById(choice);
                    // Si le lieu de destination existe
                    if (toRoom != null) {
                        // Récupére la connexion existante
                        RoomConnection connection = connectionRepository.findByStartingRoomAndDirection(startingRoom, direction);
                        // Si la connexion n'existe pas encore
                        if (connection == null) {
                            // Crée la connexion
                            connection = new RoomConnection();
                            connection.setFromRoom(startingRoom);
                            connection.setDirection(direction);
                            connection.setToRoom(toRoom);
                            connectionRepository.save(connection);
                        } else {
                            // Modifie le lieu de destination
                            connection.setToRoom(toRoom);
                            connectionRepository.save(connection);
                        }
                    }
                    return;
            }
        }
        catch (NumberFormatException exception) {
            System.out.println("You must enter a number!");
            return;
        }


    }
}
