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

import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;

public final class WkSerdeSignedByteWriter
    implements WkSerdeDtreeNumberWriter<
                    Byte,
                    WkSettingsSrlzPacketOperationData,
                    WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                    WkResultSrlzPacketOperationData<Byte>,
                    WkSerdeSignedByte>
{

  static WkSerdeDtreeNumberWriterCoreSimplified<
                    Byte,
                    WkSerdeSignedByteWriter,
                    WkSerdeSignedByte> newCore(
    int index,
    Byte serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<Byte,?,WkSerdeSignedByte,?,?,?> writingfieldCore,
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
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<Byte,?,WkSerdeSignedByte,?,?,?> writingfieldCore,
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
  public WkSettingsSrlzPacketOperationData settings() {
    return this.writingCore.settings();
  }

  @Override
  public WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream> dashboard() {
    return this.writingCore.dashboard();
  }

  @Override
  public Optional<WkResultSrlzPacketOperationData<Byte>> result() {
    return this.writingCore.result();
  }

  @Override
  public int index() {
    return this.writingCore.index();
  }

  @Override
  public WkSrlzOutputPacketFieldFrameNode<Byte, WkSerdeSignedByte, ?> packetField() {
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
