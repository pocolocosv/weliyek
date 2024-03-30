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

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSerdeDtreeNodeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestreamBase;

public class WkSerdeUnsignedBigEndianInteger
    implements WkSerdeDtreeNumberDefinition<Long>
{

  public static WkSrlzStruct<
                        Long,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeUnsignedBigEndianInteger,
                        WkSerdeUnsignedBigEndianIntegerReader,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeUnsignedBigEndianInteger,
                        WkSerdeUnsignedBigEndianIntegerWriter,
                        WkSzOutputBytestreamBase<?>,
                        WkSerdeUnsignedBigEndianInteger>
  newStruct(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkSerdeUnsignedBigEndianInteger::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSerdeDtreeNodeStructDefinitionCore<
                        Long,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkSerdeUnsignedBigEndianInteger,
                        WkSerdeUnsignedBigEndianIntegerReader,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkSerdeUnsignedBigEndianInteger,
                        WkSerdeUnsignedBigEndianIntegerWriter,
                        WkSzOutputBytestreamBase<?>,
                        WkSerdeUnsignedBigEndianInteger,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeUnsignedBigEndianInteger(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Long,
                        WkSerdeUnsignedBigEndianIntegerReader,
                        WkSerdeUnsignedBigEndianIntegerWriter,
                        WkSerdeUnsignedBigEndianInteger> definitionCore;

  private WkSerdeUnsignedBigEndianInteger(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                  Long,
                                  WkSerdeUnsignedBigEndianIntegerReader,
                                  WkSerdeUnsignedBigEndianIntegerWriter,
                                  WkSerdeUnsignedBigEndianInteger>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSerdeUnsignedBigEndianIntegerReader(i,xs,axb,xkc,dc).operationCore,
                                      WkSerdeDtreeLongUnsignedBigEndianReaderDecoderEngine.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSerdeUnsignedBigEndianIntegerWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkSerdeDtreeIntegerUnsignedBigEndianWriterEncoderEngine.FACTORY,
                                      this,
                                      Long.class);
  }

  @Override
  public Class<Long> serializableClass() {
    return Long.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

}
