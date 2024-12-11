package com.coollord22.structurecondition;

import com.gmail.zariust.common.Verbosity;
import com.gmail.zariust.otherdrops.ConfigurationNode;
import com.gmail.zariust.otherdrops.Log;
import com.gmail.zariust.otherdrops.OtherDropsConfig;
import com.gmail.zariust.otherdrops.parameters.Condition;
import net.minecraft.core.BaseBlockPosition;
import net.minecraft.world.level.chunk.Chunk;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.WorldGenFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.bukkit.Location;
import org.bukkit.StructureType;
import org.bukkit.craftbukkit.v1_18_R2.CraftChunk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StructureCheckv1_18_R2 extends StructureCheck {
    public StructureCheckv1_18_R2(Map<String, Boolean> structureMap) {
        super(structureMap);
    }

    @Override
    boolean isPartOfNaturalStructure(Location location) {
        try {
            for(Map.Entry<String, Boolean> entry : structureMap.entrySet()) {
                if(getStructureGenerator(entry.getKey()) != null) {
                    Log.logInfo("Searching for nearest " + entry.getKey(), Verbosity.HIGHEST);
                    Location closestStructure = location.getWorld().locateNearestStructure(location, getStructureType(entry.getKey()), 5, false);
                    Chunk craftChunk = ((CraftChunk) closestStructure.getChunk()).getHandle();

                    for(Map.Entry<StructureFeature<?, ?>, StructureStart> structureEntry : craftChunk.g().entrySet()) {
                        StructureFeature<?, ?> structureFeature = structureEntry.getKey();
                        if(structureFeature.d instanceof StructureGenerator) {
                            if(getStructureGenerator(entry.getKey()).equals(structureFeature.d)) {
                                for (StructurePiece piece : structureEntry.getValue().i()) {
                                    if (piece.f().b(new BaseBlockPosition(location.getX(), location.getY(), location.getZ()))) {
                                        Log.logInfo("Location was found in structure bounding box!", Verbosity.HIGHEST);
                                        return true;
                                    }
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
        Map<String, Boolean> result = OtherDropsConfig.parseStructuresFrom(configurationNode);
        if(result == null || result.isEmpty())
            return null;
        List<Condition> conditionList = new ArrayList<>();
        conditionList.add(new StructureCheckv1_18_R2(result));
        return conditionList;
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
        return switch(structureType.toUpperCase()) {
            case "PILLAGER_OUTPOST" -> StructureGenerator.b; // Corresponds to "Pillager_Outpost"
            case "MINESHAFT" -> StructureGenerator.c; // Corresponds to "Mineshaft"
            case "MANSION" -> StructureGenerator.d; // Corresponds to "Mansion"
            case "JUNGLE_PYRAMID" -> StructureGenerator.e; // Corresponds to "Jungle_Pyramid"
            case "DESERT_PYRAMID" -> StructureGenerator.f; // Corresponds to "Desert_Pyramid"
            case "IGLOO" -> StructureGenerator.g; // Corresponds to "Igloo"
            case "RUINED_PORTAL" -> StructureGenerator.h; // Corresponds to "Ruined_Portal"
            case "SHIPWRECK" -> StructureGenerator.i; // Corresponds to "Shipwreck"
            case "SWAMP_HUT" -> StructureGenerator.j; // Corresponds to "Swamp_Hut"
            case "STRONGHOLD" -> StructureGenerator.k; // Corresponds to "Stronghold"
            case "MONUMENT" -> StructureGenerator.l; // Corresponds to "Monument"
            case "OCEAN_RUIN" -> StructureGenerator.m; // Corresponds to "Ocean_Ruin"
            case "FORTRESS" -> StructureGenerator.n; // Corresponds to "Fortress"
            case "ENDCITY" -> StructureGenerator.o; // Corresponds to "EndCity"
            case "BURIED_TREASURE" -> StructureGenerator.p; // Corresponds to "Buried_Treasure"
            case "VILLAGE" -> StructureGenerator.q; // Corresponds to "Village"
            case "NETHER_FOSSIL" -> StructureGenerator.r; // Corresponds to "Nether_Fossil"
            case "BASTION_REMNANT" -> StructureGenerator.s; // Corresponds to "Bastion_Remnant"

            default -> {
                Log.logWarning("Unknown structure type: " + structureType);
                Log.logWarning("Possible Types: PILLAGER_OUTPOST, MINESHAFT, MANSION, JUNGLE_PYRAMID, DESERT_PYRAMID, IGLOO, RUINED_PORTAL, SHIPWRECK, SWAMP_HUT, STRONGHOLD, MONUMENT, OCEAN_RUIN, FORTRESS, ENDCITY, BURIED_TREASURE, VILLAGE, NETHER_FOSSIL, BASTION_REMNANT");
                yield null;
            }
        };
    }
}