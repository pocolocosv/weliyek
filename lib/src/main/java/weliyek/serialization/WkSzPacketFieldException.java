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
package weliyek.serialization;

import java.util.Objects;

public class WkSzPacketFieldException extends WkSzSegmentException
{

    private static final long serialVersionUID = 2020_05_01_000L;

    private final WkSerdeDTreeNodeDataComponent<?,?,?> packetField;

    public WkSzPacketFieldException(WkSerdeDTreeNodeDataComponent<?,?,?> packetField) {
        super(packetField);
        this.packetField = Objects.requireNonNull(packetField);
    }

    public WkSzPacketFieldException(
        WkSerdeDTreeNodeDataComponent<?,?,?> packet,
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace) {
        super(packet, message, cause, enableSuppression, writableStackTrace);
        this.packetField = Objects.requireNonNull(packet);
    }

    public WkSzPacketFieldException(WkSerdeDTreeNodeDataComponent<?,?,?> packet, String message, Throwable cause) {
        super(packet, message, cause);
        this.packetField = Objects.requireNonNull(packet);
    }

    public WkSzPacketFieldException(WkSerdeDTreeNodeDataComponent<?,?,?> packet, String message) {
        super(packet, message);
        this.packetField = Objects.requireNonNull(packet);
    }

    public WkSzPacketFieldException(WkSerdeDTreeNodeDataComponent<?,?,?> packet, Throwable cause) {
        super(packet, cause);
        this.packetField = Objects.requireNonNull(packet);
    }

    public final WkSerdeDTreeNodeDataComponent<?,?,?> packetField() {
        return this.packetField;
    }

}
