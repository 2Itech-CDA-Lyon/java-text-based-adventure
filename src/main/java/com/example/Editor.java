package com.example;

import com.example.game.GameEditor;

public class Editor {
    public static void main( String[] args ) throws Exception
    {
        GameEditor editor = new GameEditor();

        editor.setup();
        
        while (editor.isRunning()) {
            editor.update();
        }
    }
}
