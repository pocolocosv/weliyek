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
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSerdeDTreeNodeStructComponentHandler;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestreamBase;

public class WkUnsignedLittleEndianShortSrlzStructNode
    implements WkSerdeDTreeNumberDefinition<Integer>
{

  public static WkSrlzStruct<
                      Integer,
                      WkSettingsSrlzPacketOperationData,
                      WkUnsignedLittleEndianShortSrlzStructNode,
                      WkUnsignedLittleEndianShortSrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,
                      WkUnsignedLittleEndianShortSrlzStructNode,
                      WkUnsignedLittleEndianShortSrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkUnsignedLittleEndianShortSrlzStructNode>
  newStruct(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkUnsignedLittleEndianShortSrlzStructNode::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                        Integer,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkUnsignedLittleEndianShortSrlzStructNode,
                        WkUnsignedLittleEndianShortSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkUnsignedLittleEndianShortSrlzStructNode,
                        WkUnsignedLittleEndianShortSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkUnsignedLittleEndianShortSrlzStructNode,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkUnsignedLittleEndianShortSrlzStructNode(componentCore).definitionCore;
  }

  private final WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                        Integer,
                        WkUnsignedLittleEndianShortSrlzInputNode,
                        WkUnsignedLittleEndianShortSrlzOutputNode,
                        WkUnsignedLittleEndianShortSrlzStructNode> definitionCore;

  private WkUnsignedLittleEndianShortSrlzStructNode(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                                  Integer,
                                  WkUnsignedLittleEndianShortSrlzInputNode,
                                  WkUnsignedLittleEndianShortSrlzOutputNode,
                                  WkUnsignedLittleEndianShortSrlzStructNode>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkUnsignedLittleEndianShortSrlzInputNode(i,xs,axb,xkc,dc).operationCore,
                                      WkUnsignedLittleEndianShortSrlzEngineDecoder.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkUnsignedLittleEndianShortSrlzOutputNode(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkUnsignedLittleEndianShortSrlzEngineEncoder.FACTORY,
                                      this,
                                      Integer.class);
  }

  @Override
  public Class<Integer> serializableClass() {
    return Integer.class;
  }

  @Override
  public List<WkSerdeDTreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

}
