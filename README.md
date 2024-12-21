# OtherDrops Structure Condition [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0) [![Discord](https://img.shields.io/discord/418432278113550337.svg?logo=discord&logoWidth=18&colorB=7289DA)](https://discordapp.com/invite/eHBxk5q)

StructureCondition is a simple add-on for the OtherDrops Plugin that uses NMS code to check if a location
is contained within a Minecraft Structure.

* Please check the OtherDrops [Wiki](https://github.com/CoolLord22/OtherDrops1.13/wiki) for more information

---

### Usage Information

Using StructureCondition is as simple as dropping it into your plugins/ folder! OtherDrops will automatically handle the
registration of the condition for you.

**IMPORTANT**:
It is crucial to use the latest OtherDrops Beta Release for this plugin. It depends on key config parsing methods that
are only in **OtherDrops3.2.8-b421.jar** and on.

To use the new condition in your drops file, you can use the `structure:` keyword how you normally define OD conditions.
See examples below. Do note, that the current implementation only supports a list of valid structures. the `ANY` keyterm
and `-NEGATIVE_MATCHING` are not yet supported. For fishing events, the **hook's location** is automatically read in by
the plugin.

```yaml
    ANY_BLOCK:
        - trigger: RIGHT_CLICK
          structure: [VILLAGE_PLAINS, VILLAGE_SAVANNA, IGLOO, DESERT_PYRAMID]
          message.server: "Clicked on specified structure!"
    PLAYER:
      - trigger: FISH_CAUGHT
        structure: [DESERT_PYRAMID]
        message: "Caught a fish in the pyramid!"
```

Depending on your Minecraft version, the values supported by this condition may change. If you define something that
could not be found by the plugin, please check your console log for a list of valid Enums.

```
Unknown structure type: _____
Possible Types: PILLAGER_OUTPOST, MINESHAFT, MINESHAFT_MESA, MANSION, JUNGLE_PYRAMID, DESERT_PYRAMID, IGLOO, SHIPWRECK, SHIPWRECK_BEACHED, SWAMP_HUT, STRONGHOLD, MONUMENT, OCEAN_RUIN_COLD, OCEAN_RUIN_WARM, FORTRESS, NETHER_FOSSIL, ENDCITY, BURIED_TREASURE, BASTION_REMNANT, VILLAGE_PLAINS, VILLAGE_DESERT, VILLAGE_SAVANNA, VILLAGE_SNOWY, VILLAGE_TAIGA, RUINED_PORTAL, RUINED_PORTAL_DESERT, RUINED_PORTAL_JUNGLE, RUINED_PORTAL_SWAMP, RUINED_PORTAL_MOUNTAIN, RUINED_PORTAL_OCEAN, RUINED_PORTAL_NETHER, ANCIENT_CITY, TRAIL_RUINS, TRIAL_CHAMBERS
```

* For versions 1.17-1.18, this list consists of the
  now-deprecated [StructureType](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/StructureType.html).
* For versions 1.19-1.21, this list largely contains values
  from [Structure](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/generator/structure/Structure.html).

---

### Supported Versions:

StructureCondition **is highly version dependent** as new supporting methods must be manually added every time a new NMS
version is released. As of now it is built and tested on the following Spigot versions:

* **NMS 1_17_R1**:
    * 1.17-R0.1-SNAPSHOT
    * 1.17.1-R0.1-SNAPSHOT
* **NMS 1_18_R1**:
    * 1.18-R0.1-SNAPSHOT
    * 1.18.1-R0.1-SNAPSHOT
* **NMS 1_18_R2**:
    * 1.18.2-R0.1-SNAPSHOT
* **NMS 1_19_R1**:
    * 1.19-R0.1-SNAPSHOT
    * 1.19.1-R0.1-SNAPSHOT
    * 1.19.2-R0.1-SNAPSHOT
* **NMS 1_19_R2**:
    * 1.19.3-R0.1-SNAPSHOT
* **NMS 1_19_R3**:
    * 1.19.4-R0.1-SNAPSHOT
* **NMS 1_20_R1**:
    * 1.20-R0.1-SNAPSHOT
    * 1.20.1-R0.1-SNAPSHOT
* **NMS 1_20_R2**:
    * 1.20.2-R0.1-SNAPSHOT
* **NMS 1_20_R3**:
    * 1.20.3-R0.1-SNAPSHOT
    * 1.20.4-R0.1-SNAPSHOT
* **NMS 1_21_R1**:
    * 1.21.1-R0.1-SNAPSHOT
    * 1.21.1-R0.1-SNAPSHOT
* **NMS 1_21_R2**:
    * 1.21.2-R0.1-SNAPSHOT
    * 1.21.3-R0.1-SNAPSHOT
* **NMS 1_21_R3**:
    * 1.21.4-R0.1-SNAPSHOT

---

### Donation Link

If you appreciate our plugins and support, consider donating and showin' us some love <3

[![ko-fi](https://www.ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/O4O425D12)

---

### Contact Us

If you have a problem please create a ticket and include the error (if there was one). Feel free to join the Discord
Server linked above! I'm super active there and tend to respond faster on it.
