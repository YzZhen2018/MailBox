package com.tripleying.qwq.MailBox.Mail;

import com.tripleying.qwq.MailBox.API.Event.MailCollectEvent;
import com.tripleying.qwq.MailBox.Utils.MailUtil;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CdkeyFileMail extends BaseFileMail implements MailCdkey {
    
    private boolean only;
    
    public CdkeyFileMail(int id, String sender, String topic, String content, String date, boolean only, String filename) {
        super("cdkey", id, sender, topic, content, date, filename);
        this.only = only;
    }
    public CdkeyFileMail(int id, String sender, String topic, String content, String date, boolean only, String filename, List<ItemStack> isl, List<String> cl, List<String> cd, double coin, int point) {
        super("cdkey", id, sender, topic, content, date, filename, isl, cl, cd, coin, point);
        this.only = only;
    }
    
    @Override
    public boolean sendData() {
        return MailUtil.setSend("cdkey", getId(), getSender(), "", "", getTopic(), getContent(), getDate(), "", 0, "", only, getFileName());
    }
    
    @Override
    public BaseMail removeFile() {
        return new CdkeyMail(getId(),getSender(),getTopic(),getContent(),getDate(),only);
    }
    
    @Override
    public boolean isOnly() {
        return only;
    }

    @Override
    public void setOnly(boolean only) {
        this.only = only;
    }
    
    @Override
    public boolean Collect(Player p){
        if(MailUtil.createBaseFileMail("player", 0, getSender(), Arrays.asList(p.getName()), "", getTopic(), getContent(), getDate(), "", 0, "", false, "", "0",getItemList(),getCommandList(),getCommandDescription(),getCoin(),getPoint()).Send(Bukkit.getConsoleSender(), null)){
            MailUtil.setCollect(getType(), getId(), p.getName());
            MailCollectEvent mse = new MailCollectEvent(this, p);
            Bukkit.getServer().getPluginManager().callEvent(mse);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean Delete(Player p){
        DeleteLocalCdkey();
        if(DeleteFile()){
            return DeleteData(p);
        }else{
            return false;
        }
    }

    @Override
    public boolean collectValidate(Player p) {
        return true;
    }

    @Override
    public boolean sendValidate(Player p, ConversationContext cc) {
        return true;
    }
    
}
