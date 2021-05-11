import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    private ItemStack itemStack;

    private Material material;

    private int amount;

    private short durability;

    private String displayName;

    private List<String> lore = new ArrayList<>();

    private List<ItemFlag> itemFlags = new ArrayList<>();

    private Map<Enchantment, Integer> enchantments = new HashMap<>();

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

    /**
     * @param amount The amount of items to be included in the stack.
     * */
    public ItemBuilder setAmount(int amount) {
        if (amount > this.material.getMaxStackSize() || amount <= 0)
            amount = 1;
        this.amount = amount;
        return this;
    }

    /**
     * @param durability The amount of durability the item should have.
     * */
    public ItemBuilder setDurability(short durability) {
        this.durability = durability;
        return this;
    }

    /**
     * @param material The material type of the item.
     * */
    public ItemBuilder setMaterial(Material material) {
        if (material == null)
            material = Material.AIR;
        this.material = material;
        return this;
    }

    /**
     * @param meta The ItemMeta of the item.
     * */
    public ItemBuilder setMeta(ItemMeta meta) {
        if (meta != null)
            this.meta = meta;
        return this;
    }

    /**
     * @param name The display name of the item (without colors)
     * */
    public ItemBuilder setDisplayName(String name) {
        if (name == null)
            name = material.name();
        this.displayName = name;
        return this;
    }

    /**
     * @param name The display name of the item
     * @param colorize If true, the name will be translated to include the proper color codes
     * */
    public ItemBuilder setDisplayName(String name, boolean colorize) {
        if (name == null)
            name = material.name();
        this.displayName = colorize ? ChatColor.translateAlternateColorCodes('&', name) : name;
        return this;
    }

    /**
     * @param lore The lore of the item
     * */
    public ItemBuilder setLore(List<String> lore) {
        if (lore != null)
            this.lore = lore;
        return this;
    }

    /**
     * @param string The string that should be added to the end of the item's lore (withour colors)
     * */
    public ItemBuilder addLore(String string) {
        if (string != null)
            this.lore.add(string);
        return this;
    }

    /**
     * @param string The string that should be added to the end of the item's lore
     * @param colorize If true, the name will be translated to include the proper color codes
     * */
    public ItemBuilder addLore(String string, boolean colorize) {
        if (string == null)
            return this;
        this.lore.add(colorize ? ChatColor.translateAlternateColorCodes('&', string) : string);
        return this;
    }

    /**
     * @param itemFlag The item flag that should be added to the item
     * */
    public ItemBuilder addFlag(ItemFlag itemFlag) {
        if (itemFlag != null)
            this.itemFlags.add(itemFlag);
        return this;
    }

    /**
     * @param enchantment The enchantment that should be added to the item
     * @param level The level of the enchantment to be added
     * */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        if (enchantment != null && level > 0)
            enchantments.put(enchantment, level);
        return this;
    }

    /**
     * @return The material type of the ItemStack.
     * */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * @return The amount of items to be included in the stack.
     * */
    public int getAmount() {
        return this.amount;
    }

    /**
     * @return The durability to be applied to the item.
     * */
    public short getDurability() {
        return this.durability;
    }

    /**
     * @return The display name to be applied to the item.
     * */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * @return The list of lore to be applied to the item.
     * */
    public List<String> getLore() {
        return this.lore;
    }

    /**
     * @return The list of ItemFlags to be applied to the item.
     * */
    public List<ItemFlag> getItemFlags() {
        return this.itemFlags;
    }

    /**
    * @return A map of all enchantment to be applied to the item.
    * */
    public Map<Enchantment, Integer> getEnchantments() {
        return this.enchantments;
    }

    /**
     * @return The ItemMeta to be applied to the item.
     * */
    public ItemMeta getMeta() {
        return this.meta;
    }

    /**
     * Builds the item converting it from an ItemBuilder to an ItemStack.
     *
     * @return The final ItemStack.
     * */
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
        if (enchantments.size() > 0)
            itemStack.addUnsafeEnchantments(enchantments);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
