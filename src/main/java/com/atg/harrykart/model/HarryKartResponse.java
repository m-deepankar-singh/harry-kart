package com.atg.harrykart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * HarryKartResponse which returns top 3 horses and its positions and names
 * @author Parasuram
 */
@Getter
@Setter
@AllArgsConstructor
public class HarryKartResponse {
    private int position;
    private String horseName;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null
                || this.getClass() != obj.getClass())
            return false;
        HarryKartResponse harryKartResponse = (HarryKartResponse) obj;
        return this.position == harryKartResponse.position
                && this.horseName.equals(harryKartResponse.horseName);
    }

}
