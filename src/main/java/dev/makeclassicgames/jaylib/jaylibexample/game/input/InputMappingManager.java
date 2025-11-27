package dev.makeclassicgames.jaylib.jaylibexample.game.input;

import java.util.ArrayList;
import java.util.List;

import static com.raylib.Raylib.*;

public class InputMappingManager {
    private final List<InputEvent> inputEvents;

    public InputMappingManager() {
        this.inputEvents = new ArrayList<>();
    }

    public void updateEvents() {

        if (IsKeyDown(KEY_UP) || IsGamepadButtonDown(0, GAMEPAD_BUTTON_LEFT_FACE_UP)
            || GetGamepadAxisMovement(0,GAMEPAD_AXIS_LEFT_Y)<0) {
            this.inputEvents.add(InputEvent.UP);
        }
        if (IsKeyDown(KEY_DOWN) || IsGamepadButtonDown(0, GAMEPAD_BUTTON_LEFT_FACE_DOWN)
            || GetGamepadAxisMovement(0,GAMEPAD_AXIS_LEFT_Y)>0) {
            this.inputEvents.add(InputEvent.DOWN);
        }
        if (IsKeyDown(KEY_LEFT) || IsGamepadButtonDown(0, GAMEPAD_BUTTON_LEFT_FACE_LEFT)
        || GetGamepadAxisMovement(0,GAMEPAD_AXIS_LEFT_X)<0) {
            this.inputEvents.add(InputEvent.LEFT);
        }

        if (IsKeyDown(KEY_RIGHT) || IsGamepadButtonDown(0, GAMEPAD_BUTTON_LEFT_FACE_RIGHT)
            || GetGamepadAxisMovement(0,GAMEPAD_AXIS_LEFT_X)>0) {
            this.inputEvents.add(InputEvent.RIGHT);
        }

        if (IsKeyPressed(KEY_S) || IsGamepadButtonPressed(0, GAMEPAD_BUTTON_RIGHT_FACE_DOWN)){
            this.inputEvents.add(InputEvent.FIRE_UP);
        }

        if (IsKeyPressed(KEY_D) || IsGamepadButtonPressed(0, GAMEPAD_BUTTON_RIGHT_FACE_RIGHT)){
            this.inputEvents.add(InputEvent.FIRE_RIGHT);
        }

        if (IsKeyPressed(KEY_A) || IsGamepadButtonPressed(0, GAMEPAD_BUTTON_RIGHT_FACE_LEFT)){
            this.inputEvents.add(InputEvent.FIRE_LEFT);
        }

        if(IsKeyPressed(KEY_ENTER)|| IsGamepadButtonPressed(0, GAMEPAD_BUTTON_MIDDLE_RIGHT)){
            this.inputEvents.add(InputEvent.START);
        }

    }

    public List<InputEvent> getEvents() {
        List<InputEvent> copia = new ArrayList<>(this.inputEvents);
        this.inputEvents.clear();
        return copia;
    }
}
