package vip.floatationdevice;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import javax.inject.Inject;
import java.util.logging.Logger;

@Plugin(id = "mmprotect", name="MMPROTECT", version="1.0-SNAPSHOT",description = "MiniMine Protect")
public class MMPROTECT {
    @Inject
    private Logger logger;

    @Listener
    public void svrStart(GameStartedServerEvent event) {
        System.out.println("MMPROTECT started.");
    }

    @Listener
    public void svrStop(GameStoppingServerEvent event) {
        System.out.println("MMPROTECT stopping.");
    }

    @Listener
    public void onBreakBedrock(ChangeBlockEvent.Break evt, @Root Player player) {
        //go through all changes (will mostly be only one)
        for (Transaction<BlockSnapshot> tr : evt.getTransactions()) {
            //check if the original state
            if (tr.getOriginal().getState().getType() == BlockTypes.BEDROCK) {
                //if not, set the transaction invalid
                //System.out.println("Detected & cancelled bedrock breaking caused by " + player.getName());
                tr.setValid(false);
            }
        }
    }
    @Listener
    public void onDamageEntityEvent(DamageEntityEvent evt) {
        if (evt.getBaseDamage() > 40) {
            evt.setBaseDamage(40);
            //System.out.println("Detected base damage >40");
        }
    }

}