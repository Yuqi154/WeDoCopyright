package org.hiedacamellia.wedocopyright.api.kubejs;

import org.hiedacamellia.wedocopyright.WeDoCopyRight;
import org.hiedacamellia.wedocopyright.api.event.AddCopyRightPageEvent;

public class CREventPoster {

    public static final CREventPoster INSTANCE = new CREventPoster();

    public void post(AddCopyRightPageEvent event){
        if(WeDoCopyRight.kubeJsLoaded) {
            post(new AddCopyRightPageEventJS(event));
        }
    }

    public void post(AddCopyRightPageEventJS event) {
        CopyRightJSEvents.ADD_COPYRIGHT_PAGE.post(event);
    }


}
