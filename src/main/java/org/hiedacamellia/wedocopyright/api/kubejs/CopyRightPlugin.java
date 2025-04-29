package org.hiedacamellia.wedocopyright.api.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;

public class CopyRightPlugin extends KubeJSPlugin {

    @Override
    public void init() {

    }

    @Override
    public void registerEvents() {
        CopyRightJSEvents.EVENT_GROUP.register();
    }


}
