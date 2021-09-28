package com.example.game.mode;

import java.util.List;

import com.example.entity.Room;
import com.example.game.GameEditor;
import com.example.repository.RoomRepository;

public class RoomListMode extends EditorMode
{
    public RoomListMode(GameEditor editor)
    {
        super(editor);
    }

    @Override
    public void display()
    {
        RoomRepository repository = new RoomRepository();
        List<Room> rooms = repository.findAll();

        System.out.println("0: Create new");
        for (Room room : rooms) {
            System.out.println(String.format("%d: %s", room.getId(), room.getName()));
        }
    }

    @Override
    public void processInput(String userInput)
    {
        // Si la saisie de l'utilisateur est vide, arrête l'application
        if (userInput.equals("")) {
            editor.terminate();
        }

        try {
            RoomRepository repository = new RoomRepository();
            // Rècupère le lieu choisi par l'utilisateur
            Integer roomId = Integer.parseInt(userInput);
            // Si l'utilisateur a entré 0
            if (roomId == 0) {
                // Crée un nouveau lieu avec des propriétés par défaut et l'envoie en base de données
                Room room = new Room();
                room.setName("undefined");
                repository.save(room);
                // Navigue dans le menu créer un lieu
                editor.setMode(new EditRoomMode(editor, room));
                return;
            }

            Room room = repository.findById(roomId);
            // Si le lieu n'existe pas, affiche un message d'erreur
            if (room == null) {
                System.out.println("This room does not exist!");
                return;
            }
            // Navigue dans le menu d'un lieu particulier
            editor.setMode(new SingleRoomMode(editor, room));
        }
        // Si l'utilisateur a rentré autre chose qu'un nombre
        catch (NumberFormatException exception) {
            System.out.println("You must enter a number!");
        }
    }
}
