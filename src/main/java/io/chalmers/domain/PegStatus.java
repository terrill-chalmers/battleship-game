package io.chalmers.domain;

import io.chalmers.utility.DisplayUtil;

public enum PegStatus {
    EMPTY (" "),
    OCCUPIED ("O"),
    HIT ("H"),
    MISS ("M");

    PegStatus(final String status) { this.status = status; }

    private final String status;

    public String getStatus() { return status; }

    public String getName() { return DisplayUtil.toTitleCase(this.name()); }
}
