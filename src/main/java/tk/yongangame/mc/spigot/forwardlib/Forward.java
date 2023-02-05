package tk.yongangame.mc.spigot.forwardlib;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.plugin.messaging.PluginMessageRecipient;
import tk.yongangame.mc.data.PlayerData;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class Forward<T> {
    private final int IDX = 1;
    private final String channel;

    private Class<T> aClass;
    public final Gson gson = new Gson();
    private static final JavaPlugin plugin = ForwardLib.getPlugin(ForwardLib.class);

    public Forward(String channel) {
        this.channel = channel;
    }

    public void Register(PluginMessageListener listener){
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(plugin, channel, listener);
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(plugin, channel);
    }

    public void Send(Player player, T msg) {
        try {
            Class<? extends CommandSender> senderClass = player.getClass();
            Method addChannel = senderClass.getDeclaredMethod("addChannel", String.class);
            addChannel.setAccessible(true);
            addChannel.invoke(player, channel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] bytes = gson.toJson(msg).getBytes(StandardCharsets.UTF_8);
        ByteBuf buf = Unpooled.buffer(bytes.length + 1);
        buf.writeByte(IDX);
        buf.writeBytes(bytes);
        player.sendPluginMessage(plugin, channel, buf.array());
    }

    public T Read(byte[] array) {
        ByteBuf buf = Unpooled.wrappedBuffer(array);
        if (buf.readUnsignedByte() == IDX) {
            return gson.fromJson(buf.toString(StandardCharsets.UTF_8),aClass);
        } else throw new RuntimeException();
    }
}
