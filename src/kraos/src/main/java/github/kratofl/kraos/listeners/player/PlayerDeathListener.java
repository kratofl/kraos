package github.kratofl.kraos.listeners.player;

import github.kratofl.kraos.deathchest.DeathChestHandler;
import github.kratofl.kraos.player.PlayerData;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        PlayerData.setPlayersLastCoordinates(player, player.getLocation());

//        String[] deathMessages = {
//                "forgot how to swim and took a lava bath",
//                "tried to hug a creeper. It didn't end well",
//                "mistook the ground for a water pool",
//                "challenged gravity and lost",
//                "wanted to see if zombies are friendly",
//                "found out that TNT doesn't make friends",
//                "thought cactus could be a nice pillow",
//                "took a high dive without the water part",
//                "thought they could outrun an arrow. They couldn't",
//                "learned that skeletons have good aim",
//                "tried to swim in lava. It was super effective!",
//                "hugged a blaze. Too warm for comfort",
//                "lost a staring contest with an Enderman",
//                "boat took a shortcut to the bottom of the ocean",
//                "found out that beds explode in the Nether",
//                "got too close to the edge, and the edge pushed back",
//                "tried to ride a pig... off a cliff",
//                "asked a Wither for a handshake",
//                "fought a spider and got tangled in the web",
//                "challenged a ghast to a fireball duel. Big mistake"
//        };
//
//        Random random = new Random();
//        String randomDeathMessage = deathMessages[random.nextInt(deathMessages.length)];
//        Component deathMessage = Component.text(player.getName())
//                .append(Component.text(" "))
//                .append(Component.text(randomDeathMessage));
//
//        event.deathMessage(deathMessage);
//
//        DeathChestHandler.spawnDeathChest(player);
//        event.getDrops().removeAll(event.getDrops());
    }
}
