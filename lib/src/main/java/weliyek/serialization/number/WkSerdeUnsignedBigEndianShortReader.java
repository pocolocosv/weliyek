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
package weliyek.serialization.number;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;

public final class WkSerdeUnsignedBigEndianShortReader
        implements WkSerdeDtreeNumberReader<
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<Integer>,
                        WkSerdeUnsignedBigEndianShort>
{

  final WkSerdeDtreeNumberReaderCoreSimplified<
                      Integer,
                      WkSerdeUnsignedBigEndianShortReader,
                      WkSerdeUnsignedBigEndianShort> operationCore;

  WkSerdeUnsignedBigEndianShortReader(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataInputComponentCore<
      Integer,?,WkSerdeUnsignedBigEndianShort,?,?,?> packetfieldCore,
    WkSerdeDtreeNumberDefinitionCoreSimplified<
      Integer,WkSerdeUnsignedBigEndianShortReader,?,WkSerdeUnsignedBigEndianShort> definitionCore) {
    operationCore = new WkSerdeDtreeNumberReaderCoreSimplified<>(
                            index, settings, parentBytestream, packetfieldCore, definitionCore, this);
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<Integer>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSerdeDtreeNodeDataInputComponent<Integer, WkSerdeUnsignedBigEndianShort, ?> packetField() {
    return this.operationCore.packet();
  }

  @Override
  public final List<WkSrlzInputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public String toString() {
    return this.operationCore.toString();
  }

  @Override
  public WkSerdeUnsignedBigEndianShort definition() {
    return this.operationCore.definition();
  }

}
