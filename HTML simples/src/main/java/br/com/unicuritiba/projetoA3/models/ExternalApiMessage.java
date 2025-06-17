package br.com.unicuritiba.projetoA3.models;

import com.fasterxml.jackson.annotation.JsonProperty; // Adicionar para customizar o JSON (se necessário)

public class ExternalApiMessage {
    private String number;
    private String body;
    private String userId;
    private String queueId;
    private Boolean sendSignature;
    private Boolean closeTicket;

    // Construtor com todos os campos
    public ExternalApiMessage(String number, String body, String userId, String queueId, Boolean sendSignature, Boolean closeTicket) {
        this.number = number;
        this.body = body;
        this.userId = userId;
        this.queueId = queueId;
        this.sendSignature = sendSignature;
        this.closeTicket = closeTicket;
    }

    // Construtor padrão (pode ser útil para deserialização, embora não seja o caso aqui)
    public ExternalApiMessage() {
    }

    // Getters
    public String getNumber() {
        return number;
    }

    public String getBody() {
        return body;
    }

    // Usando @JsonProperty para garantir o nome exato no JSON se for diferente do camelCase do Java
    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("queueId")
    public String getQueueId() {
        return queueId;
    }

    @JsonProperty("sendSignature")
    public Boolean getSendSignature() {
        return sendSignature;
    }

    @JsonProperty("closeTicket")
    public Boolean getCloseTicket() {
        return closeTicket;
    }

    // Setters (opcionais se você sempre usar o construtor, mas geralmente boa prática)
    public void setNumber(String number) {
        this.number = number;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public void setSendSignature(Boolean sendSignature) {
        this.sendSignature = sendSignature;
    }

    public void setCloseTicket(Boolean closeTicket) {
        this.closeTicket = closeTicket;
    }

    // Opcional: toString para facilitar o debug, especialmente ao imprimir o JSON
    @Override
    public String toString() {
        return "{" +
               "\"number\":\"" + number + '\"' +
               ", \"body\":\"" + body + '\"' +
               ", \"userId\":\"" + userId + '\"' +
               ", \"queueId\":\"" + queueId + '\"' +
               ", \"sendSignature\":" + sendSignature +
               ", \"closeTicket\":" + closeTicket +
               '}';
    }
}