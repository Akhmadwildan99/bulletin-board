package com.bulletin.bulletinboard.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("data")
    private T data;

    public ApiResponse(Meta meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ApiResponse<?> that = (ApiResponse<?>) o;
        return Objects.equals(meta, that.meta) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meta, data);
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }

    public static class Meta {
        @JsonProperty("status")
        private String status;

        @JsonProperty("message")
        private String message;

        @JsonProperty("code")
        private int code;

        public Meta(String status, String message, int code) {
            this.status = status;
            this.message = message;
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Meta meta = (Meta) o;
            return code == meta.code && Objects.equals(status, meta.status) && Objects.equals(message, meta.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(status, message, code);
        }

        @Override
        public String toString() {
            return "Meta{" +
                    "status='" + status + '\'' +
                    ", message='" + message + '\'' +
                    ", code=" + code +
                    '}';
        }
    }
}
