package com.example.game.mode;

import com.example.entity.Room;
import com.example.game.GameEditor;

public class EditRoomMode extends EditorMode
{
    protected Room room;

    public EditRoomMode(GameEditor editor, Room room)
    {
        super(editor);
        this.room = room;
    }

    @Override
    public void display()
    {
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
