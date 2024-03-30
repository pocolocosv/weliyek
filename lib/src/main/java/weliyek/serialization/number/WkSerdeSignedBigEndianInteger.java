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

public class WkSerdeSignedBigEndianInteger
    implements WkSerdeDtreeNumberDefinition<Integer>
{

  public static WkSrlzStruct<
                        Integer,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeSignedBigEndianInteger,
                        WkSerdeSignedBigEndianIntegerReader,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeSignedBigEndianInteger,
                        WkSerdeSignedBigEndianIntegerWriter,
                        WkSzOutputBytestreamBase<?>,
                        WkSerdeSignedBigEndianInteger>
  newStruct(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkSerdeSignedBigEndianInteger::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSerdeDtreeNodeStructDefinitionCore<
                        Integer,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkSerdeSignedBigEndianInteger,
                        WkSerdeSignedBigEndianIntegerReader,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkSerdeSignedBigEndianInteger,
                        WkSerdeSignedBigEndianIntegerWriter,
                        WkSzOutputBytestreamBase<?>,
                        WkSerdeSignedBigEndianInteger,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeSignedBigEndianInteger(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Integer,
                        WkSerdeSignedBigEndianIntegerReader,
                        WkSerdeSignedBigEndianIntegerWriter,
                        WkSerdeSignedBigEndianInteger> definitionCore;

  private WkSerdeSignedBigEndianInteger(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                  Integer,
                                  WkSerdeSignedBigEndianIntegerReader,
                                  WkSerdeSignedBigEndianIntegerWriter,
                                  WkSerdeSignedBigEndianInteger>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSerdeSignedBigEndianIntegerReader(i,xs,axb,xkc,dc).operationCore,
                                      WkSerdeDtreeIntegerSignedBigEndianReaderDecoderEngine.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSerdeSignedBigEndianIntegerWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkSerdeDtreeIntSignedBigEndianWriterEncoderEngine.FACTORY,
                                      this,
                                      Integer.class);
  }

  @Override
  public Class<Integer> serializableClass() {
    return Integer.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
