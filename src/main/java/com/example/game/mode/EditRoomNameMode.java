package com.example.game.mode;

import com.example.entity.Room;
import com.example.game.GameEditor;
import com.example.repository.RoomRepository;

public class EditRoomNameMode extends EditorMode
{
    protected Room room;

    public EditRoomNameMode(GameEditor editor, Room room)
    {
        super(editor);
        this.room = room;
    }

    @Override
    public void display()
    {
        System.out.println(String.format("Enter new value for [name] %s:", room.getName()));
    }

    @Override
    public void processInput(String userInput)
    {
        RoomRepository repository = new RoomRepository();

        room.setName(userInput);
        repository.save(room);
        editor.setMode(new EditRoomMode(editor, room));
    }
}
