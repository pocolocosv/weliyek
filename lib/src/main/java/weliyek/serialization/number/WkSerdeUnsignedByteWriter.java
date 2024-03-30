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

public final class WkSerdeUnsignedByteWriter
    implements WkSerdeDtreeNumberWriter<
                        Integer,
                        WkSettingsSrlzPacketOperationData,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkResultSrlzPacketOperationData<Integer>,
                        WkSerdeUnsignedByte>
{

  final WkSerdeDtreeNumberWriterCoreSimplified<
                        Integer,
                        WkSerdeUnsignedByteWriter,
                        WkSerdeUnsignedByte> writingCore;

  WkSerdeUnsignedByteWriter(
    int index,
    Integer serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<Integer,?,WkSerdeUnsignedByte,?,?,?> writingfieldCore,
    WkSerdeDtreeNumberDefinitionCoreSimplified<
      Integer,?,WkSerdeUnsignedByteWriter,WkSerdeUnsignedByte> defintionCore) {
    this.writingCore = new WkSerdeDtreeNumberWriterCoreSimplified<>(
                                index,
                                serializable,
                                settings,
                                parentBytestream,
                                writingfieldCore,
                                defintionCore,
                                this);
  }

  @Override
  public WkSerdeUnsignedByte definition() {
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
  public Optional<WkResultSrlzPacketOperationData<Integer>> result() {
    return this.writingCore.result();
  }

  @Override
  public int index() {
    return this.writingCore.index();
  }

  @Override
  public WkSrlzOutputPacketFieldFrameNode<Integer, WkSerdeUnsignedByte, ?> packetField() {
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
  public Integer serializable() {
    return this.writingCore.serializable();
  }

  @Override
  public String toString() {
    return this.writingCore.toString();
  }

}
