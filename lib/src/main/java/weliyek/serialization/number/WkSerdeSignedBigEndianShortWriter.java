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

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;

public final class WkSerdeSignedBigEndianShortWriter
        implements WkSerdeDtreeNumberWriter<
                        Short,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<Short>,
                        WkSerdeSignedBigEndianShort>
{

  final WkSerdeDtreeNumberWriterCoreSimplified<
                      Short,
                      WkSerdeSignedBigEndianShortWriter,
                      WkSerdeSignedBigEndianShort> operationCore;

  WkSerdeSignedBigEndianShortWriter(
    int index,
    Short serializable,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<
      Short,?,WkSerdeSignedBigEndianShort,?,?,?> serializingfieldCore,
    WkSerdeDtreeNumberDefinitionCoreSimplified<
      Short,?,WkSerdeSignedBigEndianShortWriter,WkSerdeSignedBigEndianShort> definitionCore) {
    operationCore = new WkSerdeDtreeNumberWriterCoreSimplified<
                            Short,
                            WkSerdeSignedBigEndianShortWriter,
                            WkSerdeSignedBigEndianShort>(
                                index,
                                serializable,
                                settings,
                                parentBytestream,
                                serializingfieldCore,
                                definitionCore,
                                this);
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<Short>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public Short serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public WkSerdeDtreeNodeDataOutputComponent<Short, WkSerdeSignedBigEndianShort, ?> packetField() {
    return this.operationCore.packet();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields() {
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
  public WkSerdeSignedBigEndianShort definition() {
    return this.operationCore.definition();
  }

}
