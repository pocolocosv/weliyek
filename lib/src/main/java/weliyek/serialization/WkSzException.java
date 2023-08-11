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

import weliyek.WkException;

/**
 * General exception for the serialization portion of the library.
 */
public class WkSzException extends WkException
{

  private static final long serialVersionUID = 2023_08_07_000L;

  public WkSzException() {
  }

  public WkSzException(String message) {
    super(message);
  }

  public WkSzException(Throwable cause) {
    super(cause);
  }

  public WkSzException(String message, Throwable cause) {
    super(message, cause);
  }

  public WkSzException(
    String message,
    Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
