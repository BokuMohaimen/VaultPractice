package vault.vaultpractice;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class VaultPractice extends JavaPlugin {

    public VaultManager val = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("VaultPracticeを起動しました");
        getCommand("mbal").setExecutor(this);
        val = new VaultManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic]
        getLogger().info("VaultPracticeを停止しました");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        UUID puuid = p.getUniqueId();
        double bal = val.getBalance(puuid);

        if (args[0] == "get") {
            sender.sendMessage("100円を追加しました");
            VaultManager.economy.withdrawPlayer(p, 100);
        }
        if (args[0] == "take") {
            sender.sendMessage("100円を引きました");
            VaultManager.economy.depositPlayer(p, 100);
        }
        if (args.length == 0) {
            sender.sendMessage("あなたは今" + bal + "円を持っています");
        }
        return true;
    }
}
