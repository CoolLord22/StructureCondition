package com.coollord22.structurecondition;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;

public class StructureCondition extends JavaPlugin {
    public static StructureCheck structureCheck;
    static {
        try {
            String packageName = StructureCondition.class.getPackage().getName();
            String internalsName = Bukkit.getServer().getClass().getName().split("\\.")[3];
            structureCheck = (StructureCheck) Class.forName(packageName + ".StructureCheck" + internalsName).getDeclaredConstructor(Map.class).newInstance((Object) null);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException |
                 NoSuchMethodException | InvocationTargetException exception) {
            Bukkit.getLogger().log(Level.SEVERE, "StructureCondition could not find a valid implementation for this server version.");
        }
    }

    @Override
    public void onEnable() {
        if(structureCheck != null) {
            com.gmail.zariust.otherdrops.parameters.Condition.registerCondition(structureCheck);
        }
    }

    @Override
    public void onDisable() {

    }
}
