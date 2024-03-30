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

public class WkSerdeDtreeNodeDataComponentException extends WkSerdeDtreeNodeException
{

    private static final long serialVersionUID = 2020_05_01_000L;

    private final WkSerdeDtreeNodeDataComponent<?,?,?> dataComponent;

    public WkSerdeDtreeNodeDataComponentException(WkSerdeDtreeNodeDataComponent<?,?,?> dataComponent) {
        super(dataComponent);
        this.dataComponent = Objects.requireNonNull(dataComponent);
    }

    public WkSerdeDtreeNodeDataComponentException(
        WkSerdeDtreeNodeDataComponent<?,?,?> dataComponent,
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace) {
        super(dataComponent, message, cause, enableSuppression, writableStackTrace);
        this.dataComponent = Objects.requireNonNull(dataComponent);
    }

    public WkSerdeDtreeNodeDataComponentException(WkSerdeDtreeNodeDataComponent<?,?,?> dataComponent, String message, Throwable cause) {
        super(dataComponent, message, cause);
        this.dataComponent = Objects.requireNonNull(dataComponent);
    }

    public WkSerdeDtreeNodeDataComponentException(WkSerdeDtreeNodeDataComponent<?,?,?> dataComponent, String message) {
        super(dataComponent, message);
        this.dataComponent = Objects.requireNonNull(dataComponent);
    }

    public WkSerdeDtreeNodeDataComponentException(WkSerdeDtreeNodeDataComponent<?,?,?> dataComponent, Throwable cause) {
        super(dataComponent, cause);
        this.dataComponent = Objects.requireNonNull(dataComponent);
    }

    public final WkSerdeDtreeNodeDataComponent<?,?,?> dataComponent() {
        return this.dataComponent;
    }

}
