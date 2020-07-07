package vip.floatationdevice;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
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
        for (Transaction<BlockSnapshot> tr : evt.getTransactions()) {
            if (tr.getOriginal().getState().getType() == BlockTypes.BEDROCK) {
                //System.out.println("Detected & cancelled bedrock breaking caused by " + player.getName());
                tr.setValid(false);
            }
        }
    }

    @Listener
    public void onDamageEntityEvent(DamageEntityEvent evt, @First Entity entity, @Root DamageSource source) {
        if (!source.getType().equals(DamageTypes.CUSTOM) && !source.getType().equals(DamageTypes.EXPLOSIVE) && !source.getType().equals(DamageTypes.FALL) && !source.getType().equals(DamageTypes.VOID) && evt.getBaseDamage() > 40) {
            evt.setBaseDamage(37);
            //System.out.println("Detected base damage >40 caused by " + entity.getType().getName() + ". Setting to 40");
        }
    }

}