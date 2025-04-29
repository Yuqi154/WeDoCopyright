package org.hiedacamellia.wedocopyright.api.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import org.hiedacamellia.wedocopyright.WeDoCopyRight;

public interface CopyRightJSEvents {

    EventGroup EVENT_GROUP = EventGroup.of(WeDoCopyRight.MODID);

    EventHandler ADD_COPYRIGHT_PAGE = EVENT_GROUP.client("add_copy_right_page", ()->AddCopyRightPageEventJS.class);


}
