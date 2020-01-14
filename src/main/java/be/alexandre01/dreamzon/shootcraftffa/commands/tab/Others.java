package be.alexandre01.dreamzon.shootcraftffa.commands.tab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Others implements TabCompleter {
    private static final String[] COMMANDS = { "minecraft", "spigot", "bukkit", "google" };
    //create a static array of values

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        //create new array
        final List<String> completions = new ArrayList<>();
        //copy matches of first argument from list (ex: if first arg is 'm' will return just 'minecraft')
        StringUtil.copyPartialMatches(args[0], Arrays.asList(COMMANDS), completions);
        //sort the list
        Collections.sort(completions);
        return completions;
    }
}
