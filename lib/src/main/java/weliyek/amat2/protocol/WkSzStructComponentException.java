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
package weliyek.amat2.protocol;

import java.util.Objects;
import java.util.Optional;

import weliyek.base.WkException;
import weliyek.serialization.WkSzSegmentException;
import weliyek.serialization.WkSzStructComponent;

public class WkSzStructComponentException extends WkSzSegmentException
{

  private static final long serialVersionUID = 2018_03_05_000L;

  private final WkSzStructComponent<?> structComponent;

  public WkSzStructComponentException(WkSzStructComponent<?> structComponent) {
    super(structComponent);
    this.structComponent = Objects.requireNonNull(structComponent);
  }

  public WkSzStructComponentException(WkSzStructComponent<?> structComponent, String message) {
    super(structComponent, message);
    this.structComponent = Objects.requireNonNull(structComponent);
  }

  public WkSzStructComponentException(WkSzStructComponent<?> structComponent, Throwable cause) {
    super(structComponent, cause);
    this.structComponent = Objects.requireNonNull(structComponent);
  }

  public WkSzStructComponentException(
    WkSzStructComponent<?> structComponent,
    String message,
    Throwable cause) {
    super(structComponent, message, cause);
    this.structComponent = Objects.requireNonNull(structComponent);
  }

  public WkSzStructComponentException(
    WkSzStructComponent<?> structComponent,
    String message,
    Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
    super(structComponent, message, cause, enableSuppression, writableStackTrace);
    this.structComponent = Objects.requireNonNull(structComponent);
  }
  
  public WkSzStructComponent<?> getStructComponent() {
    return this.structComponent;
  }

}
