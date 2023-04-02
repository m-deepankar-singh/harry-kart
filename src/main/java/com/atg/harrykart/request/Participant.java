package com.atg.harrykart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Part of HarryKart Object which represents each Horse as a participant
 * @author Parasuram
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "participant")
@XmlAccessorType(XmlAccessType.FIELD)
public class Participant {
	@XmlElement
	private int lane;
	private String name;  //duplicate horseName with Lane
	private int baseSpeed;
}