package com.densev.departments.rest.api;

/**
 * Created on: 10/08/18
 */
public class Message {

    private String header;
    private String message;

    public Message() {
    }

    private Message(Builder builder) {
        this.header = builder.header;
        this.message = builder.message;
    }

    public String getHeader() {
        return header;
    }

    public String getMessage() {
        return message;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder {
        private String header;
        private String message;

        private Builder() {
        }

        public static Builder aMessage() {
            return new Builder();
        }

        public Builder header(String header) {
            this.header = header;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
