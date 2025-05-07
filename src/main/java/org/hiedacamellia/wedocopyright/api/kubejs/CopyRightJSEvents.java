package org.hiedacamellia.wedocopyright.api.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface CopyRightJSEvents {

    EventGroup EVENT_GROUP = EventGroup.of("WeDoCopyRightEvents");

    EventHandler ADD_COPYRIGHT_PAGE = EVENT_GROUP.client("addPage", ()->AddCopyRightPageEventJS.class);


}
