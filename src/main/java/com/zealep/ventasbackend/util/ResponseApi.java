package com.zealep.ventasbackend.util;

public class ResponseApi {
    private String status;
    private String accessToken;
    private String idToken;
    private String refreshToken;
    private String sessionId;
    private Long idEntity;

    private Object body;

    public ResponseApi() {}

    public ResponseApi(String status,Long idEntity, Object body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Object getBody() {
        return body;
    }
    public void setBody(Object body) {
        this.body = body;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(Long idEntity) {
        this.idEntity = idEntity;
    }
}
