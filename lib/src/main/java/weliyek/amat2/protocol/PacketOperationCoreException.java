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

import weliyek.amat.base.PacketOperationSegmentCore;

public class PacketOperationCoreException extends ProtocolDefinitionException
{

  private static final long serialVersionUID = 2022_07_04_0000L;
  public final PacketOperationSegmentCore<?,?,?,?,?,?,?,?,?,?,?> operationCore;

  public PacketOperationCoreException(
    PacketOperationSegmentCore<?,?,?,?,?,?,?,?,?,?,?> operationCore) {
    super(operationCore.definitionCore());
    this.operationCore = operationCore;
  }

  public PacketOperationCoreException(
    PacketOperationSegmentCore<?,?,?,?,?,?,?,?,?,?,?> operationCore,
    Throwable cause) {
    super(operationCore.definitionCore(), cause);
    this.operationCore = operationCore;
  }

  public PacketOperationCoreException(
    PacketOperationSegmentCore<?,?,?,?,?,?,?,?,?,?,?> operationCore,
    String message) {
    super(operationCore.definitionCore(), message);
    this.operationCore = operationCore;
  }

  public PacketOperationCoreException(
    PacketOperationSegmentCore<?,?,?,?,?,?,?,?,?,?,?> operationCore,
    String message,
    Throwable cause) {
    super(operationCore.definitionCore(), message, cause);
    this.operationCore = operationCore;
  }

  public PacketOperationCoreException(
    PacketOperationSegmentCore<?,?,?,?,?,?,?,?,?,?,?> operationCore,
    String message,
    Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
    super(operationCore.definitionCore(), message, cause, enableSuppression, writableStackTrace);
    this.operationCore = operationCore;
  }

}
