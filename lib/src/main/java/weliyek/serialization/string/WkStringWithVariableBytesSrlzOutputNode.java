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

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOptionalLengthOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkVariableSizeByteArraySrlzStructNode;
import weliyek.util.array.WkVariableSizeByteArraySrlzOutputNode;

public class WkStringWithVariableBytesSrlzOutputNode
    implements WkStringFromBytesSrlzOutputPacketEncoderFrameNode<
                        WkSzOptionalLengthOperationSettings,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingResultSrlzPacketOperationData,
                        WkStringWithVariableBytesSrlzStructNode,
                        WkVariableSizeByteArraySrlzStructNode,
                        WkVariableSizeByteArraySrlzOutputNode>
{

  final SimpleStringFromBytesWritingCore<
                        WkSzOptionalLengthOperationSettings,
                        WkStringWithVariableBytesSrlzOutputNode,
                        WkStringWithVariableBytesSrlzStructNode,
                        WkSettingsSrlzPacketOperationData,
                        WkVariableSizeByteArraySrlzOutputNode,
                        WkVariableSizeByteArraySrlzStructNode> operationCore;

  WkStringWithVariableBytesSrlzOutputNode(
    int index,
    String serializable,
    WkSzOptionalLengthOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<
      String,?,WkStringWithVariableBytesSrlzStructNode,?,?,?> serializingfieldCore,
    SimplifiedStringFromBytesCore<
      ?,?,?,WkSzOptionalLengthOperationSettings,WkStringWithVariableBytesSrlzOutputNode,
      WkStringWithVariableBytesSrlzStructNode,?,?,?,WkSettingsSrlzPacketOperationData,
      WkVariableSizeByteArraySrlzOutputNode,WkVariableSizeByteArraySrlzStructNode,?,
      ? extends WkStringWithVariableBytesSrlzStructNode> definitionCore) {
    this.operationCore = new SimpleStringFromBytesWritingCore<
                                WkSzOptionalLengthOperationSettings,
                                WkStringWithVariableBytesSrlzOutputNode,
                                WkStringWithVariableBytesSrlzStructNode,
                                WkSettingsSrlzPacketOperationData,
                                WkVariableSizeByteArraySrlzOutputNode,
                                WkVariableSizeByteArraySrlzStructNode>(
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
  WkSrlzOutputPacketSubfieldFrameNode<WkByteArray, WkVariableSizeByteArraySrlzStructNode, WkVariableSizeByteArraySrlzOutputNode>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public WkStringWithVariableBytesSrlzStructNode definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSzOptionalLengthOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkEncodingResultSrlzPacketOperationData> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzOutputPacketFieldFrameNode<String, WkStringWithVariableBytesSrlzStructNode, ?> packetField() {
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
  WkSrlzOutputPacketSubfieldFrameNode<WkByteArray, WkVariableSizeByteArraySrlzStructNode, WkVariableSizeByteArraySrlzOutputNode>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
