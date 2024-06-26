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

public class WkSzStructComponentException extends WkSerdeDtreeNodeException
{

  private static final long serialVersionUID = 2018_03_05_000L;

  private final WkSerdeDtreeStructField<?> structComponent;

  public WkSzStructComponentException(WkSerdeDtreeStructField<?> structComponent) {
    super(structComponent);
    this.structComponent = Objects.requireNonNull(structComponent);
  }

  public WkSzStructComponentException(WkSerdeDtreeStructField<?> structComponent, String message) {
    super(structComponent, message);
    this.structComponent = Objects.requireNonNull(structComponent);
  }

  public WkSzStructComponentException(WkSerdeDtreeStructField<?> structComponent, Throwable cause) {
    super(structComponent, cause);
    this.structComponent = Objects.requireNonNull(structComponent);
  }

  public WkSzStructComponentException(
    WkSerdeDtreeStructField<?> structComponent,
    String message,
    Throwable cause) {
    super(structComponent, message, cause);
    this.structComponent = Objects.requireNonNull(structComponent);
  }

  public WkSzStructComponentException(
    WkSerdeDtreeStructField<?> structComponent,
    String message,
    Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
    super(structComponent, message, cause, enableSuppression, writableStackTrace);
    this.structComponent = Objects.requireNonNull(structComponent);
  }
  
  public WkSerdeDtreeStructField<?> getStructComponent() {
    return this.structComponent;
  }

}
