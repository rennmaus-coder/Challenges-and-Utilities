package de.chris.my_plugin.utils;

import org.bukkit.*;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static int toTicks(int seconds){
        return seconds * 20;
    }

    public static String prefix(){
        return ChatColor.GRAY + "[" + ChatColor.BLUE +"$"+ ChatColor.GREEN + "Server" + ChatColor.BLUE +"$"+ ChatColor.GRAY + "] ";
    }

    public static String FormattedTime(long seconds) {
        String formatted =  String.format("%02d:%02d:%02d",
                (seconds / 60 / 60) % 24,
                (seconds / 60) % 60,
                seconds % 60);
        if (formatted.split(":")[0].equals("00")){
            formatted = String.format("%02d:%02d",
                    (seconds / 60) % 60,
                    seconds % 60);
        }
        if ((seconds / 60 / 60 / 24) % 7 == 1){
            formatted =  String.format("%02d Tage %02d:%02d:%02d",
                    (seconds / 60 / 60 / 24) % 7,
                    (seconds / 60 / 60) % 24,
                    (seconds / 60) % 60,
                    seconds % 60);
        }

        return formatted;
    }

    public static List<Integer> xcoords = new ArrayList();
    public static List<Integer> ycoords = new ArrayList();
    public static List<Integer> zcoords = new ArrayList();

    public static void getBlocks(Chunk chunk, Material target_block){
        int minY = 0;
        int minZ = chunk.getZ() * 16;
        int minX = chunk.getX() * 16;
        int maxY = chunk.getWorld().getMaxHeight() - 1;
        int maxZ;
        int maxX;
        maxZ = minZ + 15;

        maxX = minX + 15;


        List<Integer> xCoords = new ArrayList<>();
        List<Integer> yCoords = new ArrayList<>();
        List<Integer> zCoords = new ArrayList<>();
        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    if (chunk.getBlock(Math.abs(x%16), y, Math.abs(z%16)).getType() == Material.AIR){
                        continue;
                    }
                    if (chunk.getBlock(Math.abs(x%16), y, Math.abs(z%16)).getType() == target_block) {
                        xCoords.add(Math.abs(x%16));
                        yCoords.add(y);
                        zCoords.add(Math.abs(z%16));
                    }
                }
            }
        }
        xcoords = xCoords;
        ycoords = yCoords;
        zcoords = zCoords;
    }
}
