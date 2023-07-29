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

import weliyek.amat.base.DefinitionSegmentCore;

public class ProtocolDefinitionException extends ProtocolFieldException
{

  private static final long serialVersionUID = 2022_05_26_0000L;
  public final DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> definitionCore;

  public ProtocolDefinitionException(
    DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> definitionCore) {
    super(definitionCore.componentCore());
    this.definitionCore = definitionCore;
  }

  public ProtocolDefinitionException(
    DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> definitionCore,
    String message) {
    super(definitionCore.componentCore(), message);
    this.definitionCore = definitionCore;
  }

  public ProtocolDefinitionException(
    DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> definitionCore,
    Throwable cause) {
    super(definitionCore.componentCore(), cause);
    this.definitionCore = definitionCore;
  }

  public ProtocolDefinitionException(
    DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> definitionCore,
    String message,
    Throwable cause) {
    super(definitionCore.componentCore(), message, cause);
    this.definitionCore = definitionCore;
  }

  public ProtocolDefinitionException(
    DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> definitionCore,
    String message,
    Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
    super(definitionCore.componentCore(), message, cause, enableSuppression, writableStackTrace);
    this.definitionCore = definitionCore;
  }

}
