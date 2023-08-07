/*
 * Weliyek Java Library
 * Copyright (C) 2023  Ricardo Villalobos - All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package weliyek.amat.base;

import java.util.Objects;

import weliyek.base.WkException;
import weliyek.serialization.base.WkSzSegmentException;

public class WkSzPacketFieldException extends WkSzSegmentException
{

    private static final long serialVersionUID = 2020_05_01_000L;

    private final WkSzPacketField<?,?,?> packetField;

    public WkSzPacketFieldException(WkSzPacketField<?,?,?> packetField) {
        super(packetField);
        this.packetField = Objects.requireNonNull(packetField);
    }

    public WkSzPacketFieldException(
        WkSzPacketField<?,?,?> packet,
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace) {
        super(packet, message, cause, enableSuppression, writableStackTrace);
        this.packetField = Objects.requireNonNull(packet);
    }

    public WkSzPacketFieldException(WkSzPacketField<?,?,?> packet, String message, Throwable cause) {
        super(packet, message, cause);
        this.packetField = Objects.requireNonNull(packet);
    }

    public WkSzPacketFieldException(WkSzPacketField<?,?,?> packet, String message) {
        super(packet, message);
        this.packetField = Objects.requireNonNull(packet);
    }

    public WkSzPacketFieldException(WkSzPacketField<?,?,?> packet, Throwable cause) {
        super(packet, cause);
        this.packetField = Objects.requireNonNull(packet);
    }

    public final WkSzPacketField<?,?,?> packetField() {
        return this.packetField;
    }

}
