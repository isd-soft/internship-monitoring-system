package org.inthergroup.ims.email;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Email {
    private List<String> recipients;
    private String subject;
    private String body;
    private String to;
    private String text;
}
