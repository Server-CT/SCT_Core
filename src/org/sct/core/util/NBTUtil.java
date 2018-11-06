package org.sct.core.util;

import java.lang.reflect.Method;

import org.sct.core.dto.SlotType;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_11_R1.NBTTagByte;
import net.minecraft.server.v1_11_R1.NBTTagCompound;

public class NBTUtil {

//	/**
//	 * 各类属性
//	 */
//	private static String attackDamage = "generic.attackDamage";
//	private static String attackSpeed = "generic.attackSpeed";
//	private static String maxHealth = "generic.maxHealth";
//	private static String moveMentSpeed = "generic.movementSpeed";
//	private static String armor = "generic.armor";
//	private static String luck = "generic.luck";

//	public static Object NBTTagString(String str) {
//		try {
//			return NMSUtil.getNMSClass("NBTTagString").getConstructor(String.class).newInstance(str);
//		} catch (Exception e) {
//			System.out.println("错误: " + e.getMessage());
//		}
//		return null;
//	}

    public static Object NBTTagInt(int num) {
        try {
            return NMSUtil.getNMSClass("NBTTagInt").getConstructor(Integer.TYPE).newInstance(num);
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return null;
    }

    public static Object NBTTagDouble(double num) {
        try {
            return NMSUtil.getNMSClass("NBTTagDouble").getConstructor(Double.TYPE).newInstance(num);
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return null;
    }
//
//	public static Object NBTTagByte(byte num) {
//		try {
//			return NMSUtil.getNMSClass("NBTTagByte").getConstructor(Byte.TYPE).newInstance(num);
//		} catch (Exception e) {
//			System.out.println("错误: " + e.getMessage());
//		}
//		return null;
//	}

    public static Object NBTTagFloat(float num) {
        try {
            return NMSUtil.getNMSClass("NBTTagFloat").getConstructor(Float.TYPE).newInstance(num);
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return null;
    }

    public static Object getItemNBT(ItemStack is) {
        Object nmsItem = NMSUtil.getNMSItem(is);
        if (nmsItem == null) {
            return null;
        }
        try {
            return nmsItem.getClass().getMethod("getTag").invoke(nmsItem) != null ? nmsItem.getClass().getMethod("getTag").invoke(nmsItem) : NMSUtil.getNMSClass("NBTTagCompound").newInstance();
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return null;
    }

    public static void setUnbreakable(ItemStack is, boolean unbreak) {
        Object nmsItem = NMSUtil.getNMSItem(is);
        Object itemNbt = getItemNBT(is);
        Method set;
        //ItemStack bukItem = null;
        try {
            net.minecraft.server.v1_11_R1.ItemStack nms = CraftItemStack.asNMSCopy(is);
            NBTTagCompound nbt = nms.hasTag() ? nms.getTag() : new NBTTagCompound();
            System.out.println(nbt);
            nbt.set("Unbreakable", new NBTTagByte((byte) 1));

            //set = itemNbt.getClass().getMethod("set", new Class[] { String.class, NMSUtil.getNMSClass("NBTBase") });

            //set.invoke(itemNbt, new Object[] { "Unbreakable", new net.minecraft.server.v1_10_R1.NBTTagByte((byte) 1)});
            //set.invoke(itemNbt, new Object[] { "Unbreakable", NBTTagByte() });
            //itemNbt.set("Unbreakable", new NBTTagByte((byte) (unbreak ? 1 : 0)));
            //nmsItem.getClass().getMethod("setTag", NMSUtil.getNMSClass("NBTTagCompound")).invoke(nmsItem, itemNbt);
            nms.setTag(nbt);
            System.out.println(nbt);
            //nmsItem.setTag(itemNbt);
            //bukItem = (ItemStack) NMSUtil.getOBCClass("inventory.CraftItemStack").getMethod("asBukkitCopy", NMSUtil.getNMSClass("ItemStack")).invoke(nmsItem, nmsItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return bukItem;
    }

    /**
     * 取武器的Attribute数据
     * @param is 物品
     * @return NBTTagCompound的实例
     */
    public static Object getAttribute(ItemStack is) {
        Object nmsItem = NMSUtil.getNMSItem(is);
        //判断是否有无Tag数据
        try {
            if (nmsItem.getClass().getMethod("getTag").invoke(nmsItem) != null) {
                return nmsItem.getClass().getMethod("getTag").invoke(nmsItem);
            }else {
                //如果没有Tag数据则实例化个NBTTagCompound的实例
                return NMSUtil.getNMSClass("NBTTagCompound").newInstance();
            }
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }

        return null;
    }


    /**
     * 设置物品伤害
     * @param is 物品
     * @param damage 伤害
     * @param slot 位置
     * @return 物品的ItemStack
     */
    public static ItemStack setItemDamage(ItemStack is, int damage, SlotType slot) {
        try {
            //物品的nms对象
            Object nmsItem = is.getClass().getMethod("asNMSCopy", ItemStack.class).invoke(is, is);
            //物品的nbt对象
            Object itemNbt = nmsItem.getClass().getMethod("getTag").invoke(nmsItem) != null ? nmsItem.getClass().getMethod("getTag").invoke(nmsItem) : NMSUtil.getNMSClass("NBTTagCompound").newInstance();
            //NbtTagList对象
            Object modifiers = NMSUtil.getNMSClass("NBTTagList").getConstructor().newInstance();
            Object damageTag = NMSUtil.getNMSClass("NBTTagCompound").getConstructor().newInstance();
            //模块数据构造
            Object attackDamage = NMSUtil.getNMSClass("NBTTagString").getConstructor(String.class).newInstance("generic.attackDamage");
            Object name = NMSUtil.getNMSClass("NBTTagString").getConstructor(String.class).newInstance("Damage");
            Object amount = NMSUtil.getNMSClass("NBTTagInt").getConstructor(Integer.TYPE).newInstance(damage);
            Object operation = NMSUtil.getNMSClass("NBTTagInt").getConstructor(Integer.TYPE).newInstance(0);
            Object uuidLeast = NMSUtil.getNMSClass("NBTTagInt").getConstructor(Integer.TYPE).newInstance(894654);
            Object uuidMost = NMSUtil.getNMSClass("NBTTagInt").getConstructor(Integer.TYPE).newInstance(2872);
            Object slotTag = NMSUtil.getNMSClass("NBTTagString").getConstructor(String.class).newInstance(slot.toString());
            //模块数据输入
            damageTag.getClass().getMethod("set", new Class[] {String.class, NMSUtil.getNMSClass("NBTBase")}).invoke(damageTag, new Object[] { "AttributeName" , attackDamage});
            damageTag.getClass().getMethod("set", new Class[] {String.class, NMSUtil.getNMSClass("NBTBase")}).invoke(damageTag, new Object[] { "Name" , name});
            damageTag.getClass().getMethod("set", new Class[] {String.class, NMSUtil.getNMSClass("NBTBase")}).invoke(damageTag, new Object[] { "Amount" , amount});
            damageTag.getClass().getMethod("set", new Class[] {String.class, NMSUtil.getNMSClass("NBTBase")}).invoke(damageTag, new Object[] { "Operation" , operation});
            damageTag.getClass().getMethod("set", new Class[] {String.class, NMSUtil.getNMSClass("NBTBase")}).invoke(damageTag, new Object[] { "UUIDLeast" , uuidLeast});
            damageTag.getClass().getMethod("set", new Class[] {String.class, NMSUtil.getNMSClass("NBTBase")}).invoke(damageTag, new Object[] { "UUIDMost" , uuidMost});
            damageTag.getClass().getMethod("set", new Class[] {String.class, NMSUtil.getNMSClass("NBTBase")}).invoke(damageTag, new Object[] { "Slot" , slotTag});
            //NbtTagList数据导入
            modifiers.getClass().getMethod("add", NMSUtil.getNMSClass("NBTBase")).invoke(modifiers, damageTag);
            //设置该NbtTagList
            itemNbt.getClass().getMethod("set", new Class[] {String.class, NMSUtil.getNMSClass("NBTBase")}).invoke(itemNbt, new Object[] { "AttributeModifiers", modifiers});
            //保存nbt数据
            nmsItem.getClass().getMethod("setTag", NMSUtil.getNMSClass("NBTTagCompound")).invoke(nmsItem, itemNbt);
            ItemStack bukItem = (ItemStack) is.getClass().getMethod("asBukkitCopy", NMSUtil.getNMSClass("ItemStack")).invoke(nmsItem, nmsItem);
            return bukItem;
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return is;
    }


}
