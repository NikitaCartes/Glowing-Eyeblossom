package xyz.nikitacartes.glowingeyeblossom.config;

import com.google.common.io.Resources;
import org.apache.commons.text.StringSubstitutor;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.io.Resources.getResource;
import static java.nio.charset.StandardCharsets.UTF_8;

@ConfigSerializable
public class MainConfigV1 extends ConfigTemplate {
    public int openEyeblossomBrightness = 4;
    public int closedEyeblossomBrightness = 1;
    public int openEyeblossomFlowerpotBrightness = 13;
    public int closedEyeblossomFlowerpotBrightness = 3;

    public String configVersion = "1";

    public MainConfigV1() {
        super("main.conf");
    }

    public static MainConfigV1 load() {
        MainConfigV1 config = loadConfig(MainConfigV1.class, "main.conf");
        if (config == null) {
            config = new MainConfigV1();
            config.save();
        }
        return config;
    }

    protected String handleTemplate() throws IOException {
        Map<String, String> configValues = new HashMap<>();
        configValues.put("openEyeblossomBrightness", wrapIfNecessary(openEyeblossomBrightness));
        configValues.put("closedEyeblossomBrightness", wrapIfNecessary(closedEyeblossomBrightness));
        configValues.put("openEyeblossomFlowerpotBrightness", wrapIfNecessary(openEyeblossomFlowerpotBrightness));
        configValues.put("closedEyeblossomFlowerpotBrightness", wrapIfNecessary(closedEyeblossomFlowerpotBrightness));
        configValues.put("configVersion", wrapIfNecessary(configVersion));
        String configTemplate = Resources.toString(getResource("config/" + configPath), UTF_8);
        return new StringSubstitutor(configValues).replace(configTemplate);
    }
}
