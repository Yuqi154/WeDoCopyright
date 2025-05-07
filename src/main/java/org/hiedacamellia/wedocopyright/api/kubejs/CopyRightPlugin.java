package org.hiedacamellia.wedocopyright.api.kubejs;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;

public class CopyRightPlugin implements KubeJSPlugin {

    @Override
    public void init() {

    }

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(CopyRightJSEvents.EVENT_GROUP);
    }


}
