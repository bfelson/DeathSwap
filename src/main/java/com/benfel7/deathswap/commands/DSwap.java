package com.benfel7.deathswap.commands;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import static org.bukkit.scoreboard.RenderType.HEARTS;

public class DSwap implements CommandExecutor {

    ArrayList<Player> players = new ArrayList<Player>();
    Player player1, player2;
    World world;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            player1 = (Player)sender;
            if(Bukkit.getOnlinePlayers().size() != 2) {
                sender.sendMessage("Either there are more than two players online, or you are alone, ");
                sender.sendMessage("so you must specify with whom you want to play.");
                return true;
            }
            else {
                if(Bukkit.getOnlinePlayers().toArray()[0]==player1) {
                    player2 = (Player) Bukkit.getOnlinePlayers().toArray()[1];
                }
                else {
                    player2 = (Player) Bukkit.getOnlinePlayers().toArray()[0];
                }
            }
        }

        if (args.length == 1) {
            player1 = (Player)sender;
            player2 = Bukkit.getPlayer(args[0]);
        }

        else if (args.length > 1) {
            player1 = Bukkit.getPlayer(args[0]);
            player2 = Bukkit.getPlayer(args[1]);

            if (args.length == 3) {
                int maxSpread = Integer.parseInt(args[2]);
                Location p1random = new Location(world, (int) (Math.random() * maxSpread), (int) (Math.random() * maxSpread), (int) (Math.random() * maxSpread));
                Location p2random = new Location(world, (int) (Math.random() * maxSpread), (int) (Math.random() * maxSpread), (int) (Math.random() * maxSpread));
                player1.teleport(p1random);
                player2.teleport(p2random);

                int distance = (int) p1random.distance(p2random);
            }
        }

        for (Player player: players) {
            player.setFoodLevel(20);
            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
        }

        player1.sendMessage(ChatColor.RED + "Your mission is to kill " + player2.getName());
        player2.sendMessage(ChatColor.RED + "Your mission is to kill " + player1.getName());

        Scoreboard sb = new Scoreboard() {
            @Override
            public Objective registerNewObjective(String name, String criteria) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Objective registerNewObjective(String name, String criteria, String displayName) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Objective registerNewObjective(String name, String criteria, String displayName, RenderType renderType) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Objective getObjective(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Set<Objective> getObjectivesByCriteria(String criteria) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Set<Objective> getObjectives() {
                return null;
            }

            @Override
            public Objective getObjective(DisplaySlot slot) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Set<Score> getScores(OfflinePlayer player) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Set<Score> getScores(String entry) throws IllegalArgumentException {
                return null;
            }

            @Override
            public void resetScores(OfflinePlayer player) throws IllegalArgumentException {

            }

            @Override
            public void resetScores(String entry) throws IllegalArgumentException {

            }

            @Override
            public Team getPlayerTeam(OfflinePlayer player) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Team getEntryTeam(String entry) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Team getTeam(String teamName) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Set<Team> getTeams() {
                return null;
            }

            @Override
            public Team registerNewTeam(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Set<OfflinePlayer> getPlayers() {
                return null;
            }

            @Override
            public Set<String> getEntries() {
                return null;
            }

            @Override
            public void clearSlot(DisplaySlot slot) throws IllegalArgumentException {

            }
        };
        sb.registerNewObjective("Health", "health", "Health", HEARTS);
        sb.getObjective("Health").setDisplaySlot(DisplaySlot.PLAYER_LIST);

        return true;
    }


}
