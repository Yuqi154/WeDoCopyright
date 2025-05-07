package org.hiedacamellia.wedocopyright.client.config;

import me.shedaniel.autoconfig.AutoConfig;

public interface CRConfig {

    static void save() {
        AutoConfig.getConfigHolder(CRClientConfig.class).save();
    }
    static CRClientConfig get() {
        return AutoConfig.getConfigHolder(CRClientConfig.class).getConfig();
    }

}
