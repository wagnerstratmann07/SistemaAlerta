package br.com.unicuritiba.projetoA3.models;

public class MessageRequest {
    private String name;
    private String number;
    private String messageBody;

    // Construtor padrão (necessário para deserialização JSON)
    public MessageRequest() {
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getMessageBody() {
        return messageBody;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    // Opcional: toString para debug
    @Override
    public String toString() {
        return "MessageRequest{" +
               "name='" + name + '\'' +
               ", number='" + number + '\'' +
               ", messageBody='" + messageBody + '\'' +
               '}';
    }
}