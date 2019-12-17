package com.嘤嘤嘤.qwq.MailBox.API;

import com.嘤嘤嘤.qwq.MailBox.GlobalConfig;
import com.嘤嘤嘤.qwq.MailBox.MailBox;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class Placeholder extends PlaceholderExpansion {
    
    @Override
    public String onPlaceholderRequest(Player p, String identifier){

        if(p == null){
            return "";
        }
        
        // 服务器system邮件数量
        if(identifier.equals("server_mail_system")){
            return Integer.toString(MailBox.MailListSystem.size());
        }
        // 服务器permission邮件数量
        if(identifier.equals("server_mail_permission")){
            return Integer.toString(MailBox.MailListPermission.size());
        }
        // 服务器player邮件数量
        if(identifier.equals("server_mail_player")){
            return Integer.toString(MailBox.MailListPlayer.size());
        }
        // 服务器允许发送物品的最大数量
        if(identifier.equals("server_send_item")){
            return Integer.toString(GlobalConfig.maxItem);
        }
        // 服务器发送一封邮件消耗的金币
        if(identifier.equals("server_send_expand_vault")){
            return Double.toString(GlobalConfig.vaultExpand);
        }
        // 服务器发送一封邮件消耗的点券
        if(identifier.equals("server_send_expand_point")){
            return Double.toString(GlobalConfig.playerPointsExpand);
        }
        // 服务器每附带一个物品额外消耗的金币
        if(identifier.equals("server_send_expand_item_vault")){
            return Double.toString(GlobalConfig.vaultItem);
        }
        // 服务器每附带一个物品额外消耗的点券
        if(identifier.equals("server_send_expand_item_point")){
            return Double.toString(GlobalConfig.playerPointsItem);
        }
        // 服务器允许发送的最大金币
        if(identifier.equals("server_send_vault")){
            return Double.toString(GlobalConfig.vaultMax);
        }
        // 服务器允许发送的最大点券
        if(identifier.equals("server_send_point")){
            return Integer.toString(GlobalConfig.playerPointsMax);
        }
        
        // 玩家可领取system邮件数量
        if(identifier.equals("player_mail_system")){
            return Integer.toString(MailBox.getRelevantMailList(p, "system").get("asRecipient").size());
        }
        // 玩家可领取permission邮件数量
        if(identifier.equals("player_mail_permission")){
            return Integer.toString(MailBox.getRelevantMailList(p, "permission").get("asRecipient").size());
        }
        // 玩家可领取player邮件数量
        if(identifier.equals("player_mail_player")){
            return Integer.toString(MailBox.getRelevantMailList(p, "player").get("asRecipient").size());
        }
        // 玩家可发送物品的最大数量
        if(identifier.equals("player_send_item")){
            return Integer.toString(MailBoxAPI.playerSendItemAllow(p));
        }
        // 玩家已发送player邮件的数量
        if(identifier.equals("player_send_number")){
            return Integer.toString(MailBoxAPI.playerAsSender(p));
        }
        // 玩家可发送player邮件的最大数量
        if(identifier.equals("player_send_max")){
            return Integer.toString(MailBoxAPI.playerAsSenderAllow(p));
        }
        
        return null;
    }

    @Override
    public String getIdentifier() {
        return "mailbox";
    }

    @Override
    public String getAuthor() {
        return MailBox.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return MailBox.getInstance().getDescription().getVersion();
    }
    
    @Override
    public boolean persist(){
        return true;
    }
    
    @Override
    public boolean canRegister(){
        return true;
    }

}
