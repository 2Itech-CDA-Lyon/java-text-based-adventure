package com.example.game.mode;

import com.example.game.GameEditor;

public abstract class EditorMode
{
    GameEditor editor;

    public EditorMode(GameEditor editor)
    {
        this.editor = editor;
    }

    abstract public void display();

    abstract public void processInput(String userInput);
}
