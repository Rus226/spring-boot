package com.example.bootweb.jfr.event;

import com.example.bootweb.jfr.JfrEventType;
import jdk.jfr.Category;
import jdk.jfr.DataAmount;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name(JfrEventType.EVENT_NAME_PREFIX + "Request")
@Label("HTTP Request")
@Category({"HTTP", "Request"})
public class RequestEvent extends Event {
    @Label("HTTP method")
    public String method;
    @Label("HTTP uri")
    public String uri;
    @Label("HTTP Body Size")
    @DataAmount
    public long size;
}
