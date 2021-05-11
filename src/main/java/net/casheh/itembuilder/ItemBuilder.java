package net.casheh.itembuilder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private ItemStack itemStack;

    private Material material;

    private int amount;

    private short durability;

    private String displayName;

    private List<String> lore = new ArrayList<>();

    private List<ItemFlag> itemFlags = new ArrayList<>();

    private ItemMeta meta;

    public ItemBuilder(Material material) {
        if (material == null)
            material = Material.AIR;
        this.itemStack = new ItemStack(material);
        this.material = material;
    }

    public ItemBuilder(Material material, int amount) {
        if (material == null)
            material = Material.AIR;
        this.itemStack = new ItemStack(material, amount);
        this.material = material;
        this.amount = amount;
    }

    public ItemBuilder setAmount(int amount) {
        if (amount > this.material.getMaxStackSize() || amount <= 0)
            amount = 1;
        this.amount = amount;
        return this;
    }

    public ItemBuilder setDurability(short durability) {
        this.durability = durability;
        return this;
    }

    public ItemBuilder setMaterial(Material material) {
        if (material == null)
            material = Material.AIR;
        this.material = material;
        return this;
    }

    public ItemBuilder setMeta(ItemMeta meta) {
        if (meta != null)
            this.meta = meta;
        return this;
    }

    public ItemBuilder setDisplayName(String name) {
        if (name == null)
            name = material.name();
        this.displayName = name;
        return this;
    }

    public ItemBuilder setDisplayName(String name, boolean colorize) {
        if (name == null)
            name = material.name();
        this.displayName = colorize ? ChatColor.translateAlternateColorCodes('&', name) : name;
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        if (lore != null)
            this.lore = lore;
        return this;
    }

    public ItemBuilder addLore(String string) {
        if (string != null)
            this.lore.add(string);
        return this;
    }

    public ItemBuilder addLore(String string, boolean colorize) {
        if (string == null)
            return this;
        this.lore.add(colorize ? ChatColor.translateAlternateColorCodes('&', string) : string);
        return this;
    }

    public ItemBuilder flag(ItemFlag itemFlag) {
        if (itemFlag != null)
            this.itemFlags.add(itemFlag);
        return this;
    }

    public Material getMaterial() {
        return this.material;
    }

    public int getAmount() {
        return this.amount;
    }

    public short getDurability() {
        return this.durability;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public List<ItemFlag> getItemFlags() {
        return this.itemFlags;
    }

    public ItemMeta getMeta() {
        return this.meta;
    }

    public ItemStack build() {
        itemStack.setType(material);
        itemStack.setAmount(amount);
        itemStack.setDurability(durability);
        meta = itemStack.getItemMeta();
        if (itemFlags.size() > 0)
            itemFlags.forEach(el -> meta.addItemFlags(el));
        if (displayName != null)
            meta.setDisplayName(displayName);
        if (lore.size() > 0)
            meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }




}
