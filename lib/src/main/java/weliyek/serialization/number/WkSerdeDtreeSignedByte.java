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

public class WkSerdeDtreeSignedByte
    implements WkSerdeDtreeNumberDefinition<Byte>
{

  public static WkSrlzStruct<
                      Byte,
                      WkSettingsSrlzPacketOperationData,
                      WkSerdeDtreeSignedByte,
                      WkSerdeDtreeSignedByteReader,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,
                      WkSerdeDtreeSignedByte,
                      WkSerdeDtreeSignedByteWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSerdeDtreeSignedByte>
  newStruct(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkSerdeDtreeSignedByte::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSerdeDtreeNodeStructDefinitionCore<
                      Byte,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkSerdeDtreeSignedByte,
                      WkSerdeDtreeSignedByteReader,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkSerdeDtreeSignedByte,
                      WkSerdeDtreeSignedByteWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSerdeDtreeSignedByte,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeDtreeSignedByte(componentCore).definitionCore;
  }

  private WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                        Byte,
                        WkSerdeDtreeSignedByteReader,
                        WkSerdeDtreeSignedByteWriter,
                        WkSerdeDtreeSignedByte> definitionCore;

  private WkSerdeDtreeSignedByte(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                                Byte,
                                WkSerdeDtreeSignedByteReader,
                                WkSerdeDtreeSignedByteWriter,
                                WkSerdeDtreeSignedByte>(
                                    componentCore,
                                    (i,xs,xab,xkc,xdc) -> new WkSerdeDtreeSignedByteReader(i,xs,xab,xkc,xdc).operationCore,
                                    WkSignedByteSrlzEngineDecoder.FACTORY,
                                    (i,y,ys,yab,ykc,ydc) -> new WkSerdeDtreeSignedByteWriter(i,y,ys,yab,ykc,ydc).writingCore,
                                    WkSignedByteSrlzEngineEncoder.FACTORY,
                                    this,
                                    Byte.class);
  }

  @Override
  public Class<Byte> serializableClass() {
    return Byte.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

}
