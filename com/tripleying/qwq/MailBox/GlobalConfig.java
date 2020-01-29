package com.tripleying.qwq.MailBox;

import com.tripleying.qwq.MailBox.API.MailBoxAPI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

public class GlobalConfig {
    public static boolean server_under_1_11 = false;
    public static boolean server_under_1_10 = false;
    public static boolean server_under_1_9 = false;
    public static boolean server_under_1_8 = false;
    public static boolean vexview_over_2_5;
    public static boolean vexview_under_2_5;
    public static boolean enVexView;
    public static boolean enVault;
    public static boolean enPlayerPoints;
    
    public static String language;
    public static boolean fileSQL;
    public static String pluginPrefix;
    public static List<String> tips;
    public static Sound tipsSound;
    public static int maxItem;
    public static String fileBanLore;
    public static List<String> fileBanId;
     public static HashMap<String, int[]> fileRandomLoreChange = new HashMap();
    public static HashMap<String, List<String>> fileRandomLoreSelect = new HashMap();
    public static int topicMax;
    public static int contentMax;
    public static String playerExpiredDay;
    public static int playerMultiplayer;
    public static int playerOut;
    public static int timesCount;
    public static int cdkeyDay;
    public static double vaultMax;
    public static double vaultExpand;
    public static double vaultItem;
    public static int playerPointsMax;
    public static int playerPointsExpand;
    public static int playerPointsItem;
    
    public static void setGlobalConfig(FileConfiguration config){
        // 语言
        Bukkit.getConsoleSender().sendMessage(ConfigMessage.lang_check);
        String lang = config.getString("language");
        try {
            if(MailBoxAPI.existFiles("/Message/"+lang) || MailBoxAPI.getDefaultLanguage().contains(lang)){
                language = lang;
            }else{
                Bukkit.getConsoleSender().sendMessage(ConfigMessage.lang_not_exist);
                language = "zh_cn";
            }
        } catch (IOException ex) {
            Bukkit.getConsoleSender().sendMessage(ConfigMessage.lang_error);
            language = "zh_cn";
        }
        Message.setLanguage(language);
        fileSQL = config.getBoolean("database.fileSQL");// 是否使用数据库存储附件
        tips = config.getStringList("mailbox.newMailTips");// 新消息提示
        // 提示声音
        try{
            tipsSound = Sound.valueOf(config.getString("mailbox.newMailTipsSound"));
        }catch(Exception e){
            if(server_under_1_9) tipsSound = Sound.valueOf("LEVEL_UP");
            else tipsSound = Sound.valueOf("ENTITY_PLAYER_LEVELUP");
        }
        // 附件
        maxItem = config.getInt("mailbox.file.maxItem");// 最大发送物品数量
        fileBanLore = config.getString("mailbox.file.ban.lore");// Lore中包含此文字的物品禁止作为附件发送
        fileBanId = formatMaterial(config.getStringList("mailbox.file.ban.id"));// ID在此列表中的物品禁止作为附件发送
        fileRandomLoreChange.clear();// 物品被领取时随机修改的Lore
        config.getStringList("mailbox.file.random.lore.change").forEach(v -> {
            int t = v.indexOf("->");
            fileRandomLoreChange.put(v.substring(0, t), String2int(v.substring(t+2).split("~")));
        });
        System.out.println(ConfigMessage.random_lore_int);
        if(!fileRandomLoreChange.isEmpty()) fileRandomLoreChange.forEach((k,v) -> System.out.println("§6-----§r"+k+" → ["+v[0]+","+v[1]+"]"));
        fileRandomLoreSelect.clear();// 物品被领取时随机添加的Lore
        config.getConfigurationSection("mailbox.file.random.lore.select").getKeys(false).forEach((section) -> {
            fileRandomLoreSelect.put(config.getString("mailbox.file.random.lore.select."+section+".lore"), config.getStringList("mailbox.file.random.lore.select."+section+".list"));
        });
        System.out.println(ConfigMessage.random_lore_select);
        if(!fileRandomLoreSelect.isEmpty()) fileRandomLoreSelect.forEach((k,v) -> System.out.println("§6-----§r"+k));
        // 邮件
        topicMax = config.getInt("mailbox.topic_max");// 邮件主题最大长度
        contentMax = config.getInt("mailbox.content_max");// 邮件内容最大长度
        // player邮件
        playerExpiredDay = config.getString("mailbox.player.expire");// 过期时间
        playerMultiplayer = config.getInt("mailbox.player.multiplayer");// 单封邮件最大玩家数量
        playerOut = config.getInt("mailbox.player.out");// 玩家player邮件发件最大数量
        // times邮件
        timesCount = config.getInt("mailbox.times_count");//单封times邮件最大数量
        // cdkey邮件
        cdkeyDay = config.getInt("mailbox.cdkey_day");// 玩家每日可输入兑换码的次数
        // [Vault]设置
        vaultMax = config.getDouble("mailbox.vault.max");// 单次邮件发送最大值
        vaultExpand = config.getDouble("mailbox.vault.expand");// 发送邮件时所消耗的金钱
        vaultItem = config.getDouble("mailbox.vault.item");// 每多一个附件物品增加的金钱消耗
        // [PlayerPoints]设置
        playerPointsMax = config.getInt("mailbox.player_points.max");// 单次邮件发送最大值
        playerPointsExpand = config.getInt("mailbox.player_points.expand");// 发送邮件时所消耗的点券
        playerPointsItem = config.getInt("mailbox.player_points.item");// 每多一个附件物品增加的点券消耗
    }
    
    private static List<String> formatMaterial(List<String> idList){
        List<String> material = new ArrayList();
        idList.stream().map((id) -> {
            if(id.length()>9 && id.substring(0, 10).equalsIgnoreCase("minecraft:")) id = id.substring(10);
            return id;
        }).map((id) -> {
            if(id.contains(":")) id = id.replace(":", "_");
            return id;
        }).map((id) -> id.toUpperCase()).forEach((id) -> {
            material.add(id);
        });
        return material;
    }
    
    private static int[] String2int(String[] s){
        int[] i = new int[s.length];
        for(int j=0;j<s.length;j++){
            i[j] = Integer.parseInt(s[j]);
        }
        return i;
    }
    
}