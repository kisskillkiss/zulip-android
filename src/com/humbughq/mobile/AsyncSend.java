package com.humbughq.mobile;

import org.json.JSONArray;

public class AsyncSend extends HumbugAsyncPushTask {

    /**
     * Initialise an AsyncSend task to send a specific message.
     * 
     * @param humbugActivity
     *            The calling Activity
     * @param msg
     *            The mesasge to send.
     */
    public AsyncSend(HumbugActivity humbugActivity, Message msg) {
        super(humbugActivity);
        this.setProperty("type", msg.getType().toString());
        if (msg.getType() == MessageType.STREAM_MESSAGE) {
            this.setProperty("to", msg.getStream());
        } else {
            JSONArray arr = new JSONArray();
            for (String email : msg.getReplyToArray()) {
                arr.put(email);
            }
            this.setProperty("to", arr.toString());
        }
        this.setProperty("stream", msg.getSubject());
        this.setProperty("subject", msg.getSubject());
        this.setProperty("content", msg.getContent());
    }

    public final void execute() {
        execute("api/v1/send_message");
    }

}