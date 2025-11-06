package dev.makeclassicgames.jaylib.jaylibexample.game.resources;

import static com.raylib.Raylib.*;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    private static ResourceManager instance;

    private Map<ResourceIdentifier, Texture> resourceMap;

    public ResourceManager(){
        this.resourceMap = new HashMap<>();

        this.resourceMap.put(
                ResourceIdentifier.PLAYER,LoadTexture("src/main/resources/player.png"));
        this.resourceMap.put(
                ResourceIdentifier.ENEMY, LoadTexture("src/main/resources/enemypirate.png"));
        this.resourceMap.put(
                ResourceIdentifier.BULLET,LoadTexture("src/main/resources/bullet.png"));
        this.resourceMap.put(
                ResourceIdentifier.MAP, LoadTexture("src/main/resources/map.png"));
    }

    public Texture getTexture(ResourceIdentifier texureId){
        return this.resourceMap.getOrDefault(texureId,null); //TODO: DEFAULT
    }

    public void UnloadResources(){
        for(Texture t: this.resourceMap.values()){
            UnloadTexture(t);
        }
    }

    public static  ResourceManager getInstance(){
        if(instance==null){
            instance = new ResourceManager();
        }
        return instance;
    }

}
