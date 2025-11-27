package dev.makeclassicgames.jaylib.jaylibexample;

import dev.makeclassicgames.jaylib.jaylibexample.game.Game;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

public class Launcher {
    public static void main(String[] args) {
        InitWindow(640,480, "Blue Perl");
        SetTargetFPS(60);
        Game game = new Game();
        while(!WindowShouldClose()){
            game.update();
            BeginDrawing();
                ClearBackground(RAYWHITE);
                game.draw();
            EndDrawing();
        }
        game.unload();
        CloseWindow();
    }
}
