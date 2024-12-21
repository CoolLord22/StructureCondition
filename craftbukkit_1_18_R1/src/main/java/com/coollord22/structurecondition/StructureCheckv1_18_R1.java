package com.coollord22.structurecondition;

import com.gmail.zariust.common.Verbosity;
import com.gmail.zariust.otherdrops.ConfigurationNode;
import com.gmail.zariust.otherdrops.Log;
import com.gmail.zariust.otherdrops.OtherDropsConfig;
import com.gmail.zariust.otherdrops.parameters.Condition;
import net.minecraft.core.BaseBlockPosition;
import net.minecraft.world.level.chunk.Chunk;
import net.minecraft.world.level.levelgen.feature.StructureGenerator;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.bukkit.Location;
import org.bukkit.StructureType;
import org.bukkit.craftbukkit.v1_18_R1.CraftChunk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StructureCheckv1_18_R1 extends StructureCheck {
    public StructureCheckv1_18_R1(Set<String> structureSet) {
        super(structureSet);
    }

    @Override
    boolean isPartOfNaturalStructure(Location location) {
        try {
            for (String entry : structureSet) {
                if (getStructureGenerator(entry) != null) {
                    Log.logInfo("Searching for nearest " + entry, Verbosity.HIGHEST);
                    Location closestStructure = location.getWorld().locateNearestStructure(location, getStructureType(entry), 5, false);
                    Chunk craftChunk = ((CraftChunk) closestStructure.getWorld().getChunkAt(closestStructure)).getHandle();

                    if (craftChunk.a(getStructureGenerator(entry)) != null) {
                        for (StructurePiece piece : craftChunk.a(getStructureGenerator(entry)).i()) {
                            if (piece.f().b(new BaseBlockPosition(location.getX(), location.getY(), location.getZ()))) {
                                Log.logInfo("Location was found in structure bounding box!", Verbosity.HIGHEST);
                                return true;
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
        conditionList.add(new StructureCheckv1_18_R1(result));
        return conditionList;
    }

    @Override
    boolean validStructure(String structure) {
        return getStructureType(structure) != null;
    }

    private StructureType getStructureType(String structureType) {
        return switch (structureType.toUpperCase()) {
            case "PILLAGER_OUTPOST" -> StructureType.PILLAGER_OUTPOST;
            case "MINESHAFT" -> StructureType.MINESHAFT;
            case "MANSION" -> StructureType.WOODLAND_MANSION;
            case "JUNGLE_PYRAMID" -> StructureType.JUNGLE_PYRAMID;
            case "DESERT_PYRAMID" -> StructureType.DESERT_PYRAMID;
            case "IGLOO" -> StructureType.IGLOO;
            case "RUINED_PORTAL" -> StructureType.RUINED_PORTAL;
            case "SHIPWRECK" -> StructureType.SHIPWRECK;
            case "SWAMP_HUT" -> StructureType.SWAMP_HUT;
            case "STRONGHOLD" -> StructureType.STRONGHOLD;
            case "MONUMENT" -> StructureType.OCEAN_MONUMENT;
            case "OCEAN_RUIN" -> StructureType.OCEAN_RUIN;
            case "FORTRESS" -> StructureType.NETHER_FORTRESS;
            case "ENDCITY" -> StructureType.END_CITY;
            case "BURIED_TREASURE" -> StructureType.BURIED_TREASURE;
            case "VILLAGE" -> StructureType.VILLAGE;
            case "NETHER_FOSSIL" -> StructureType.NETHER_FOSSIL;
            case "BASTION_REMNANT" -> StructureType.BASTION_REMNANT;

            default -> {
                Log.logWarning("Unknown structure type: " + structureType);
                Log.logWarning("Possible Types: PILLAGER_OUTPOST, MINESHAFT, MANSION, JUNGLE_PYRAMID, DESERT_PYRAMID, IGLOO, RUINED_PORTAL, SHIPWRECK, SWAMP_HUT, STRONGHOLD, MONUMENT, OCEAN_RUIN, FORTRESS, ENDCITY, BURIED_TREASURE, VILLAGE, NETHER_FOSSIL, BASTION_REMNANT");
                yield null;
            }
        };
    }

    private StructureGenerator<?> getStructureGenerator(String structureType) {
        return switch (structureType.toUpperCase()) {
            case "PILLAGER_OUTPOST" -> StructureGenerator.c; // Corresponds to "Pillager_Outpost"
            case "MINESHAFT" -> StructureGenerator.d; // Corresponds to "Mineshaft"
            case "MANSION" -> StructureGenerator.e; // Corresponds to "Mansion"
            case "JUNGLE_PYRAMID" -> StructureGenerator.f; // Corresponds to "Jungle_Pyramid"
            case "DESERT_PYRAMID" -> StructureGenerator.g; // Corresponds to "Desert_Pyramid"
            case "IGLOO" -> StructureGenerator.h; // Corresponds to "Igloo"
            case "RUINED_PORTAL" -> StructureGenerator.i; // Corresponds to "Ruined_Portal"
            case "SHIPWRECK" -> StructureGenerator.j; // Corresponds to "Shipwreck"
            case "SWAMP_HUT" -> StructureGenerator.k; // Corresponds to "Swamp_Hut"
            case "STRONGHOLD" -> StructureGenerator.l; // Corresponds to "Stronghold"
            case "MONUMENT" -> StructureGenerator.m; // Corresponds to "Monument"
            case "OCEAN_RUIN" -> StructureGenerator.n; // Corresponds to "Ocean_Ruin"
            case "FORTRESS" -> StructureGenerator.o; // Corresponds to "Fortress"
            case "ENDCITY" -> StructureGenerator.p; // Corresponds to "EndCity"
            case "BURIED_TREASURE" -> StructureGenerator.q; // Corresponds to "Buried_Treasure"
            case "VILLAGE" -> StructureGenerator.r; // Corresponds to "Village"
            case "NETHER_FOSSIL" -> StructureGenerator.s; // Corresponds to "Nether_Fossil"
            case "BASTION_REMNANT" -> StructureGenerator.t; // Corresponds to "Bastion_Remnant"

            default -> {
                Log.logWarning("Unknown structure type: " + structureType);
                Log.logWarning("Possible Types: PILLAGER_OUTPOST, MINESHAFT, MANSION, JUNGLE_PYRAMID, DESERT_PYRAMID, IGLOO, RUINED_PORTAL, SHIPWRECK, SWAMP_HUT, STRONGHOLD, MONUMENT, OCEAN_RUIN, FORTRESS, ENDCITY, BURIED_TREASURE, VILLAGE, NETHER_FOSSIL, BASTION_REMNANT");
                yield null;
            }
        };
    }
}
