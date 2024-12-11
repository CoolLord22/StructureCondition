package com.coollord22.structurecondition;

import com.gmail.zariust.otherdrops.event.CustomDrop;
import com.gmail.zariust.otherdrops.event.OccurredEvent;
import com.gmail.zariust.otherdrops.parameters.Condition;
import org.bukkit.Location;

import java.util.Map;

public abstract class StructureCheck extends Condition {
    protected final Map<String, Boolean> structureMap;

    public StructureCheck(Map<String, Boolean> structureMap) {
        this.structureMap = structureMap;
    }

    @Override
    protected boolean checkInstance(CustomDrop drop, OccurredEvent occurrence) {
        if (structureMap == null)
            return true;

        // Use NMS to check for structure
        return isPartOfNaturalStructure(occurrence.getLocation());
    }

    abstract boolean isPartOfNaturalStructure(Location location);
}
