package com.atg.harrykart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Input XML Equivalent Java Object for HarryKary Game
 *
 * @author Parasuram
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "harryKart")
@XmlAccessorType(XmlAccessType.FIELD)
public class HarryKart {
    private int numberOfLoops;
    @XmlElementWrapper(name = "startList")
    @XmlElement
    private List<Participant> participant;

    @XmlElementWrapper(name = "powerUps")
    @XmlElement(name = "loop")
    private List<Loop> loop;
}
