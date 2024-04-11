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

public final class WkSerdeSignedByteWriter
    implements WkSerdeDtreeNumberWriter<
                    Byte,
                    WkSerdeDtreeOperationSettings,
                    WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                    WkSerdeDtreeOperationResult<Byte>,
                    WkSerdeSignedByte>
{

  static WkSerdeDtreeNumberWriterCoreSimplified<
                    Byte,
                    WkSerdeSignedByteWriter,
                    WkSerdeSignedByte> newCore(
    int index,
    Byte serializable,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<Byte,?,WkSerdeSignedByte,?,?,?> writingfieldCore,
    WkSerdeDtreeNumberDefinitionCoreSimplified<
      Byte,?,WkSerdeSignedByteWriter,WkSerdeSignedByte> defintionCore) {
    return new WkSerdeSignedByteWriter(
                    index,
                    serializable,
                    settings,
                    parentBytestream,
                    writingfieldCore,
                    defintionCore).writingCore;
  }

  final WkSerdeDtreeNumberWriterCoreSimplified<
                    Byte,
                    WkSerdeSignedByteWriter,
                    WkSerdeSignedByte> writingCore;

  WkSerdeSignedByteWriter(
    int index,
    Byte serializable,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<Byte,?,WkSerdeSignedByte,?,?,?> writingfieldCore,
    WkSerdeDtreeNumberDefinitionCoreSimplified<
      Byte,?,WkSerdeSignedByteWriter,WkSerdeSignedByte> defintionCore) {
    this.writingCore = new WkSerdeDtreeNumberWriterCoreSimplified<
                              Byte, WkSerdeSignedByteWriter, WkSerdeSignedByte>(
                                    index,
                                    serializable,
                                    settings,
                                    parentBytestream,
                                    writingfieldCore,
                                    defintionCore,
                                    this);
  }

  @Override
  public WkSerdeSignedByte definition() {
    return this.writingCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.writingCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
    return this.writingCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<Byte>> result() {
    return this.writingCore.result();
  }

  @Override
  public int index() {
    return this.writingCore.index();
  }

  @Override
  public WkSerdeDtreeNodeDataOutputComponent<Byte, WkSerdeSignedByte, ?> packetField() {
    return this.writingCore.packetField();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.writingCore.subfields();
  }

  @Override
  public String name() {
    return this.writingCore.name();
  }

  @Override
  public Byte serializable() {
    return this.writingCore.serializable();
  }

  @Override
  public String toString() {
    return this.writingCore.toString();
  }

}
