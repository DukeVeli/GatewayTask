package com.api.gateway.data.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement (name = "command")
@XmlAccessorType (XmlAccessType.FIELD)
public class XmlDtoCurrent {
    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute (name = "consumer")
    private String consumer;

    @XmlElement(name = "currency")
    private String currency;


}
