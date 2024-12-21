package com.coollord22.structurecondition;

import com.gmail.zariust.common.Verbosity;
import com.gmail.zariust.otherdrops.Log;
import com.gmail.zariust.otherdrops.event.CustomDrop;
import com.gmail.zariust.otherdrops.event.OccurredEvent;
import com.gmail.zariust.otherdrops.parameters.Condition;
import org.bukkit.Location;

import java.util.Set;

public abstract class StructureCheck extends Condition {
    protected final Set<String> structureSet;

    public StructureCheck(Set<String> structureSet) {
        this.structureSet = structureSet;
    }

    @Override
    protected boolean checkInstance(CustomDrop drop, OccurredEvent occurrence) {
        if (structureSet == null)
            return true;

        if (occurrence.getFishingLocation() != null) {
            Log.logInfo("Fishing location was not null, processing structure check with fishhook location.", Verbosity.HIGH);
            return isPartOfNaturalStructure(occurrence.getFishingLocation());
        }
        return isPartOfNaturalStructure(occurrence.getLocation());
    }

    abstract boolean isPartOfNaturalStructure(Location location);
    abstract boolean validStructure(String structure);
}
