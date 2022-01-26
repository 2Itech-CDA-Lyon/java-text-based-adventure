package com.example.game.mode;

import java.util.List;

import com.example.entity.Direction;
import com.example.entity.Room;
import com.example.game.GameEditor;
import com.example.repository.DirectionRepository;
import com.example.repository.RoomRepository;

public class EditRoomMode extends EditorMode
{
    protected Room room;
    protected DirectionRepository directionRepository;
    protected List<Direction> directions;

    public EditRoomMode(GameEditor editor, Room room)
    {
        super(editor);
        this.room = room;
        directionRepository = new DirectionRepository();
        // Rédcupère toutes les directions de la base de données
        directions = directionRepository.findAll();
    }

    @Override
    public void display()
    {
        
        RoomRepository roomRepository = new RoomRepository();
        
        // Affiche toutes les directions
        for (Direction direction : directions) {
            System.out.print(String.format("%s: ", direction.getCommand()));
            // Cherche si une pièce est connectée à la pièce en cours de modification dans cette direction
            Room connectedRoom = roomRepository.findByStartingRoomAndDirection(room,direction);
            if(connectedRoom != null) {
                System.out.print(String.format(connectedRoom.getName()));
            }
            System.out.println("");
        }
        System.out.println(String.format("1: [name] %s", room.getName()));
    }

    @Override
    public void processInput(String userInput)
    {
        // Si la saisie de l'utilisateur est vide, retourne au menu d'une pièce en particulier
        if (userInput.equals("")) {
            editor.setMode(new SingleRoomMode(editor, room));
            return;
        }

        // Si la saisie utilisateur correspond à une direction
        Direction direction = directionRepository.findByCommand(userInput);
        if (direction != null) {
            // Navigue vers le menu de modification de connexion entre lieux
            editor.setMode(new RoomConnectionEditMode(editor, room, direction));
            return;
        }

        try {
            // Rècupère le nombre entré par l'utilisateur
            Integer option = Integer.parseInt(userInput);
            
            switch (option) {
                // Si l'utilisateur veut modifier le nom du lieu
                case 1:
                    editor.setMode(new EditRoomNameMode(editor, room));
                    return;
            }
        }
        // Si l'utilisateur a rentré autre chose qu'un nombre
        catch (NumberFormatException exception) {
            System.out.println("You must enter a number!");
        }
    }
}
