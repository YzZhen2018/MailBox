package com.tripleying.qwq.MailBox;

import com.tripleying.qwq.MailBox.API.MailBoxAPI;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigMessage {
    
    public static String enable;
    public static String author;
    public static String website;
    public static String version;
    public static String server_version;
    public static String under1_11;
    public static String under1_10;
    public static String under1_9;
    public static String under1_8;
    public static String reflect_version;
    public static String folder_create;
    public static String file_create;
    public static String file_error;
    public static String file_read;
    public static String soft_depend_enable;
    public static String soft_depend_close;
    public static String soft_depend_disable;
    public static String vexview_under2_5_6;
    public static String vexview_over2_5;
    public static String vexview_under2_5;
    public static String vexview_under2_4;
    public static String lang_check;
    public static String lang_not_exist;
    public static String lang_error;
    public static String lang_setup;
    public static String random_lore_int;
    public static String random_lore_select;
    public static String vexview;
    public static String hud_box;
    public static String command_box;
    public static String single_box;
    public static String double_box;
    public static String sql_connect;
    public static String enabled;
    public static String disable;
    public static String placeholder_unhook;
    public static String sql_shutdown;
    public static String sql_shutdown_error;
    public static String diasbled;
    
    public static boolean set(MailBox mb){
        try {
            YamlConfiguration config;
            if(MailBoxAPI.existFiles("message")) config = YamlConfiguration.loadConfiguration(new File("plugins/MailBox", "message.yml"));
            else config = YamlConfiguration.loadConfiguration(new InputStreamReader(mb.getResource("message.yml"), "UTF-8"));
            enable = config.getString("enable");
            author = config.getString("author");
            website = config.getString("website");
            version = config.getString("version");
            server_version = config.getString("server-version");
            under1_11 = config.getString("under1-11");
            under1_10 = config.getString("under1-10");
            under1_9 = config.getString("under1-9");
            under1_8 = config.getString("under1-8");
            reflect_version = config.getString("reflect-version");
            folder_create = config.getString("folder-create");
            file_create = config.getString("file-create");
            file_error = config.getString("file-error");
            file_read = config.getString("file-read");
            soft_depend_enable = config.getString("soft-depend-enable");
            soft_depend_close = config.getString("soft-depend-close");
            soft_depend_disable = config.getString("soft-depend-disable");
            vexview_under2_5_6 = config.getString("vexview_under2-5-6");
            vexview_over2_5 = config.getString("vexview-over2-5");
            vexview_under2_5 = config.getString("vexview-under2-5");
            vexview_under2_4 = config.getString("vexview-under2-4");
            lang_check = config.getString("lang-check");
            lang_not_exist = config.getString("lang-not-exist");
            lang_error = config.getString("lang-error");
            lang_setup = config.getString("lang-setup");
            random_lore_int = config.getString("random-lore-int");
            random_lore_select = config.getString("random-lore-select");
            vexview = config.getString("vexview");
            hud_box = config.getString("hud-box");
            command_box = config.getString("command-box");
            single_box = config.getString("single-box");
            double_box = config.getString("double-box");
            sql_connect = config.getString("sql-connect");
            enabled = config.getString("enabled");
            disable = config.getString("disable");
            placeholder_unhook = config.getString("placeholder-unhook");
            sql_shutdown = config.getString("sql-shutdown");
            sql_shutdown_error = config.getString("sql-shutdown-error");
            diasbled = config.getString("diasbled");
            return true;
        }catch (Exception ex) {
            return false;
        }
    }
    
}