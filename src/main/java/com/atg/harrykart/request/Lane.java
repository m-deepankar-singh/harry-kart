package com.atg.harrykart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

/**
 * Part of HarryKart Object which represents the Lane of each Horse
 * @author Parasuram
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "lane")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lane {
	@XmlAttribute
	private int number;
	@XmlValue
	private int text;
}
