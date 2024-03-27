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
package weliyek.util.array;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.sequence.WkPrimitiveArraySrlzUtils;

public final class WkSerdeDTreeVariableSizeByteArrayReader
    implements WkSerdeDTreeByteArrayReader<
                        WkSzVariableLengthOperationSettings,
                        WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkResultSrlzPacketOperationData<WkByteArray>,
                        WkSerdeDTreeVariableSizeByteArray>,
               WkSerdeDTreeGenericVariableSizePrimitiveArrayReader<
                        WkByteArray,
                        WkSzVariableLengthOperationSettings,
                        WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkResultSrlzPacketOperationData<WkByteArray>,
                        WkSerdeDTreeVariableSizeByteArray>
{

  final WkSerdeDTreeGenericPrimitiveArrayReaderCoreSimplified<
                    WkByteArray,
                    WkSzVariableLengthOperationSettings,
                    WkSerdeDTreeVariableSizeByteArray,
                    WkSerdeDTreeVariableSizeByteArrayReader> operationCore;

  WkSerdeDTreeVariableSizeByteArrayReader(
    int index,
    WkSzVariableLengthOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<
      WkByteArray,?,WkSerdeDTreeVariableSizeByteArray,?,?,?> deserializingfieldCore,
    WkSerdeDTreeGenericPrimitiveArrayDefinitionCoreSimplified<
      WkByteArray,WkSzVariableLengthOperationSettings,WkSerdeDTreeVariableSizeByteArrayReader,?,?,WkSerdeDTreeVariableSizeByteArray> definitionCore) {
    this.operationCore = new WkSerdeDTreeGenericPrimitiveArrayReaderCoreSimplified<
        WkByteArray,
        WkSzVariableLengthOperationSettings,
        WkSerdeDTreeVariableSizeByteArray,
        WkSerdeDTreeVariableSizeByteArrayReader>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this,
                                    WkPrimitiveArraySrlzUtils::onVariableSizeDeserilizingInitialization);
  }

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public WkSerdeDTreeVariableSizeByteArray definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSzVariableLengthOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkResultSrlzPacketOperationData<WkByteArray>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzInputPacketFieldFrameNode<WkByteArray, WkSerdeDTreeVariableSizeByteArray, ?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public int getRequestedLength() {
    return this.operationCore.getRequestedLength();
  }

}
