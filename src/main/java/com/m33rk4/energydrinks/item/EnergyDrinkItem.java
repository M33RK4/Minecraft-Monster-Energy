package com.m33rk4.energydrinks.item;

import com.m33rk4.energydrinks.registry.ModItems;
import com.m33rk4.energydrinks.util.DrinkConstants;
import com.m33rk4.energydrinks.util.DrinkFlavor;
import com.m33rk4.energydrinks.util.EnergyEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.List;

// Drinks like a potion, and you can drink it even when you're full.
public class EnergyDrinkItem extends Item {
    private final DrinkFlavor flavor;

    public EnergyDrinkItem(DrinkFlavor flavor, Properties properties) {
        super(properties);
        this.flavor = flavor;
    }

    public DrinkFlavor getFlavor() {
        return flavor;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return DrinkConstants.DRINK_TIME;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        Player player = entity instanceof Player p ? p : null;

        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!level.isClientSide) {
            if (player != null) {
                player.getFoodData().eat(DrinkConstants.NUTRITION, DrinkConstants.SATURATION_MODIFIER);
            }
            EnergyEffects.applyBoost(entity);
        }

        entity.gameEvent(GameEvent.DRINK);

        // In creative you keep the can.
        if (player != null && player.getAbilities().instabuild) {
            return stack;
        }

        stack.shrink(1);
        ItemStack emptyCan = new ItemStack(ModItems.EMPTY_CAN.get());

        // Last can in the stack, so the empty one just goes in your hand.
        if (stack.isEmpty()) {
            return emptyCan;
        }

        // Otherwise it goes in your inventory, or on the ground if you're full.
        if (player != null && !player.getInventory().add(emptyCan)) {
            player.drop(emptyCan, false);
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.energydrinks.boost",
                DrinkConstants.formatTicks(DrinkConstants.BOOST_DURATION)).withStyle(ChatFormatting.BLUE));
        tooltip.add(Component.translatable("tooltip.energydrinks.crash",
                DrinkConstants.formatTicks(DrinkConstants.CRASH_DURATION)).withStyle(ChatFormatting.RED));
    }
}
