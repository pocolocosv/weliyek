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
package weliyek.serialization.string;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkDynamicByteArraySrlzStructNode;
import weliyek.util.array.WkDynamicByteArraySrlzOutputNode;

public class WkStringWithDynamicBytesSrlzOutputNode<
                        ZT extends Number,
                        ZYD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,?>,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZT,WkSzOperationSettings,?,?,ZYD>>
    implements WkStringFromBytesSrlzOutputPacketEncoderFrameNode<
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        WkStringWithDynamicBytesSrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkDynamicByteArraySrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkDynamicByteArraySrlzOutputNode<ZT,ZYO,ZYD>>
{

  final SimpleStringFromBytesWritingCore<
              WkSzOperationSettings,
              WkStringWithDynamicBytesSrlzOutputNode<ZT,ZYD,ZYO>,
              WkStringWithDynamicBytesSrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,
              WkSzOperationSettings,
              WkDynamicByteArraySrlzOutputNode<ZT,ZYO,ZYD>,
              WkDynamicByteArraySrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>> operationCore;

  WkStringWithDynamicBytesSrlzOutputNode(
    int index,
    String serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<
      String,?,WkStringWithDynamicBytesSrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,?,?,?> serializingfieldCore,
    SimplifiedStringFromBytesCore<
      ?,?,?,WkSzOperationSettings,WkStringWithDynamicBytesSrlzOutputNode<ZT,ZYD,ZYO>,
      WkStringWithDynamicBytesSrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,?,?,?,
      WkSzOperationSettings,WkDynamicByteArraySrlzOutputNode<ZT,ZYO,ZYD>,
      WkDynamicByteArraySrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,?,?> definitionCore) {
    this.operationCore = new SimpleStringFromBytesWritingCore<
                                  WkSzOperationSettings,
                                  WkStringWithDynamicBytesSrlzOutputNode<ZT,ZYD,ZYO>,
                                  WkStringWithDynamicBytesSrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  WkSzOperationSettings,
                                  WkDynamicByteArraySrlzOutputNode<ZT,ZYO,ZYD>,
                                  WkDynamicByteArraySrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>>(
                                      index,
                                      serializable,
                                      settings,
                                      parentBytestream,
                                      serializingfieldCore,
                                      definitionCore,
                                      this);
  }

  @Override
  public
  WkSrlzOutputPacketSubfieldFrameNode<WkByteArray, WkDynamicByteArraySrlzStructNode<ZT,?,?,ZYD,ZYO, ? extends ZYD>, WkDynamicByteArraySrlzOutputNode<ZT, ZYO, ZYD>>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public WkStringWithDynamicBytesSrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD> definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSzOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzWritingRuntime<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzWritingResult> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public
  WkSrlzOutputPacketFieldFrameNode<String, WkStringWithDynamicBytesSrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,?>
  packetField() {
    return this.operationCore.packetField();
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
  public String serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public
  WkSrlzOutputPacketSubfieldFrameNode<WkByteArray, WkDynamicByteArraySrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>, WkDynamicByteArraySrlzOutputNode<ZT, ZYO, ZYD>>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
