package com.coollord22.structurecondition;

import com.gmail.zariust.common.Verbosity;
import com.gmail.zariust.otherdrops.ConfigurationNode;
import com.gmail.zariust.otherdrops.Log;
import com.gmail.zariust.otherdrops.OtherDropsConfig;
import com.gmail.zariust.otherdrops.parameters.Condition;
import net.minecraft.core.BaseBlockPosition;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.IChunkAccess;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R2.CraftChunk;
import org.bukkit.util.StructureSearchResult;

import java.util.*;

public class StructureCheckv1_20_R2 extends StructureCheck {
    public StructureCheckv1_20_R2(Set<String> structureSet) {
        super(structureSet);
    }

    @Override
    boolean isPartOfNaturalStructure(Location location) {
        try {
            for (String entry : structureSet) {
                if (getStructureType(entry) != null) {
                    Log.logInfo("Searching for nearest " + entry, Verbosity.HIGHEST);
                    StructureSearchResult searchResult = location.getWorld().locateNearestStructure(location, getStructureType(entry), 5, false);
                    if (searchResult != null) {
                        IChunkAccess craftChunk = ((CraftChunk) searchResult.getLocation().getChunk()).getHandle(ChunkStatus.n);
                        for (Map.Entry<Structure, StructureStart> structureEntry : craftChunk.g().entrySet()) {
                            for (StructurePiece piece : structureEntry.getValue().i()) {
                                if (piece.f().b(new BaseBlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()))) {
                                    Log.logInfo("Location was found in structure bounding box!", Verbosity.HIGHEST);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.logWarning("Ran into an error while searching for structure. Please notify developer if this issue persists.");
            return false;
        }
        return false;
    }

    @Override
    public List<Condition> parse(ConfigurationNode configurationNode) {
        Set<String> temp = OtherDropsConfig.parseStructuresFrom(configurationNode);
        if (temp == null || temp.isEmpty())
            return null;

        Set<String> result = new HashSet<>();
        for (String entry : temp) {
            if (getStructureType(entry) != null) {
                result.add(entry);
            }
        }

        List<Condition> conditionList = new ArrayList<>();
        conditionList.add(new StructureCheckv1_20_R2(result));
        return conditionList;
    }

    private org.bukkit.generator.structure.Structure getStructureType(String structureType) {
        return switch (structureType.toUpperCase()) {
            case "PILLAGER_OUTPOST" -> org.bukkit.generator.structure.Structure.PILLAGER_OUTPOST;
            case "MINESHAFT" -> org.bukkit.generator.structure.Structure.MINESHAFT;
            case "MINESHAFT_MESA" -> org.bukkit.generator.structure.Structure.MINESHAFT_MESA;
            case "MANSION" -> org.bukkit.generator.structure.Structure.MANSION;
            case "JUNGLE_PYRAMID" -> org.bukkit.generator.structure.Structure.JUNGLE_PYRAMID;
            case "DESERT_PYRAMID" -> org.bukkit.generator.structure.Structure.DESERT_PYRAMID;
            case "IGLOO" -> org.bukkit.generator.structure.Structure.IGLOO;
            case "SHIPWRECK" -> org.bukkit.generator.structure.Structure.SHIPWRECK;
            case "SHIPWRECK_BEACHED" -> org.bukkit.generator.structure.Structure.SHIPWRECK_BEACHED;
            case "SWAMP_HUT" -> org.bukkit.generator.structure.Structure.SWAMP_HUT;
            case "STRONGHOLD" -> org.bukkit.generator.structure.Structure.STRONGHOLD;
            case "MONUMENT" -> org.bukkit.generator.structure.Structure.MONUMENT;
            case "OCEAN_RUIN_COLD" -> org.bukkit.generator.structure.Structure.OCEAN_RUIN_COLD;
            case "OCEAN_RUIN_WARM" -> org.bukkit.generator.structure.Structure.OCEAN_RUIN_WARM;
            case "FORTRESS" -> org.bukkit.generator.structure.Structure.FORTRESS;
            case "NETHER_FOSSIL" -> org.bukkit.generator.structure.Structure.NETHER_FOSSIL;
            case "ENDCITY" -> org.bukkit.generator.structure.Structure.END_CITY;
            case "BURIED_TREASURE" -> org.bukkit.generator.structure.Structure.BURIED_TREASURE;
            case "BASTION_REMNANT" -> org.bukkit.generator.structure.Structure.BASTION_REMNANT;
            case "VILLAGE_PLAINS" -> org.bukkit.generator.structure.Structure.VILLAGE_PLAINS;
            case "VILLAGE_DESERT" -> org.bukkit.generator.structure.Structure.VILLAGE_DESERT;
            case "VILLAGE_SAVANNA" -> org.bukkit.generator.structure.Structure.VILLAGE_SAVANNA;
            case "VILLAGE_SNOWY" -> org.bukkit.generator.structure.Structure.VILLAGE_SNOWY;
            case "VILLAGE_TAIGA" -> org.bukkit.generator.structure.Structure.VILLAGE_TAIGA;
            case "RUINED_PORTAL" -> org.bukkit.generator.structure.Structure.RUINED_PORTAL;
            case "RUINED_PORTAL_DESERT" -> org.bukkit.generator.structure.Structure.RUINED_PORTAL_DESERT;
            case "RUINED_PORTAL_JUNGLE" -> org.bukkit.generator.structure.Structure.RUINED_PORTAL_JUNGLE;
            case "RUINED_PORTAL_SWAMP" -> org.bukkit.generator.structure.Structure.RUINED_PORTAL_SWAMP;
            case "RUINED_PORTAL_MOUNTAIN" -> org.bukkit.generator.structure.Structure.RUINED_PORTAL_MOUNTAIN;
            case "RUINED_PORTAL_OCEAN" -> org.bukkit.generator.structure.Structure.RUINED_PORTAL_OCEAN;
            case "RUINED_PORTAL_NETHER" -> org.bukkit.generator.structure.Structure.RUINED_PORTAL_NETHER;
            case "ANCIENT_CITY" -> org.bukkit.generator.structure.Structure.ANCIENT_CITY;
            case "TRAIL_RUINS" -> org.bukkit.generator.structure.Structure.TRAIL_RUINS;
            default -> {
                Log.logWarning("Unknown structure type: " + structureType);
                Log.logWarning("Possible Types: PILLAGER_OUTPOST, MINESHAFT, MINESHAFT_MESA, MANSION, JUNGLE_PYRAMID, DESERT_PYRAMID, IGLOO, SHIPWRECK, SHIPWRECK_BEACHED, SWAMP_HUT, STRONGHOLD, MONUMENT, OCEAN_RUIN_COLD, OCEAN_RUIN_WARM, FORTRESS, NETHER_FOSSIL, ENDCITY, BURIED_TREASURE, BASTION_REMNANT, VILLAGE_PLAINS, VILLAGE_DESERT, VILLAGE_SAVANNA, VILLAGE_SNOWY, VILLAGE_TAIGA, RUINED_PORTAL, RUINED_PORTAL_DESERT, RUINED_PORTAL_JUNGLE, RUINED_PORTAL_SWAMP, RUINED_PORTAL_MOUNTAIN, RUINED_PORTAL_OCEAN, RUINED_PORTAL_NETHER, ANCIENT_CITY, TRAIL_RUINS");
                yield null;
            }
        };
    }
}
