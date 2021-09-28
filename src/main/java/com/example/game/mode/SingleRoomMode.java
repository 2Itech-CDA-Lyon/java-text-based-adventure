package com.example.game.mode;

import com.example.entity.Room;
import com.example.game.GameEditor;
import com.example.repository.RoomRepository;

public class SingleRoomMode extends EditorMode
{
    protected Room room;

    public SingleRoomMode(GameEditor editor, Room room)
    {
        super(editor);
        this.room = room;
    }

    @Override
    public void display()
    {
        System.out.println(room.getName());
        System.out.println("1: Edit");
        System.out.println("2: Delete");
    }

    @Override
    public void processInput(String userInput)
    {
        RoomRepository repository = new RoomRepository();

        if (userInput.equals("")) {
            editor.setMode(new RoomListMode(editor));
            return;
        }
        try {
            // Rècupère le nombre entré par l'utilisateur
            Integer option = Integer.parseInt(userInput);
            // En fonction du nombre rentré par l'utilisateur
            switch (option) {
                // Si l'utlisateur a entré 1
                case 1:
                    // Navigue vers le menu de modification d'un lieu
                    editor.setMode(new EditRoomMode(editor, room));
                    return;

                // Si l'utilisateur a entré 2
                case 2:
                    // Supprime le lieu actuel
                    repository.delete(room);
                    // Navigue vers la liste des lieux
                    editor.setMode(new RoomListMode(editor));
                    return;
            }
        }
        // Si l'utilisateur a rentré autre chose qu'un nombre
        catch (NumberFormatException exception) {
            System.out.println("You must enter a number!");
        }
    }
}
