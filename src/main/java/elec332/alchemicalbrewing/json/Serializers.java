package elec332.alchemicalbrewing.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import elec332.alchemicalbrewing.registry.PotionRegistry;
import elec332.alchemicalbrewing.registry.WrappedPotion;
import elec332.core.json.JsonHandler;
import net.minecraft.potion.Potion;

import java.io.IOException;

/**
 * Created by Elec332 on 19-8-2015.
 */
public class Serializers {

    public static final JsonHandler.ElecFactory<WrappedPotion> wrappedPotion = new JsonHandler.ElecFactory<WrappedPotion>() {
        @Override
        public TypeAdapter<WrappedPotion> getTypeAdapter() {
            return new TypeAdapter<WrappedPotion>() {
                @Override
                public void write(JsonWriter out, WrappedPotion value) throws IOException {
                    if (value == null) {
                        out.nullValue();
                        return;
                    }
                    out.beginObject();
                    out.name("potionName").value(value.getName());
                    out.name("strength").value(value.getStrength());
                    out.name("splash").value(value.isSplash());
                    out.endObject();
                }

                @Override
                public WrappedPotion read(JsonReader in) throws IOException {
                    if (in.peek() == JsonToken.NULL) {
                        in.nextNull();
                        return null;
                    }
                    int strength = 0;
                    boolean splash = false;
                    String potion = "";
                    in.beginObject();
                    while (in.hasNext()){
                        String name  = in.nextName();
                        if (name.equals("potionName")){
                            potion = in.nextString();
                        } else if (name.equals("strength")){
                            strength = in.nextInt();
                        } else if (name.equals("splash")){
                            splash = in.nextBoolean();
                        }
                    }
                    in.endObject();
                    Potion p = PotionRegistry.getPotionByName(potion);
                    if (p == null)
                        return new WrappedPotion(potion, strength, splash);
                    return new WrappedPotion(p, strength, splash);
                }
            };
        }

        @Override
        public Class<WrappedPotion> getFactoryClass() {
            return WrappedPotion.class;
        }
    };

}
