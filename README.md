# ItemBuilder
A single class item utility builder that allows for single line item setting.


# Example usage

```java

ItemStack itemStack = new ItemBuilder(Material.BEACON)
               .setDisplayName("&5&lDisplay Name", true)
               .addLore("lore one")
               .addLore("&blore two", true)
               .addEnchantment(Enchantment.DURABILITY, 99)
               .setAmount(52)
               .build();
```
