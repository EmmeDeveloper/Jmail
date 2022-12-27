package jmail.lib.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Email {
    // TODO: final id
    private String id;
    private String subject;
    private String body;
    private final @NonNull String sender;
    private final @NonNull List<String> recipients = new ArrayList<>();
    private @NonNull Date date;
    private @NonNull Boolean read;

    // Void constructor for Jackson
    public Email() {
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.read = false;
        this.sender = "";
    }

    public Email(
            String id,
            String subject,
            String body,
            @NonNull String sender,
            @NonNull List<String> recipients,
            @NonNull Date date,
            @NonNull Boolean read) {
        if (sender.isEmpty()) {
            throw new IllegalArgumentException("Sender cannot be empty");
        }
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.recipients.clear();
        this.recipients.addAll(recipients);
        this.date = date;
        this.read = read;
    }

    public String fileID() {
        return String.format(
                "%s_%s",
                id == null || id.isEmpty() ? UUID.randomUUID() : id,
                date.toInstant().getEpochSecond());
    }

    public void setRecipients(List<String> recipients) {
        this.recipients.clear();
        this.recipients.addAll(recipients);
    }
}
