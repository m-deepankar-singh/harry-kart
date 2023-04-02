package com.atg.harrykart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;
/**
 * Part of HarryKart Object which represents the Loop of each Horse contains list of Lanes and number
 * @author Parasuram
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "loop")
@XmlAccessorType(XmlAccessType.FIELD)
public class Loop {

	private List<Lane> lane;
	@XmlAttribute
	private int number;
}