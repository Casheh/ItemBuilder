# ItemBuilder
A single class item utility builder that allows for single line item setting.

# Instructions
Create a new class in your project titled `ItemBuilder`. From there, all you have to do is reference the class.

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
# Supported versions
This particular class has been tested on versions `1.12` through `1.14.4`. It may work on newer versions, but some methods used are deprecated in the newer Bukkit versions.
